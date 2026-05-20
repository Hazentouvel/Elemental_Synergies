package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis;

import com.github.L_Ender.cataclysm.client.particle.Options.CircleLightningParticleOptions;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.hazen.elemental_synergies.Entities.Spells.Misc.ElementalSpearMagicProjectile;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class LightningSpearMagicProjectile extends ElementalSpearMagicProjectile {
    int bounces;
    HashMap<UUID, Integer> victims;
    private int phase = 0;


    private Vec3 spawnPos = null;
    private Vec3 targetPos = null;

    // timing fields
    public int delay = 10;
    public int age = 0;
    private int phaseTick = 0;
    @javax.annotation.Nullable
    public Vec3 launchDir = null;
    private boolean pendingLightning = false;
    private Entity target = null;
    private int lightningDelay = 0;
    private int lightningDamage = 0;

    public LightningSpearMagicProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.victims = new HashMap<>();
        this.setNoGravity(true);
        this.setPierceLevel(-1);
    }

    public LightningSpearMagicProjectile(Level level, LivingEntity shooter) {
        this(ESEntityRegistry.LIGHTNING_SPEAR.get(), level);
        this.setOwner(shooter);
    }

    public void setLaunchDir(Vec3 dir) {
        this.launchDir = dir;
        this.setRotationFromVector(dir);
    }

    public void setDelay(int d) {
        this.delay = d;
    }

    protected void SpawnParticle() {
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        int r = 89 + this.random.nextInt(5);
        int g = 180 + this.random.nextInt(5);
        int b = 180 + this.random.nextInt(5);
        this.level().addParticle(new CircleLightningParticleOptions(0.1F, r, g, b), this.getX(), this.getY((double)0.5F), this.getZ(), d0, d1, d2);
    }

    public float getSpeed() {
        return 1.4F;
    }

    protected boolean canHitEntity(Entity pTarget) {
        return super.canHitEntity(pTarget) && this.canHitVictim(pTarget);
    }

    @Override
    public void tick() {
        ++this.age;
        ++this.phaseTick;

        // delayed lightning hit
        if (!this.level().isClientSide && this.pendingLightning && this.target != null) {

            if (this.lightningDelay > 0) {
                this.lightningDelay--;
                return;
            }

            Entity target = this.target;

            if (target.isAlive()) {

                DamageSource lightningSource = new DamageSource(DamageSources.getHolderFromResource(this, ISSDamageTypes.LIGHTNING_MAGIC), this, this.getOwner());

                DamageSources.applyDamage(target, this.damage, lightningSource);
            }

            this.pendingLightning = false;
            this.target = null;

            if (this.phase == 3) this.discard();
            return;
        }
        // INITIALIZE OUTWARD MOTION
        if (this.phase == 0) {

            if (this.launchDir == null && this.getOwner() != null) {
                this.launchDir = this.getOwner().getLookAngle();
            }

            if (this.spawnPos == null && this.launchDir != null) {

                this.spawnPos = this.position();

                this.targetPos = this.spawnPos.add(
                        this.launchDir.normalize().scale(4.0D)
                );

                // slow outward speed
                double outwardSpeed = 0.22D;

                this.setDeltaMovement(
                        this.launchDir.normalize().scale(outwardSpeed)
                );

                ProjectileUtil.rotateTowardsMovement(this, 1.0F);

                this.yRotO = this.getYRot();
                this.xRotO = this.getXRot();
            }
        }

        // normal projectile movement
        super.tick();

        // PHASE 0 = moving outward slowly
        if (this.phase == 0) {

            if (this.targetPos != null) {

                Vec3 toTarget = this.targetPos.subtract(this.position());

                double remaining = toTarget.length();

                // reached hover point
                if (remaining <= 0.25D) {

                    this.setPos(
                            this.targetPos.x,
                            this.targetPos.y,
                            this.targetPos.z
                    );

                    // COMPLETE STOP
                    this.setDeltaMovement(Vec3.ZERO);

                    this.phase = 2;
                    this.phaseTick = 0;
                }
            }

            return;
        }

        // PHASE 2 = hovering
        if (this.phase == 2) {

            // slow hover drift instead of freezing
            if (this.launchDir != null) {

                Vec3 hoverMotion =
                        this.launchDir.normalize().scale(0.03D);

                this.setDeltaMovement(hoverMotion);

                // IMPORTANT:
                // do NOT rotate during hover
                // preserves outward-facing side spear angles
            }

            if (this.phaseTick >= this.delay) {

                this.phase = 3;
                this.phaseTick = 0;

                if (this.launchDir == null && this.getOwner() != null) {
                    this.launchDir = this.getOwner().getLookAngle();
                }

                if (this.launchDir != null && !this.level.isClientSide) {

                    // full-speed firing phase
                    Vec3 fire =
                            this.launchDir.normalize().scale(this.getSpeed());

                    this.setDeltaMovement(fire);

                    // NOW rotate toward actual fired motion
                    ProjectileUtil.rotateTowardsMovement(this, 1.0F);

                    this.yRotO = this.getYRot();
                    this.xRotO = this.getXRot();

                    this.playSound(ESSounds.LIGHTNING_SPEAR_FIRE.get(), 1.5F, 0.85F + (float)(this.random.nextDouble() * 0.2)
                    );
                }
            }

            return;
        }
    }

    public void handleHitDetection() {
        Vec3 vec3 = this.getDeltaMovement();
        Vec3 pos = this.position();
        Vec3 vec32 = pos.add(vec3);
        HitResult hitresult = this.level.clip(new ClipContext(pos, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            this.onHit(hitresult);
        } else {
            for(Entity entity : this.level.getEntities(this, this.getBoundingBox().inflate(0.25D), this::canHitEntity)) {
                this.onHit(new EntityHitResult(entity, this.getBoundingBox().getCenter().add(entity.getBoundingBox().getCenter()).scale(0.5D)));
            }
        }

    }

    public boolean canHitVictim(Entity entity) {
        Integer timestamp = (Integer)this.victims.get(entity.getUUID());
        return timestamp == null || entity.tickCount - timestamp >= 10;
    }

    protected void onHitEntity(EntityHitResult pResult) {
        Entity target = pResult.getEntity();

        if (target instanceof LivingEntity livingTarget) {
            DamageSources.ignoreNextKnockback(livingTarget);
        }

        float totalDamage = this.damage;

        this.victims.put(target.getUUID(), target.tickCount);

        DamageSources.applyDamage(target, totalDamage, (ESSpellRegistries.SPEARS_OF_ACROPOLIS.get()).getDamageSource(this, this.getOwner()));
        this.target = target;
        this.lightningDelay = 1;
        this.pendingLightning = true;
    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        switch (pResult.getDirection()) {
            case UP:
            case DOWN:
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, this.isNoGravity() ? -1.0D : -0.8D, 1.0D));
                break;
            case EAST:
            case WEST:
                this.setDeltaMovement(this.getDeltaMovement().multiply(-1.0D, 1.0D, 1.0D));
                break;
            case NORTH:
            case SOUTH:
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 1.0D, -1.0D));
        }

        if (++this.bounces >= 6) {
            this.discard();
        }

    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(HnSSounds.ELECTRIC_IMPACT);
    }
}

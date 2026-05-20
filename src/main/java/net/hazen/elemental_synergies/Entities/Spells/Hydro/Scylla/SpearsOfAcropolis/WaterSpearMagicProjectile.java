package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis;

import com.github.L_Ender.cataclysm.client.particle.Options.StormParticleOptions;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.hazen.elemental_synergies.Entities.Spells.Misc.ElementalSpearMagicProjectile;
import net.hazen.elemental_synergies.A.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
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

public class WaterSpearMagicProjectile extends ElementalSpearMagicProjectile {
    int bounces;
    HashMap<UUID, Integer> victims;
    // phases: 0 = move outward to target (2 blocks), 1 = arrived & stationary for delay, 2 = firing
    private int phase = 0;

    // travel timing
    private final double outwardDistance = 2.0D; // 2 blocks
    private Vec3 spawnPos = null;
    private Vec3 targetPos = null;
    private final double arriveThreshold = 0.05D;
    private final double maxOutwardSpeedMultiplier = 2.0D; // cap speed when moving outward
    private double movedDistance = 0.0D;

    // timing fields
    public int delay = 10; // ticks to wait (after arriving) before firing final shot
    public int age = 0; // total ticks since spawn
    private int phaseTick = 0; // ticks within current phase
    @javax.annotation.Nullable
    public Vec3 launchDir = null;
    private double initialOutwardSpeed = 0.0D;

    public WaterSpearMagicProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.victims = new HashMap<>();
        this.setNoGravity(true);
        this.setPierceLevel(-1);
    }

    public WaterSpearMagicProjectile(Level level, LivingEntity shooter) {
        this(ESEntityRegistry.WATER_SPEAR.get(), level);
        this.setOwner(shooter);
    }

    public void setLaunchDir(Vec3 dir) {
        this.launchDir = dir;
        // immediately set rotation so it faces correctly
        this.setRotationFromVector(dir);
    }

    public void setDelay(int d) {
        this.delay = d;
    }

    public float getSpeed() {
        return 1.4F;
    }

    protected void SpawnParticle() {
        double dx = this.getX() + (double)(1.5F * (this.random.nextFloat() - 0.5F));
        double dy = this.getY() + (double)(1.5F * (this.random.nextFloat() - 0.5F));
        double dz = this.getZ() + (double)(1.5F * (this.random.nextFloat() - 0.5F));
        float r = (float)(89 + this.random.nextInt(35)) / 255.0F;
        float g = (float)(180 + this.random.nextInt(35)) / 255.0F;
        float b = (float)(180 + this.random.nextInt(35)) / 255.0F;
        this.level().addParticle(new StormParticleOptions(r, g, b, 0.1F, this.getBbHeight() / 2.0F, this.getId()), dx, dy, dz, (double)0.0F, (double)0.0F, (double)0.0F);
    }

    protected boolean canHitEntity(Entity pTarget) {
        return super.canHitEntity(pTarget) && this.canHitVictim(pTarget);
    }

    @Override
    public void tick() {
        ++this.age;
        ++this.phaseTick;
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
        super.onHitEntity(pResult);
        Entity target = pResult.getEntity();
        if (target instanceof LivingEntity livingEntity) {
            DamageSources.ignoreNextKnockback(livingEntity);
        }

        DamageSources.applyDamage(target, this.getDamage(), ((AbstractSpell) ESSpellRegistries.SPEARS_OF_ACROPOLIS.get()).getDamageSource(this, this.getOwner()));
        this.victims.put(target.getUUID(), target.tickCount);
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
        return Optional.of(HnSSounds.HYDRO_CAST_1);
    }
}

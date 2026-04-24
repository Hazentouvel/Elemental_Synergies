package net.hazen.elemental_synergies.Entities.Spells.Fire.HolyBlast;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.network.particles.FieryExplosionParticlesPacket;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.hazen.elemental_synergies.Entities.Spells.Fire.HolyBlast.HolyFire.HolyFlame;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Optional;

public class HolyBlast extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // track spell level so impact can scale spawned projectiles
    private int spellLevel = 0;

    public void setSpellLevel(int level) {
        this.spellLevel = Math.max(0, level);
    }

    public int getSpellLevel() {
        return this.spellLevel;
    }

    public HolyBlast(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public HolyBlast(Level pLevel, LivingEntity pShooter) {
        this(ESEntityRegistry.HOLY_BLAST.get(), pLevel);
        this.setOwner(pShooter);
    }

    public void trailParticles() {
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        int count = Mth.clamp((int)(vec3.lengthSqr() * (double)2.0F), 1, 4);

        for(int i = 0; i < count; ++i) {
            Vec3 random = Utils.getRandomVec3((double)(this.getBbHeight() * 0.2F));
            float f = (float)i / (float)count;
            double x = Mth.lerp((double)f, d0, this.getX() + vec3.x);
            double y = Mth.lerp((double)f, d1, this.getY() + vec3.y);
            double z = Mth.lerp((double)f, d2, this.getZ() + vec3.z);
            this.level.addParticle(ParticleHelper.FIERY_SMOKE, true, x - random.x, y + (double)(this.getBbHeight() * 0.5F) - random.y, z - random.z, (double)0.0F, (double)0.0F, (double)0.0F);
        }

    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(this.level, ParticleHelper.FIERY_SMOKE, x, y, z, 5, .1, .1, .1, .25, true);
    }

    @Override
    public float getSpeed() {
        return 1.4f;
    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(HnSSounds.BRIMSTONE_HELLBLAST_IMPACT);
    }

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 1.5f, 1.0f);
    }

    protected void onHit(HitResult hitResult) {
        if (!this.level.isClientSide) {
            this.impactParticles(this.xOld, this.yOld, this.zOld);
            float explosionRadius = this.getExplosionRadius();
            float explosionRadiusSqr = explosionRadius * explosionRadius;
            List<Entity> entities = this.level.getEntities(this, this.getBoundingBox().inflate((double)explosionRadius));
            Vec3 losPoint = Utils.raycastForBlock(this.level, this.position(), this.position().add((double)0.0F, (double)2.0F, (double)0.0F), Fluid.NONE).getLocation();

            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(hitResult.getLocation());

                if (distanceSqr < (double) explosionRadiusSqr
                        && this.canHitEntity(entity)
                        && Utils.hasLineOfSight(this.level, losPoint, entity.getBoundingBox().getCenter(), true)) {

                    double p = 0.5F - distanceSqr / (double) explosionRadiusSqr;
                    float totalDamage = (float) ((double) this.damage * p);

                    float firstHalf = totalDamage / 2.0F;
                    float secondHalf = totalDamage - firstHalf;

                    DamageSources.applyDamage(entity, firstHalf, ((AbstractSpell) ESSpellRegistries.HOLY_BLAST.get()).getDamageSource(this, this.getOwner()));
                    DamageSources.applyDamage(entity, secondHalf, (DamageSource) DamageSources.getHolderFromResource(entity, ISSDamageTypes.HOLY_MAGIC));
                }
            }

            // Spawn HolyFlame projectiles outward from the impact point
            try {
                // base 2-4 flames
                int base = this.level.random.nextInt(3) + 2; // 2..4
                int count = Math.min(10, base + this.spellLevel); // +1 per spell level, cap 10

                Entity ownerEntity = this.getOwner();
                LivingEntity owner = ownerEntity instanceof LivingEntity ? (LivingEntity) ownerEntity : null;

                Vec3 impactPos = hitResult.getLocation();

                for (int i = 0; i < count; i++) {
                    HolyFlame flame = new HolyFlame(this.level, owner);
                    flame.setPos(impactPos.x, impactPos.y + 0.1 + (this.level.random.nextDouble() - 0.5) * 0.2, impactPos.z);

                    Vec3 dir = Utils.getRandomVec3(1.0).normalize();

                    Vec3 bias = this.getDeltaMovement();
                    if (bias.lengthSqr() > 0.0001) {
                        dir = dir.scale(0.5).add(bias.normalize().scale(0.5)).normalize();
                    }

                    try {
                        flame.shoot(dir);
                    } catch (Throwable t) {
                        flame.setDeltaMovement(dir.scale(flame.getSpeed()));
                    }

                    float flameDamage = Math.max(0.5f, this.damage / Math.max(1, count));
                    flame.setDamage(flameDamage);

                    this.level.addFreshEntity(flame);
                }
            } catch (Exception ex) {
                // ensure any problem here doesn't break the rest of the impact handling
                ex.printStackTrace();
            }

            if ((Boolean)ServerConfigs.SPELL_GREIFING.get()) {
                Explosion explosion = new Explosion(this.level, (Entity)null, ((AbstractSpell) ESSpellRegistries.HOLY_BLAST.get()).getDamageSource(this, this.getOwner()), (ExplosionDamageCalculator)null, this.getX(), this.getY(), this.getZ(), this.getExplosionRadius() / 2.0F, true,
                        Explosion.BlockInteraction.DESTROY,
                        ParticleTypes.EXPLOSION,
                        ParticleTypes.EXPLOSION_EMITTER,
                        HnSSounds.BRIMSTONE_HELLBLAST_IMPACT);
                if (!((ExplosionEvent.Start)NeoForge.EVENT_BUS.post(new ExplosionEvent.Start(this.level, explosion))).isCanceled()) {
                    explosion.explode();
                    explosion.finalizeExplosion(false);
                }
            }

            PacketDistributor.sendToPlayersTrackingEntity(this, new FieryExplosionParticlesPacket(hitResult.getLocation().subtract(this.getDeltaMovement().scale((double)0F)), this.getExplosionRadius()), new CustomPacketPayload[0]);
            this.playSound((SoundEvent)HnSSounds.BRIMSTONE_HELLBLAST_IMPACT.value(), 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);
            this.discard();
        }

    }

    //ANIMATION
    private final RawAnimation idle = RawAnimation.begin().thenLoop("animation.brimstone_hellblast.idle");

    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(idle);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}


package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave;

import com.github.L_Ender.cataclysm.client.particle.Options.CustomPoofParticleOptions;
import com.github.L_Ender.cataclysm.init.ModEffect;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class WaveMagicProjectileEntity extends AbstractMagicProjectile {
    private static final EntityDataAccessor<Integer> LIFESPAN;
    private static final EntityDataAccessor<Integer> MAX_TICKS;
    private static final EntityDataAccessor<Float> Y_ROT;
    private static final EntityDataAccessor<Integer> STATE;
    private static final EntityDataAccessor<Float> DAMAGE;
    public AnimationState SpawnAnimationState;
    public AnimationState DespawnAnimationState;
    public AnimationState idleAnimationState;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private boolean sentEvent;
    private boolean clientSideAttackEnded;
    private int lSteps;
    private double lx;
    private double ly;
    private double lz;
    private double lyr;
    private double lxr;
    private double lxd;
    private double lyd;
    private double lzd;

    public WaveMagicProjectileEntity(EntityType entityType, Level level) {
        super(entityType, level);
        this.SpawnAnimationState = new AnimationState();
        this.DespawnAnimationState = new AnimationState();
        this.idleAnimationState = new AnimationState();
    }

    public WaveMagicProjectileEntity(Level level, LivingEntity shooter, int life, float damage) {
        this((EntityType) ESEntityRegistry.WAVE.get(), level);
        this.setOwner(shooter);
        this.setMaxTicks(life);
        this.setDamage(damage);
        this.setLifespan(0);
    }

    public float maxUpStep() {
        return 2.0F;
    }

    public void setOwner(@Nullable LivingEntity living) {
        this.owner = living;
        this.ownerUUID = living == null ? null : living.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(LIFESPAN, 0);
        builder.define(MAX_TICKS, 0);
        builder.define(Y_ROT, 0.0F);
        builder.define(STATE, 0);
        builder.define(DAMAGE, 0.0F);
    }

    public AnimationState getAnimationState(String input) {
        if (input == "spawn") {
            return this.SpawnAnimationState;
        } else if (input == "idle") {
            return this.idleAnimationState;
        } else {
            return input == "despawn" ? this.DespawnAnimationState : new AnimationState();
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (STATE.equals(entityDataAccessor)) {
            switch (this.getState()) {
                case 0:
                    this.stopAllAnimationStates();
                    break;
                case 1:
                    this.stopAllAnimationStates();
                    this.SpawnAnimationState.startIfStopped(this.tickCount);
                    break;
                case 2:
                    this.stopAllAnimationStates();
                    this.idleAnimationState.startIfStopped(this.tickCount);
                    break;
                case 3:
                    this.stopAllAnimationStates();
                    this.DespawnAnimationState.startIfStopped(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(entityDataAccessor);
    }

    public void stopAllAnimationStates() {
        this.DespawnAnimationState.stop();
        this.idleAnimationState.stop();
        this.SpawnAnimationState.stop();
    }

    public int getState() {
        return (Integer)this.entityData.get(STATE);
    }

    public void setState(int state) {
        this.entityData.set(STATE, state);
    }

    public float getDamage() {
        return (Float)this.entityData.get(DAMAGE);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }

        if (tag.contains("Lifespan")) {
            this.setLifespan(tag.getInt("Lifespan"));
        }

        if (tag.contains("Maxticks")) {
            this.setMaxTicks(tag.getInt("Maxticks"));
        }

    }

    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        if (this.ownerUUID != null) {
            compoundTag.putUUID("Owner", this.ownerUUID);
        }

        compoundTag.putInt("Lifespan", this.getLifespan());
        compoundTag.putInt("Maxticks", this.getMaxTicks());
    }

    public float getYRot() {
        return (Float)this.entityData.get(Y_ROT);
    }

    public void setYRot(float f) {
        this.entityData.set(Y_ROT, f);
    }

    public int getLifespan() {
        return (Integer)this.entityData.get(LIFESPAN);
    }

    public void setLifespan(int time) {
        this.entityData.set(LIFESPAN, time);
    }

    public int getMaxTicks() {
        return (Integer)this.entityData.get(MAX_TICKS);
    }

    public void setMaxTicks(int time) {
        this.entityData.set(MAX_TICKS, time);
    }

    private void spawnParticleAt(float yOffset, float zOffset, float xOffset, ParticleOptions particleType) {
        Vec3 vec3 = (new Vec3((double)xOffset, (double)yOffset, (double)zOffset)).yRot((float)Math.toRadians((double)(-this.getYRot())));
        this.level().addParticle(particleType, this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z, this.getDeltaMovement().x, (double)0.1F, this.getDeltaMovement().z);
    }

    @Override
    public void trailParticles() {

    }

    @Override
    public void impactParticles(double v, double v1, double v2) {

    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    public void tick() {
        super.tick();
        if (!this.isNoGravity() && !this.isInWaterOrBubble()) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)0.0F, (double)-0.04F, (double)0.0F));
        }

        if (this.getState() == 1 && this.getLifespan() >= 5) {
            this.setState(2);
        }

        if (this.getState() == 2) {
            if (this.getLifespan() >= this.getMaxTicks() - 30 && !this.sentEvent) {
                this.level().broadcastEntityEvent(this, (byte)4);
                this.sentEvent = true;
            }

            if (this.getLifespan() >= this.getMaxTicks() - 10) {
                this.setState(3);
            }
        }

        this.setLifespan(this.getLifespan() + 1);
        if (this.getLifespan() >= this.getMaxTicks()) {
            this.remove(RemovalReason.DISCARDED);
        }

        float f = Math.max(1.0F - (float)this.getLifespan() / (1.0F * (float)this.getMaxTicks()), 0.0F);
        Vec3 directionVec = (new Vec3((double)0.0F, (double)0.0F, (double)(f * f * 0.1F))).yRot((float)Math.toRadians((double)(-this.getYRot())));
        if (this.level().isClientSide) {
            if (this.lSteps > 0) {
                double d5 = this.getX() + (this.lx - this.getX()) / (double)this.lSteps;
                double d6 = this.getY() + (this.ly - this.getY()) / (double)this.lSteps;
                double d7 = this.getZ() + (this.lz - this.getZ()) / (double)this.lSteps;
                this.setYRot(Mth.wrapDegrees((float)this.lyr));
                this.setXRot(this.getXRot() + (float)(this.lxr - (double)this.getXRot()) / (float)this.lSteps);
                --this.lSteps;
                this.setPos(d5, d6, d7);
            } else {
                this.reapplyPosition();
            }

            if (!this.clientSideAttackEnded) {
                for(int particleCount = 0; particleCount < 2; ++particleCount) {
                    for(int i = 0; i < 2; ++i) {
                        float xOffset = (float)(i - 1) / 2.0F + 0.25F + (this.random.nextFloat() - 0.5F) * 0.2F;
                        int rand = this.random.nextInt(20);
                        this.spawnParticleAt(0.1F + this.random.nextFloat() * 0.2F, 0.7F, xOffset * 3.5F, new CustomPoofParticleOptions(76 + rand, 147 + rand, 173 + rand, 0.1F));
                    }
                }
            }
        } else {
            this.reapplyPosition();
            this.setRot(this.getYRot(), this.getXRot());
            this.attackEntities((double)1.5F, (double)Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
        }

        Vec3 vec3 = this.getDeltaMovement().scale((double)0.9F).add(directionVec);
        this.move(MoverType.SELF, vec3);
        this.setDeltaMovement(vec3.multiply((double)0.99F, (double)0.98F, (double)0.99F));
    }

    protected void attackEntities(double strength, double x, double z) {
        AABB bashBox = this.getBoundingBox().inflate(0.01F);

        for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, bashBox)) {

            if (this.owner == null
                    || (!this.owner.equals(entity)
                    && !this.owner.isAlliedTo(entity)
                    && !entity.isAlliedTo(this.owner))) {

                DamageSources.ignoreNextKnockback(entity);
                float totalDamage = this.getDamage();
                boolean flag = DamageSources.applyDamage(entity, totalDamage, (ESSpellRegistries.TIDAL_WAVE.get()).getDamageSource(this, this.getOwner()));

                if (flag) {

                    entity.extinguishFire();

                    MobEffectInstance effectinstance1 =
                            entity.getEffect(ModEffect.EFFECTWETNESS);

                    int i = 1;

                    if (effectinstance1 != null) {
                        i += effectinstance1.getAmplifier();
                        entity.removeEffectNoUpdate(ModEffect.EFFECTWETNESS);
                    } else {
                        --i;
                    }

                    i = Mth.clamp(i, 0, 4);

                    MobEffectInstance effectinstance =
                            new MobEffectInstance(
                                    ModEffect.EFFECTWETNESS,
                                    200,
                                    i,
                                    false,
                                    true,
                                    true
                            );

                    entity.addEffect(effectinstance);
                }

                entity.hasImpulse = true;

                Vec3 vec3;

                for (vec3 = entity.getDeltaMovement();
                     x * x + z * z < 1.0E-5F;
                     z = (Math.random() - Math.random()) * 0.01D) {

                    x = (Math.random() - Math.random()) * 0.01D;
                }

                double playerSize = 1.08D;
                double entitySize =
                        entity.getBbWidth() * entity.getBbHeight();

                double scale =
                        playerSize / Math.max(0.1D, entitySize);

                double knockbackScale =
                        Math.min(scale, 1.5D);

                double adjustedStrength =
                        strength * knockbackScale;

                Vec3 knockback =
                        (new Vec3(x, 0.0D, z))
                                .normalize()
                                .scale(adjustedStrength);

                entity.setDeltaMovement(
                        vec3.x / 2.0D - knockback.x,
                        entity.onGround()
                                ? Math.min(0.5D, vec3.y / 2.0D + strength)
                                : vec3.y,
                        vec3.z / 2.0D - knockback.z
                );
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 4) {
            this.clientSideAttackEnded = true;
        }

    }

    public void lerpTo(double x, double y, double z, float yr, float xr, int steps) {
        this.lx = x;
        this.ly = y;
        this.lz = z;
        this.lyr = (double)yr;
        this.lxr = (double)xr;
        this.lSteps = steps;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    public void lerpMotion(double lerpX, double lerpY, double lerpZ) {
        this.lxd = lerpX;
        this.lyd = lerpY;
        this.lzd = lerpZ;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    static {
        LIFESPAN = SynchedEntityData.defineId(WaveMagicProjectileEntity.class, EntityDataSerializers.INT);
        MAX_TICKS = SynchedEntityData.defineId(WaveMagicProjectileEntity.class, EntityDataSerializers.INT);
        Y_ROT = SynchedEntityData.defineId(WaveMagicProjectileEntity.class, EntityDataSerializers.FLOAT);
        STATE = SynchedEntityData.defineId(WaveMagicProjectileEntity.class, EntityDataSerializers.INT);
        DAMAGE = SynchedEntityData.defineId(WaveMagicProjectileEntity.class, EntityDataSerializers.FLOAT);
    }
}


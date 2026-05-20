package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;

import java.util.Optional;

import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

public class TidalWaveStomp extends AbstractMagicProjectile {
    int step;
    int maxSteps;

    public void trailParticles() {
    }

    public void impactParticles(double x, double y, double z) {
    }

    public float getSpeed() {
        return 0.0F;
    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    public TidalWaveStomp(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
        this.setNoGravity(true);
        this.maxSteps = 5;
    }

    public TidalWaveStomp(Level level, int steps, float yRot) {
        this(ESEntityRegistry.TIDAL_WAVE_STOMP.get(), level);
        this.maxSteps = steps;
        this.setYRot(yRot);
    }

    public void tick() {
        if (!this.level.isClientSide) {
            if (this.tickCount % 1 == 0) {
                this.checkHits();
            }

            if (this.step > this.maxSteps) {
                this.discard();
            }
        }

    }

    protected void checkHits() {
        if (!this.level.isClientSide) {
            ++this.step;
            int width = Math.max(this.step / 2, 2);
            float angle = this.getYRot() * ((float)Math.PI / 180F);
            Vec3 forward = new Vec3((double)Mth.sin(-angle), (double)0.0F, (double)Mth.cos(-angle));
            Vec3 orth = new Vec3(-forward.z, (double)0.0F, forward.x);
            Vec3 center = this.position().add(forward.scale((double)this.step));
            Vec3 leftBound = Utils.moveToRelativeGroundLevel(this.level, center.subtract(orth.scale((double)width)), 2).add((double)0.0F, (double)0.75F, (double)0.0F);
            Vec3 rightBound = Utils.moveToRelativeGroundLevel(this.level, center.add(orth.scale((double)width)), 2).add((double)0.0F, (double)0.75F, (double)0.0F);
            this.level.getEntities(this, new AABB(leftBound.add((double)0.0F, (double)-1.0F, (double)0.0F), rightBound.add((double)0.0F, (double)1.0F, (double)0.0F))).forEach((entity) -> {
                if (this.canHitEntity(entity) && Utils.checkEntityIntersecting(entity, leftBound, rightBound, 1.0F).getType() != Type.MISS && DamageSources.applyDamage(entity, this.getDamage(), ((AbstractSpell) ESSpellRegistries.TIDAL_WAVE.get()).getDamageSource(this, this.getOwner())) && entity instanceof LivingEntity livingEntity) {
                    livingEntity.knockback((double)(this.explosionRadius * -0.35F), forward.x, forward.z);
                }

            });

            for(int i = 0; i < this.step; ++i) {
                Vec3 pos = leftBound.add(rightBound.subtract(leftBound).scale(((double)i + (double)0.5F) / (double)((float)this.step)));
                BlockPos blockPos = BlockPos.containing(Utils.moveToRelativeGroundLevel(this.level, pos, 2)).below();
                float impulseStrength = Utils.random.nextFloat() * 0.15F + 0.3F;
                Utils.createTremorBlock(this.level, blockPos, impulseStrength);
            }
        }

    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("stompStep", this.step);
        pCompound.putInt("maxSteps", this.maxSteps);
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.step = pCompound.getInt("stompStep");
        this.maxSteps = pCompound.getInt("maxSteps");
    }
}

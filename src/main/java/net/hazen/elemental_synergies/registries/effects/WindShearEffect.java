package net.hazen.elemental_synergies.registries.effects;

import com.snackpirate.aeromancy.data.AADamageTypes;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class WindShearEffect extends MagicMobEffect implements ISyncedMobEffect {

    public WindShearEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        DamageSource source = entity.damageSources().source(AADamageTypes.WIND_MAGIC);
        entity.hurt(source, 1.0F + amplifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = 20 >> amplifier;
        return interval > 0 ? duration % interval == 0 : true;
    }

    @Override
    public void clientTick(LivingEntity livingEntity, MobEffectInstance instance) {
        var random = livingEntity.getRandom();
        ParticleOptions particle = ParticleTypes.CLOUD;

        for (int i = 0; i < 1; i++) {
            Vec3 motion = new Vec3(
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1,
                    random.nextFloat() * 2 - 1
            ).scale(0.04f);

            livingEntity.level().addParticle(
                    particle,
                    livingEntity.getRandomX(0.4f),
                    livingEntity.getRandomY(),
                    livingEntity.getRandomZ(0.4f),
                    motion.x, motion.y, motion.z
            );
        }
    }
}

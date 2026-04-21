package net.hazen.elemental_synergies.Registries.Effects;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.hazen.elemental_synergies.Registries.ESParticleHelper;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class NightStateEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float REND_PER_LEVEL = 0.1F;
    public static final float CRIT_PER_LEVEL = 0.15F;
    public static final float SPELL_RES_PER_LEVEL = -0.15F;

    public NightStateEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
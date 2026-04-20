package net.hazen.elemental_synergies.Registries.Effects;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class NightStateEffect extends MagicMobEffect implements ISyncedMobEffect {
    public static final float REND_PER_LEVEL = 0.1F;
    public static final float CRIT_PER_LEVEL = 0.15F;
    public static final float SPELL_RES_PER_LEVEL = -0.15F;

    public NightStateEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
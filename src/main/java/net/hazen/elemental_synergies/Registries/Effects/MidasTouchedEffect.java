package net.hazen.elemental_synergies.Registries.Effects;

import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.hazen.elemental_synergies.Datagen.ESTags;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MidasTouchedEffect extends MagicMobEffect implements ISyncedMobEffect {

    private static final TagKey<EntityType<?>> MIDAS_IMMUNE_TYPE_TAG =
            ESTags.EntityTypes.MIDAS_TOUCHED_IMMUNE;

    public MidasTouchedEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public static boolean isImmune(LivingEntity entity) {
        if (entity == null) return false;

        return entity.getType().is(MIDAS_IMMUNE_TYPE_TAG);
    }

    public static boolean tryApplyIfNotImmune(LivingEntity entity, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        if (entity == null) return false;
        if (isImmune(entity)) return false;
        if (entity.level().isClientSide) return false;
        entity.addEffect(new MobEffectInstance(ESEffectRegistry.MIDAS_TOUCHED, duration, amplifier, ambient, visible, showIcon));
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return true;

        float health = entity.getHealth();
        float maxHealth = entity.getMaxHealth();

        if (health <= maxHealth * 0.25f && !(entity instanceof Player)) {
            entity.kill();
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration == 1;
    }

}
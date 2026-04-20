package net.hazen.elemental_synergies.Registries;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.Effects.NightStateEffect;
import net.hazen.elemental_synergies.Registries.Effects.WindShearEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ElementalSynergies.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> NIGHT_STATE = MOB_EFFECTS.register("night_state", () -> new NightStateEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.SPELL_RES_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ASAttributeRegistry.SPELL_RES_PENETRATION,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.REND_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_SHRED,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.REND_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_PIERCE,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.REND_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_DAMAGE,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.CRIT_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_CHANCE,
                    ElementalSynergies.id("lunar_state"),
                    NightStateEffect.CRIT_PER_LEVEL,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> WIND_SHEAR = MOB_EFFECTS.register("wind_shear",
            () -> new WindShearEffect(MobEffectCategory.HARMFUL, 0xa3b6ff));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}

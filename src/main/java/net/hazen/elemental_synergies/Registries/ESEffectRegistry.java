package net.hazen.elemental_synergies.Registries;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.Effects.*;
import net.hazen.hazennstuff.Registries.Effects.GenericHazenEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ElementalSynergies.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> NIGHT_STATE = MOB_EFFECTS.register("night_state", () -> new GenericHazenEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST,
                    ElementalSynergies.id("night_state"),
                    -0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ASAttributeRegistry.SPELL_RES_PENETRATION,
                    ElementalSynergies.id("night_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_SHRED,
                    ElementalSynergies.id("night_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_PIERCE,
                    ElementalSynergies.id("night_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_DAMAGE,
                    ElementalSynergies.id("night_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_CHANCE,
                    ElementalSynergies.id("night_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> BRIMSTONE_STATE = MOB_EFFECTS.register("brimstone_state", () -> new GenericHazenEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST,
                    ElementalSynergies.id("brimstone_state"),
                    -0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ASAttributeRegistry.SPELL_RES_PENETRATION,
                    ElementalSynergies.id("brimstone_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_SHRED,
                    ElementalSynergies.id("brimstone_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_PIERCE,
                    ElementalSynergies.id("brimstone_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_DAMAGE,
                    ElementalSynergies.id("brimstone_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_CHANCE,
                    ElementalSynergies.id("brimstone_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> IGNIS_SOUL_STATE = MOB_EFFECTS.register("ignis_soul_state", () -> new GenericHazenEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST,
                    ElementalSynergies.id("ignis_soul_state"),
                    -0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ASAttributeRegistry.SPELL_RES_PENETRATION,
                    ElementalSynergies.id("ignis_soul_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_SHRED,
                    ElementalSynergies.id("ignis_soul_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.ARMOR_PIERCE,
                    ElementalSynergies.id("ignis_soul_state"),
                    0.1F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_DAMAGE,
                    ElementalSynergies.id("ignis_soul_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(ALObjects.Attributes.CRIT_CHANCE,
                    ElementalSynergies.id("ignis_soul_state"),
                    0.15F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> VULNERABILITY_HEX = MOB_EFFECTS.register("vulnerability_hex", () -> new VulnerabilityHexEffect(MobEffectCategory.HARMFUL, 3311322)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    ElementalSynergies.id("vulnerability_hex"),
                    VulnerabilityHexEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.ATTACK_SPEED,
                    ElementalSynergies.id("vulnerability_hex"),
                    VulnerabilityHexEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.ARMOR,
                    ElementalSynergies.id("vulnerability_hex"),
                    VulnerabilityHexEffect.DEFENSE_REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                    ElementalSynergies.id("vulnerability_hex"),
                    VulnerabilityHexEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> NIGHT_WITHER = MOB_EFFECTS.register("night_wither", () -> new NightWitherEffect(MobEffectCategory.HARMFUL, 3311322)
            .addAttributeModifier(Attributes.MAX_HEALTH,
                    ElementalSynergies.id("vulnerability_hex"),
                    NightWitherEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
                    ElementalSynergies.id("vulnerability_hex"),
                    NightWitherEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.ARMOR,
                    ElementalSynergies.id("vulnerability_hex"),
                    NightWitherEffect.DEFENSE_REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

            .addAttributeModifier(Attributes.BURNING_TIME,
                    ElementalSynergies.id("vulnerability_hex"),
                    NightWitherEffect.REDUCTION,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final DeferredHolder<MobEffect, MobEffect> WIND_SHEAR = MOB_EFFECTS.register("wind_shear",
            () -> new WindShearEffect(MobEffectCategory.HARMFUL, 0xa3b6ff));

    public static final DeferredHolder<MobEffect, MobEffect> BRIMSTONE_FLAMES = MOB_EFFECTS.register("brimstone_flames",
            () -> new BrimstoneFlamesEffect(MobEffectCategory.HARMFUL, 0xa3b6ff));

    public static final DeferredHolder<MobEffect, MobEffect> HOLY_FLAMES = MOB_EFFECTS.register("holy_flames",
            () -> new HolyFlamesEffect(MobEffectCategory.HARMFUL, 0xa3b6ff));


    public static final DeferredHolder<MobEffect, MobEffect> MIDAS_TOUCHED = MOB_EFFECTS.register("midas_touched",
            () -> new MidasTouchedEffect(MobEffectCategory.HARMFUL, 0xa3b6ff));



    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}

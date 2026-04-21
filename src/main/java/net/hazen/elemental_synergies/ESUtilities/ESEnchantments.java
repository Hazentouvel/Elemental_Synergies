package net.hazen.elemental_synergies.ESUtilities;

import net.alshanex.tunes_n_tomes.registry.TAttributeRegistry;
import net.hazen.elemental_synergies.Datagen.ESEnchantmentTags;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Enchantments.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public class ESEnchantments {
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "lightning_striker"));
    public static final ResourceKey<Enchantment> MELODIC_ATTUNEMENT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "melodic_attunement"));
    public static final ResourceKey<Enchantment> SPELLBLADE_ATTUNEMENT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "melodic_attunement"));

    public static void bootstrap (BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);



        register(context, LIGHTNING_STRIKER, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                5,
                2,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 7),
                2,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect()
                )
        );

        register(context, MELODIC_ATTUNEMENT, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        5,
                        2,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(ESEnchantmentTags.SPELLBOOK_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "melodic_attunement"),
                                TAttributeRegistry.MELODY_SPELL_POWER,
                                LevelBasedValue.perLevel(0.05F),
                                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                        )
                )
        );
    }

    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder)
    {
        registry.register(key, builder.build(key.location()));
    }

}

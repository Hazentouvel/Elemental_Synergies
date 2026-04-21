package net.hazen.elemental_synergies.Datagen;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public interface ESEnchantmentTags {
    TagKey<Enchantment> SPELLBOOK_EXCLUSIVE = createTag("spellbook_exclusive");


    private static TagKey<Enchantment> createTag(String name) {
        return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, name)
        );
    }
}

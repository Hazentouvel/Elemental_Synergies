package net.hazen.elemental_synergies.Datagen;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;

public class ESTags {

    public static final TagKey<Item> ASCENSION_HELMET = ItemTags
            .create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "armors/ascension_helmet").toString()));
    public static final TagKey<Item> ASCENSION_CHESTPLATE = ItemTags
            .create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "armors/ascension_chestplate").toString()));
    public static final TagKey<Item> ASCENSION_LEGGINGS = ItemTags
            .create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "armors/ascension_leggings").toString()));
    public static final TagKey<Item> ASCENSION_BOOTS = ItemTags
            .create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "armors/ascension_boots").toString()));


    private static TagKey<Block> createTag (String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, name));

    }
}
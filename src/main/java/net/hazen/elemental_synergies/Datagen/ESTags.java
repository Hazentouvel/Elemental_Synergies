package net.hazen.elemental_synergies.Datagen;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
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


    public static class Blocks {

        public static final TagKey<Block> HOLY_FIRE_SURVIVES_ON = BlockTags
                .create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "fire/holy_fire_survives_on").toString()));

    }

    public static class EntityTypes {

        public static final TagKey<EntityType<?>> HOLY_FIRE_IMMUNE =
                TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "fire/abyssal_fire_immune"));
    }

    public static class Spells {

        public static final TagKey<AbstractSpell> BRIMSTONE_SPELLS =
                TagKey.create(SpellRegistry.SPELL_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "brimstone_spells"));

        public static final TagKey<AbstractSpell> PROFANE_SPELLS =
                TagKey.create(SpellRegistry.SPELL_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "profane_spells"));
    }

    private static TagKey<Block> createTag (String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, name));

    }
}
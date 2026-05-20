package net.hazen.elemental_synergies.Datagen.Items;

import io.redspace.ironsspellbooks.util.ModTags;
import net.acetheeldritchking.cataclysm_spellbooks.registries.ItemRegistries;
import net.hazen.elemental_synergies.Datagen.ESTags;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazentouvelib.Datagen.HLTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ESItemTagProvider extends ItemTagsProvider {
    public ESItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ElementalSynergies.MOD_ID, existingFileHelper);
    }

    //.add(GGItems.GEO_RUNE.get())

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Ores and Ingots
        tag(Tags.Items.INGOTS)
                .add(
                        ESItemRegistry.AERIALITE_INGOT.get()
                )
        ;

        tag(Tags.Items.ORES)

                .add(
                        ESItemRegistry.AERIALITE_FRAGMENT.get()
                )

        ;


        tag(Tags.Items.ENCHANTABLES)

                .add(
                        ESItemRegistry.ARCANE_MACE.get(),
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get(),
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;


        tag(HLTags.FIRE_SPELLBOOK)
                .add(
                        ItemRegistries.IGNIS_SPELL_BOOK.get()
                )
        ;


        tag(ESTags.HYDRO_NORMALIZING_CURIO)
                .add(
                        ESItemRegistry.RING_OF_ACROPOLIS.get()
                )
        ;


        tag(ESTags.HYDRO_ENHANCING_CURIO)
                .add(
                        ESItemRegistry.LUNAR_SHARD.get()
                )
        ;


        tag(ESTags.LIGHTNING_ENHANCING_CURIO)
                .add(
                        ESItemRegistry.THE_SKYWARD_EMBLEM.get()
                )
        ;

        /*
         *** Tools and Weapons Tags
         */

        tag(ItemTags.SWORDS)
                .add(
                        ESItemRegistry.ARCANE_MACE.get(),
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;

        tag(Tags.Items.TOOLS_MACE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )
        ;

        tag(ItemTags.MACE_ENCHANTABLE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )
        ;

        tag(Tags.Items.TOOLS_SPEAR)
                .add(
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get(),
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;

        tag(HLTags.ENDER_MASK)
                .add(
                        ESItemRegistry.ONYX_HELMET.get()
                )
        ;

        tag(ESTags.ASCENSION_HELMET)
                .add(
                        ESItemRegistry.SUPREME_CALAMITAS_HELMET.get(),
                        ESItemRegistry.PROVIDENCE_HELMET.get()
                )
        ;

        tag(ESTags.ASCENSION_CHESTPLATE)
                .add(
                        ESItemRegistry.SUPREME_CALAMITAS_CHESTPLATE.get(),
                        ESItemRegistry.PROVIDENCE_CHESTPLATE.get()
                )
        ;

        tag(ESTags.ASCENSION_LEGGINGS)
                .add(
                        ESItemRegistry.SUPREME_CALAMITAS_LEGGINGS.get(),
                        ESItemRegistry.PROVIDENCE_LEGGINGS.get()
                )
        ;

        tag(ESTags.ASCENSION_BOOTS)
                .add(
                        ESItemRegistry.SUPREME_CALAMITAS_BOOTS.get(),
                        ESItemRegistry.PROVIDENCE_BOOTS.get()
                )
        ;

        tag(HLTags.PURE_HELMET)
                .add(
                        ESItemRegistry.AEROSPEC_HAT.get(),
                        ESItemRegistry.AEROSPEC_CROWN.get(),
                        ESItemRegistry.CLOUDMASTER_CROWN.get(),
                        ESItemRegistry.CLOUDMASTER_HAT.get(),
                        ESItemRegistry.TITAN_HELMET.get(),
                        ESItemRegistry.SOUL_FLAME_HELMET.get(),
                        ESItemRegistry.EXO_MECH_HELMET.get(),
                        ESItemRegistry.CATACLYSM_HELMET.get()
                )
                .add(
                        ESItemRegistry.ONYX_HELMET.get(),
                        ESItemRegistry.IGNIS_HELMET.get(),
                        ESItemRegistry.SCYLLA_HELMET.get(),
                        ESItemRegistry.MALEDICTUS_HELMET.get()
                );

        tag(HLTags.PURE_CHESTPLATE)
                .add(
                        ESItemRegistry.AEROSPEC_CHESTPLATE.get(),
                        ESItemRegistry.CLOUDMASTER_CHESTPLATE.get(),
                        ESItemRegistry.TITAN_CHESTPLATE.get(),
                        ESItemRegistry.SOUL_FLAME_CHESTPLATE.get(),
                        ESItemRegistry.EXO_MECH_CHESTPLATE.get(),
                        ESItemRegistry.CATACLYSM_CHESTPLATE.get()
                )
                .add(
                        ESItemRegistry.ONYX_CHESTPLATE.get(),
                        ESItemRegistry.IGNIS_CHESTPLATE.get(),
                        ESItemRegistry.SCYLLA_CHESTPLATE.get(),
                        ESItemRegistry.MALEDICTUS_CHESTPLATE.get()
                )
        ;

        tag(HLTags.PURE_LEGGINGS)
                .add(
                        ESItemRegistry.AEROSPEC_LEGGINGS.get(),
                        ESItemRegistry.CLOUDMASTER_LEGGINGS.get(),
                        ESItemRegistry.TITAN_LEGGINGS.get(),
                        ESItemRegistry.SOUL_FLAME_LEGGINGS.get(),
                        ESItemRegistry.EXO_MECH_LEGGINGS.get(),
                        ESItemRegistry.CATACLYSM_LEGGINGS.get()
                )
                .add(
                        ESItemRegistry.ONYX_LEGGINGS.get(),
                        ESItemRegistry.IGNIS_LEGGINGS.get(),
                        ESItemRegistry.SCYLLA_LEGGINGS.get(),
                        ESItemRegistry.MALEDICTUS_LEGGINGS.get()
                )

        ;

        tag(HLTags.PURE_BOOTS)
                .add(
                        ESItemRegistry.AEROSPEC_BOOTS.get(),
                        ESItemRegistry.CLOUDMASTER_BOOTS.get(),
                        ESItemRegistry.TITAN_BOOTS.get(),
                        ESItemRegistry.SOUL_FLAME_BOOTS.get(),
                        ESItemRegistry.EXO_MECH_BOOTS.get(),
                        ESItemRegistry.CATACLYSM_BOOTS.get()
                )
                .add(
                        ESItemRegistry.ONYX_BOOTS.get(),
                        ESItemRegistry.IGNIS_BOOTS.get(),
                        ESItemRegistry.SCYLLA_BOOTS.get(),
                        ESItemRegistry.MALEDICTUS_BOOTS.get()
                )

        ;

        tag(HLTags.PARAGON_HELMET)
                .add(
                        ESItemRegistry.ONYX_HELMET.get(),
                        ESItemRegistry.IGNIS_HELMET.get(),
                        ESItemRegistry.SCYLLA_HELMET.get(),
                        ESItemRegistry.MALEDICTUS_HELMET.get()
                )
        ;

        tag(HLTags.PARAGON_CHESTPLATE)
                .add(
                        ESItemRegistry.ONYX_CHESTPLATE.get(),
                        ESItemRegistry.IGNIS_CHESTPLATE.get(),
                        ESItemRegistry.SCYLLA_CHESTPLATE.get(),
                        ESItemRegistry.MALEDICTUS_CHESTPLATE.get()
                )
        ;

        tag(HLTags.PARAGON_LEGGINGS)
                .add(
                        ESItemRegistry.ONYX_LEGGINGS.get(),
                        ESItemRegistry.IGNIS_LEGGINGS.get(),
                        ESItemRegistry.SCYLLA_LEGGINGS.get(),
                        ESItemRegistry.MALEDICTUS_LEGGINGS.get()
                )

        ;

        tag(HLTags.PARAGON_BOOTS)
                .add(
                        ESItemRegistry.ONYX_BOOTS.get(),
                        ESItemRegistry.IGNIS_BOOTS.get(),
                        ESItemRegistry.SCYLLA_BOOTS.get(),
                        ESItemRegistry.MALEDICTUS_BOOTS.get()
                )

        ;

        tag(HLTags.SCHOOL_HELMET)
                .add(
                        ESItemRegistry.SYNTHESIZER_V_HELMET.get(),
                        ESItemRegistry.ROTTEN_GIRL_HELMET.get(),
                        ESItemRegistry.PROJECT_SEKAI_HELMET.get(),
                        ESItemRegistry.NERU_HELMET.get(),
                        ESItemRegistry.UTAU_HELMET.get()
                )
        ;

        tag(HLTags.SCHOOL_CHESTPLATE)
                .add(
                        ESItemRegistry.SYNTHESIZER_V_CHESTPLATE.get(),
                        ESItemRegistry.ROTTEN_GIRL_CHESTPLATE.get(),
                        ESItemRegistry.PROJECT_SEKAI_CHESTPLATE.get(),
                        ESItemRegistry.NERU_CHESTPLATE.get(),
                        ESItemRegistry.UTAU_CHESTPLATE.get()
                )
        ;

        tag(HLTags.SCHOOL_LEGGINGS)
                .add(
                        ESItemRegistry.SYNTHESIZER_V_LEGGINGS.get(),
                        ESItemRegistry.ROTTEN_GIRL_LEGGINGS.get(),
                        ESItemRegistry.PROJECT_SEKAI_LEGGINGS.get(),
                        ESItemRegistry.NERU_LEGGINGS.get(),
                        ESItemRegistry.UTAU_LEGGINGS.get()
                )
        ;

        tag(HLTags.SCHOOL_BOOTS)
                .add(
                        ESItemRegistry.SYNTHESIZER_V_BOOTS.get(),
                        ESItemRegistry.ROTTEN_GIRL_BOOTS.get(),
                        ESItemRegistry.PROJECT_SEKAI_BOOTS.get(),
                        ESItemRegistry.NERU_BOOTS.get(),
                        ESItemRegistry.UTAU_BOOTS.get()
                )
        ;

        /*
         *** Tools and Weapons Tags
         */

        tag(ItemTags.AXES)

        ;

        tag(ItemTags.SWORDS)
                .add(
                        ESItemRegistry.EXCELSIOR.get(),
                        ESItemRegistry.VIOLENCE.get(),
                        ESItemRegistry.CATASTROPHE.get()
                )
        ;

        tag(ItemTags.PICKAXES)

        ;

        tag(Tags.Items.TOOLS_MACE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )
        ;

        tag(ItemTags.MACE_ENCHANTABLE)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )
        ;

        tag(ItemTags.MINING_ENCHANTABLE)
        ;

        tag(Tags.Items.TOOLS_SPEAR)
        ;

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
        ;

        /*
        *** Misc
         */

        tag(ModTags.CAN_BE_IMBUED)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )
        ;

        tag(ModTags.CAN_BE_UPGRADED)
                .add(
                        ESItemRegistry.ARCANE_MACE.get()
                )

        ;

    }
}
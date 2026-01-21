package net.hazen.elemental_synergies.Datagen.Items;

import io.redspace.ironsspellbooks.util.ModTags;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazennstuff.datagen.HnSTags;
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
                .add(ESItemRegistry.AERIALITE_INGOT.get())
        ;

        tag(Tags.Items.ORES)

                .add(ESItemRegistry.AERIALITE_FRAGMENT.get())

        ;


        tag(Tags.Items.ENCHANTABLES)

                /*
                *** Aeromancy
                 */

                //Aerospec
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.AEROSPEC_HELM.get())
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())

                //Cloudmaster Sage
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())

                /*
                *** Geomancy
                 */

                //Titan
                .add(ESItemRegistry.TITAN_HELMET.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                //Titan
                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())


                /*
                *** Weapons/Staves
                */

                .add(ESItemRegistry.ARCANE_MACE.get())
        ;

        tag(Tags.Items.ARMORS)

                /*
                 *** Aeromancy
                 */

                //Aerospec
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.AEROSPEC_HELM.get())
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())

                //Cloudmaster Sage
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())

                /*
                 *** Geomancy
                 */

                //Titan
                .add(ESItemRegistry.TITAN_HELMET.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                //Titan
                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)

                /*
                 *** Aeromancy
                 */

                //Aerospec
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.AEROSPEC_HELM.get())
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())

                //Cloudmaster Sage
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())

                /*
                 *** Geomancy
                 */

                //Titan
                .add(ESItemRegistry.TITAN_HELMET.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                //Titan
                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        tag(ItemTags.ARMOR_ENCHANTABLE)

                /*
                 *** Aeromancy
                 */

                //Aerospec
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.AEROSPEC_HELM.get())
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())

                //Cloudmaster Sage
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())

                /*
                 *** Geomancy
                 */

                //Titan
                .add(ESItemRegistry.TITAN_HELMET.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                //Titan
                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        tag(ItemTags.HEAD_ARMOR)
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.TITAN_HELMET.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
        ;

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.TITAN_HELMET.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
        ;

        tag(HnSTags.PURE_HELMET)
                .add(ESItemRegistry.AEROSPEC_HAT.get())
                .add(ESItemRegistry.AEROSPEC_CROWN.get())
                .add(ESItemRegistry.CLOUDMASTER_HAT.get())
                .add(ESItemRegistry.CLOUDMASTER_CROWN.get())
                .add(ESItemRegistry.TITAN_HELMET.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_HELMET.get())
        ;

        tag(ItemTags.CHEST_ARMOR)
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
        ;

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
        ;

        tag(HnSTags.PURE_CHESTPLATE)
                .add(ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .add(ESItemRegistry.AEROSPEC_ROBES.get())
                .add(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .add(ESItemRegistry.TITAN_CHESTPLATE.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get())
        ;

        tag(ItemTags.LEG_ARMOR)
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
        ;

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
        ;

        tag(HnSTags.PURE_LEGGINGS)
                .add(ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .add(ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .add(ESItemRegistry.TITAN_LEGGINGS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get())
        ;

        tag(ItemTags.FOOT_ARMOR)
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        tag(HnSTags.PURE_BOOTS)
                .add(ESItemRegistry.AEROSPEC_BOOTS.get())
                .add(ESItemRegistry.CLOUDMASTER_BOOTS.get())
                .add(ESItemRegistry.TITAN_BOOTS.get())

                .add(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get())
        ;

        /*
         *** Tools and Weapons Tags
         */

        tag(ItemTags.AXES)
        ;

        tag(ItemTags.SWORDS)
        ;

        tag(ItemTags.PICKAXES)
        ;

        tag(Tags.Items.TOOLS_MACE)
                .add(ESItemRegistry.ARCANE_MACE.get())
        ;

        tag(ItemTags.MACE_ENCHANTABLE)
                .add(ESItemRegistry.ARCANE_MACE.get())
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
                .add(ESItemRegistry.ARCANE_MACE.get())
        ;

        tag(ModTags.CAN_BE_UPGRADED)
                .add(ESItemRegistry.ARCANE_MACE.get())

        ;

    }
}
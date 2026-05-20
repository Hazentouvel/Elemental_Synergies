package net.hazen.elemental_synergies.Datagen;

import com.gametechbc.gtbcs_geomancy_plus.init.GGItems;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.snackpirate.aeromancy.item.AAItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.acetheeldritchking.discerning_the_eldritch.registries.ItemRegistries;
import net.ender.ess_requiem.registries.GGItemRegistry;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazennstuff.Registries.HnSItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
//import net.warphan.iss_magicfromtheeast.registries.MFTEItemRegistries;

import java.util.concurrent.CompletableFuture;

public class ESRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ESRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        // Brimstone Debris
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ESItemRegistry.BRIMSTONE_DEBRIS.get())
                .requires(HnSItemRegistry.NIGHTMARE_FUEL.get())
                .requires(ItemRegistries.SHARD_OF_MALICE.get())
                .requires(ItemRegistry.BLOOD_VIAL.get())
                .requires(ItemRegistry.CINDER_ESSENCE.get())
                .unlockedBy("has_cinder_essence", has(ItemRegistry.CINDER_ESSENCE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/focus/brimstone_debris"));



        // Divine Geode
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ESItemRegistry.DIVINE_GEODE.get())
                .requires(HnSItemRegistry.NETHER_STAR_FRAGMENT.get())
                .requires(ESItemRegistry.CLUSTER.get())
                .requires(ItemRegistry.DIVINE_PEARL.get())
                .requires(ItemRegistry.CINDER_ESSENCE.get())
                .unlockedBy("has_cinder_essence", has(ItemRegistry.CINDER_ESSENCE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/focus/divine_geode"));



        //
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ESItemRegistry.ECTOPLASM.get())
                .requires(HnSItemRegistry.NIGHTMARE_FUEL.get())
                .requires(ItemRegistries.SOUL_EMBER.get())
                .requires(Items.SOUL_SAND)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_soul_ember", has(ItemRegistries.SOUL_EMBER.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/focus/ectoplasm"));



        //Temporary Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ESItemRegistry.AERIALITE_FRAGMENT.get())
                .pattern(" Z ")
                .pattern("BSB")
                .pattern(" Z ")
                .define('S', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('Z', ESItemRegistry.ZEPHYR_ESSENCE.get())
                .define('B', Items.BREEZE_ROD)
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/materials/aerialite_fragment"));


        /*
        *** Weapons
         */

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.EXCELSIOR.get(), 1)
                .pattern(" ZE")
                .pattern("RFZ")
                .pattern("WR ")
                .define('F', HnSItemRegistry.EXCALIBUR_FRAGMENT.get())
                .define('W', ItemRegistry.WEAPON_PARTS.get())
                .define('E', HnSItemRegistry.STEEL_INGOT)
                .define('Z', HnSItemRegistry.ZENALITE_INGOT)
                .define('R', GGItemRegistry.SPELLBLADE_RUNE)
                .unlockedBy("has_excalibur_fragment", has(HnSItemRegistry.EXCALIBUR_FRAGMENT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/weapons/excelsior"));


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.VIOLENCE.get(), 1)
                .pattern("ZSI")
                .pattern("BTS")
                .pattern("WBZ")
                .define('T', ItemRegistry.TWILIGHT_GALE.get())
                .define('W', ItemRegistry.WEAPON_PARTS.get())
                .define('I', ModItems.IGNITIUM_INGOT)
                .define('S', ItemRegistries.ELDRITCH_SOUL_SHARD.get())
                .define('Z', HnSItemRegistry.ZENALITE_INGOT)
                .define('B', ESItemRegistry.BRIMSTONE_DEBRIS)
                .unlockedBy("has_excalibur_fragment", has(HnSItemRegistry.EXCALIBUR_FRAGMENT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/weapons/violence"));

        /*
         *** Shields
         */


        //Arcane Mace
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ESItemRegistry.ARCANE_MACE.get())
                .pattern("SMS")
                .pattern("RZR")
                .pattern(" A ")
                .define('S', HnSItemRegistry.STEEL_INGOT.get())
                .define('Z', ESItemRegistry.ZEPHYR_ESSENCE.get())
                .define('A', AAItems.AIR_STAFF.get())
                .define('R', AAItems.WIND_RUNE.get())
                .define('M', Items.MACE)
                .unlockedBy("has_mace", has(Items.MACE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/weapons/arcane_mace"));


        /*
        *** Curios
         */

        /*

        //Temporary Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get())
                .pattern(" CS")
                .pattern("RAR")
                .pattern("SR ")
                .define('S', HnSItemRegistry.STEEL_INGOT.get())
                .define('R', HnSItemRegistry.SHADOW_RUNE.get())
                .define('A', MFTEItemRegistries.RITUAL_ORIHON.get())
                .define('C', ItemTags.CANDLES)
                .unlockedBy("has_ritual_orihon", has(MFTEItemRegistries.RITUAL_ORIHON.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/curios/grimoire_of_corruption"));

        */

        /*
        *** Aeromancy
         */

        //Aerospec
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_HELM.get())
                .pattern("RCR")
                .pattern("CPC")
                .pattern("   ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', HnSItemRegistry.MELEE_RUNE.get())
                .define('P', Items.NETHERITE_HELMET)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_CROWN.get())
                .pattern("RCR")
                .pattern("CPC")
                .pattern("   ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', HnSItemRegistry.ARCHERY_RUNE.get())
                .define('P', Items.NETHERITE_HELMET)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_crown"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_HAT.get())
                .pattern("RCR")
                .pattern("CPC")
                .pattern(" C ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', ItemRegistry.MANA_RUNE.get())
                .define('P', Items.NETHERITE_HELMET)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_hat"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_ROBES.get())
                .pattern("CPC")
                .pattern("RCR")
                .pattern("CCC")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', ItemRegistry.MANA_RUNE.get())
                .define('P', Items.NETHERITE_CHESTPLATE)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_robes"));


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_CHESTPLATE.get())
                .pattern("CPC")
                .pattern("RCR")
                .pattern("CCC")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', ItemRegistry.BLANK_RUNE.get())
                .define('P', Items.NETHERITE_CHESTPLATE)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .pattern("RPR")
                .pattern("C C")
                .pattern("C C")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', ItemRegistry.BLANK_RUNE.get())
                .define('P', Items.NETHERITE_LEGGINGS)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.AEROSPEC_BOOTS.get())
                .pattern("R R")
                .pattern("CPC")
                .pattern("   ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('R', ItemRegistry.BLANK_RUNE.get())
                .define('P', Items.NETHERITE_BOOTS)
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/aerospec/aerospec_boots"));


        //Cloudmaster
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.CLOUDMASTER_HAT.get())
                .pattern("C C")
                .pattern("MPM")
                .pattern(" D ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('D', ItemRegistry.DIVINE_SOULSHARD.get())
                .define('M', ItemRegistry.MITHRIL_WEAVE.get())
                .define('P', ESItemRegistry.AEROSPEC_HAT.get())
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/cloudmaster/cloudmaster_hat"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.CLOUDMASTER_CROWN.get())
                .pattern("C C")
                .pattern("MPM")
                .pattern(" D ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('D', ItemRegistry.DIVINE_SOULSHARD.get())
                .define('M', ItemRegistry.MITHRIL_WEAVE.get())
                .define('P', ESItemRegistry.AEROSPEC_CROWN.get())
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/cloudmaster/cloudmaster_crown"));


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.CLOUDMASTER_CHESTPLATE.get())
                .pattern("C C")
                .pattern("MPM")
                .pattern("CDC")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('D', ItemRegistry.DIVINE_SOULSHARD.get())
                .define('M', ItemRegistry.MITHRIL_WEAVE.get())
                .define('P', ESItemRegistry.AEROSPEC_ROBES.get())
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/cloudmaster/cloudmaster_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.CLOUDMASTER_LEGGINGS.get())
                .pattern("MDM")
                .pattern("CPC")
                .pattern("C C")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('D', ItemRegistry.DIVINE_SOULSHARD.get())
                .define('M', ItemRegistry.MITHRIL_WEAVE.get())
                .define('P', ESItemRegistry.AEROSPEC_LEGGINGS.get())
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/cloudmaster/cloudmaster_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.CLOUDMASTER_BOOTS.get())
                .pattern("CPC")
                .pattern("MDM")
                .pattern("   ")
                .define('C', ESItemRegistry.AERIALITE_INGOT.get())
                .define('D', ItemRegistry.DIVINE_SOULSHARD.get())
                .define('M', ItemRegistry.MITHRIL_WEAVE.get())
                .define('P', ESItemRegistry.AEROSPEC_BOOTS.get())
                .unlockedBy("has_aerialite_ingot", has(ESItemRegistry.AERIALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/cloudmaster/cloudmaster_boots"));



        /*
        *** Geomancy
         */

        //Titan Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.TITAN_HELMET.get())
                .pattern("FCG")
                .pattern("ZNZ")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', ESItemRegistry.CLUSTER.get())
                .define('F', ItemRegistry.FIRE_RUNE.get())
                .define('G', GGItems.GEO_RUNE.get())
                .define('N', ItemRegistry.NETHERITE_MAGE_HELMET.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/titan/titan_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.TITAN_CHESTPLATE.get())
                .pattern("CNC")
                .pattern("FZG")
                .pattern("ZZZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', ESItemRegistry.CLUSTER.get())
                .define('F', ItemRegistry.FIRE_RUNE.get())
                .define('G', GGItems.GEO_RUNE.get())
                .define('N', ItemRegistry.NETHERITE_MAGE_CHESTPLATE.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/titan/titan_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.TITAN_LEGGINGS.get())
                .pattern("FNG")
                .pattern("Z Z")
                .pattern("C C")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', ESItemRegistry.CLUSTER.get())
                .define('F', ItemRegistry.FIRE_RUNE.get())
                .define('G', GGItems.GEO_RUNE.get())
                .define('N', ItemRegistry.NETHERITE_MAGE_LEGGINGS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/titan/titan_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.TITAN_BOOTS.get())
                .pattern("FZG")
                .pattern("CNC")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', ESItemRegistry.CLUSTER.get())
                .define('F', ItemRegistry.FIRE_RUNE.get())
                .define('G', GGItems.GEO_RUNE.get())
                .define('N', ItemRegistry.NETHERITE_MAGE_BOOTS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/titan/titan_boots"));


        //Soul Flame Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_HELMET.get()),
                        Ingredient.of(ESItemRegistry.ECTOPLASM.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_HELMET.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_CHESTPLATE.get()),
                        Ingredient.of(ESItemRegistry.ECTOPLASM.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_CHESTPLATE.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_LEGGINGS.get()),
                        Ingredient.of(ESItemRegistry.ECTOPLASM.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_LEGGINGS.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_BOOTS.get()),
                        Ingredient.of(ESItemRegistry.ECTOPLASM.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_BOOTS.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_boots"));


        /*
         *** Paragon
         */

        //Providence Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SCYLLA_HELMET.get())
                .pattern("LRL")
                .pattern("EAE")
                .pattern("   ")
                .define('R', HnSItemRegistry.HYDRO_RUNE.get())
                .define('L', ModItems.LACRIMA.get())
                .define('E', ModItems.ESSENCE_OF_THE_STORM.get())
                .define('A', HnSItemRegistry.ELDER_GUARDIAN_HELMET.get())
                .unlockedBy("has_essence_of_the_storm", has(ModItems.ESSENCE_OF_THE_STORM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/scylla/scylla_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SCYLLA_CHESTPLATE.get())
                .pattern("L L")
                .pattern("EAE")
                .pattern(" R ")
                .define('R', HnSItemRegistry.HYDRO_RUNE.get())
                .define('L', ModItems.LACRIMA.get())
                .define('E', ModItems.ESSENCE_OF_THE_STORM.get())
                .define('A', HnSItemRegistry.ELDER_GUARDIAN_CHESTPLATE.get())
                .unlockedBy("has_essence_of_the_storm", has(ModItems.ESSENCE_OF_THE_STORM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/scylla/scylla_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SCYLLA_LEGGINGS.get())
                .pattern("LRL")
                .pattern("EAE")
                .pattern("   ")
                .define('R', HnSItemRegistry.HYDRO_RUNE.get())
                .define('L', ModItems.LACRIMA.get())
                .define('E', ModItems.ESSENCE_OF_THE_STORM.get())
                .define('A', HnSItemRegistry.ELDER_GUARDIAN_LEGGINGS.get())
                .unlockedBy("has_essence_of_the_storm", has(ModItems.ESSENCE_OF_THE_STORM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/scylla/scylla_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SCYLLA_BOOTS.get())
                .pattern("LRL")
                .pattern("EAE")
                .pattern("   ")
                .define('R', HnSItemRegistry.HYDRO_RUNE.get())
                .define('L', ModItems.LACRIMA.get())
                .define('E', ModItems.ESSENCE_OF_THE_STORM.get())
                .define('A', HnSItemRegistry.ELDER_GUARDIAN_BOOTS.get())
                .unlockedBy("has_essence_of_the_storm", has(ModItems.ESSENCE_OF_THE_STORM.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/scylla/scylla_boots"));


        //Ignis Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.IGNIS_HELMET.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern(" S ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.IGNITIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.IGNITIUM_INGOT.get())
                .define('A', ESItemRegistry.SOUL_FLAME_HELMET.get())
                .unlockedBy("has_ignitium", has(ModItems.IGNITIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/ignis/ignis_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.IGNIS_CHESTPLATE.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern("ZSZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.IGNITIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.IGNITIUM_INGOT.get())
                .define('A', ESItemRegistry.SOUL_FLAME_CHESTPLATE.get())
                .unlockedBy("has_ignitium", has(ModItems.IGNITIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/ignis/ignis_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.IGNIS_LEGGINGS.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern("ZSZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.IGNITIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.IGNITIUM_INGOT.get())
                .define('A', ESItemRegistry.SOUL_FLAME_LEGGINGS.get())
                .unlockedBy("has_ignitium", has(ModItems.IGNITIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/ignis/ignis_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.IGNIS_BOOTS.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern(" S ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.IGNITIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.IGNITIUM_INGOT.get())
                .define('A', ESItemRegistry.SOUL_FLAME_BOOTS.get())
                .unlockedBy("has_ignitium", has(ModItems.IGNITIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/ignis/ignis_boots"));


        //Maledictus Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.MALEDICTUS_HELMET.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern(" S ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.CURSIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.CURSIUM_INGOT.get())
                .define('A', HnSItemRegistry.CRYSTAL_ARACHNID_HELMET.get())
                .unlockedBy("has_cursium", has(ModItems.CURSIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/maledictus/maledictus_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.MALEDICTUS_CHESTPLATE.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern("ZSZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.CURSIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.CURSIUM_INGOT.get())
                .define('A', HnSItemRegistry.CRYSTAL_ARACHNID_CHESTPLATE.get())
                .unlockedBy("has_cursium", has(ModItems.CURSIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/maledictus/maledictus_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.MALEDICTUS_LEGGINGS.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern("ZSZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.CURSIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.CURSIUM_INGOT.get())
                .define('A', HnSItemRegistry.CRYSTAL_ARACHNID_LEGGINGS.get())
                .unlockedBy("has_cursium", has(ModItems.CURSIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/maledictus/maledictus_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.MALEDICTUS_BOOTS.get())
                .pattern("ZIZ")
                .pattern("ZAZ")
                .pattern(" S ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('S', ModItems.CURSIUM_UPGARDE_SMITHING_TEMPLATE.get())
                .define('I', ModItems.CURSIUM_INGOT.get())
                .define('A', HnSItemRegistry.CRYSTAL_ARACHNID_BOOTS.get())
                .unlockedBy("has_cursium", has(ModItems.CURSIUM_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/maledictus/maledictus_boots"));


        /*
         *** Ascended
         */

        //Providence Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.PROVIDENCE_HELMET.get())
                .pattern("ZAZ")
                .pattern("DCD")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.DIVINE_GEODE.get())
                .define('A', ESItemRegistry.IGNIS_HELMET.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/providence/providence_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.PROVIDENCE_CHESTPLATE.get())
                .pattern("DCD")
                .pattern("ZAZ")
                .pattern("ZEZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.DIVINE_GEODE.get())
                .define('E', Items.ELYTRA)
                .define('A', ESItemRegistry.IGNIS_CHESTPLATE.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/providence/providence_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.PROVIDENCE_LEGGINGS.get())
                .pattern("DAD")
                .pattern("ZCZ")
                .pattern("Z Z")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.DIVINE_GEODE.get())
                .define('A', ESItemRegistry.IGNIS_LEGGINGS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/providence/providence_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.PROVIDENCE_BOOTS.get())
                .pattern("DCD")
                .pattern("ZAZ")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.DIVINE_GEODE.get())
                .define('A', ESItemRegistry.IGNIS_BOOTS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/providence/providence_boots"));


        //Supreme Calamitas Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SUPREME_CALAMITAS_HELMET.get())
                .pattern("ZAZ")
                .pattern("DCD")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.BRIMSTONE_DEBRIS.get())
                .define('A', ESItemRegistry.IGNIS_HELMET.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/supreme_calamitas/supreme_calamitas_helm"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SUPREME_CALAMITAS_CHESTPLATE.get())
                .pattern("DCD")
                .pattern("ZAZ")
                .pattern("ZZZ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.BRIMSTONE_DEBRIS.get())
                .define('A', ESItemRegistry.IGNIS_CHESTPLATE.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/supreme_calamitas/supreme_calamitas_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SUPREME_CALAMITAS_LEGGINGS.get())
                .pattern("DAD")
                .pattern("ZCZ")
                .pattern("Z Z")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.BRIMSTONE_DEBRIS.get())
                .define('A', ESItemRegistry.IGNIS_LEGGINGS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/supreme_calamitas/supreme_calamitas_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESItemRegistry.SUPREME_CALAMITAS_BOOTS.get())
                .pattern("DCD")
                .pattern("ZAZ")
                .pattern("   ")
                .define('Z', HnSItemRegistry.ZENALITE_INGOT.get())
                .define('C', HnSItemRegistry.CATALYST.get())
                .define('D', ESItemRegistry.BRIMSTONE_DEBRIS.get())
                .define('A', ESItemRegistry.IGNIS_BOOTS.get())
                .unlockedBy("has_zenalite_ingot", has(HnSItemRegistry.ZENALITE_INGOT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "crafting/armor/supreme_calamitas/supreme_calamitas_boots"));

        /*

        List<ItemLike> AERIALITE_SMELTABLES = List.of(
                ESItemRegistry.AERIALITE_FRAGMENT);

        oreSmelting(recipeOutput,
                AERIALITE_SMELTABLES,
                RecipeCategory.MISC,
                ESItemRegistry.AERIALITE_INGOT.get(),
                0.25f,
                200,
                "aerialite_ingot");

        oreBlasting(recipeOutput,
                AERIALITE_SMELTABLES,
                RecipeCategory.MISC,
                ESItemRegistry.AERIALITE_INGOT.get(),
                0.25f,
                100,
                "aerialite_ingot");

         */
    }
}
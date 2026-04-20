package net.hazen.elemental_synergies.Datagen;

import com.gametechbc.gtbcs_geomancy_plus.init.GGItems;
import com.snackpirate.aeromancy.item.AAItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.ender.ess_requiem.registries.GGItemRegistry;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazennstuff.HazenNStuff;
import net.hazen.hazennstuff.Registries.HnSItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
//import net.warphan.iss_magicfromtheeast.registries.MFTEItemRegistries;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ESRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ESRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


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

        /*

        //Soul Flame Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_HELMET.get()),
                        Ingredient.of(MFTEItemRegistries.BOTTLE_OF_SOULS.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_HELMET.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_CHESTPLATE.get()),
                        Ingredient.of(MFTEItemRegistries.BOTTLE_OF_SOULS.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_CHESTPLATE.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_LEGGINGS.get()),
                        Ingredient.of(MFTEItemRegistries.BOTTLE_OF_SOULS.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_LEGGINGS.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(HnSItemRegistry.FLAMING_TEMPLATE.get()),
                        Ingredient.of(HnSItemRegistry.BLAZEBORNE_BOOTS.get()),
                        Ingredient.of(MFTEItemRegistries.BOTTLE_OF_SOULS.get()),
                        RecipeCategory.COMBAT,
                        ESItemRegistry.SOUL_FLAME_BOOTS.get())
                .unlocks("has_flaming_template", has(HnSItemRegistry.FLAMING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "smithing/armor/soul_flame/soul_flame_boots"));

         */



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
    }
}
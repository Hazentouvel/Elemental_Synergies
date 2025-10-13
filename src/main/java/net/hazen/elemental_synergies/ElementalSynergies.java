package net.hazen.elemental_synergies;

import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import mod.azure.azurelib.rewrite.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererRegistry;
import net.acetheeldritchking.aces_spell_utils.entity.render.items.SheathCurioRenderer;
import net.acetheeldritchking.aces_spell_utils.items.curios.SheathCurioItem;
import net.hazen.elemental_synergies.items.armor.ESArmorMaterials;
import net.hazen.elemental_synergies.items.armor.Titan.Azurelib.TitanArmorRenderer;
import net.hazen.elemental_synergies.items.curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbookCurioItemRenderer;
import net.hazen.elemental_synergies.items.curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbookCurioRenderer;
import net.hazen.elemental_synergies.registries.ESCreativeModeTabs;
import net.hazen.elemental_synergies.registries.ESEffectRegistry;
import net.hazen.elemental_synergies.registries.ESEntityRegistry;
import net.hazen.elemental_synergies.registries.ESItemRegistry;
import net.hazen.hazennstuff.item.curios.Spellbooks.EnergizedCoreSpellbook.EnergizedCoreSpellbookCurioItemRenderer;
import net.hazen.hazennstuff.item.curios.Spellbooks.EnergizedCoreSpellbook.EnergizedCoreSpellbookCurioRenderer;
import net.hazen.hazennstuff.registries.HnSItems;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(ElementalSynergies.MOD_ID)
public class ElementalSynergies {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "elemental_synergies";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ElementalSynergies(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ESItemRegistry.register(modEventBus);
        ESCreativeModeTabs.register(modEventBus);
        ESArmorMaterials.register(modEventBus);


        ESEffectRegistry.register(modEventBus);


        ESEntityRegistry.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, path);
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

            ESItemRegistry.getESItems().stream().filter(item -> item.get() instanceof SpellBook).forEach((item) -> CuriosRendererRegistry.register(item.get(), SpellBookCurioRenderer::new));
            ESItemRegistry.getESItems().stream().filter(item -> item.get() instanceof SheathCurioItem).forEach((item) -> CuriosRendererRegistry.register(item.get(), SheathCurioRenderer::new));



            // Armor Rendering Registry

            //Arbitrium Robes
            AzArmorRendererRegistry.register(TitanArmorRenderer::new,
                    ESItemRegistry.TITAN_HELMET.get(),
                    ESItemRegistry.TITAN_CHESTPLATE.get(),
                    ESItemRegistry.TITAN_LEGGINGS.get(),
                    ESItemRegistry.TITAN_BOOTS.get());

            // Animation Registry
            AzIdentityRegistry.register(

                    //Armor
                    ESItemRegistry.TITAN_HELMET.get(),
                    ESItemRegistry.TITAN_CHESTPLATE.get(),
                    ESItemRegistry.TITAN_LEGGINGS.get(),
                    ESItemRegistry.TITAN_BOOTS.get()

            );

            /*
             *** Spellbooks
             */

            //Energized Core Spellbook
            AzArmorRendererRegistry.register(GrimoireOfCorruptionSpellbookCurioItemRenderer::new, ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get());
            CuriosRendererRegistry.register(
                    ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get(), GrimoireOfCorruptionSpellbookCurioRenderer::new
            );

            // Animation Registry
            AzIdentityRegistry.register(

                    ESItemRegistry.TITAN_HELMET.get(),
                    ESItemRegistry.TITAN_CHESTPLATE.get(),
                    ESItemRegistry.TITAN_LEGGINGS.get(),
                    ESItemRegistry.TITAN_BOOTS.get(),


                    ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get()

            );


        }
    }
}

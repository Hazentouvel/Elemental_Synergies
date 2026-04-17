package net.hazen.elemental_synergies;

import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import net.acetheeldritchking.aces_spell_utils.entity.render.items.SheathCurioRenderer;
import net.acetheeldritchking.aces_spell_utils.items.curios.SheathCurioItem;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Cataclysm.CataclysmArmorRenderer;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.SoulFlameArmorRenderer;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.TitanArmorRenderer;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.ExoMech.ExoMechArmorRenderer;
import net.hazen.elemental_synergies.Items.Curios.GauntletsOfIgnis.GauntletsOfIgnisCurioRenderer;
import net.hazen.elemental_synergies.Items.Curios.GauntletsOfIgnis.GauntletsOfIgnisItemRenderer;
import net.hazen.elemental_synergies.Items.Curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbookCurioItemRenderer;
import net.hazen.elemental_synergies.Items.Curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbookCurioRenderer;
import net.hazen.elemental_synergies.Registries.ESCreativeModeTabs;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
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

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Animation Registry
        AzIdentityRegistry.register(

                //Armor

                //ESItemRegistry.GABRIEL_ULTRAKILL_HELMET.get(),
                //ESItemRegistry.GABRIEL_ULTRAKILL_CHESTPLATE.get(),
                //ESItemRegistry.GABRIEL_ULTRAKILL_LEGGINGS.get(),
                //ESItemRegistry.GABRIEL_ULTRAKILL_BOOTS.get(),

                // Weapons


                // Staves

                // Curios

                ESItemRegistry.GAUNTLETS_OF_IGNIS.get()

        );
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

            //Titan Armor
            AzArmorRendererRegistry.register(TitanArmorRenderer::new,
                    ESItemRegistry.TITAN_HELMET.get(),
                    ESItemRegistry.TITAN_CHESTPLATE.get(),
                    ESItemRegistry.TITAN_LEGGINGS.get(),
                    ESItemRegistry.TITAN_BOOTS.get());

            //Soul Flame Armor
            AzArmorRendererRegistry.register(SoulFlameArmorRenderer::new,
                    ESItemRegistry.SOUL_FLAME_HELMET.get(),
                    ESItemRegistry.SOUL_FLAME_CHESTPLATE.get(),
                    ESItemRegistry.SOUL_FLAME_LEGGINGS.get(),
                    ESItemRegistry.SOUL_FLAME_BOOTS.get());

            // Exo Mech Armor
            AzArmorRendererRegistry.register(ExoMechArmorRenderer::new,
                    ESItemRegistry.EXO_MECH_HELMET.get(),
                    ESItemRegistry.EXO_MECH_CHESTPLATE.get(),
                    ESItemRegistry.EXO_MECH_LEGGINGS.get(),
                    ESItemRegistry.EXO_MECH_BOOTS.get());

            //Cataclysm Armor
            AzArmorRendererRegistry.register(CataclysmArmorRenderer::new,
                    ESItemRegistry.CATACLYSM_HELMET.get(),
                    ESItemRegistry.CATACLYSM_CHESTPLATE.get(),
                    ESItemRegistry.CATACLYSM_LEGGINGS.get(),
                    ESItemRegistry.CATACLYSM_BOOTS.get());

            /*
             *** Spellbooks
             */

            // Grimoire of Corruption
            AzArmorRendererRegistry.register(GrimoireOfCorruptionSpellbookCurioItemRenderer::new, ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get());
            CuriosRendererRegistry.register(
                    ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get(), GrimoireOfCorruptionSpellbookCurioRenderer::new
            );

            // Gauntlets of Ignis
            AzArmorRendererRegistry.register(GauntletsOfIgnisItemRenderer::new, ESItemRegistry.GAUNTLETS_OF_IGNIS.get());
            CuriosRendererRegistry.register(
                    ESItemRegistry.GAUNTLETS_OF_IGNIS.get(), GauntletsOfIgnisCurioRenderer::new
            );

            // Animation Registry
            AzIdentityRegistry.register(

                    ESItemRegistry.TITAN_HELMET.get(),
                    ESItemRegistry.TITAN_CHESTPLATE.get(),
                    ESItemRegistry.TITAN_LEGGINGS.get(),
                    ESItemRegistry.TITAN_BOOTS.get(),

                    ESItemRegistry.SOUL_FLAME_HELMET.get(),
                    ESItemRegistry.SOUL_FLAME_CHESTPLATE.get(),
                    ESItemRegistry.SOUL_FLAME_LEGGINGS.get(),
                    ESItemRegistry.SOUL_FLAME_BOOTS.get(),

                    ESItemRegistry.CATACLYSM_HELMET.get(),
                    ESItemRegistry.CATACLYSM_CHESTPLATE.get(),
                    ESItemRegistry.CATACLYSM_LEGGINGS.get(),
                    ESItemRegistry.CATACLYSM_BOOTS.get(),

                    ESItemRegistry.EXO_MECH_HELMET.get(),
                    ESItemRegistry.EXO_MECH_CHESTPLATE.get(),
                    ESItemRegistry.EXO_MECH_LEGGINGS.get(),
                    ESItemRegistry.EXO_MECH_BOOTS.get(),


                    ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get(),
                    ESItemRegistry.GAUNTLETS_OF_IGNIS.get()

            );


        }
    }
}

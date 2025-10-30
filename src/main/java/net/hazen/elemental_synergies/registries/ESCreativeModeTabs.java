package net.hazen.elemental_synergies.registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ESCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ElementalSynergies.MOD_ID);

    public static final Supplier<CreativeModeTab> SYNERGIZED_EQUIPMENT = CREATIVE_MODE_TAB.register("synergized_equipment",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ESItemRegistry.CLOUDMASTER_CROWN.get()))
                    .title(Component.translatable("creativetab.elemental_synergies.synergized_equipment"))
                    .displayItems((itemDisplayParameters, output) -> {


                        /*
                        *** Armor
                         */

                        //Cloudmaster Armor
                        output.accept(ESItemRegistry.CLOUDMASTER_CROWN.get());
                        output.accept(ESItemRegistry.CLOUDMASTER_HAT.get());
                        output.accept(ESItemRegistry.CLOUDMASTER_CHESTPLATE.get());
                        output.accept(ESItemRegistry.CLOUDMASTER_LEGGINGS.get());
                        output.accept(ESItemRegistry.CLOUDMASTER_BOOTS.get());

                        //Aerospec Armor
                        output.accept(ESItemRegistry.AEROSPEC_CROWN.get());
                        output.accept(ESItemRegistry.AEROSPEC_HELM.get());
                        output.accept(ESItemRegistry.AEROSPEC_HAT.get());
                        output.accept(ESItemRegistry.AEROSPEC_CHESTPLATE.get());
                        output.accept(ESItemRegistry.AEROSPEC_ROBES.get());
                        output.accept(ESItemRegistry.AEROSPEC_LEGGINGS.get());
                        output.accept(ESItemRegistry.AEROSPEC_BOOTS.get());

                        //Titan Armor
                        output.accept(ESItemRegistry.TITAN_HELMET.get());
                        output.accept(ESItemRegistry.TITAN_CHESTPLATE.get());
                        output.accept(ESItemRegistry.TITAN_LEGGINGS.get());
                        output.accept(ESItemRegistry.TITAN_BOOTS.get());
                        //output.accept(ESItemRegistry.GECKOLIB_TITAN_HELMET.get());
                        //output.accept(ESItemRegistry.GECKOLIB_TITAN_CHESTPLATE.get());
                        //output.accept(ESItemRegistry.GECKOLIB_TITAN_LEGGINGS.get());
                        //output.accept(ESItemRegistry.GECKOLIB_TITAN_BOOTS.get());

                        //Maledictus Armor
                        output.accept(ESItemRegistry.MALEDICTUS_HELMET.get());
                        output.accept(ESItemRegistry.MALEDICTUS_CHESTPLATE.get());
                        output.accept(ESItemRegistry.MALEDICTUS_LEGGINGS.get());
                        output.accept(ESItemRegistry.MALEDICTUS_BOOTS.get());

                        //Scylla Armor
                        output.accept(ESItemRegistry.SCYLLA_HELMET.get());
                        output.accept(ESItemRegistry.SCYLLA_CHESTPLATE.get());
                        output.accept(ESItemRegistry.SCYLLA_LEGGINGS.get());
                        output.accept(ESItemRegistry.SCYLLA_BOOTS.get());


                        /*
                        *** Weapons
                         */

                        /*
                        *** Tools
                         */

                        /*
                        *** Staves
                         */
                        //Arcane Mace
                        output.accept(ESItemRegistry.ARCANE_MACE.get());

                        /*
                         *** Curios
                         */

                        //Spellbooks


                        //Arcane Mace
                        output.accept(ESItemRegistry.GRIMOIRE_OF_CORRUPTION.get());



                    })
                    .build());

    public static final Supplier<CreativeModeTab> SYNERGIZED_MATERIALS = CREATIVE_MODE_TAB.register("synergized_materials",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ESItemRegistry.ZEPHYR_ESSENCE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "synergized_equipment"))
                    .title(Component.translatable("creativetab.hazennstuff.hazennstuff_equipment"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ESItemRegistry.ZEPHYR_ESSENCE.get());
                        output.accept(ESItemRegistry.AERIALITE_FRAGMENT.get());
                        output.accept(ESItemRegistry.AERIALITE_INGOT.get());
                        output.accept(ESItemRegistry.CLUSTER.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

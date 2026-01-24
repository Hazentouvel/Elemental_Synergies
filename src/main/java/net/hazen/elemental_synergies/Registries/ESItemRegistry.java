package net.hazen.elemental_synergies.Registries;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.AerospecArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Crown.AerospecCrownArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Helm.AerospecHelmArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Mage.AerospecMageArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.CloudmasterArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.Crown.CloudmasterCrownArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.Hat.CloudmasterHatArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis.IgnisArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Maledictus.MaledictusArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Scylla.ScyllaArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.TitanArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.Geckolib.GeckolibTitanArmorItem;
import net.hazen.elemental_synergies.Items.Curios.GauntletsOfIgnis.GauntletsOfIgnis;
import net.hazen.elemental_synergies.Items.Curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbook;
import net.hazen.elemental_synergies.Items.Staves.ArcaneMace.ArcaneMaceItem;
import net.hazen.elemental_synergies.Extensions.ESRarities;
import net.hazen.hazennstuff.Rarity.HnSRarities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class ESItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ElementalSynergies.MOD_ID);

    /*
    *** Materials
     */


    // Zephyr Essence
    public static final DeferredItem<Item> ZEPHYR_ESSENCE = ITEMS.register("zephyr_essence",
            () -> new Item(new Item
                    .Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant())

    );

    // Cluster
    public static final DeferredItem<Item> CLUSTER = ITEMS.register("cluster",
            () -> new Item(new Item
                    .Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant())

    );

    // Aerialite Fragment
    public static final DeferredItem<Item> AERIALITE_FRAGMENT = ITEMS.register("aerialite_fragment",
            () -> new Item(new Item
                    .Properties()
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .rarity(Rarity.EPIC)
                    .fireResistant())
    );

    // Aerialite Ingot
    public static final DeferredItem<Item> AERIALITE_INGOT = ITEMS.register("aerialite_ingot",
            () -> new Item(new Item
                    .Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant())

    );




    /*
    *** Weapons
     */

    /*
    *** Tools
     */

    /*
    *** Staves
     */

    // Arcane Mace
    public static final DeferredHolder<Item, Item> ARCANE_MACE = ITEMS.register("arcane_mace", ArcaneMaceItem::new);




    /*
    *** Curios
     */

    // Gauntlets of Ignis
    public static final DeferredItem <GauntletsOfIgnis> GAUNLETS_OF_IGNIS = ITEMS.register("gauntlets_of_ignis", GauntletsOfIgnis::new);


    //Grimoire of Corruption
    public static final DeferredItem <GrimoireOfCorruptionSpellbook> GRIMOIRE_OF_CORRUPTION = ITEMS.register("grimoire_of_corruption", GrimoireOfCorruptionSpellbook::new);





    /***
     * Geckolib
     */

    //Cloudmaster Armor Set
    public static final DeferredHolder<Item, Item> CLOUDMASTER_CROWN = ITEMS.register("cloudmaster_crown", () -> new CloudmasterCrownArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_HAT = ITEMS.register("cloudmaster_hat", () -> new CloudmasterHatArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_CHESTPLATE = ITEMS.register("cloudmaster_chestplate", () -> new CloudmasterArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_LEGGINGS = ITEMS.register("cloudmaster_leggings", () -> new CloudmasterArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> CLOUDMASTER_BOOTS = ITEMS.register("cloudmaster_boots", () -> new CloudmasterArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));


    //Aerospec Armor

    public static final DeferredHolder<Item, Item> AEROSPEC_CROWN = ITEMS.register("aerospec_crown", () -> new AerospecCrownArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_HELM = ITEMS.register("aerospec_helm", () -> new AerospecHelmArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_HAT = ITEMS.register("aerospec_hat", () -> new AerospecMageArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_CHESTPLATE = ITEMS.register("aerospec_chestplate", () -> new AerospecArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_ROBES = ITEMS.register("aerospec_robes", () -> new AerospecMageArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_LEGGINGS = ITEMS.register("aerospec_leggings", () -> new AerospecArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> AEROSPEC_BOOTS = ITEMS.register("aerospec_boots", () -> new AerospecArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
    ));



    // Geckolib Titan Armor Set
    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_HELMET = ITEMS.register("geckolib_titan_helmet", () -> new GeckolibTitanArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_CHESTPLATE = ITEMS.register("geckolib_titan_chestplate", () -> new GeckolibTitanArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_LEGGINGS = ITEMS.register("geckolib_titan_leggings", () -> new GeckolibTitanArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_BOOTS = ITEMS.register("geckolib_titan_boots", () -> new GeckolibTitanArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    // Maledictus Armor Set
    public static final DeferredHolder<Item, Item> MALEDICTUS_HELMET = ITEMS.register("maledictus_helmet", () -> new MaledictusArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> MALEDICTUS_CHESTPLATE = ITEMS.register("maledictus_chestplate", () -> new MaledictusArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> MALEDICTUS_LEGGINGS = ITEMS.register("maledictus_leggings", () -> new MaledictusArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> MALEDICTUS_BOOTS = ITEMS.register("maledictus_boots", () -> new MaledictusArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    // Scylla Armor Set
    public static final DeferredHolder<Item, Item> SCYLLA_HELMET = ITEMS.register("scylla_helmet", () -> new ScyllaArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> SCYLLA_CHESTPLATE = ITEMS.register("scylla_chestplate", () -> new ScyllaArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> SCYLLA_LEGGINGS = ITEMS.register("scylla_leggings", () -> new ScyllaArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> SCYLLA_BOOTS = ITEMS.register("scylla_boots", () -> new ScyllaArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    // Ignis Armor Set
    public static final DeferredHolder<Item, Item> IGNIS_HELMET = ITEMS.register("ignis_helmet", () -> new IgnisArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> IGNIS_CHESTPLATE = ITEMS.register("ignis_chestplate", () -> new IgnisArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> IGNIS_LEGGINGS = ITEMS.register("ignis_leggings", () -> new IgnisArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> IGNIS_BOOTS = ITEMS.register("ignis_boots", () -> new IgnisArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));



    /*
    *** Azurelib
     */



    //Titan Armor Set
    public static final DeferredHolder<Item, Item> TITAN_HELMET = ITEMS.register("titan_helmet", () -> new TitanArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> TITAN_CHESTPLATE = ITEMS.register("titan_chestplate", () -> new TitanArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));

    public static final DeferredHolder<Item, Item> TITAN_LEGGINGS = ITEMS.register("titan_leggings", () -> new TitanArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));


    public static final DeferredHolder<Item, Item> TITAN_BOOTS = ITEMS.register("titan_boots", () -> new TitanArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
    ));



    public static Collection<DeferredHolder<Item, ? extends Item>> getESItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

package net.hazen.elemental_synergies.Registries;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.ESUtilities.ESRarities;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.AzureLib.ProvidenceArmorItem;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.GeckolibProvidenceArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis.IgnisArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Maledictus.MaledictusArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Onyx.OnyxArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Scylla.ScyllaArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.CloudmasterArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.Crown.CloudmasterCrownArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster.Hat.CloudmasterHatArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Cataclysm.CataclysmArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Cataclysm.Geckolib.GeckolibCataclysmArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.GeckoLib.GeckolibSoulFlameArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.SoulFlameArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.Geckolib.GeckolibTitanArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.TitanArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.AerospecArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Crown.AerospecCrownArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Helm.AerospecHelmArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Mage.AerospecMageArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.ExoMech.ExoMechArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.ExoMech.Geckolib.GeckolibExoMechArmorItem;
import net.hazen.elemental_synergies.Items.Armor.SchoolTier.Neru.AkitaNeruArmorItem;
import net.hazen.elemental_synergies.Items.Armor.SchoolTier.ProjectSekai.ProjectSekaiArmorItem;
import net.hazen.elemental_synergies.Items.Armor.SchoolTier.RottenGirl.RottenGirlArmorItem;
import net.hazen.elemental_synergies.Items.Armor.SchoolTier.SynthesizerV.SynthV2ArmorItem;
import net.hazen.elemental_synergies.Items.Armor.SchoolTier.Utau.UtauArmorItem;
import net.hazen.elemental_synergies.Items.Curios.GauntletsOfIgnis.GauntletsOfIgnis;
import net.hazen.elemental_synergies.Items.Curios.Spellbooks.GrimoireOfCorruption.GrimoireOfCorruptionSpellbook;
import net.hazen.elemental_synergies.Items.Staves.ArcaneMace.ArcaneMaceItem;
import net.hazen.elemental_synergies.Items.Weapons.Generic.Excelsior.Excelsior;
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

    // Arcane Mace
    public static final DeferredHolder<Item, Item> EXCELSIOR = ITEMS.register("excelsior", Excelsior::new);


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
    public static final DeferredItem <GauntletsOfIgnis> GAUNTLETS_OF_IGNIS = ITEMS.register("gauntlets_of_ignis", GauntletsOfIgnis::new);


    //Grimoire of Corruption
    public static final DeferredItem <GrimoireOfCorruptionSpellbook> GRIMOIRE_OF_CORRUPTION = ITEMS.register("grimoire_of_corruption", GrimoireOfCorruptionSpellbook::new);





    /*
     * Geckolib
     */

    //Cloudmaster Armor Set
    public static final DeferredHolder<Item, Item> CLOUDMASTER_CROWN = ITEMS.register("cloudmaster_crown", () -> new CloudmasterCrownArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(86))
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_HAT = ITEMS.register("cloudmaster_hat", () -> new CloudmasterHatArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(86))
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_CHESTPLATE = ITEMS.register("cloudmaster_chestplate", () -> new CloudmasterArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(86))
    ));

    public static final DeferredHolder<Item, Item> CLOUDMASTER_LEGGINGS = ITEMS.register("cloudmaster_leggings", () -> new CloudmasterArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(86))
    ));


    public static final DeferredHolder<Item, Item> CLOUDMASTER_BOOTS = ITEMS.register("cloudmaster_boots", () -> new CloudmasterArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(86))
    ));


    //Aerospec Armor

    public static final DeferredHolder<Item, Item> AEROSPEC_CROWN = ITEMS.register("aerospec_crown", () -> new AerospecCrownArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_HELM = ITEMS.register("aerospec_helm", () -> new AerospecHelmArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_HAT = ITEMS.register("aerospec_hat", () -> new AerospecMageArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_CHESTPLATE = ITEMS.register("aerospec_chestplate", () -> new AerospecArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_ROBES = ITEMS.register("aerospec_robes", () -> new AerospecMageArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> AEROSPEC_LEGGINGS = ITEMS.register("aerospec_leggings", () -> new AerospecArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> AEROSPEC_BOOTS = ITEMS.register("aerospec_boots", () -> new AerospecArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(ESRarities.AEROMANCY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    // Maledictus Armor Set
    public static final DeferredHolder<Item, Item> MALEDICTUS_HELMET = ITEMS.register("maledictus_helmet", () -> new MaledictusArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> MALEDICTUS_CHESTPLATE = ITEMS.register("maledictus_chestplate", () -> new MaledictusArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> MALEDICTUS_LEGGINGS = ITEMS.register("maledictus_leggings", () -> new MaledictusArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));


    public static final DeferredHolder<Item, Item> MALEDICTUS_BOOTS = ITEMS.register("maledictus_boots", () -> new MaledictusArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));


    // Scylla Armor Set
    public static final DeferredHolder<Item, Item> SCYLLA_HELMET = ITEMS.register("scylla_helmet", () -> new ScyllaArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> SCYLLA_CHESTPLATE = ITEMS.register("scylla_chestplate", () -> new ScyllaArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> SCYLLA_LEGGINGS = ITEMS.register("scylla_leggings", () -> new ScyllaArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));


    public static final DeferredHolder<Item, Item> SCYLLA_BOOTS = ITEMS.register("scylla_boots", () -> new ScyllaArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    // Ignis Armor Set
    public static final DeferredHolder<Item, Item> IGNIS_HELMET = ITEMS.register("ignis_helmet", () -> new IgnisArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> IGNIS_CHESTPLATE = ITEMS.register("ignis_chestplate", () -> new IgnisArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> IGNIS_LEGGINGS = ITEMS.register("ignis_leggings", () -> new IgnisArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));


    public static final DeferredHolder<Item, Item> IGNIS_BOOTS = ITEMS.register("ignis_boots", () -> new IgnisArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    // Onyx Armor Set
    public static final DeferredHolder<Item, Item> ONYX_HELMET = ITEMS.register("onyx_helmet", () -> new OnyxArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.ENDER_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> ONYX_CHESTPLATE = ITEMS.register("onyx_chestplate", () -> new OnyxArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.ENDER_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));

    public static final DeferredHolder<Item, Item> ONYX_LEGGINGS = ITEMS.register("onyx_leggings", () -> new OnyxArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.ENDER_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));


    public static final DeferredHolder<Item, Item> ONYX_BOOTS = ITEMS.register("onyx_boots", () -> new OnyxArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.ENDER_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(96))
    ));



    //Titan Armor Set
    public static final DeferredHolder<Item, Item> TITAN_HELMET = ITEMS.register("titan_helmet", () -> new TitanArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> TITAN_CHESTPLATE = ITEMS.register("titan_chestplate", () -> new TitanArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> TITAN_LEGGINGS = ITEMS.register("titan_leggings", () -> new TitanArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> TITAN_BOOTS = ITEMS.register("titan_boots", () -> new TitanArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    //*** Geckolib ***


    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_HELMET = ITEMS.register("geckolib_titan_helmet", () -> new GeckolibTitanArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_CHESTPLATE = ITEMS.register("geckolib_titan_chestplate", () -> new GeckolibTitanArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_LEGGINGS = ITEMS.register("geckolib_titan_leggings", () -> new GeckolibTitanArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> GECKOLIB_TITAN_BOOTS = ITEMS.register("geckolib_titan_boots", () -> new GeckolibTitanArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    // Cataclysm Armor Set
    public static final DeferredHolder<Item, Item> CATACLYSM_HELMET = ITEMS.register("cataclysm_helmet", () -> new CataclysmArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> CATACLYSM_CHESTPLATE = ITEMS.register("cataclysm_chestplate", () -> new CataclysmArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> CATACLYSM_LEGGINGS = ITEMS.register("cataclysm_leggings", () -> new CataclysmArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> CATACLYSM_BOOTS = ITEMS.register("cataclysm_boots", () -> new CataclysmArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    //*** Geckolib ***


    public static final DeferredHolder<Item, Item> GECKOLIB_CATACLYSM_HELMET = ITEMS.register("geckolib_cataclysm_helmet", () -> new GeckolibCataclysmArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_CATACLYSM_CHESTPLATE = ITEMS.register("geckolib_cataclysm_chestplate", () -> new GeckolibCataclysmArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_CATACLYSM_LEGGINGS = ITEMS.register("geckolib_cataclysm_leggings", () -> new GeckolibCataclysmArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> GECKOLIB_CATACLYSM_BOOTS = ITEMS.register("geckolib_cataclysm_boots", () -> new GeckolibCataclysmArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    // Cataclysm Armor Set
    public static final DeferredHolder<Item, Item> EXO_MECH_HELMET = ITEMS.register("exo_mech_helmet", () -> new ExoMechArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> EXO_MECH_CHESTPLATE = ITEMS.register("exo_mech_chestplate", () -> new ExoMechArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> EXO_MECH_LEGGINGS = ITEMS.register("exo_mech_leggings", () -> new ExoMechArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> EXO_MECH_BOOTS = ITEMS.register("exo_mech_boots", () -> new ExoMechArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    //*** Geckolib ***


    public static final DeferredHolder<Item, Item> GECKOLIB_EXO_MECH_HELMET = ITEMS.register("geckolib_exo_mech_helmet", () -> new GeckolibExoMechArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_EXO_MECH_CHESTPLATE = ITEMS.register("geckolib_exo_mech_chestplate", () -> new GeckolibExoMechArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> GECKOLIB_EXO_MECH_LEGGINGS = ITEMS.register("geckolib_exo_mech_leggings", () -> new GeckolibExoMechArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    public static final DeferredHolder<Item, Item> GECKOLIB_EXO_MECH_BOOTS = ITEMS.register("geckolib_exo_mech_boots", () -> new GeckolibExoMechArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));


    //Soul Flame

    public static final DeferredHolder<Item, Item> SOUL_FLAME_HELMET = ITEMS.register("soul_flame_helmet", () -> new SoulFlameArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> SOUL_FLAME_CHESTPLATE = ITEMS.register("soul_flame_chestplate", () -> new SoulFlameArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> SOUL_FLAME_LEGGINGS = ITEMS.register("soul_flame_leggings", () -> new SoulFlameArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));

    public static final DeferredHolder<Item, Item> SOUL_FLAME_BOOTS = ITEMS.register("soul_flame_boots", () -> new SoulFlameArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));



    //*** GeckoLib ***



    public static final DeferredHolder<Item, Item> GECKOLIB_SOUL_FLAME_HELMET = ITEMS.register("geckolib_soul_flame_helmet", () -> new GeckolibSoulFlameArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> GECKOLIB_SOUL_FLAME_CHESTPLATE = ITEMS.register("geckolib_soul_flame_chestplate", () -> new GeckolibSoulFlameArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> GECKOLIB_SOUL_FLAME_LEGGINGS = ITEMS.register("geckolib_soul_flame_leggings", () -> new GeckolibSoulFlameArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> GECKOLIB_SOUL_FLAME_BOOTS = ITEMS.register("geckolib_soul_flame_boots", () -> new GeckolibSoulFlameArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.FIRE_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    /*
    *** Ascended
     */

    // Onyx Armor Set
    public static final DeferredHolder<Item, Item> PROVIDENCE_HELMET = ITEMS.register("providence_helmet", () -> new ProvidenceArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.HOLY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(128))
    ));

    public static final DeferredHolder<Item, Item> PROVIDENCE_CHESTPLATE = ITEMS.register("providence_chestplate", () -> new ProvidenceArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.HOLY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(128))
    ));

    public static final DeferredHolder<Item, Item> PROVIDENCE_LEGGINGS = ITEMS.register("providence_leggings", () -> new ProvidenceArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.HOLY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(128))
    ));


    public static final DeferredHolder<Item, Item> PROVIDENCE_BOOTS = ITEMS.register("providence_boots", () -> new ProvidenceArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HnSRarities.HOLY_RARITY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(128))
    ));



    /*
    *** Cosmetics
     */

    //Project Sekai [Marked for Deletion]

    public static final DeferredHolder<Item, Item> PROJECT_SEKAI_HELMET = ITEMS.register("project_sekai_helmet", () -> new ProjectSekaiArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> PROJECT_SEKAI_CHESTPLATE = ITEMS.register("project_sekai_chestplate", () -> new ProjectSekaiArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> PROJECT_SEKAI_LEGGINGS = ITEMS.register("project_sekai_leggings", () -> new ProjectSekaiArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> PROJECT_SEKAI_BOOTS = ITEMS.register("project_sekai_boots", () -> new ProjectSekaiArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    //Rotten Girl [Marked for Deletion]

    public static final DeferredHolder<Item, Item> ROTTEN_GIRL_HELMET = ITEMS.register("rotten_girl_helmet", () -> new RottenGirlArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> ROTTEN_GIRL_CHESTPLATE = ITEMS.register("rotten_girl_chestplate", () -> new RottenGirlArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> ROTTEN_GIRL_LEGGINGS = ITEMS.register("rotten_girl_leggings", () -> new RottenGirlArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> ROTTEN_GIRL_BOOTS = ITEMS.register("rotten_girl_boots", () -> new RottenGirlArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));

    //Synthesizer V [Marked for Deletion]

    public static final DeferredHolder<Item, Item> SYNTHESIZER_V_HELMET = ITEMS.register("synthv2_helmet", () -> new SynthV2ArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> SYNTHESIZER_V_CHESTPLATE = ITEMS.register("synthv2_chestplate", () -> new SynthV2ArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> SYNTHESIZER_V_LEGGINGS = ITEMS.register("synthv2_leggings", () -> new SynthV2ArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> SYNTHESIZER_V_BOOTS = ITEMS.register("synthv2_boots", () -> new SynthV2ArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    //Utau [Marked for Deletion]

    public static final DeferredHolder<Item, Item> UTAU_HELMET = ITEMS.register("utau_helmet", () -> new UtauArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> UTAU_CHESTPLATE = ITEMS.register("utau_chestplate", () -> new UtauArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> UTAU_LEGGINGS = ITEMS.register("utau_leggings", () -> new UtauArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> UTAU_BOOTS = ITEMS.register("utau_boots", () -> new UtauArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
    ));

    //Neru [Marked for Deletion]

    public static final DeferredHolder<Item, Item> NERU_HELMET = ITEMS.register("neru_helmet", () -> new AkitaNeruArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .fireResistant()
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> NERU_CHESTPLATE = ITEMS.register("neru_chestplate", () -> new AkitaNeruArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .fireResistant()
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> NERU_LEGGINGS = ITEMS.register("neru_leggings", () -> new AkitaNeruArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .fireResistant()
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> NERU_BOOTS = ITEMS.register("neru_boots", () -> new AkitaNeruArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))
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

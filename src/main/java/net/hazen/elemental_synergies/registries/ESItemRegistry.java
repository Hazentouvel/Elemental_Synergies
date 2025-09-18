package net.hazen.elemental_synergies.registries;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.items.armor.Cloudsage.CloudmasterArmorItem;
import net.hazen.elemental_synergies.items.armor.Cloudsage.Crown.CloudmasterCrownArmorItem;
import net.hazen.elemental_synergies.items.armor.Cloudsage.Hat.CloudmasterHatArmorItem;
import net.hazen.elemental_synergies.rarity.AeromancyRarity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class ESItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ElementalSynergies.MOD_ID);

    /***
     * Geckolib
     */

    //Cloudmaster Armor Set
    public static final DeferredHolder<Item, Item> CLOUDMASTER_CROWN = ITEMS.register("cloudmaster_crown", () -> new CloudmasterCrownArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(AeromancyRarity.AEROMANCY_RARITY_PROXY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.HELMET.getDurability(64))));
    public static final DeferredHolder<Item, Item> CLOUDMASTER_HAT = ITEMS.register("cloudmaster_hat", () -> new CloudmasterHatArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(AeromancyRarity.AEROMANCY_RARITY_PROXY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.HELMET.getDurability(64))));
    public static final DeferredHolder<Item, Item> CLOUDMASTER_CHESTPLATE = ITEMS.register("cloudmaster_chestplate", () -> new CloudmasterArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(AeromancyRarity.AEROMANCY_RARITY_PROXY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))));
    public static final DeferredHolder<Item, Item> CLOUDMASTER_LEGGINGS = ITEMS.register("cloudmaster_leggings", () -> new CloudmasterArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(AeromancyRarity.AEROMANCY_RARITY_PROXY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))));
    public static final DeferredHolder<Item, Item> CLOUDMASTER_BOOTS = ITEMS.register("cloudmaster_boots", () -> new CloudmasterArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(AeromancyRarity.AEROMANCY_RARITY_PROXY.getValue())
            .fireResistant()
            .durability(ArmorItem.Type.BOOTS.getDurability(64))));


    public static Collection<DeferredHolder<Item, ? extends Item>> getHnSItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

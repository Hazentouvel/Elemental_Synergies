package net.hazen.elemental_synergies.Extensions.ESExtras.MagicMace.CastingItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ESMaceStaffItem extends ESMaceCastingItem {
    public ESMaceStaffItem(Item.Properties properties) {
        super(properties);
    }

    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    public int getEnchantmentValue(ItemStack stack) {
        return 20;
    }

    public boolean hasCustomRendering() {
        return false;
    }
}

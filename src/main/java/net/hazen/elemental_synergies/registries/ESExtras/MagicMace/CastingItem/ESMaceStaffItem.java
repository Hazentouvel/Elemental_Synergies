package net.hazen.elemental_synergies.registries.ESExtras.MagicMace.CastingItem;

import io.redspace.ironsspellbooks.item.CastingItem;
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

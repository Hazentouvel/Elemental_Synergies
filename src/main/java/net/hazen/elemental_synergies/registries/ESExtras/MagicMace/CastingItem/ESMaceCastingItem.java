package net.hazen.elemental_synergies.registries.ESExtras.MagicMace.CastingItem;

import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;

public class ESMaceCastingItem extends MaceItem {
    public ESMaceCastingItem(Item.Properties pProperties) {
        super(pProperties.component(ComponentRegistry.CASTING_IMPLEMENT, Unit.INSTANCE));
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }
}


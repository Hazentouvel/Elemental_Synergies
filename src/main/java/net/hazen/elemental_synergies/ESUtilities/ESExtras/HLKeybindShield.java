package net.hazen.elemental_synergies.ESUtilities.ESExtras;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface HLKeybindShield {

    void onKeyPacket(Player player, ItemStack stack, int type);

}
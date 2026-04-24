package net.hazen.elemental_synergies.Spells.AbstractSpells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import static net.acetheeldritchking.aces_spell_utils.utils.ASUtils.isValidUnlockItemInInventory;

public abstract class ProvidenceSpells extends AbstractSpell {

    @Override
    public Component getLockedMessage() {
        return Component.translatable("ui.hazennstuff.armor_resonation");
    }

    @Override
    public boolean allowLooting() {
        return false;
    }

    @Override
    public boolean canBeCraftedBy(Player player) {
        Item[] validItems = {
                ESItemRegistry.PROVIDENCE_HELMET.get(),
                ESItemRegistry.PROVIDENCE_CHESTPLATE.get(),
                ESItemRegistry.PROVIDENCE_LEGGINGS.get(),
                ESItemRegistry.PROVIDENCE_BOOTS.get()
        };
        for (Item item : validItems) {
            if (isValidUnlockItemInInventory(item, player)) {
                return true;
            }
        }
        return false;
    }
}

package net.hazen.elemental_synergies.Spells.AbstractSpells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import static net.acetheeldritchking.aces_spell_utils.utils.ASUtils.isValidUnlockItemInInventory;

public abstract class BrimstoneSpells extends AbstractSpell {

    @Override
    public boolean allowLooting() {
        return false;
    }
}

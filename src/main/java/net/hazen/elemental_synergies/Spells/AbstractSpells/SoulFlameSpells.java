package net.hazen.elemental_synergies.Spells.AbstractSpells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

public abstract class SoulFlameSpells extends AbstractSpell {

    @Override
    public boolean allowLooting() {
        return false;
    }
}

package net.hazen.elemental_synergies.Spells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Spells.Schools.Fire.Providence.HolyBlastSpell;
import net.hazen.elemental_synergies.Spells.Schools.Fire.SoulFire.SoulflameBoltSpell;
import net.hazen.elemental_synergies.Spells.Schools.Fire.SupremeCalamitas.BrimflameBoltSpell;
import net.hazen.elemental_synergies.Spells.Schools.Fire.SupremeCalamitas.BrimstoneHellblastSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class ESSpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, ElementalSynergies.MOD_ID);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    /*
    *** Fire
     */


    //Soulflame Bolt
    public static final Supplier<AbstractSpell> SOULFLAME_BOLT = registerSpell(new SoulflameBoltSpell());

    //Brimstone Hellblast
    public static final Supplier<AbstractSpell> BRIMSTONE_HELLBLAST = registerSpell(new BrimstoneHellblastSpell());
    //Brimflame Bolt
    public static final Supplier<AbstractSpell> BRIMFLAME_BOLT = registerSpell(new BrimflameBoltSpell());

    //Holy Blast
    public static final Supplier<AbstractSpell> HOLY_BLAST = registerSpell(new HolyBlastSpell());


    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }

}
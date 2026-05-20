package net.hazen.elemental_synergies.Registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ElementalSynergies.MOD_ID);



    /*
    *** Providence
     */

    // Night State Activate
    public static DeferredHolder<SoundEvent, SoundEvent> NIGHT_STATE_ACTIVATE = registerSoundEvent("night_state_activate");

    /*
    *** Maledictus
     */

    // Maledictus Armor Revive
    public static DeferredHolder<SoundEvent, SoundEvent> MALEDICTUS_ARMOR_REVIVE = registerSoundEvent("maledictus_armor_revive");

    public static DeferredHolder<SoundEvent, SoundEvent> DODGE = registerSoundEvent("dodge");

    /*
    *** Scylla
     */

    public static DeferredHolder<SoundEvent, SoundEvent> WATER_SPEAR_CAST = registerSoundEvent("water_spear_cast");
    public static DeferredHolder<SoundEvent, SoundEvent> WATER_SPEAR_FIRE = registerSoundEvent("water_spear_fire");
    public static DeferredHolder<SoundEvent, SoundEvent> LIGHTNING_SPEAR_CAST = registerSoundEvent("lightning_spear_cast");
    public static DeferredHolder<SoundEvent, SoundEvent> LIGHTNING_SPEAR_FIRE = registerSoundEvent("lightning_spear_fire");

    //
    public static DeferredHolder<SoundEvent, SoundEvent> HOLY_BLAST_IMPACT = registerSoundEvent("holy_blast_impact");
    public static DeferredHolder<SoundEvent, SoundEvent> HOLY_BLAST_CAST = registerSoundEvent("holy_blast_cast");


    /*
    *** Calamitas
     */

    // Brimflame Bolt
    public static DeferredHolder<SoundEvent, SoundEvent> BRIMFLAME_BOLT_IMPACT = registerSoundEvent("brimflame_bolt_impact");
    public static DeferredHolder<SoundEvent, SoundEvent> BRIMFLAME_BOLT_CAST = registerSoundEvent("brimflame_bolt_cast");


    /*
    *** Weapons
     */

    public static DeferredHolder<SoundEvent, SoundEvent> VIOLENCE_IMPACT = registerSoundEvent("violence_impact");


    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent
                (ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, name)));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

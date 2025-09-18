package net.hazen.elemental_synergies.setup;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.entity.mobs.wizards.Good.CloudmasterSage.CloudmasterSageEntity;
import net.hazen.elemental_synergies.registries.ESEntityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ElementalSynergies.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ESEntityRegistry.CLOUDMASTER_SAGE.get(), CloudmasterSageEntity.prepareAttributes().build());
    }
}


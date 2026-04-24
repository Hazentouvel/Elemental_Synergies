package net.hazen.elemental_synergies.Setup;

import net.hazen.elemental_synergies.Entities.Mobs.Wizards.CloudmasterSageEntity;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ESCommonSetup {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ESEntityRegistry.CLOUDMASTER_SAGE.get(), CloudmasterSageEntity.prepareAttributes().build());
    }
}


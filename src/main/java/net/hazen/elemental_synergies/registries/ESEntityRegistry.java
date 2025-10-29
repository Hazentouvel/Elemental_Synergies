package net.hazen.elemental_synergies.registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Entity.mobs.wizards.Good.CloudmasterSage.CloudmasterSageEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(Registries.ENTITY_TYPE, ElementalSynergies.MOD_ID);

    /*
     *** Entities
     */

    //Electromancer
    public static final DeferredHolder<EntityType<?>, EntityType<CloudmasterSageEntity>> CLOUDMASTER_SAGE =
            ENTITIES.register("cloudmaster_sage", () -> EntityType.Builder.of(CloudmasterSageEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "cloudmaster_sage").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}

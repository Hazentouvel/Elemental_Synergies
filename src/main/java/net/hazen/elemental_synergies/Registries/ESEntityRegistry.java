package net.hazen.elemental_synergies.Registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Entities.Spells.Fire.BrimstoneHellblast.BrimstoneHellblast;
import net.hazen.elemental_synergies.Entities.Mobs.wizards.Good.CloudmasterSage.CloudmasterSageEntity;
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
     *** Spells
     */

    // Brimstone Hellblast
    public static final DeferredHolder<EntityType<?>, EntityType<BrimstoneHellblast>> BRIMSTONE_HELLBLAST =
            ENTITIES.register("brimstone_hellblast", () -> EntityType.Builder.<BrimstoneHellblast>of(BrimstoneHellblast::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "brimstone_hellblast").toString())
            );

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

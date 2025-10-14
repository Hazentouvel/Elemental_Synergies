package net.hazen.elemental_synergies.setup;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.entity.mobs.wizards.Good.CloudmasterSage.CloudmasterSageRenderer;
import net.hazen.elemental_synergies.registries.ESEntityRegistry;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientSetup {


    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {

        /*
        *** Spells
         */


        /*
        *** Entities
         */

        event.registerEntityRenderer(ESEntityRegistry.CLOUDMASTER_SAGE.get(), CloudmasterSageRenderer::new);



        /*
        *** Particles
         */
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {

    }

}
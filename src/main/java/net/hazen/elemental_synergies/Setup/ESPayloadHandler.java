package net.hazen.elemental_synergies.Setup;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Particle.HolyFlameExplosionParticlesPacket;
import net.hazen.elemental_synergies.Particle.HolyNightFlameExplosionParticlesPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber

public class ESPayloadHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(ElementalSynergies.MOD_ID).versioned("1.0.0").optional();

        payloadRegistrar.playToClient(HolyFlameExplosionParticlesPacket.TYPE, HolyFlameExplosionParticlesPacket.STREAM_CODEC, HolyFlameExplosionParticlesPacket::handle);
        payloadRegistrar.playToClient(HolyNightFlameExplosionParticlesPacket.TYPE, HolyNightFlameExplosionParticlesPacket.STREAM_CODEC, HolyNightFlameExplosionParticlesPacket::handle);

    }
}

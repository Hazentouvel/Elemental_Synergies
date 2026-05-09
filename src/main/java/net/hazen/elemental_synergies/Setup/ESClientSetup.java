package net.hazen.elemental_synergies.Setup;

import net.hazen.elemental_synergies.ESUtilities.Tooltips.ESSpellTooltip;
import net.hazen.elemental_synergies.Entities.Mobs.Wizards.CloudmasterSageRenderer;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SoulFlame.SoulflameBolt.SoulflameBoltRenderer;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SupremeCalamitas.BrimflameBolt.BrimflameBoltRenderer;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SupremeCalamitas.BrimstoneHellblast.BrimstoneHellblastRenderer;
import net.hazen.elemental_synergies.Entities.Spells.Fire.Providence.HolyBlast.HolyBlastRenderer;
import net.hazen.elemental_synergies.Entities.Spells.Fire.Providence.HolyBlast.HolyFire.HolyFlameRenderer;
import net.hazen.elemental_synergies.Entities.Weapons.Violence.ViolenceRenderer;
import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESParticleRegistry;
import net.hazen.hazennstuff.Particle.HnSGenericParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ESClientSetup {


    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {

        /*
        *** Spells
         */


        //Soulflame
        event.registerEntityRenderer(ESEntityRegistry.SOULFLAME_BOLT.get(), SoulflameBoltRenderer::new);


        //Supreme Calamitas
        event.registerEntityRenderer(ESEntityRegistry.BRIMSTONE_HELLBLAST.get(), BrimstoneHellblastRenderer::new);
        event.registerEntityRenderer(ESEntityRegistry.BRIMFLAME_BOLT.get(), BrimflameBoltRenderer::new);

        //Providence
        event.registerEntityRenderer(ESEntityRegistry.HOLY_BLAST.get(), HolyBlastRenderer::new);
        event.registerEntityRenderer(ESEntityRegistry.HOLY_FLAME.get(), HolyFlameRenderer::new);


        /*
        *** Weapons
         */

        event.registerEntityRenderer(ESEntityRegistry.VIOLENCE.get(), ViolenceRenderer::new);

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
        event.registerSpriteSet(ESParticleRegistry.HOLY_EMBER_PARTICLE.get(), HnSGenericParticle.Provider::new);
        event.registerSpriteSet(ESParticleRegistry.HOLY_NIGHT_EMBER_PARTICLE.get(), HnSGenericParticle.Provider::new);
        event.registerSpriteSet(ESParticleRegistry.BRIMSTONE_EMBER_PARTICLE.get(), HnSGenericParticle.Provider::new);
    }

    @SubscribeEvent
    public static void onRegisterTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ESSpellTooltip.ESSpellTooltipData.class, ESSpellTooltip::new);
    }

}
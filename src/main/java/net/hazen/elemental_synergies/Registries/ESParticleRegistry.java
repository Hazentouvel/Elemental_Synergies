package net.hazen.elemental_synergies.Registries;

import com.mojang.serialization.MapCodec;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HazenNStuff;
import net.hazen.hazennstuff.Particle.LeafParticle.LeafParticleOptions;
import net.hazen.hazennstuff.Particle.SlashParticles.Spells.IonicSLash.IonicSlashOptions;
import net.hazen.hazennstuff.Particle.SlashParticles.Spells.NatureSlash.NatureSlashOptions;
import net.hazen.hazennstuff.Particle.SlashParticles.Spells.NightsEdgeStrike.NightsEdgeStrikeOptions;
import net.hazen.hazennstuff.Particle.SlashParticles.Spells.ScorchingSlash.ScorchingSlashOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ESParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ElementalSynergies.MOD_ID);

    // Brimstone Ember Particle
    public static final Supplier<SimpleParticleType> BRIMSTONE_EMBER_PARTICLE = PARTICLE_TYPES.register("brimstone_ember_particle",
            () -> new SimpleParticleType(false));

    // Holy Ember Particle
    public static final Supplier<SimpleParticleType> HOLY_EMBER_PARTICLE = PARTICLE_TYPES.register("holy_ember_particle",
            () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> HOLY_NIGHT_EMBER_PARTICLE = PARTICLE_TYPES.register("holy_night_ember_particle",
            () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
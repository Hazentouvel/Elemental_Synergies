package net.hazen.elemental_synergies.Registries;

import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.particle.FogParticleOptions;
import io.redspace.ironsspellbooks.particle.SparkParticleOptions;
import net.hazen.hazennstuff.Particle.LeafParticle.LeafParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import org.joml.Vector3f;

public class ESParticleHelper {

    public static final ParticleOptions BRIMSTONE_EMBER_PARTICLE = ESParticleRegistry.BRIMSTONE_EMBER_PARTICLE.get();

    public static final ParticleOptions HOLY_EMBER_PARTICLE = ESParticleRegistry.HOLY_EMBER_PARTICLE.get();
    public static final ParticleOptions HOLY_NIGHT_EMBER_PARTICLE = ESParticleRegistry.HOLY_NIGHT_EMBER_PARTICLE.get();

    public static final ParticleOptions BRIMSTONE_IMPACT = new BlastwaveParticleOptions(new Vector3f(0.9F, 0.31F, 0.31F), 4.0F);

    public static final ParticleOptions HOLY_IMPACT = new BlastwaveParticleOptions(new Vector3f(1F, 1F, 0.6F), 3.0F);
    public static final ParticleOptions HOLY_NIGHT_IMPACT = new BlastwaveParticleOptions(new Vector3f(0.35F, 0.6F, 0.5F), 4.0F);
}
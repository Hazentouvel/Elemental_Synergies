package net.hazen.elemental_synergies.Registries;

import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomFireBlock;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.ApiStatus;


public final class ESFireRegistry {


  public static void init() {

    //Holy Fire
    Fire.Builder fireBuilder = FireManager.fireBuilder(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "holy_fire"));
    FireManager.registerFire(fireBuilder
            .setLight(15)
            .setDamage(5)
            .setCanRainDouse(false)
            .removeComponents(
                    Fire.Component.CAMPFIRE_BLOCK,
                    Fire.Component.CAMPFIRE_ITEM,
                    Fire.Component.LANTERN_BLOCK,
                    Fire.Component.LANTERN_ITEM,
                    Fire.Component.TORCH_BLOCK,
                    Fire.Component.WALL_TORCH_BLOCK,
                    Fire.Component.TORCH_ITEM
            )
            .setComponent(Fire.Component.FLAME_PARTICLE, BuiltInRegistries.PARTICLE_TYPE.getKey(ESParticleRegistry.HOLY_EMBER_PARTICLE.get()))
            .build());
  }

}
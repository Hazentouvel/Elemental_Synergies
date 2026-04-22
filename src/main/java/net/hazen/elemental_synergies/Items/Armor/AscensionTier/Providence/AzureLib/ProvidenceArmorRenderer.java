package net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.AzureLib;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HnSUtilities.AzArmorLeggingTorsoLayerPipeline;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class ProvidenceArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "geo/armor/providence_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/armor/providence_armor_animated.png"
    );
    private static final Map<String, ResourceLocation> NIGHT = Map.of(
            "night", ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/providence_night_armor_animated.png")
    );

    public ProvidenceArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(
                                (entity, stack) -> GEO,
                                (entity, stack) -> {
                                    if (entity instanceof Player player) {
                                        if (player.hasEffect(ESEffectRegistry.NIGHT_STATE)) {
                                            return NIGHT.getOrDefault("night", TEX);
                                        }
                                    }
                                    return TEX;
                                }
                        )
                        .setAnimatorProvider(ProvidenceAnimator::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .setPipelineContext(AzArmorLeggingTorsoLayerPipeline::new)
                        .build()
        );
    }
}
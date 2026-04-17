package net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Cataclysm;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HnSUtilities.AzArmorLeggingTorsoLayerPipeline;
import net.minecraft.resources.ResourceLocation;

public class CataclysmArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "geo/armor/cataclysm_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/armor/cataclysm_armor.png"
    );

    public CataclysmArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setPipelineContext(AzArmorLeggingTorsoLayerPipeline::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}
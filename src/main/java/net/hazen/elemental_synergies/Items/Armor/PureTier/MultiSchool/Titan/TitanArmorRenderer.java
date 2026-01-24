package net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.Item.Armor.AzureLib.AzArmorLeggingTorsoLayerPipeline;
import net.minecraft.resources.ResourceLocation;

public class TitanArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "geo/armor/titan_armor.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/armor/titan_armor_azurelib.png"
    );

    public TitanArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setPipelineContext(AzArmorLeggingTorsoLayerPipeline::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}
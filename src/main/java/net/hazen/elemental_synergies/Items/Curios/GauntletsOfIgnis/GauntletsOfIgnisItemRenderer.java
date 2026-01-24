package net.hazen.elemental_synergies.Items.Curios.GauntletsOfIgnis;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;

public class GauntletsOfIgnisItemRenderer extends AzItemRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "geo/curios/gauntlets_of_ignis.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/curios/gauntlets_of_ignis.png"
    );

    public GauntletsOfIgnisItemRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(GauntletsOfIgnisAnimator::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}
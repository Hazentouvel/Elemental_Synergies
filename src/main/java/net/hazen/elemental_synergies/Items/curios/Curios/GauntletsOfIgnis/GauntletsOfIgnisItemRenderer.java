package net.hazen.elemental_synergies.Items.curios.Curios.GauntletsOfIgnis;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.rewrite.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;

public class GauntletsOfIgnisItemRenderer extends AzArmorRenderer {
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
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(GauntletsOfIgnisAnimator::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}
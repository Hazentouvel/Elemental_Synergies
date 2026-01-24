package net.hazen.elemental_synergies.Items.curios.Spellbooks.GrimoireOfCorruption;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;

public class GrimoireOfCorruptionSpellbookCurioItemRenderer extends AzItemRenderer {
    public static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "geo/curios/grimoire_of_corruption.geo.json"
    );

    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/curios/grimoire_of_corruption.png"
    );

    public GrimoireOfCorruptionSpellbookCurioItemRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(GrimoireOfCorruptionSpellbookAnimator::new)
                        .addRenderLayer(new AzAutoGlowingLayer<>())
                        .build()
        );
    }
}
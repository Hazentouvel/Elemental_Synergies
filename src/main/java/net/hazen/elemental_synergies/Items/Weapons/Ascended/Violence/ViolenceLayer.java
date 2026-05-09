package net.hazen.elemental_synergies.Items.Weapons.Ascended.Violence;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Items.Weapons.Ascended.Catastrophe.Catastrophe;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class ViolenceLayer extends GeoRenderLayer<ViolenceItem> {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            ElementalSynergies.MOD_ID,
            "textures/items/weapons/violence_glowmask.png");

    public ViolenceLayer(GeoRenderer<ViolenceItem> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, ViolenceItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType glowRenderType = RenderType.breezeEyes(LAYER);

        this.getRenderer()
                .reRender(this.getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        glowRenderType,
                        bufferSource.getBuffer(glowRenderType),
                        partialTick,
                        packedLight,
                        OverlayTexture.NO_OVERLAY,
                        0xFFFFFFFF
                );
    }
}

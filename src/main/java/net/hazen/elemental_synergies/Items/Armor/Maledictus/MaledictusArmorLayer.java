package net.hazen.elemental_synergies.Items.Armor.Maledictus;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class MaledictusArmorLayer extends GeoRenderLayer<MaledictusArmorItem> {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(
            HazenNStuff.MOD_ID,
            "textures/armor/blazeborne_armor_glowmask.png");

    public MaledictusArmorLayer(GeoRenderer<MaledictusArmorItem> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, MaledictusArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType glowRenderType = RenderType.eyes(LAYER);
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

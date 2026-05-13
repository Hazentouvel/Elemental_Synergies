package net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class IgnisArmorLayer extends GeoRenderLayer<IgnisArmorItem> {

    private static final ResourceLocation NORMAL_LAYER = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/ignis_armor_layer.png");

    private static final ResourceLocation SOUL_LAYER = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/ignis_soul_armor_layer.png");
    public IgnisArmorLayer(GeoRenderer<IgnisArmorItem> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(
            PoseStack poseStack,
            IgnisArmorItem animatable,
            BakedGeoModel bakedModel,
            RenderType renderType,
            MultiBufferSource bufferSource,
            VertexConsumer buffer,
            float partialTick,
            int packedLight,
            int packedOverlay
    ) {

        Player player = Minecraft.getInstance().player;

        ResourceLocation texture = NORMAL_LAYER;

        if (player != null && player.hasEffect(ESEffectRegistry.IGNIS_SOUL_STATE)) {
            texture = SOUL_LAYER;
        }

        RenderType glowRenderType = RenderType.breezeEyes(texture);

        this.getRenderer().reRender(
                this.getDefaultBakedModel(animatable),
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
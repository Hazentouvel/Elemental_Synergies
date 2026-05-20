package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave;

import com.github.L_Ender.cataclysm.client.model.CMModelLayers;
import com.github.L_Ender.cataclysm.client.model.entity.Wave_Model;
import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.github.L_Ender.cataclysm.entity.effect.Wave_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.hazen.elemental_synergies.Entities.Renderer.CataclysmModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.FastColor.ARGB32;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaveMagicProjectileRenderer extends EntityRenderer<WaveMagicProjectileEntity> {
    private static final ResourceLocation WAVE_TEXTURES = ResourceLocation.fromNamespaceAndPath("cataclysm", "textures/entity/sea/wave.png");
    private static final ResourceLocation[] TEXTURE_PROGRESS = new ResourceLocation[5];
    public WaveMagicProjectileModel model;

    public WaveMagicProjectileRenderer(EntityRendererProvider.Context manager) {
        super(manager);
        this.model = new WaveMagicProjectileModel(manager.bakeLayer(CataclysmModelLayers.WAVE_MODEL));

        for(int i = 0; i < 5; ++i) {
            TEXTURE_PROGRESS[i] = ResourceLocation.fromNamespaceAndPath("cataclysm", "textures/entity/sea/wave/wave_" + i + ".png");
        }

    }

    public void render(WaveMagicProjectileEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YN.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) + 180.0F));
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(entity, 0.0F, 0.0F, (float)entity.tickCount + partialTicks, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = buffer.getBuffer(CMRenderTypes.getGhost(this.getTextureLocation(entity)));
        float alpha = 0.7F;
        int i1 = ARGB32.color((int)(alpha * 255.0F), 255, 255, 255);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, i1);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(WaveMagicProjectileEntity entity) {
        return this.getGrowingTexture((int)((float)entity.tickCount * 1.5F % 5.0F));
    }

    public ResourceLocation getGrowingTexture(int age) {
        return TEXTURE_PROGRESS[Mth.clamp(age, 0, 5)];
    }
}

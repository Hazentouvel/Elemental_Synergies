package net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis;

import com.github.L_Ender.cataclysm.client.model.CMModelLayers;
import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hazen.elemental_synergies.Entities.Spells.Misc.ElementalSpearMagicProjectileModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

//Base Cataclysm btw.

@OnlyIn(Dist.CLIENT)
public class LightningSpearMagicProjectileRenderer extends EntityRenderer<LightningSpearMagicProjectile> {
    private static final ResourceLocation[] TEXTURE_PROGRESS = new ResourceLocation[6];
    public ElementalSpearMagicProjectileModel model;

    public LightningSpearMagicProjectileRenderer(EntityRendererProvider.Context manager) {
        super(manager);
        this.model = new ElementalSpearMagicProjectileModel(manager.bakeLayer(CMModelLayers.ELEMENTAL_SPEAR_MODEL));

        for(int i = 0; i < 6; ++i) {
            TEXTURE_PROGRESS[i] = ResourceLocation.fromNamespaceAndPath("cataclysm", "textures/entity/sea/spear/lightning_spear_" + i + ".png");
        }

    }

    public void render(LightningSpearMagicProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        float f = Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot());
        float f1 = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        this.model.setupAnim(entity, 0.0F, 0.0F, (float)entity.tickCount + partialTicks, f, f1);
        VertexConsumer vertexconsumer = buffer.getBuffer(CMRenderTypes.CMEyes(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(LightningSpearMagicProjectile entity) {
        return this.getGrowingTexture((int)((float)entity.tickCount * 0.5F % 5.0F));
    }

    public ResourceLocation getGrowingTexture(int age) {
        return TEXTURE_PROGRESS[Mth.clamp(age, 0, 5)];
    }
}

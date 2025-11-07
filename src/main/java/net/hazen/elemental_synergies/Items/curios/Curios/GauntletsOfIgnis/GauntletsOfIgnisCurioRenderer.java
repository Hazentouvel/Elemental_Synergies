package net.hazen.elemental_synergies.Items.curios.Curios.GauntletsOfIgnis;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.azure.azurelib.rewrite.model.AzBakedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@OnlyIn(Dist.CLIENT)
public class GauntletsOfIgnisCurioRenderer implements ICurioRenderer {
    private final GauntletsOfIgnisItemRenderer curioRenderer = new GauntletsOfIgnisItemRenderer();

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack,
            SlotContext slotContext,
            PoseStack matrixStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {

        LivingEntity entity = slotContext.entity();

        matrixStack.pushPose();

        curioRenderer.prepForRender(entity, stack, EquipmentSlot.BODY, (HumanoidModel<?>) renderLayerParent.getModel());

        matrixStack.translate(0D, -1.75D, 2D); // x = sideways, y = up/down, z = forward/back

        AzBakedModel model = curioRenderer.provider().provideBakedModel(stack);
        ResourceLocation textureLocation = GauntletsOfIgnisItemRenderer.TEX;
        RenderType renderType = RenderType.entityCutout(textureLocation);
        VertexConsumer buffer = renderTypeBuffer.getBuffer(renderType);

        curioRenderer.rendererPipeline().render(matrixStack, model, stack, renderTypeBuffer, renderType, buffer, netHeadYaw, partialTicks, light);

        matrixStack.popPose();

    }
}
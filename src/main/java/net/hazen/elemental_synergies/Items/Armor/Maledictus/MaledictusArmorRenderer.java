package net.hazen.elemental_synergies.Items.Armor.Maledictus;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MaledictusArmorRenderer extends GeoArmorRenderer<MaledictusArmorItem> {
    public MaledictusArmorRenderer(MaledictusArmorModel blazeborneArmorModel) {
        super(new MaledictusArmorModel());
        this.addRenderLayer(new MaledictusArmorLayer(this));
        this.head = new GeoBone((GeoBone)null, "armorHead", false, (double)0.0F, false, false);
        this.body = new GeoBone((GeoBone)null, "armorBody", false, (double)0.0F, false, false);
        this.rightArm = new GeoBone((GeoBone)null, "armorRightArm", false, (double)0.0F, false, false);
        this.leftArm = new GeoBone((GeoBone)null, "armorLeftArm", false, (double)0.0F, false, false);
        this.rightLeg = new GeoBone((GeoBone)null, "armorRightLeg", false, (double)0.0F, false, false);
        this.leftLeg = new GeoBone((GeoBone)null, "armorLeftLeg", false, (double)0.0F, false, false);
        this.rightBoot = new GeoBone((GeoBone)null, "armorRightBoot", false, (double)0.0F, false, false);
        this.leftBoot = new GeoBone((GeoBone)null, "armorLeftBoot", false, (double)0.0F, false, false);
    }

    public RenderType getRenderType(MaledictusArmorItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
}

package net.hazen.elemental_synergies.Items.Shields.SupremeShield;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class SupremeShieldModel extends DefaultedItemGeoModel<SupremeShield> {
    public SupremeShieldModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(SupremeShield animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/shields/supreme_shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SupremeShield animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/shields/supreme_shield.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SupremeShield animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/shields/supreme_shield.animation.json");
    }
}

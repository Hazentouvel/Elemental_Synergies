package net.hazen.elemental_synergies.Items.Weapons.Ascended.Violence;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ViolenceModel extends DefaultedItemGeoModel<ViolenceItem> {
    public ViolenceModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(ViolenceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/weapons/violence.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ViolenceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/weapons/violence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ViolenceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/weapons/violence.animation.json");
    }
}

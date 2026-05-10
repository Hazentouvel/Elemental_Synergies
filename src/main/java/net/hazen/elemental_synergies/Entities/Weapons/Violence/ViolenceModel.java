package net.hazen.elemental_synergies.Entities.Weapons.Violence;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ViolenceModel extends GeoModel<Violence> {

    @Override
    public ResourceLocation getModelResource(Violence animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/weapons/violence.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Violence animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/weapons/violence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Violence animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/weapons/violence.animation.json");
    }

}
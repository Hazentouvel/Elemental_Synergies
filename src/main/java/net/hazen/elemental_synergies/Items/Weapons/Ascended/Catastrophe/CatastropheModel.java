package net.hazen.elemental_synergies.Items.Weapons.Ascended.Catastrophe;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Items.Weapons.Generic.Excelsior.Excelsior;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class CatastropheModel extends DefaultedItemGeoModel<Catastrophe> {
    public CatastropheModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/weapons/catastrophe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/weapons/catastrophe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/weapons/catastrophe.animation.json");
    }
}

package net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Onyx;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class OnyxArmorModel extends DefaultedEntityGeoModel<OnyxArmorItem> {
    public OnyxArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(OnyxArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/onyx_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OnyxArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/onyx_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OnyxArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/armor/onyx_armor.animation.json");
    }
}


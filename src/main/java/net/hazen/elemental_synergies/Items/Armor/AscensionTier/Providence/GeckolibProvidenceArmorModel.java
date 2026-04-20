package net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeckolibProvidenceArmorModel extends DefaultedEntityGeoModel<GeckolibProvidenceArmorItem> {
    public GeckolibProvidenceArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(GeckolibProvidenceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/providence_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckolibProvidenceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/providence_armor_animated.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckolibProvidenceArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/armor/providence_armor.animation.json");
    }
}


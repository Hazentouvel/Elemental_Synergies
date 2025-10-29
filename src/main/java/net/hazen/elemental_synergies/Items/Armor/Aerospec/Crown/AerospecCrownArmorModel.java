package net.hazen.elemental_synergies.Items.Armor.Aerospec.Crown;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AerospecCrownArmorModel extends DefaultedEntityGeoModel<AerospecCrownArmorItem> {
    public AerospecCrownArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(AerospecCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/aerospec_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AerospecCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/aerospec_crown_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AerospecCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

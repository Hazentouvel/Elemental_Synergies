package net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AerospecArmorModel extends DefaultedEntityGeoModel<AerospecArmorItem> {
    public AerospecArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(AerospecArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/aerospec_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AerospecArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/aerospec_crown_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AerospecArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

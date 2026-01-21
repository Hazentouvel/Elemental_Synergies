package net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec.Helm;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AerospecHelmArmorModel extends DefaultedEntityGeoModel<AerospecHelmArmorItem> {
    public AerospecHelmArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(AerospecHelmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/aerospec_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AerospecHelmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/aerospec_helm_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AerospecHelmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

package net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Cataclysm.Geckolib;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeckolibCataclysmArmorModel extends DefaultedEntityGeoModel<GeckolibCataclysmArmorItem> {
    public GeckolibCataclysmArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(GeckolibCataclysmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/cataclysm_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckolibCataclysmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/cataclysm_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckolibCataclysmArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

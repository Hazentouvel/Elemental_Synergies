package net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.Titan.Geckolib;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeckolibTitanArmorModel extends DefaultedEntityGeoModel<GeckolibTitanArmorItem> {
    public GeckolibTitanArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(GeckolibTitanArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/titan_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckolibTitanArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/titan_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckolibTitanArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

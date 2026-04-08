package net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.ExoMech.Geckolib;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeckolibExoMechArmorModel extends DefaultedEntityGeoModel<GeckolibExoMechArmorItem> {
    public GeckolibExoMechArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(GeckolibExoMechArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/titan_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeckolibExoMechArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/titan_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckolibExoMechArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

package net.hazen.elemental_synergies.items.armor.Cloudsage.Crown;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class CloudmasterCrownArmorModel extends DefaultedEntityGeoModel<CloudmasterCrownArmorItem> {
    public CloudmasterCrownArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(CloudmasterCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/cloudmaster_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CloudmasterCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/cloudmaster_helmet_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CloudmasterCrownArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

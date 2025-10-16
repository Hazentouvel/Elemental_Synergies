package net.hazen.elemental_synergies.items.armor.Cloudmaster.Hat;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class CloudmasterHatArmorModel extends DefaultedEntityGeoModel<CloudmasterHatArmorItem> {
    public CloudmasterHatArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(CloudmasterHatArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/cloudmaster_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CloudmasterHatArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/cloudmaster_hat_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CloudmasterHatArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

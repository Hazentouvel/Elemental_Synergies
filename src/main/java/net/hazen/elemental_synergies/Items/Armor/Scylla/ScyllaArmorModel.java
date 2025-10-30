package net.hazen.elemental_synergies.Items.Armor.Scylla;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ScyllaArmorModel extends DefaultedEntityGeoModel<ScyllaArmorItem> {
    public ScyllaArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(ScyllaArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/scylla_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScyllaArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/scylla_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScyllaArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/armor/scylla_armor.json");
    }
}


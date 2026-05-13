package net.hazen.elemental_synergies.Items.Weapons.Ascended.Excelsior;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Items.Shields.SupremeShield.SupremeShield;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ExcelsiorModel extends DefaultedItemGeoModel<Excelsior> {
    public ExcelsiorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(Excelsior animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/weapons/excelsior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Excelsior animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/weapons/excelsior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Excelsior animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

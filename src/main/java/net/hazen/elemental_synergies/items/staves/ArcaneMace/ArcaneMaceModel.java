package net.hazen.elemental_synergies.items.staves.ArcaneMace;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ArcaneMaceModel extends DefaultedItemGeoModel<ArcaneMaceItem> {
    public ArcaneMaceModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(ArcaneMaceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/items/staves/arcane_mace.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArcaneMaceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/items/staves/arcane_mace.png");
    }
    @Override
    public ResourceLocation getAnimationResource(ArcaneMaceItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

package net.hazen.elemental_synergies.Items.Armor.Maledictus;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Items.Armor.Aerospec.AerospecArmorItem;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MaledictusArmorModel extends DefaultedEntityGeoModel<MaledictusArmorItem> {
    public MaledictusArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/maledictus_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/maledictus_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}


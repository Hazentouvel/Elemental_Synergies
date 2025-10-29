package net.hazen.elemental_synergies.Items.Armor.Maledictus;

import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MaledictusArmorModel extends DefaultedEntityGeoModel<MaledictusArmorItem> {
    public static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "geo/armor/blazeborne_armor.geo.json");
    public MaledictusArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "geo/armor/blazeborne_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "textures/armor/blazeborne_armor_alt.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MaledictusArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "animations/armor/blazeborne_armor.animation.json");
    }
}


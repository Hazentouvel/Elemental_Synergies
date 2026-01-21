package net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.GeckoLib;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.hazennstuff.HazenNStuff;
import net.hazen.hazennstuff.Item.Armor.Geckolib.SoulFlame.GeckolibSoulFlameArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeckolibSoulFlameArmorModel extends DefaultedEntityGeoModel<net.hazen.hazennstuff.Item.Armor.Geckolib.SoulFlame.GeckolibSoulFlameArmorItem> {
    public GeckolibSoulFlameArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(net.hazen.hazennstuff.Item.Armor.Geckolib.SoulFlame.GeckolibSoulFlameArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "geo/armor/soul_flame_armor_geckolib.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(net.hazen.hazennstuff.Item.Armor.Geckolib.SoulFlame.GeckolibSoulFlameArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "textures/armor/soul_flame_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeckolibSoulFlameArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}


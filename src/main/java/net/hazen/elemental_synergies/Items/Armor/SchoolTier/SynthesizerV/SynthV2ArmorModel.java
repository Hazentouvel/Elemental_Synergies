package net.hazen.elemental_synergies.Items.Armor.SchoolTier.SynthesizerV;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SynthV2ArmorModel extends DefaultedEntityGeoModel<SynthV2ArmorItem> {
    public SynthV2ArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(SynthV2ArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/synthv2_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SynthV2ArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/synthv2_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SynthV2ArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}

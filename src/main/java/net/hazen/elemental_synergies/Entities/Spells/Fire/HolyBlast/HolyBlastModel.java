package net.hazen.elemental_synergies.Entities.Spells.Fire.HolyBlast;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HolyBlastModel extends GeoModel<HolyBlast> {

    @Override
    public ResourceLocation getModelResource(HolyBlast animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/entities/spells/holy_blast.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HolyBlast animatable) {
        int frameCount = 7;
        int frameDuration = 2; // ticks per frame
        int frame = (animatable.tickCount / frameDuration) % frameCount;
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/spells/holy_blast_" + frame + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(HolyBlast animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "animations/entities/spells/brimstone_hellblast.animation.json");
    }

}
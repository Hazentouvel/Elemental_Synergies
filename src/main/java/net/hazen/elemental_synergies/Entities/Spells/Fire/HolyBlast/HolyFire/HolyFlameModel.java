package net.hazen.elemental_synergies.Entities.Spells.Fire.HolyBlast.HolyFire;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.Entity.Spells.Eldritch.SoulSeeker.SoulSeeker;
import net.hazen.hazennstuff.HazenNStuff;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HolyFlameModel extends GeoModel<SoulSeeker> {

    @Override
    public ResourceLocation getModelResource(SoulSeeker animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/entities/spells/holy_flame.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoulSeeker animatable) {
        int frameCount = 3;
        int frameDuration = 1; // ticks per frame
        int frame = (animatable.tickCount / frameDuration) % frameCount;
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/spells/holy_flame_" + frame + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoulSeeker animatable) {
        return ResourceLocation.fromNamespaceAndPath(HazenNStuff.MOD_ID, "animations/entities/spells/spark.animation.json");
    }
}
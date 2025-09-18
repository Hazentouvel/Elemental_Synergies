package net.hazen.elemental_synergies.entity.mobs.wizards.Good.CloudmasterSage;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMobModel;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.resources.ResourceLocation;

public class CloudmasterSageModel extends AbstractSpellCastingMobModel {
    public static final ResourceLocation textureResource = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/mobs/cloudmaster_sage.png");
    public static final ResourceLocation modelResource = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/entities/mobs/cloudmaster_sage.geo.json");
    public static final ResourceLocation animationResource = ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/casting_animation.json");

    @Override
    public ResourceLocation getTextureResource(AbstractSpellCastingMob object) {
        return textureResource;
    }

    @Override
    public ResourceLocation getModelResource(AbstractSpellCastingMob object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getAnimationResource(AbstractSpellCastingMob animatable) {
        return animationResource;
    }
}
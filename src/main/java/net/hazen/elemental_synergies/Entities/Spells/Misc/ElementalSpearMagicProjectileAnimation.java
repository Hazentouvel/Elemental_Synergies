package net.hazen.elemental_synergies.Entities.Spells.Misc;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.AnimationChannel.Interpolations;
import net.minecraft.client.animation.AnimationChannel.Targets;
import net.minecraft.client.animation.AnimationDefinition.Builder;

//Base Cataclysm btw.

public class ElementalSpearMagicProjectileAnimation {
    public static final AnimationDefinition SPAWN;
    public static final AnimationDefinition IDLE;

    public ElementalSpearMagicProjectileAnimation() {
    }

    static {
        SPAWN = Builder.withLength(0.25F).addAnimation("rot", new AnimationChannel(Targets.SCALE, new Keyframe[]{new Keyframe(0.0F, KeyframeAnimations.scaleVec((double)0.0F, (double)0.0F, (double)0.0F), Interpolations.CATMULLROM), new Keyframe(0.125F, KeyframeAnimations.scaleVec((double)0.5F, (double)0.5F, (double)1.8F), Interpolations.CATMULLROM), new Keyframe(0.1667F, KeyframeAnimations.scaleVec((double)1.0F, (double)1.0F, (double)1.0F), Interpolations.CATMULLROM), new Keyframe(0.25F, KeyframeAnimations.scaleVec((double)1.0F, (double)1.0F, (double)1.0F), Interpolations.CATMULLROM)})).build();
        IDLE = Builder.withLength(0.5F).looping().addAnimation("rot", new AnimationChannel(Targets.ROTATION, new Keyframe[]{new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -360.0F), Interpolations.LINEAR)})).build();
    }
}

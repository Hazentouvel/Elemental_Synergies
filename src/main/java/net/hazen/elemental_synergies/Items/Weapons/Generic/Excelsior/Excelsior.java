package net.hazen.elemental_synergies.Items.Weapons.Generic.Excelsior;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.elemental_synergies.ESUtilities.ESRarities;
import net.hazen.elemental_synergies.ESUtilities.Items.ESExtendedWeaponsTiers;
import net.hazen.hazennstuff.HnSUtilities.Item.HnSExtendedWeaponsTiers;
import net.hazen.hazennstuff.Item.Weapons.Generic.Dawnmaker.DawnmakerRenderer;
import net.hazen.hazennstuff.Rarity.HnSRarities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class Excelsior extends ExtendedSwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Excelsior() {
        super(
                ESExtendedWeaponsTiers.EXCELSIOR,
                ItemPropertiesHelper
                        .equipment(1)
                        .fireResistant()
                        .rarity(ESRarities.SPELLBLADE_RARITY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(ESExtendedWeaponsTiers.EXCELSIOR)
                        )
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(animationController);
    }

    // Animations and stuff
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    private final AnimationController<Excelsior> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<Excelsior> event)
    {
        event.getController().setAnimation(IDLE_ANIMATION);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Your renderer for items
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private ExcelsiorRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new ExcelsiorRenderer();

                return this.renderer;
            }
        });
    }
}

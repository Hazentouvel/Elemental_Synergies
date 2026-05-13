package net.hazen.elemental_synergies.Items.Weapons.Ascended.Excelsior;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.hazen.elemental_synergies.ESUtilities.ESRarities;
import net.hazen.elemental_synergies.ESUtilities.Items.ESExtendedWeaponsTiers;
import net.hazen.elemental_synergies.Registries.Effects.MidasTouchedEffect;
import net.hazen.hazennstuff.HnSUtilities.Item.HnSExtendedWeaponsTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

@EventBusSubscriber
public class Excelsior extends MagicSwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Excelsior() {
        super(HnSExtendedWeaponsTiers.NIGHTS_EDGE, ItemPropertiesHelper.equipment(1)
                        .fireResistant()
                        .rarity((Rarity)ESRarities.SPELLBLADE_RARITY.getValue())
                        .attributes(ExtendedSwordItem
                        .createAttributes(ESExtendedWeaponsTiers.EXCELSIOR)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder[]{
                        new SpellDataRegistryHolder(GGSpellRegistry.PARRY, 10)
                })
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.irons_spellbooks.passive_ability", new Object[]{Component.literal(Utils.timeFromTicks(400.0F, 1)).withStyle(ChatFormatting.LIGHT_PURPLE)}).withStyle(ChatFormatting.DARK_PURPLE));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable(this.getDescriptionId() + ".desc")).withStyle(ChatFormatting.YELLOW));
        } else {
            tooltipComponents.add(Component.translatable("item.discerning_the_eldritch.more_details").withStyle(ChatFormatting.GRAY));
        }
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
            private SupremeShieldRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new SupremeShieldRenderer();

                return this.renderer;
            }
        });
    }

    protected int getPassiveCooldownTicks() {
        return 400;
    }


    @SubscribeEvent
    public static void handleAbility(LivingIncomingDamageEvent event) {

        Entity attacker = event.getSource().getEntity();

        if (!(attacker instanceof ServerPlayer player))
            return;

        ItemStack weapon = player.getWeaponItem();

        if (!(weapon.getItem() instanceof Excelsior excelsior))
            return;

        LivingEntity victim = event.getEntity();

        if (victim == attacker)
            return;

        if (!player.getCooldowns().isOnCooldown(excelsior)) {
            MidasTouchedEffect.tryApplyIfNotImmune(victim, 600, 0, false, true, true);
            player.getCooldowns().addCooldown(excelsior, excelsior.getPassiveCooldownTicks());
        }
    }


}

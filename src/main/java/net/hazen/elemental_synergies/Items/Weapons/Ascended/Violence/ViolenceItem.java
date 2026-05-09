package net.hazen.elemental_synergies.Items.Weapons.Ascended.Violence;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.elemental_synergies.ESUtilities.ESRarities;
import net.hazen.elemental_synergies.ESUtilities.Items.ESExtendedWeaponsTiers;
import net.hazen.elemental_synergies.Entities.Weapons.Violence.Violence;
import net.hazen.hazennstuff.HnSUtilities.Item.HnSExtendedWeaponsTiers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class ViolenceItem extends ExtendedSwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public ViolenceItem() {
        super(
                HnSExtendedWeaponsTiers.NIGHTS_EDGE,
                ItemPropertiesHelper.equipment(1)
                        .fireResistant()
                        .rarity((Rarity) ESRarities.OCCULT_RARITY.getValue())
                        .attributes(
                                ExtendedSwordItem.createAttributes(
                                        ESExtendedWeaponsTiers.VIOLENCE
                                )
                        )
        );
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (isTooDamagedToUse(itemstack)) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment) || enchantment.is(Enchantments.LOYALTY) || enchantment.is(Enchantments.CHANNELING);
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            int i = this.getUseDuration(stack, entityLiving) - timeLeft;
            if (i >= 8 && !isTooDamagedToUse(stack)) {
                Holder<SoundEvent> holder = (Holder)EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(entityLiving.getUsedItemHand()));
                    double damage = 1.0F + ESExtendedWeaponsTiers.VIOLENCE.getAttackDamageBonus();
                    if (stack.equals(player.getWeaponItem())) {
                        damage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    }

                    Violence violence = new Violence(level, stack, damage);
                    violence.setOwner(player);
                    violence.moveTo(player.getEyePosition());
                    violence.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.5F);
                    if (player.hasInfiniteMaterials()) {
                        violence.pickup = Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(violence);
                    if (!player.hasInfiniteMaterials()) {
                        player.getCooldowns().addCooldown(stack.getItem(), 200);
                    }

                    level.playSound((Player)null, violence, (SoundEvent)holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

    }

    private static boolean isTooDamagedToUse(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(animationController);
    }

    // Animations and stuff
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    private final AnimationController<ViolenceItem> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<ViolenceItem> event)
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
            private ViolenceRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new ViolenceRenderer();

                return this.renderer;
            }
        });
    }
}

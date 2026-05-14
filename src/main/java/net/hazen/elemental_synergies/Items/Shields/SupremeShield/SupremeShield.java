package net.hazen.elemental_synergies.Items.Shields.SupremeShield;

import com.github.L_Ender.cataclysm.Attachment.ChargeAttachment;
import com.github.L_Ender.cataclysm.init.ModDataAttachments;
import java.util.List;
import java.util.function.Consumer;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ESConfig;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.hazen.hazentouvelib.Registries.HLKeybinds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SupremeShield extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public SupremeShield(Item.Properties group) {
        super(group);
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHIELD_ACTIONS.contains(itemAbility);
    }

    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 72000;
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving.isShiftKeyDown() && !entityLiving.isFallFlying()) {
            int t = 4;

            float f7 = entityLiving.getYRot();
            float f = entityLiving.getXRot();

            float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));

            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 3.0F * ((float)t / 6.0F);

            f1 *= f5 / f4;
            f3 *= f5 / f4;

            entityLiving.push(f1, 0.0F, f3);
            if (!entityLiving.level().isClientSide) {
                entityLiving.level().playSound(null, entityLiving.getX(), entityLiving.getY(), entityLiving.getZ(), HnSSounds.BRIMSTONE_CAST, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            // Parry nearby projectiles while dashing
            AABB parryBox = entityLiving.getBoundingBox()
                    .expandTowards(
                            new Vec3(f1, 0.0F, f3).normalize().scale(3.0))
                    .inflate(2.0);

            List<Projectile> projectiles = entityLiving.level().getEntitiesOfClass(Projectile.class, parryBox, projectile -> !projectile.noPhysics && !projectile.getType().is(ModTags.CANT_PARRY));

            for (Projectile projectile : projectiles) {
                // Skip own projectiles
                if (projectile.getOwner() == entityLiving) {
                    continue;
                }
                Vec3 reflectDirection =
                        entityLiving.getLookAngle().normalize();
                float speed =
                        (float) projectile.getDeltaMovement().length();
                if (speed < 0.5F) {
                    speed = 1.5F;
                }
                projectile.setOwner(entityLiving);
                projectile.shoot(reflectDirection.x, reflectDirection.y, reflectDirection.z, speed * 1.5F, 0.0F);
                projectile.hurtMarked = true;
            }
            if (entityLiving.onGround()) {
                entityLiving.move(MoverType.SELF, new Vec3(0.0F, 0.6F, 0.0F));
            }

            float firePower = (float) entityLiving.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            float bloodPower = (float) entityLiving.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
            float ritualPower = (float) entityLiving.getAttributeValue(ASAttributeRegistry.RITUAL_MAGIC_POWER);
            float shadowPower = (float) entityLiving.getAttributeValue(HLAttributeRegistry.SHADOW_SPELL_POWER);

            float totalPower = firePower + bloodPower + ritualPower + shadowPower;

            float bonusDamage = totalPower / 2.5F;
            float finalDamage = 0.6F + bonusDamage;

            ChargeAttachment charge = entityLiving.getData(ModDataAttachments.CHARGE_ATTACHMENT);
            charge.setCharge(true);
            charge.setTimer(t * 2);
            charge.seteffectiveChargeTime(t * 2);
            charge.setknockbackSpeedIndex((float)(t * 2));
            charge.setdamagePerEffectiveCharge(finalDamage);
            charge.setdx(f1 * 0.1F);
            charge.setdZ(f3 * 0.1F);
            if (!level.isClientSide) {
                ((Player)entityLiving).getCooldowns().addCooldown(this, ESConfig.supremeShieldCooldown);
            }
        }

    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemStack);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltips, TooltipFlag flags) {
        tooltips.add(Component.translatable("item.elemental_synergies.supreme_shield.desc")
                .withStyle(ChatFormatting.DARK_PURPLE)
        );
        tooltips.add(Component.translatable("item.elemental_synergies.supreme_shield2.desc")
                .withStyle(ChatFormatting.DARK_PURPLE)
        );
        tooltips.add(Component.translatable("item.elemental_synergies.supreme_shield3.desc")
                .withStyle(ChatFormatting.DARK_PURPLE)
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(animationController);
    }

    // Animations and stuff
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    private final AnimationController<SupremeShield> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<SupremeShield> event)
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
}

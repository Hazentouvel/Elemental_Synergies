package net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Maledictus;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.items.KeybindUsingArmor;
import com.github.L_Ender.cataclysm.message.MessageArmorKey;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.spells.fire.AbstractIgnisSpell;
import net.acetheeldritchking.cataclysm_spellbooks.spells.ice.AbstractMaledictusSpell;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis.IgnisArmorItem;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MFTECompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
//import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class MaledictusArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, IDisableHat, KeybindUsingArmor {
    public MaledictusArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.MALEDICTUS, type, settings, paragonTier(
                AttributeRegistry.ICE_SPELL_POWER
                //MFTEAttributeRegistries.SPIRIT_SPELL_POWER
        ));
    }
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        MFTECompat.addSpiritSpellPower(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        return attributes.build().modifiers();
    }


    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new MaledictusArmorRenderer(new MaledictusArmorModel());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }
        if (entity instanceof Player player) {
            if (level.isClientSide) {
                if (this.type == Type.HELMET && player.getItemBySlot(EquipmentSlot.HEAD) == stack) {
                    if (ModKeybind.HELMET_KEY_ABILITY.consumeClick()) {
                        PacketDistributor.sendToServer(new MessageArmorKey(EquipmentSlot.HEAD.ordinal(), player.getId(), 5), new CustomPacketPayload[0]);
                        this.onKeyPacket(player, stack, 5);
                    }
                } else if (this.type == Type.BOOTS && player.getItemBySlot(EquipmentSlot.FEET) == stack && ModKeybind.BOOTS_KEY_ABILITY.consumeClick()) {
                    PacketDistributor.sendToServer(new MessageArmorKey(EquipmentSlot.FEET.ordinal(), player.getId(), 7), new CustomPacketPayload[0]);
                    this.onKeyPacket(player, stack, 7);
                }

                return;
            }
        }
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
        if (this.type == Type.HELMET) {
            tooltip.add(Component.translatable("item.elemental_synergies.maledictus_helmet.ability")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.elemental_synergies.maledictus_helmet.description", new Object[]{ModKeybind.HELMET_KEY_ABILITY.getTranslatedKeyMessage()})
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.CHESTPLATE) {
            tooltip.add(Component.translatable("item.cataclysm.cursium_chestplate.desc")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.cataclysm.cursium_chestplate.desc2")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.cataclysm.cursium_chestplate.desc3")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.LEGGINGS) {
            tooltip.add(Component.translatable("item.cataclysm.cursium_leggings.desc")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.cataclysm.cursium_leggings.desc2")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.BOOTS) {
            tooltip.add(Component.translatable("item.cataclysm.cursium_boots.desc")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.cataclysm.cursium_boots.desc2", new Object[]{ModKeybind.BOOTS_KEY_ABILITY.getTranslatedKeyMessage()})
                    .withStyle(ChatFormatting.DARK_PURPLE));
        }

        tooltip.add(Component.translatable("item.elemental_synergies.maledictus_affinity.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.YELLOW)
                ));

    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.MAGE_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.MAGE_SET_BONUS, 320, 0, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof MaledictusArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof MaledictusArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof MaledictusArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof MaledictusArmorItem;
    }

    public void onKeyPacket(Player player, ItemStack itemStack, int type) {
        if (player != null) {
            if (type == 5 && !player.getCooldowns().isOnCooldown((Item) ESItemRegistry.MALEDICTUS_HELMET.get())) {
                boolean targetFound = false;

                for(Entity entity : player.level().getEntities(player, player.getBoundingBox().inflate((double)24.0F))) {
                    if (entity instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity)entity;
                        if (entity != player) {
                            targetFound = true;
                            living.addEffect(new MobEffectInstance(MobEffectRegistry.GUIDING_BOLT, 200));
                        }
                    }
                }

                if (targetFound) {
                    player.getCooldowns().addCooldown((Item)ESItemRegistry.MALEDICTUS_HELMET.get(), 400);
                }
            }

            if (type == 7 && player.onGround() && !player.getCooldowns().isOnCooldown((Item)ESItemRegistry.MALEDICTUS_BOOTS.get())) {

                float speed = -1.8F;
                float dodgeYaw = (float)Math.toRadians((double)(player.getYRot() + 90.0F));

                double velX = (double)speed * Math.cos((double)dodgeYaw);
                double velZ = (double)speed * Math.sin((double)dodgeYaw);

                Vec3 currentVel = player.getDeltaMovement();

                player.setDeltaMovement(currentVel.x + velX, 0.4, currentVel.z + velZ);

                player.hurtMarked = true;

                // Play launch sound
                player.level().playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        ESSounds.DODGE.get(),
                        player.getSoundSource(),
                        1.0F,
                        1.0F
                );

                player.getCooldowns().addCooldown((Item)ESItemRegistry.MALEDICTUS_BOOTS.get(), 200);
            }

        }
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (!(event.getSpell() instanceof AbstractMaledictusSpell)) return;

            boolean fullSet = caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof MaledictusArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof MaledictusArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof MaledictusArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof MaledictusArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }


}

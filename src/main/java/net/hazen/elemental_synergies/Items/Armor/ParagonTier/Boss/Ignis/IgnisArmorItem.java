package net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.init.ModSounds;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import it.crystalnest.prometheus.api.FireManager;
import net.acetheeldritchking.cataclysm_spellbooks.spells.fire.AbstractIgnisSpell;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas.SupremeCalamitasArmorItem;
import net.hazen.elemental_synergies.Particle.SoulFlameExplosionParticlesPacket;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.elemental_synergies.Registries.ESParticleHelper;
import net.hazen.elemental_synergies.Spells.AbstractSpells.BrimstoneSpells;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.hazen.hazentouvelib.Items.Armor.HLKeybindArmor;
import net.hazen.hazentouvelib.Items.Armor.HLMessageArmorKey;
import net.hazen.hazentouvelib.Registries.HLKeybinds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class IgnisArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, IDisableHat, HLKeybindArmor {
    public IgnisArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.IGNIS, type, settings, paragonTier(
                AttributeRegistry.FIRE_SPELL_POWER
        ));
    }
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        return attributes.build().modifiers();
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltips, TooltipFlag flags) {
        if (this.type == Type.HELMET) {
            tooltips.add(Component.translatable("item.elemental_synergies.full_set.description")
                    .withStyle(ChatFormatting.WHITE)
            );
            if (Screen.hasShiftDown()) {
                tooltips.add(Component.translatable("item.elemental_synergies.ignis_passive.description")
                        .withStyle(ChatFormatting.DARK_PURPLE)
                );
            } else {
                tooltips.add(Component.translatable("item.discerning_the_eldritch.more_details").withStyle(ChatFormatting.GRAY));
            }
        }


        if (this.type == Type.CHESTPLATE) {
            tooltips.add(Component.translatable("item.elemental_synergies.full_set.description")
                    .withStyle(ChatFormatting.DARK_PURPLE)
            );
            if (Screen.hasShiftDown()) {
                tooltips.add(Component.translatable("item.elemental_synergies.ignis_ability.description", new Object[]{HLKeybinds.ABILITY_1.getTranslatedKeyMessage()})
                        .withStyle(ChatFormatting.DARK_PURPLE)
                );
                tooltips.add(Component.translatable("item.elemental_synergies.ignis_ability.description2", new Object[]{HLKeybinds.ABILITY_1.getTranslatedKeyMessage()})
                        .withStyle(ChatFormatting.AQUA)
                );
            } else {
                tooltips.add(Component.translatable("item.discerning_the_eldritch.more_details").withStyle(ChatFormatting.GRAY));
            }
        }

        if (this.type == Type.LEGGINGS) {
            tooltips.add(Component.translatable("item.cataclysm.ignitium_leggings.desc").withStyle(ChatFormatting.DARK_PURPLE));
        }

        if (this.type == Type.BOOTS) {
            tooltips.add(Component.translatable("item.cataclysm.ignitium_boots.desc").withStyle(ChatFormatting.DARK_PURPLE));
        }

        tooltips.add(Component.translatable("item.elemental_synergies.ignis_affinity.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.YELLOW)
                ));

    }

    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ESItemRegistry.IGNIS_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ESEffectRegistry.IGNIS_SOUL_STATE, 1200, 0, true, true, true));
                player.getCooldowns().addCooldown((Item) ESItemRegistry.IGNIS_CHESTPLATE.get(), 2400);

                if (player.level() instanceof ServerLevel serverLevel) {

                    double x = player.getX();
                    double y = player.getY() + 1.0D;
                    double z = player.getZ();

                    float radius = 5.0F;

                    // Soul flame explosion particles
                    PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new SoulFlameExplosionParticlesPacket(player.position(), radius));

                    // Sound
                    try {
                        serverLevel.playSound(null, x, y, z, ModSounds.IGNIS_HURT, SoundSource.PLAYERS, 1.5F, 1.0F);
                    } catch (Exception ignored) {
                    }

                    // Affect nearby entities
                    List<LivingEntity> entities = serverLevel.getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(radius),
                            entity -> entity != player
                    );

                    for (LivingEntity target : entities) {

                        // Damage
                        float damage = (float) player.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER) * 0.4F;

                        if (damage < 8F) {
                            damage = 8F;
                        }

                        target.hurt(serverLevel.damageSources().playerAttack(player), damage);
                        target.addEffect(new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND, 100, 0));
                        FireManager.setOnFire(target, 6.0F, FireManager.SOUL_FIRE_TYPE);

                        // Knockback
                        double dx = target.getX() - player.getX();
                        double dz = target.getZ() - player.getZ();

                        double dist = Math.max(dx * dx + dz * dz, 0.001D);

                        target.push(dx / dist * 2.0D, 0.35D, dz / dist * 2.0D);

                        target.hurtMarked = true;
                    }
                }
            }
        }
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (!(event.getSpell() instanceof AbstractIgnisSpell)) return;

            boolean fullSet = caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof IgnisArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof IgnisArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof IgnisArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof IgnisArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }


    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new IgnisArmorRenderer(new IgnisArmorModel());
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }

        if (entity instanceof Player player) {
            if (level.isClientSide) {
                if (this.type == Type.CHESTPLATE && player.getItemBySlot(EquipmentSlot.CHEST) == stack && HLKeybinds.ABILITY_1.consumeClick()) {
                    PacketDistributor.sendToServer(new HLMessageArmorKey(EquipmentSlot.CHEST.ordinal(), player.getId(), 5), new CustomPacketPayload[0]);
                    this.onKeyPacket(player, stack, 5);
                }
                return;
            }
        }


    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.MAGE_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.MAGE_SET_BONUS, 320, 0, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof IgnisArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof IgnisArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof IgnisArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof IgnisArmorItem;
    }
}

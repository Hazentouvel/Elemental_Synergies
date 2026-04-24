package net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.AzureLib;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.items.KeybindUsingArmor;
import com.github.L_Ender.cataclysm.message.MessageArmorKey;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.ESUtilities.Animations.ESDispatcher;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Particle.HolyNightFlameExplosionParticlesPacket;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSKeybindArmor;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSAttributeRegistry;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.PacketDistributor;

import net.hazen.elemental_synergies.Registries.ESParticleHelper;
import net.hazen.elemental_synergies.Registries.ESSounds;

import java.util.List;

public class ProvidenceArmorItem extends ImbuableHnSArmorItem implements HnSKeybindArmor {
    public final ESDispatcher dispatcher = new ESDispatcher();

    public ProvidenceArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.ASCENSION_MATERIAL, type, settings,
                new AttributeContainer[]{
                        new AttributeContainer(AttributeRegistry.MAX_MANA, (double)500.0F, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(GGAttributes.GEO_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(HnSAttributeRegistry.RADIANCE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(AttributeRegistry.SPELL_RESIST, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                });
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        return attributes.build().modifiers();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            int armorPieces = 0;
            boolean isCurrentStackEquipped = false;

            for (ItemStack armorStack : player.getArmorSlots()) {
                if (armorStack.getItem() instanceof ProvidenceArmorItem) {
                    armorPieces++;
                }
                if (armorStack == stack) {
                    isCurrentStackEquipped = true;
                }
            }

            if (isCurrentStackEquipped) {
                if (armorPieces == 4) {
                    player.fallDistance = 0.0f;
                }
                if (!player.isFallFlying()) {
                    dispatcher.idle(player, stack);
                }
            }
        }

        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }

        if (entity instanceof Player player) {
            if (level.isClientSide) {
                if (this.type == Type.CHESTPLATE && player.getItemBySlot(EquipmentSlot.CHEST) == stack && ModKeybind.CHESTPLATE_KEY_ABILITY.consumeClick()) {
                    PacketDistributor.sendToServer(new MessageArmorKey(EquipmentSlot.CHEST.ordinal(), player.getId(), 5), new CustomPacketPayload[0]);
                    this.onKeyPacket(player, stack, 5);
                }
                return;
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.MAGE_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.MAGE_SET_BONUS, 320, 0, false, false, false
            ));
        }
    }
    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof ProvidenceArmorItem;
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (entity instanceof Player player) {
            player.fallDistance = 0.0f;
            double speed = player.getDeltaMovement().length();
            if (speed > 0.65 || player.getXRot() > 35.0f) {
                dispatcher.gliding(player, stack);
            }
        }
        return true;
    }
    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ESItemRegistry.PROVIDENCE_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ESEffectRegistry.NIGHT_STATE, 1200, 0, true, true, true));
                player.getCooldowns().addCooldown((Item) ESItemRegistry.PROVIDENCE_CHESTPLATE.get(), 2400);

                if (player.level() instanceof ServerLevel serverLevel) {
                    double x = player.getX();
                    double y = player.getY() + player.getEyeHeight() * 0.5D;
                    double z = player.getZ();

                    int count = 40;
                    double speed = 0.4;

                    for (int i = 0; i < count; i++) {
                        double angle = (2 * Math.PI * i) / count;

                        double dx = Math.cos(angle);
                        double dz = Math.sin(angle);

                        serverLevel.sendParticles(ESParticleHelper.HOLY_NIGHT_EMBER_PARTICLE, x, y, z, 1, dx * speed, 0.05, dz * speed, 0.0);
                    }

                    serverLevel.sendParticles(ESParticleHelper.HOLY_NIGHT_IMPACT, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

                    try {
                        serverLevel.playSound(null, x, y, z, ESSounds.NIGHT_STATE_ACTIVATE.get(), SoundSource.PLAYERS, 1.5F, 1.0F);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

}
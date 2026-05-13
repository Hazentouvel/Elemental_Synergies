package net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas;

import com.github.L_Ender.cataclysm.init.ModKeybind;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.elemental_synergies.Registries.ESParticleHelper;
import net.hazen.elemental_synergies.Spells.AbstractSpells.BrimstoneSpells;
import net.hazen.elemental_synergies.Spells.AbstractSpells.CalamitasSpells;
import net.hazen.elemental_synergies.Spells.AbstractSpells.ProvidenceSpells;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.hazen.hazentouvelib.Items.Armor.HLKeybindArmor;
import net.hazen.hazentouvelib.Items.Armor.HLMessageArmorKey;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class SupremeCalamitasArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, HLKeybindArmor {
    public SupremeCalamitasArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.CALAMITAS_MATERIAL, type, settings,
                new AttributeContainer[]{
                        new AttributeContainer(AttributeRegistry.MAX_MANA, (double)500.0F, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(ASAttributeRegistry.RITUAL_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        new AttributeContainer(HLAttributeRegistry.SHADOW_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
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

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);


        if (this.type == Type.HELMET) {
            tooltipComponents.add(Component.translatable("item.elemental_synergies.full_set.description")
                    .withStyle(ChatFormatting.WHITE)
            );
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Component.translatable("item.elemental_synergies.supreme_calamitas_passive.description")
                        .withStyle(ChatFormatting.DARK_PURPLE)
                );
            } else {
                tooltipComponents.add(Component.translatable("item.discerning_the_eldritch.more_details").withStyle(ChatFormatting.GRAY));
            }
        }

        if (this.type == Type.CHESTPLATE) {
            tooltipComponents.add(Component.translatable("item.elemental_synergies.full_set.description")
                    .withStyle(ChatFormatting.WHITE)
            );
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Component.translatable("item.elemental_synergies.supreme_calamitas_ability.description", new Object[]{HLKeybinds.ABILITY_1.getTranslatedKeyMessage()})
                        .withStyle(ChatFormatting.YELLOW)
                );
            } else {
                tooltipComponents.add(Component.translatable("item.discerning_the_eldritch.more_details").withStyle(ChatFormatting.GRAY));
            }
        }

        tooltipComponents.add(Component.translatable("item.elemental_synergies.supreme_calamitas_affinity.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.YELLOW)
                ));
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
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem;
    }

    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ESItemRegistry.SUPREME_CALAMITAS_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ESEffectRegistry.BRIMSTONE_STATE, 1200, 0, true, true, true));
                player.getCooldowns().addCooldown((Item) ESItemRegistry.SUPREME_CALAMITAS_CHESTPLATE.get(), 2400);

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

                        serverLevel.sendParticles(ESParticleHelper.BRIMSTONE_EMBER_PARTICLE, x, y, z, 1, dx * speed, 0.05, dz * speed, 0.0);
                    }

                    serverLevel.sendParticles(ESParticleHelper.BRIMSTONE_IMPACT, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

                    try {
                        serverLevel.playSound(null, x, y, z, HnSSounds.CALAMITAS_EQUIP, SoundSource.PLAYERS, 1.5F, 1.0F);
                    }
                    catch (Exception ignored) {
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

            if (!(event.getSpell() instanceof BrimstoneSpells)) return;

            boolean fullSet = caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof SupremeCalamitasArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new SupremeCalamitasArmorRenderer(new SupremeCalamitasArmorModel());
    }

}

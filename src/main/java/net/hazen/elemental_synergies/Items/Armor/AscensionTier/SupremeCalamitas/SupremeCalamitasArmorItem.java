package net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.github.L_Ender.cataclysm.init.ModKeybind;
import com.github.L_Ender.cataclysm.message.MessageArmorKey;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.elemental_synergies.Registries.ESParticleHelper;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.elemental_synergies.Spells.AbstractSpells.CalamitasSpells;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSArmorMaterials;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSKeybindArmor;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.HnSUtilities.HnSKeybinds;
import net.hazen.hazennstuff.Registries.HnSAttributeRegistry;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class SupremeCalamitasArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, IDisableHat, HnSKeybindArmor {
    public SupremeCalamitasArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.CALAMITAS_MATERIAL, type, settings,
                new AttributeContainer[]{
                new AttributeContainer(AttributeRegistry.MAX_MANA, (double)500.0F, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ASAttributeRegistry.RITUAL_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(HnSAttributeRegistry.SHADOW_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
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
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }

        if (entity instanceof Player player) {
            if (level.isClientSide) {
                if (this.type == Type.CHESTPLATE && player.getItemBySlot(EquipmentSlot.CHEST) == stack && HnSKeybinds.ABILITY_1.consumeClick()) {
                    PacketDistributor.sendToServer(new MessageArmorKey(EquipmentSlot.CHEST.ordinal(), player.getId(), 5), new CustomPacketPayload[0]);
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

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new SupremeCalamitasArmorRenderer(new SupremeCalamitasArmorModel());
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem;
    }

    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> lines, @NotNull TooltipFlag flags) {
        lines.add(Component.translatable("item.hazennstuff.calamitas.description")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));

        if (this.type == Type.HELMET) {
            lines.add(Component.translatable("item.cataclysm.ignitium_helmet.desc").withStyle(ChatFormatting.DARK_GREEN));
            lines.add(Component.translatable("item.cataclysm.ignitium_helmet.desc2", new Object[]{HnSKeybinds.ABILITY_1.getTranslatedKeyMessage()}).withStyle(ChatFormatting.DARK_GREEN));
        }

        if (this.type == Type.CHESTPLATE) {
            super.appendHoverText(stack, context, lines, flags);
            AffinityData affinityData = AffinityData.getAffinityData(stack);
            if (!affinityData.affinityData().isEmpty()) {
                int i = TooltipsUtils.indexOfComponent(lines, "tooltip.hazennstuff.spellbook_spell_count");
                lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
            }

            lines.add(Component.translatable("item.cataclysm.ignitium_chestplate.desc").withStyle(ChatFormatting.DARK_GREEN));
        }

        if (this.type == Type.LEGGINGS) {
            lines.add(Component.translatable("item.cataclysm.ignitium_leggings.desc").withStyle(ChatFormatting.DARK_GREEN));
        }

        if (this.type == Type.BOOTS) {
            lines.add(Component.translatable("item.cataclysm.ignitium_boots.desc").withStyle(ChatFormatting.DARK_GREEN));
        }

    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (!(event.getSpell() instanceof CalamitasSpells)) return;

            boolean fullSet =
                    caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof SupremeCalamitasArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof SupremeCalamitasArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }

    public void onKeyPacket(Player player, ItemStack itemStack, int Type) {
        if (player != null) {
            if (Type == 5 && isWearingFullSet(player) && !player.getCooldowns().isOnCooldown((Item) ESItemRegistry.SUPREME_CALAMITAS_CHESTPLATE.get())) {
                player.addEffect(new MobEffectInstance(ESEffectRegistry.BRIMSTONE_STATE, 1200, 0, true, true, true));
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

                        serverLevel.sendParticles(ESParticleHelper.BRIMSTONE_EMBER_PARTICLE, x, y, z, 1, dx * speed, 0.05, dz * speed, 0.0);
                    }

                    serverLevel.sendParticles(ESParticleHelper.BRIMSTONE_IMPACT, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

                    try {
                        serverLevel.playSound(null, x, y, z, HnSSounds.CALAMITAS_EQUIP, SoundSource.PLAYERS, 1.5F, 1.0F);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }
}

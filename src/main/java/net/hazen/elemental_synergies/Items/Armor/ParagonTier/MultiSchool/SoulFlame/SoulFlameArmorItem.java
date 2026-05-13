package net.hazen.elemental_synergies.Items.Armor.ParagonTier.MultiSchool.SoulFlame;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import net.acetheeldritchking.discerning_the_eldritch.registries.DTEDataComponentRegistry;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.ProvidenceArmorItem;
import net.hazen.elemental_synergies.Spells.AbstractSpells.ProfaneSpells;
import net.hazen.elemental_synergies.Spells.AbstractSpells.SoulFlameSpells;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MFTECompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSArmorMaterials;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
//import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.util.List;

public class SoulFlameArmorItem extends ImbuableHnSArmorItem implements IDisableHat {

    public SoulFlameArmorItem(Type type, Properties settings) {
//        super(HnSArmorMaterials.SOUL_FLAME_MATERIAL, type, settings, pureTierMulti(
//                AttributeRegistry.FIRE_SPELL_POWER,
//                MFTEAttributeRegistries.SPIRIT_SPELL_POWER
//        ));

        super(HnSArmorMaterials.SOUL_FLAME_MATERIAL, type, settings, pureTier(
                AttributeRegistry.FIRE_SPELL_POWER
        ));
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        MFTECompat.addSpiritSpellPowerPure(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        return attributes.build().modifiers();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.MAGE_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.MAGE_SET_BONUS, 320, 0, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof SoulFlameArmorItem;
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        tooltipComponents.add(Component.translatable("item.elemental_synergies.soul_flame_passive.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.AQUA)
                        .withItalic(true))
        );

        tooltipComponents.add(Component.translatable("item.elemental_synergies.soul_flame_affinity.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.YELLOW)
                ));

    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (!(event.getSpell() instanceof SoulFlameSpells)) return;

            boolean fullSet =
                    caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SoulFlameArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SoulFlameArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof SoulFlameArmorItem &&
                            caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof SoulFlameArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }
}
package net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Scylla;

import com.github.L_Ender.cataclysm.init.ModKeybind;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.spells.ice.AbstractMaledictusSpell;
import net.hazen.elemental_synergies.ESUtilities.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Maledictus.MaledictusArmorItem;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.hazen.hazennstuff.Spells.AbstractSpells.HydroSpells;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.management.Attribute;
import java.util.List;

public class ScyllaArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, IDisableHat {
    public ScyllaArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.SCYLLA, type, settings, paragonTierDual(
                AttributeRegistry.LIGHTNING_SPELL_POWER,
                ASAttributeRegistry.HYDRO_MAGIC_POWER
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

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
        if (this.type == Type.HELMET) {
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_helmet.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.CHESTPLATE) {
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_chestplate.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_chestplate_passive1.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_chestplate_passive2.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.LEGGINGS) {
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_leggings.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_leggings_passive.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        } else if (this.type == Type.BOOTS) {
            tooltip.add(Component.translatable("item.elemental_synergies.scylla_boots.description")
                    .withStyle(ChatFormatting.DARK_PURPLE));
        }

        tooltip.add(Component.translatable("item.elemental_synergies.scylla_affinity.description")
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.YELLOW)
                ));

    }


    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new ScyllaArmorRenderer(new ScyllaArmorModel());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }
        if (entity instanceof Player player && !level.isClientSide() && isHelmet(player)) {
            waterBreathing(player);
        }
        if (entity instanceof Player player && !level.isClientSide() && isLeggings(player)) {
            dolphinsGrace(player);
        }


    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.MAGE_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.MAGE_SET_BONUS, 320, 0, false, false, false));
        }
    }

    private void waterBreathing(Player player) {
        if (!player.hasEffect(MobEffects.WATER_BREATHING)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, false, false));
        }
    }

    private void dolphinsGrace(Player player) {
        if (!player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 0, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof ScyllaArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof ScyllaArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof ScyllaArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof ScyllaArmorItem;
    }
    private boolean isHelmet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof ScyllaArmorItem;
    }
    private boolean isLeggings(Player player) {
        return player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof ScyllaArmorItem;
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (!(event.getSpell() instanceof HydroSpells)) return;

            boolean fullSet = caster.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ScyllaArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ScyllaArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ScyllaArmorItem &&
                    caster.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ScyllaArmorItem;

            if (fullSet) {
                event.addLevels(1);
            }
        }
    }


}

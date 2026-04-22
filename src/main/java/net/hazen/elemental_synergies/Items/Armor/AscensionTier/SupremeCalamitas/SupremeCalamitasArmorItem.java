package net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas;

import com.github.L_Ender.cataclysm.init.ModKeybind;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.GeckolibProvidenceArmorModel;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.GeckolibProvidenceArmorRenderer;
import net.hazen.elemental_synergies.Spells.ESSpellRegistries;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSArmorMaterials;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableGeckolibHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class SupremeCalamitasArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket, IDisableHat {
    public SupremeCalamitasArmorItem(Type type, Properties settings) {
        super(HnSArmorMaterials.CALAMITAS_MATERIAL, type, settings, pureTierTri(
                AttributeRegistry.FIRE_SPELL_POWER,
                AttributeRegistry.BLOOD_SPELL_POWER,
                ASAttributeRegistry.RITUAL_MAGIC_POWER
        ));
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
            lines.add(Component.translatable("item.cataclysm.ignitium_helmet.desc2", new Object[]{ModKeybind.HELMET_KEY_ABILITY.getTranslatedKeyMessage()}).withStyle(ChatFormatting.DARK_GREEN));
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

            if (event.getSpell() != ESSpellRegistries.BRIMSTONE_HELLBLAST.get()) return;

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
}

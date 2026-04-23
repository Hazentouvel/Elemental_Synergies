package net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.ExoMech;

import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.hazennstuff.HnSUtilities.Animations.HnSDispatcher;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.HnSUtilities.Armor.HnSArmorMaterials;
import net.hazen.hazennstuff.HnSUtilities.Armor.ImbuableHnSArmorItem;
import net.hazen.hazennstuff.Registries.HnSEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;

public class ExoMechArmorItem extends ImbuableHnSArmorItem implements IDisableJacket {
    // This is your class where you will setup the AzCommands/Animations you wish to play
    public final HnSDispatcher dispatcher;

    public ExoMechArmorItem(Type type, Properties settings) {
        super(HnSArmorMaterials.PURE_ARMOR_TIER_MATERIAL, type, settings, pureTier(
                ASAttributeRegistry.TECHNOMANCY_MAGIC_POWER
        ));
        this.dispatcher = new HnSDispatcher();
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
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
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof ExoMechArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof ExoMechArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof ExoMechArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof ExoMechArmorItem;
    }
}
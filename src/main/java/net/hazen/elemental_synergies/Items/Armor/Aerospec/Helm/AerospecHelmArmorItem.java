package net.hazen.elemental_synergies.Items.Armor.Aerospec.Helm;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.Items.Armor.Aerospec.AerospecArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.ImbuableESArmorItemGeckolib;
import net.hazen.hazennstuff.compat.ArsNoveauCompat;
import net.hazen.hazennstuff.compat.EndersSpellsAndStuffCompat;
import net.hazen.hazennstuff.compat.MalumCompat;
import net.hazen.hazennstuff.registries.HnSEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class AerospecHelmArmorItem extends ImbuableESArmorItemGeckolib implements IDisableJacket {
    public AerospecHelmArmorItem(Type type, Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.AEROMANCY_MATERIAL, type, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 150.0, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AASpells.Attributes.WIND_SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(Attributes.ATTACK_DAMAGE, 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(ALObjects.Attributes.ARMOR_SHRED, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        );
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        EndersSpellsAndStuffCompat.addSpellbladeSpellPower(attributes, group);
        return attributes.build().modifiers();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @NotNull TooltipContext context,
                                @NotNull List<Component> lines,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, lines, flag);

        // Custom item description section
        lines.add(Component.translatable("item.elemental_synergies.aerospec_helm.description")
                .withStyle(Style.EMPTY.withColor(0xa3b6ff).withItalic(true)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide() && isWearingFullSet(player)) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        if (!player.hasEffect(HnSEffects.SWORDMASTER_SET_BONUS)) {
            player.addEffect(new MobEffectInstance(HnSEffects.SWORDMASTER_SET_BONUS, 320, 0, false, false, false));
        }
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof AerospecHelmArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof AerospecArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof AerospecArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof AerospecArmorItem;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new AerospecHelmArmorModel());
    }
}
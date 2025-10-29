package net.hazen.elemental_synergies.Items.Armor.Titan.Geckolib;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.Items.Armor.ESArmorMaterials;
import net.hazen.elemental_synergies.Items.Armor.ImbuableESArmorItemGeckolib;
import net.hazen.elemental_synergies.Items.Armor.Titan.Azurelib.TitanArmorItem;
import net.hazen.hazennstuff.compat.ArsNoveauCompat;
import net.hazen.hazennstuff.compat.MalumCompat;
import net.hazen.hazennstuff.registries.HnSEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class GeckolibTitanArmorItem extends ImbuableESArmorItemGeckolib implements IDisableJacket {
    public GeckolibTitanArmorItem(Type type, Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.PURE_ARTIFACT_MATERIAL, type, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 150.0, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(GGAttributes.GEO_SPELL_POWER, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        );
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
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof TitanArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof TitanArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof TitanArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof TitanArmorItem;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new GeckolibTitanArmorModel());
    }
}
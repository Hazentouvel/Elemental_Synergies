package net.hazen.elemental_synergies.items.armor.Cloudsage.Hat;

import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.items.armor.Cloudsage.CloudmasterArmorItem;
import net.hazen.elemental_synergies.items.armor.ESArmorMaterials;
import net.hazen.elemental_synergies.items.armor.ImbuableESArmorItemGeckolib;
import net.hazen.elemental_synergies.items.armor.Titan.Azurelib.TitanArmorItem;
import net.hazen.hazennstuff.registries.HnSEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class CloudmasterHatArmorItem extends ImbuableESArmorItemGeckolib implements IDisableJacket {
    public CloudmasterHatArmorItem(Type type, Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.PURE_ARTIFACT_MATERIAL, type, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 150.0, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AASpells.Attributes.WIND_SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE)
        );
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
    public void appendHoverText(@NotNull ItemStack stack,
                                @NotNull TooltipContext context,
                                @NotNull List<Component> lines,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, lines, flag);

        // Custom item description section
        lines.add(Component.translatable("item.hazennstuff.cloudmaster_hat.description")
                .withStyle(Style.EMPTY.withColor(0xa3b6ff).withItalic(true)));
    }

    private boolean isWearingFullSet(Player player) {
        return player.getItemBySlot(Type.HELMET.getSlot()).getItem() instanceof CloudmasterHatArmorItem &&
                player.getItemBySlot(Type.CHESTPLATE.getSlot()).getItem() instanceof CloudmasterArmorItem &&
                player.getItemBySlot(Type.LEGGINGS.getSlot()).getItem() instanceof CloudmasterArmorItem &&
                player.getItemBySlot(Type.BOOTS.getSlot()).getItem() instanceof CloudmasterArmorItem;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new CloudmasterHatArmorModel());
    }
}
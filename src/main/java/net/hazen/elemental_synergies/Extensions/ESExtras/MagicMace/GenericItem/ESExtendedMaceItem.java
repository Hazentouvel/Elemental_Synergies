package net.hazen.elemental_synergies.Registries.ESExtras.MagicMace.GenericItem;

import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ESExtendedMaceItem extends MaceItem {
    public ESExtendedMaceItem(Properties pProperties) {
        super(pProperties);
    }

    public static ItemAttributeModifiers createAttributes(IronsWeaponTier pTier) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, pTier.getAttackDamageBonus(), Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, pTier.getSpeed(), Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND);

        for (AttributeContainer holder : pTier.getAdditionalAttributes()) {
            builder.add(
                    holder.attribute(),
                    holder.createModifier(EquipmentSlot.MAINHAND.getName()),
                    EquipmentSlotGroup.MAINHAND
            );
        }

        return builder.build();
    }
}

package net.hazen.elemental_synergies.Items.Armor;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ImbuableESArmorItemGeckolib extends ESArmorItemGeckolib implements IPresetSpellContainer {
    public ImbuableESArmorItemGeckolib(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties, AttributeContainer... attributeContainers) {
        super(material, type, properties, attributeContainers);
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null)
        {
            return;
        }

        if (itemStack.getItem() instanceof ArmorItem armorItem)
        {
            if (armorItem.getType() == ArmorItem.Type.CHESTPLATE)
            {
                if (!ISpellContainer.isSpellContainer(itemStack))
                {
                    var spellContainer = ISpellContainer.create(1, true, true);
                    itemStack.set(ComponentRegistry.SPELL_CONTAINER, spellContainer);
                }
            }
        }
    }
}
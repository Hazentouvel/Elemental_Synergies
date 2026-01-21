package net.hazen.elemental_synergies.Items.curios.Spellbooks.GrimoireOfCorruption;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.Dispatcher.ESDispatcher;
import net.hazen.hazennstuff.item.dispatcher.HnSItemDispatcher;
import net.hazen.hazennstuff.registries.HnSAttributeRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;


public class GrimoireOfCorruptionSpellbook extends SpellBook {
    public final ESDispatcher dispatcher;
    public GrimoireOfCorruptionSpellbook() {
        super(10);
        this.withSpellbookAttributes(new AttributeContainer[]{
                new AttributeContainer(HnSAttributeRegistry.SHADOW_MAGIC_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MFTEAttributeRegistries.SPIRIT_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, (double)200.0F, AttributeModifier.Operation.ADD_VALUE)

        });

        this.dispatcher = new ESDispatcher();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player )
        {
            dispatcher.idle(player, stack);
        }
    }
}

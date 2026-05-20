package net.hazen.elemental_synergies.Items.Curios.Charms;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.hazentouvelib.Rarities.HLRarities;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class LunarShardCurio extends CurioBaseItem {
    public LunarShardCurio() {
        super(ItemPropertiesHelper.equipment()
                .stacksTo(1)
                .fireResistant()
                .rarity(HLRarities.COSMIC_RARITY.getValue()));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        return attr;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (level.isClientSide && entity instanceof ItemEntity itemEntity) {
            if (!itemEntity.getItem().isEmpty()) {
                if (level.random.nextFloat() < 0.35F) {

                    double x = itemEntity.getX() + (level.random.nextDouble() - 0.5) * 0.4;
                    double y = itemEntity.getY() + 0.2 + level.random.nextDouble() * 0.3;
                    double z = itemEntity.getZ() + (level.random.nextDouble() - 0.5) * 0.4;

                    level.addParticle(ParticleTypes.FIREWORK, x, y, z, 0.0D, 0.02D, 0.0D);
                }
            }
        }

    }

    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}

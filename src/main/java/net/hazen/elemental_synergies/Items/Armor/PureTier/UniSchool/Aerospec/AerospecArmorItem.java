package net.hazen.elemental_synergies.Items.Armor.PureTier.UniSchool.Aerospec;

import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.hazen.elemental_synergies.Extensions.ESArmorMaterials;
import net.hazen.hazennstuff.Compat.ArsNoveauCompat;
import net.hazen.hazennstuff.Compat.MalumCompat;
import net.hazen.hazennstuff.Item.HnSUtilities.ImbuableGeckolibHnSArmorItem;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class AerospecArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket {
    public AerospecArmorItem(Type type, Properties settings) {
        super(ESArmorMaterials.AEROMANCY_MATERIAL, type, settings, pureTier(
                AASpells.Attributes.WIND_SPELL_POWER
        ));
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        var group = EquipmentSlotGroup.bySlot(getEquipmentSlot());
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();
        MalumCompat.addArcaneResonance(attributes, group);
        ArsNoveauCompat.addManaRegen(attributes, group);
        ArsNoveauCompat.addMaxMana(attributes, group);
        return attributes.build().modifiers();
    }

    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new AerospecArmorModel());
    }
}
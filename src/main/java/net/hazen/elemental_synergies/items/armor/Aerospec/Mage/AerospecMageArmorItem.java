package net.hazen.elemental_synergies.items.armor.Aerospec.Mage;

import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.items.armor.Cloudsage.CloudmasterArmorModel;
import net.hazen.elemental_synergies.items.armor.ESArmorMaterials;
import net.hazen.elemental_synergies.items.armor.ImbuableESArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AerospecMageArmorItem extends ImbuableESArmorItem implements IDisableJacket {
    public AerospecMageArmorItem(Type type, Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.AEROMANCY_MATERIAL, type, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 150.0, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AASpells.Attributes.WIND_SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new AerospecMageArmorModel());
    }
}
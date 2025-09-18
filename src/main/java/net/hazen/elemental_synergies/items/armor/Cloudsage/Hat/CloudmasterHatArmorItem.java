package net.hazen.elemental_synergies.items.armor.Cloudsage.Hat;

import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.items.armor.ESArmorMaterials;
import net.hazen.elemental_synergies.items.armor.ImbuableESArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CloudmasterHatArmorItem extends ImbuableESArmorItem implements IDisableJacket {
    public CloudmasterHatArmorItem(Type type, Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.PURE_ARTIFACT_MATERIAL, type, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 150.0, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AASpells.Attributes.WIND_SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .15, AttributeModifier.Operation.ADD_VALUE)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new CloudmasterHatArmorModel());
    }
}
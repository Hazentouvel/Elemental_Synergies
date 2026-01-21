package net.hazen.elemental_synergies.Items.Armor.ParagonTier.UniSchool.Cloudmaster;

import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.elemental_synergies.Extensions.ESArmorMaterials;
import net.hazen.hazennstuff.Item.HnSUtilities.ImbuableGeckolibHnSArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CloudmasterArmorItem extends ImbuableGeckolibHnSArmorItem implements IDisableJacket {
    public CloudmasterArmorItem(ArmorItem.Type type, Item.Properties settings) {
        // Add in your armor tier + additional attributes for your item
        super(ESArmorMaterials.AEROMANCY_MATERIAL, type, settings, paragonTier(
                AASpells.Attributes.WIND_SPELL_POWER
        ));
    }

    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new CloudmasterArmorModel());
    }
}
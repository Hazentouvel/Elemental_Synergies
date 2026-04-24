package net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SupremeCalamitasArmorModel extends DefaultedEntityGeoModel<SupremeCalamitasArmorItem> {
    public SupremeCalamitasArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(SupremeCalamitasArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "geo/armor/supreme_calamitas_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SupremeCalamitasArmorItem animatable) {
        ResourceLocation normalTex = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/supreme_calamitas_armor.png");
        ResourceLocation brimstoneStateTex = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "textures/armor/supreme_calamitas_hood_armor.png");

        Player player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ESEffectRegistry.BRIMSTONE_STATE)) {
            return brimstoneStateTex;
        }
        return normalTex;
    }

    @Override
    public ResourceLocation getAnimationResource(SupremeCalamitasArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "animations/armor/supreme_calamitas_armor.animation.json");
    }
}

package net.hazen.elemental_synergies.Entities.Renderer;

import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave.WaveMagicProjectileModel;
import net.hazen.elemental_synergies.Entities.Spells.Misc.ElementalSpearMagicProjectileModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class CataclysmModelLayers {
    public static final ModelLayerLocation ELEMENTAL_SPEAR_MODEL = createLocation("elemental_spear_model", "main");
    public static final ModelLayerLocation WAVE_MODEL = createLocation("wave_model", "main");


    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition layerdefinition3 = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F)), 64, 32);
        event.registerLayerDefinition(ELEMENTAL_SPEAR_MODEL, ElementalSpearMagicProjectileModel::createBodyLayer);

        event.registerLayerDefinition(WAVE_MODEL, WaveMagicProjectileModel::createBodyLayer);


    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("cataclysm", model), layer);
    }

}
package net.hazen.elemental_synergies.Items.Weapons.Ascended.Catastrophe;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CatastropheRenderer extends GeoItemRenderer<Catastrophe> {
    public CatastropheRenderer() {
        super(new CatastropheModel());
        this.addRenderLayer(new CatastropheLayer(this));
    }
}

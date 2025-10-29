package net.hazen.elemental_synergies.Items.staves.ArcaneMace;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ArcaneMaceRenderer extends GeoItemRenderer<ArcaneMaceItem> {
    public ArcaneMaceRenderer() {
        super(new ArcaneMaceModel());
        //addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}

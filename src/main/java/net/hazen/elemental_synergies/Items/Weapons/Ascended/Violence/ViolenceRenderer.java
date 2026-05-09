package net.hazen.elemental_synergies.Items.Weapons.Ascended.Violence;

import net.hazen.elemental_synergies.Items.Weapons.Ascended.Catastrophe.CatastropheLayer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ViolenceRenderer extends GeoItemRenderer<ViolenceItem> {
    public ViolenceRenderer() {
        super(new ViolenceModel());
        this.addRenderLayer(new ViolenceLayer(this));
    }
}

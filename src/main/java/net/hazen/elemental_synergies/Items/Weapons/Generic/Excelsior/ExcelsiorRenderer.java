package net.hazen.elemental_synergies.Items.Weapons.Generic.Excelsior;

import net.hazen.hazennstuff.Item.Weapons.Generic.Dawnmaker.DawnmakerItem;
import net.hazen.hazennstuff.Item.Weapons.Generic.Dawnmaker.DawnmakerModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ExcelsiorRenderer extends GeoItemRenderer<Excelsior> {
    public ExcelsiorRenderer() {
        super(new ExcelsiorModel());
    }
}

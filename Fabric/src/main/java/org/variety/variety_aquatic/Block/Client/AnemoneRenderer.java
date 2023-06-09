package org.variety.variety_aquatic.Block.Client;

import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnemoneRenderer extends GeoBlockRenderer<AnemoneTileEntity> {
    public AnemoneRenderer() {
        super(new AnemoneModel());
    }

}
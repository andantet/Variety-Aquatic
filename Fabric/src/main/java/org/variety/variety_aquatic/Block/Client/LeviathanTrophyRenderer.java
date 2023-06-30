package org.variety.variety_aquatic.Common.Block.Client;

import org.variety.variety_aquatic.Common.Block.Tile.LeviathanTrophyTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LeviathanTrophyRenderer extends GeoBlockRenderer<LeviathanTrophyTileEntity> {
    public LeviathanTrophyRenderer() {
        super(new LeviathanTrophyModel());
    }

}
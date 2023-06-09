package org.variety.variety_aquatic.Block.Client;

import org.variety.variety_aquatic.Block.Tile.LeviathanTrophyTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LeviathanTrophyRenderer extends GeoBlockRenderer<LeviathanTrophyTileEntity> {
    public LeviathanTrophyRenderer() {
        super(new LeviathanTrophyModel());
    }

}
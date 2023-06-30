package org.variety.variety_aquatic.Common.Block.Client;

import org.variety.variety_aquatic.Common.Block.Tile.GiantGlowingSquidTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GiantGlowingSquidTrophyRenderer extends GeoBlockRenderer<GiantGlowingSquidTileEntity> {
    public GiantGlowingSquidTrophyRenderer() {
        super(new GiantGlowingSquidTrophyModel());
    }

}
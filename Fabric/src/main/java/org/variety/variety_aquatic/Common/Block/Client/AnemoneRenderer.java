package org.variety.variety_aquatic.Common.Block.Client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.variety.variety_aquatic.Common.Block.Tile.AnemoneTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnemoneRenderer extends GeoBlockRenderer<AnemoneTileEntity> {
    public AnemoneRenderer(BlockEntityRendererFactory.Context context) {
        super(new AnemoneModel());
    }
}
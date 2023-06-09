package org.variety.variety_aquatic.Block.Client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.variety.variety_aquatic.Block.Tile.BeholderTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BeholderRenderer extends GeoBlockRenderer<BeholderTileEntity> {
    public BeholderRenderer(BlockEntityRendererFactory.Context context) {
        super(new BeholderModel());
    }

}
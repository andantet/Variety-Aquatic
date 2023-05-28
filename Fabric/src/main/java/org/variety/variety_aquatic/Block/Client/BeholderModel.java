package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.BeholderTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BeholderModel extends AnimatedGeoModel<BeholderTileEntity> {
    @Override
    public Identifier getAnimationResource(BeholderTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/beholder.animation.json");
    }

    @Override
    public Identifier getModelResource(BeholderTileEntity animatable) {
            return new Identifier(Variety_Aquatic.MOD_ID, "geo/beholder.geo.json");

    }

    @Override
    public Identifier getTextureResource(BeholderTileEntity entity) {

        return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/beholder_texture.png");

    }
}
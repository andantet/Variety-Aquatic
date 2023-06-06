package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnemoneModel extends AnimatedGeoModel<AnemoneTileEntity> {
    @Override
    public Identifier getAnimationFileLocation(AnemoneTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/anemone.animation.json");
    }
    @Override
    public Identifier getModelLocation(AnemoneTileEntity animatable) {
            return new Identifier(Variety_Aquatic.MOD_ID, "geo/anemone.geo.json");
    }
    @Override
    public Identifier getTextureLocation(AnemoneTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/anemone_texture.png");
    }
}
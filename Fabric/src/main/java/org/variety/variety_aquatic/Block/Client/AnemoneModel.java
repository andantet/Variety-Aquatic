package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnemoneModel extends GeoModel<AnemoneTileEntity> {
    @Override
    public Identifier getAnimationResource(AnemoneTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/anemone.animation.json");
    }
    @Override
    public Identifier getModelResource(AnemoneTileEntity animatable) {
            return new Identifier(Variety_Aquatic.MOD_ID, "geo/anemone.geo.json");
    }
    @Override
    public Identifier getTextureResource(AnemoneTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/anemone_texture.png");
    }
}
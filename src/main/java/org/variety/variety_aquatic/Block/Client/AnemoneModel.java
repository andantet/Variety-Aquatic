package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnemoneModel extends AnimatedGeoModel<AnemoneTileEntity> {
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
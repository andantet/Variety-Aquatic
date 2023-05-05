package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import org.variety.variety_aquatic.Block.Tile.LeviathanTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LeviathanTrophyModel extends AnimatedGeoModel<LeviathanTileEntity> {
    @Override
    public Identifier getAnimationResource(LeviathanTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/leviathantrophy.animation.json");
    }

    @Override
    public Identifier getModelResource(LeviathanTileEntity animatable) {
            return new Identifier(Variety_Aquatic.MOD_ID, "geo/leviathantrophy.geo.json");

    }

    @Override
    public Identifier getTextureResource(LeviathanTileEntity entity) {
        Difficulty difficulty = entity.getWorld().getDifficulty();
        if (difficulty == Difficulty.EASY) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/leviathan_trophy_copper.png");
        } else if (difficulty == Difficulty.NORMAL) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/leviathan_trophy_copper.png");
        } else if (difficulty == Difficulty.HARD) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/leviathan_trophy_copper.png");
        } else {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/leviathan_trophy_copper.png");
        }
    }
}
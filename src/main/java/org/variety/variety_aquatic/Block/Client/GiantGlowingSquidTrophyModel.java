package org.variety.variety_aquatic.Block.Client;

import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import org.variety.variety_aquatic.Block.Tile.GiantGlowingSquidTileEntity;
import org.variety.variety_aquatic.Block.Tile.LeviathanTrophyTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantGlowingSquidTrophyModel extends AnimatedGeoModel<GiantGlowingSquidTileEntity> {
    @Override
    public Identifier getAnimationResource(GiantGlowingSquidTileEntity entity) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/glowingsquidtrophy.animation.json");
    }

    @Override
    public Identifier getModelResource(GiantGlowingSquidTileEntity animatable) {
            return new Identifier(Variety_Aquatic.MOD_ID, "geo/glowingsquidtrophy.geo.json");

    }

    @Override
    public Identifier getTextureResource(GiantGlowingSquidTileEntity entity) {
        Difficulty difficulty = entity.getWorld().getDifficulty();
        if (difficulty == Difficulty.EASY) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquidtrophy_copper_texture.png");
        } else if (difficulty == Difficulty.NORMAL) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquidtrophy_iron_texture.png");
        } else if (difficulty == Difficulty.HARD) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquidtrophy_gold_texture.png");
        } else {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquidtrophy_copper_texture.png");
        }
    }
}
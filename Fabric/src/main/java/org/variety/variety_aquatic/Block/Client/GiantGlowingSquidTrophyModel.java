package org.variety.variety_aquatic.Common.Block.Client;

import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import org.variety.variety_aquatic.Common.Block.Tile.GiantGlowingSquidTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class GiantGlowingSquidTrophyModel extends GeoModel<GiantGlowingSquidTileEntity> {
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
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquid_trophy_copper.png");
        } else if (difficulty == Difficulty.NORMAL) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquid_trophy_iron.png");
        } else if (difficulty == Difficulty.HARD) {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquid_trophy_gold.png");
        } else {
            return new Identifier(Variety_Aquatic.MOD_ID, "textures/block/glowingsquid_trophy_copper.png");
        }
    }
}
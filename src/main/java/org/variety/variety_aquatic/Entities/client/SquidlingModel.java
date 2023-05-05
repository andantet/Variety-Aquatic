package org.variety.variety_aquatic.Entities.client;

import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.VampireSquidEntity;
import org.variety.variety_aquatic.Entities.custom.squidlingEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SquidlingModel extends AnimatedGeoModel<squidlingEntity> {
    @Override
    public Identifier getModelResource(squidlingEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/squidling.geo.json");
    }

    @Override
    public Identifier getTextureResource(squidlingEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/squidling_texture.png");
    }

    @Override
    public Identifier getAnimationResource(squidlingEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/squidling.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.SharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SharkModel extends AnimatedGeoModel<SharkEntity> {

    @Override
    public Identifier getModelResource(SharkEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/shark.geo.json");
    }

    @Override
    public Identifier getTextureResource(SharkEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/shark_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SharkEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/shark.animation.json");
    }
}
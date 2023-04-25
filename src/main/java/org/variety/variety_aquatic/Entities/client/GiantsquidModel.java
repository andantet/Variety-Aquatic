package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.GiantsquidEntity;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantsquidModel extends AnimatedGeoModel<GiantsquidEntity> {
    @Override
    public Identifier getModelResource(GiantsquidEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/giantsquid.geo.json");
    }

    @Override
    public Identifier getTextureResource(GiantsquidEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/giantsquid_texture.png");
    }

    @Override
    public Identifier getAnimationResource(GiantsquidEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/giantsquid.animation.json");
    }
}
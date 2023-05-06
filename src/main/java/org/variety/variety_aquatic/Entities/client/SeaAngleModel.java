package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.AnglerFishEntity;
import org.variety.variety_aquatic.Entities.custom.SeaangleEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeaAngleModel extends AnimatedGeoModel<SeaangleEntity> {
    @Override
    public Identifier getModelResource(SeaangleEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/seaangle.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeaangleEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/seaangle_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SeaangleEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/seaangle.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HermitcrabModel extends AnimatedGeoModel<HermitcrabEntity> {


    @Override
    public Identifier getModelResource(HermitcrabEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/hermitcrab.geo.json");
    }

    @Override
    public Identifier getTextureResource(HermitcrabEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/hermitcrab_texture.png");
    }

    @Override
    public Identifier getAnimationResource(HermitcrabEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/hermitcrab.animation.json");
    }
}
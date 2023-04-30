package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.CrabEntity;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrabModel extends AnimatedGeoModel<CrabEntity> {


    @Override
    public Identifier getModelResource(CrabEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrabEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/crab_texture.png");
    }

    @Override
    public Identifier getAnimationResource(CrabEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/crab.animation.json");
    }
}
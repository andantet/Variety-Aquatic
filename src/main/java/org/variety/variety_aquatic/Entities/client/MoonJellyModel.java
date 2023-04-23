package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.MoonJellyEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MoonJellyModel extends AnimatedGeoModel<MoonJellyEntity> {
    @Override
    public Identifier getModelResource(MoonJellyEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/moonjelly.geo.json");
    }

    @Override
    public Identifier getTextureResource(MoonJellyEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/moonjelly_texture.png");
    }

    @Override
    public Identifier getAnimationResource(MoonJellyEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/moonjelly.animation.json");
    }
}
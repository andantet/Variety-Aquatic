package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhaleSharkModel extends AnimatedGeoModel<WhaleSharkEntity> {
    @Override
    public Identifier getModelResource(WhaleSharkEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/whaleshark.geo.json");
    }

    @Override
    public Identifier getTextureResource(WhaleSharkEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/whaleshark_texture.png");
    }

    @Override
    public Identifier getAnimationResource(WhaleSharkEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/whaleshark.animation.json");
    }
}
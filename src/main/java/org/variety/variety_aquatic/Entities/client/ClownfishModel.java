package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClownfishModel extends AnimatedGeoModel<ClownfishEntity> {
    @Override
    public Identifier getModelResource(ClownfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/clownfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(ClownfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/clownfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(ClownfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/clownfish.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.OarfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OarfishModel extends AnimatedGeoModel<OarfishEntity> {
    @Override
    public Identifier getModelResource(OarfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/oarfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(OarfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/oarfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(OarfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/oarfish.animation.json");
    }
}
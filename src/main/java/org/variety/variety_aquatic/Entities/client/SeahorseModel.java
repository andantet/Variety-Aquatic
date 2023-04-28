package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.SeahorseEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeahorseModel extends AnimatedGeoModel<SeahorseEntity> {
    @Override
    public Identifier getModelResource(SeahorseEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/seahorse.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeahorseEntity object) {
        return SeahorseRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(SeahorseEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/seahorse.animation.json");
    }

}
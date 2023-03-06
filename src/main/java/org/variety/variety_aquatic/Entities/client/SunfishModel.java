package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.SunfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class SunfishModel extends GeoModel<SunfishEntity> {
    @Override
    public Identifier getModelResource(SunfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/sunfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(SunfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/sunfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SunfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/sunfish.animation.json");
    }
}
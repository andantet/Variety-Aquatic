package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.LionfishEntity;
import org.variety.variety_aquatic.Entities.custom.OpahEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class LionfishModel extends GeoModel<LionfishEntity> {
    @Override
    public Identifier getModelResource(LionfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/lionfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(LionfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/lionfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(LionfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/lionfish.animation.json");
    }
}
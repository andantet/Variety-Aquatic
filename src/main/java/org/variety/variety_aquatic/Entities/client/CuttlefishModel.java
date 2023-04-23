package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.CuttlefishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CuttlefishModel extends AnimatedGeoModel<CuttlefishEntity> {
    @Override
    public Identifier getModelResource(CuttlefishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/cuttlefish.geo.json");
    }

    @Override
    public Identifier getTextureResource(CuttlefishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/cuttlefish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(CuttlefishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/cuttlefish.animation.json");
    }
}
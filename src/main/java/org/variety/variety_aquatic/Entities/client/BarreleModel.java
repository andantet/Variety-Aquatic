package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BarreleyeEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BarreleModel extends AnimatedGeoModel<BarreleyeEntity> {
    @Override
    public Identifier getModelResource(BarreleyeEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/barreleye.geo.json");
    }

    @Override
    public Identifier getTextureResource(BarreleyeEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/barreleye_texture.png");
    }

    @Override
    public Identifier getAnimationResource(BarreleyeEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/barreleye.animation.json");
    }
}
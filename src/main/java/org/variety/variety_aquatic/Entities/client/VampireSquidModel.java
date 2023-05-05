package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.VampireSquidEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VampireSquidModel extends AnimatedGeoModel<VampireSquidEntity> {
    @Override
    public Identifier getModelResource(VampireSquidEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/vampiresquid.geo.json");
    }

    @Override
    public Identifier getTextureResource(VampireSquidEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/vampiresquid_texture.png");
    }

    @Override
    public Identifier getAnimationResource(VampireSquidEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/vampiresquid.animation.json");
    }
}
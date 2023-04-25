package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Entities.custom.PiranhaEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PiranhaModel extends AnimatedGeoModel<PiranhaEntity> {
    @Override
    public Identifier getModelResource(PiranhaEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/piranha.geo.json");
    }

    @Override
    public Identifier getTextureResource(PiranhaEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/piranha_texture.png");
    }

    @Override
    public Identifier getAnimationResource(PiranhaEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/piranha.animation.json");
    }
}
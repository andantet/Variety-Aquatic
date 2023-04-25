package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.YellowfinTunaEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YelllowFinTunaModel extends AnimatedGeoModel<YellowfinTunaEntity> {
    @Override
    public Identifier getModelResource(YellowfinTunaEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/yellowfintuna.geo.json");
    }

    @Override
    public Identifier getTextureResource(YellowfinTunaEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/yellowfintuna_texture.png");
    }

    @Override
    public Identifier getAnimationResource(YellowfinTunaEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/yellowfintuna.animation.json");
    }
}
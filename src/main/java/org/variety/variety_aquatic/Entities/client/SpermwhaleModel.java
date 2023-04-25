package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Entities.custom.SpermwhaleEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpermwhaleModel extends AnimatedGeoModel<SpermwhaleEntity> {
    @Override
    public Identifier getModelResource(SpermwhaleEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/spermwhale.geo.json");
    }

    @Override
    public Identifier getTextureResource(SpermwhaleEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/spermwhale_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SpermwhaleEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/spermwhale.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BluefinTuna;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlueFinTunaModel extends AnimatedGeoModel<BluefinTuna> {
    @Override
    public Identifier getModelResource(BluefinTuna object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/bluefintuna.geo.json");
    }

    @Override
    public Identifier getTextureResource(BluefinTuna object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/bluefintuna_texture.png");
    }

    @Override
    public Identifier getAnimationResource(BluefinTuna animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/bluefintuna.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.AnglerFishEntity;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnglerFishModel extends AnimatedGeoModel<AnglerFishEntity> {
    @Override
    public Identifier getModelResource(AnglerFishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/anglerfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnglerFishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/anglerfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(AnglerFishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/anglerfish.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Entities.custom.TetraEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TetraModel extends AnimatedGeoModel<TetraEntity> {
    @Override
    public Identifier getModelResource(TetraEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/tetra.geo.json");
    }

    @Override
    public Identifier getTextureResource(TetraEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/tetra_texture.png");
    }

    @Override
    public Identifier getAnimationResource(TetraEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/tetra.animation.json");
    }
}
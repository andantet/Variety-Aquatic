package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.OpahEntity;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class OpahModel extends GeoModel<OpahEntity> {
    @Override
    public Identifier getModelResource(OpahEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/opah.geo.json");
    }

    @Override
    public Identifier getTextureResource(OpahEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/opah_texture.png");
    }

    @Override
    public Identifier getAnimationResource(OpahEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/opah.animation.json");
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.JellyfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.model.GeoModel;

public class JellyfishModel extends GeoModel<JellyfishEntity> {
    @Override
    public Identifier getModelResource(JellyfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/jellyfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(JellyfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/jellyfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(JellyfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/jellyfish.animation.json");
    }
}
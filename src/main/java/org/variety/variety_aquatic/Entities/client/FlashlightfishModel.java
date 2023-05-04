package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BarreleyeEntity;
import org.variety.variety_aquatic.Entities.custom.FlashlightfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlashlightfishModel extends AnimatedGeoModel<FlashlightfishEntity> {
    @Override
    public Identifier getModelResource(FlashlightfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/flashlightfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(FlashlightfishEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/flashlightfish_texture.png");
    }

    @Override
    public Identifier getAnimationResource(FlashlightfishEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/flashlightfish.animation.json");
    }
}
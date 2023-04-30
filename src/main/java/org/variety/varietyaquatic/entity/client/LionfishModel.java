package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.CrabEntity;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LionfishModel extends AnimatedGeoModel<LionfishEntity> {
    @Override
    public ResourceLocation getModelResource(LionfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/lionfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LionfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/lionfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LionfishEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/lionfish.animation.json");
    }
}
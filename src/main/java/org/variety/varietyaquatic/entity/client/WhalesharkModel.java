package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import org.variety.varietyaquatic.entity.custom.WhaleSharkEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhalesharkModel extends AnimatedGeoModel<WhaleSharkEntity> {
    @Override
    public ResourceLocation getModelResource(WhaleSharkEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/whaleshark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WhaleSharkEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/whaleshark_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WhaleSharkEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/whaleshark.animation.json");
    }
}
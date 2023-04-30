package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import org.variety.varietyaquatic.entity.custom.OpahEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OpahModel extends AnimatedGeoModel<OpahEntity> {
    @Override
    public ResourceLocation getModelResource(OpahEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/opah.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OpahEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/opah_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OpahEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/opah.animation.json");
    }
}
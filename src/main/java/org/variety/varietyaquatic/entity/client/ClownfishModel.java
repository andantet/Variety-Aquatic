package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import org.variety.varietyaquatic.entity.custom.ClownfishEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClownfishModel extends AnimatedGeoModel<ClownfishEntity> {
    @Override
    public ResourceLocation getModelResource(ClownfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/clownfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClownfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/clownfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClownfishEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/clownfish.animation.json");
    }
}
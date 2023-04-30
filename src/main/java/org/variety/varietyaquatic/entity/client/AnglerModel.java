package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnglerModel extends AnimatedGeoModel<AnglerfishEntity> {
    @Override
    public ResourceLocation getModelResource(AnglerfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/anglerfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnglerfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/anglerfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnglerfishEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/anglerfish.animation.json");
    }
}
package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.TetraEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TetraModel extends AnimatedGeoModel<TetraEntity> {
    @Override
    public ResourceLocation getModelResource(TetraEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/tetra.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TetraEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/tetra_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TetraEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/tetra.animation.json");
    }
}
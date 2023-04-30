package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.Jellyfish;
import org.variety.varietyaquatic.entity.custom.MoonJelly;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Moonjellymodel extends AnimatedGeoModel<MoonJelly> {
    @Override
    public ResourceLocation getModelResource(MoonJelly object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/moonjelly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MoonJelly object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/moonjelly_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MoonJelly animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/moonjelly.animation.json");
    }
}
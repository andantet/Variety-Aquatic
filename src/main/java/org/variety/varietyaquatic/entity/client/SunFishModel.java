package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SunfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class SunFishModel extends GeoModel<SunfishEntity> {
    @Override
    public ResourceLocation getModelResource(SunfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/sunfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SunfishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/sunfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SunfishEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/sunfish.animation.json");
    }
}
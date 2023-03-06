package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import software.bernie.geckolib.model.GeoModel;

public class SharkModel extends GeoModel<SharkEntity> {
    @Override
    public ResourceLocation getModelResource(SharkEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/shark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SharkEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/shark_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SharkEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/shark.animation.json");
    }
}
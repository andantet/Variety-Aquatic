package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.HermitcrabEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib.model.GeoModel;

public class HermitcrabModel extends GeoModel<HermitcrabEntity> {
    @Override
    public ResourceLocation getModelResource(HermitcrabEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/hermitcrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HermitcrabEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/hermitcrab_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HermitcrabEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/hermitcrab.animation.json");
    }
}
package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SpottedStingrayEntity;
import org.variety.varietyaquatic.entity.custom.TetraEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StingrayModel extends AnimatedGeoModel<SpottedStingrayEntity> {
    @Override
    public ResourceLocation getModelResource(SpottedStingrayEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/spottedstingray.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpottedStingrayEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/spottedstingray_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpottedStingrayEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/spottedstingray.animation.json");
    }
}
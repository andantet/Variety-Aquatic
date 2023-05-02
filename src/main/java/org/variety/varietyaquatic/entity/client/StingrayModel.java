package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SpottedStingrayEntity;
import software.bernie.geckolib.model.GeoModel;

public class StingrayModel extends GeoModel<SpottedStingrayEntity> {
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
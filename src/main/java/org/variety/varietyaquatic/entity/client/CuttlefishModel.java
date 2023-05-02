package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.ClownfishEntity;
import org.variety.varietyaquatic.entity.custom.CuttlefishEntity;
import software.bernie.geckolib.model.GeoModel;

public class CuttlefishModel extends GeoModel<CuttlefishEntity> {
    @Override
    public ResourceLocation getModelResource(CuttlefishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/cuttlefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CuttlefishEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/cuttlefish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CuttlefishEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/cuttlefish.animation.json");
    }
}
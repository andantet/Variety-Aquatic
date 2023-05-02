package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.BettaEntity;
import org.variety.varietyaquatic.entity.custom.SeahorseEntity;
import software.bernie.geckolib.model.GeoModel;

public class SeahorseModel extends GeoModel<SeahorseEntity> {
    @Override
    public ResourceLocation getModelResource(SeahorseEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/seahorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeahorseEntity object) {
        return BettaRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(SeahorseEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/seahorse.animation.json");
    }
}
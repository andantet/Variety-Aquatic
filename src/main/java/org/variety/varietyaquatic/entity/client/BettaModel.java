package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import org.variety.varietyaquatic.entity.custom.BettaEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BettaModel extends AnimatedGeoModel<BettaEntity> {
    @Override
    public ResourceLocation getModelResource(BettaEntity object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/betta.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BettaEntity object) {
        return BettaRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(BettaEntity animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/betta.animation.json");
    }
}
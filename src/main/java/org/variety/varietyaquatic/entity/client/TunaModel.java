package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.CuttlefishEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib.model.GeoModel;

public class TunaModel extends GeoModel<YellowFinTuna> {
    @Override
    public ResourceLocation getModelResource(YellowFinTuna animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/yellowfintuna.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YellowFinTuna animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/yellowfintuna_texture.png");
    }
    @Override
    public ResourceLocation getAnimationResource(YellowFinTuna animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/yellowfintuna.animation.json");
    }
}
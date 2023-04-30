package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.CuttlefishEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TunaModel extends AnimatedGeoModel<YellowFinTuna> {
    @Override
    public ResourceLocation getModelResource(YellowFinTuna object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/yellowfintuna.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YellowFinTuna object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/yellowfintuna_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(YellowFinTuna animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/yellowfintuna.animation.json");
    }
}
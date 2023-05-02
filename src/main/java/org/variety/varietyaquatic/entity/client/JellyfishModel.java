package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.Jellyfish;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class JellyfishModel extends GeoModel<Jellyfish> {
    @Override
    public ResourceLocation getModelResource(Jellyfish object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/jellyfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Jellyfish object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/jellyfish_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Jellyfish animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/jellyfish.animation.json");
    }
}
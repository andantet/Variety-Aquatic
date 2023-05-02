package org.variety.varietyaquatic.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import org.variety.varietyaquatic.entity.custom.Piranha;
import software.bernie.geckolib.model.GeoModel;

public class PiranhaModel extends GeoModel<Piranha> {
    @Override
    public ResourceLocation getModelResource(Piranha object) {
        return new ResourceLocation(VarietyAquatic.MODID, "geo/piranha.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Piranha object) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/piranha_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Piranha animatable) {
        return new ResourceLocation(VarietyAquatic.MODID, "animations/piranha.animation.json");
    }
}
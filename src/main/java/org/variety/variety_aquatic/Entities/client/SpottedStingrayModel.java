package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BluefinTuna;
import org.variety.variety_aquatic.Entities.custom.SpottedStingray;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpottedStingrayModel extends AnimatedGeoModel<SpottedStingray> {
    @Override
    public Identifier getModelResource(SpottedStingray object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/spottedstingray.geo.json");
    }

    @Override
    public Identifier getTextureResource(SpottedStingray object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/spottedstingray_texture.png");
    }

    @Override
    public Identifier getAnimationResource(SpottedStingray animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/spottedstingray.animation.json");
    }
}
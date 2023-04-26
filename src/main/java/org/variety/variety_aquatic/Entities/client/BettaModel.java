package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BettaEntity;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BettaModel extends AnimatedGeoModel<BettaEntity> {
    @Override
    public Identifier getModelResource(BettaEntity object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/betta.geo.json");
    }

    @Override
    public Identifier getTextureResource(BettaEntity object) {
        return BettaRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(BettaEntity animatable) {
        return new Identifier(Variety_Aquatic.MOD_ID, "animations/betta.animation.json");
    }

}
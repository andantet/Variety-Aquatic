package org.variety.variety_aquatic.Entities.client;

import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.ProjectileEntity.BlindnessProjectile;
import org.variety.variety_aquatic.Entities.custom.AnglerFishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlindnessProjectileModel extends AnimatedGeoModel<BlindnessProjectile> {


    // METHODS //
    // PUBLIC
    @Override
    public Identifier getAnimationResource(BlindnessProjectile animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(BlindnessProjectile object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "geo/blindnessprojectile.geo.json");
    }

    @Override
    public Identifier getTextureResource(BlindnessProjectile object) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/blindnessprojectile.png");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setLivingAnimations(BlindnessProjectile entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
    }
}
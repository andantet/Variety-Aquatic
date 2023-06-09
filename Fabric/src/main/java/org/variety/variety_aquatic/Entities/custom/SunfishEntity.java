package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;


public class SunfishEntity extends VarietyFish {

    public SunfishEntity(EntityType<? extends SunfishEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.sunfish_heath)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.sunfish_speed);
    }


    @Override
    public <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("swim2", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isDead()){
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}
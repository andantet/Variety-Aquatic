package org.variety.variety_aquatic.Common.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;


public class FlashlightfishEntity extends VarietyFish {

    public FlashlightfishEntity(EntityType<? extends FlashlightfishEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.flashlight_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.flashlight_speed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.flashlight_followrange);
    }
}
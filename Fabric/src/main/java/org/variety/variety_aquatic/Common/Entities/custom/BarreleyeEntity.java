package org.variety.variety_aquatic.Common.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;


public class BarreleyeEntity extends VarietyFish {

    public BarreleyeEntity(EntityType<? extends BarreleyeEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.barreleye_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.barreleye_speed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.barreleye_followrange);
    }
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

}
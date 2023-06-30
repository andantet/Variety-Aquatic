package org.variety.variety_aquatic.Common.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;


public class SeaangleEntity extends VarietyFish {

    public SeaangleEntity(EntityType<? extends SeaangleEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.seaangle_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.seaangle_speed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.seaangle_followrange);
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
    }
}
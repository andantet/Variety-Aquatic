package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;


public class OpahEntity extends VarietyFish {

    public OpahEntity(EntityType<? extends OpahEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.opah_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.opah_speed);
    }


}
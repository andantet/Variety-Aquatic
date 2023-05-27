package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.world.World;

public class SchoolingVarietyFish extends VarietyFish {
    public SchoolingVarietyFish(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }
}

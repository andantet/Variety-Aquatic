package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.variety.variety_aquatic.Entities.custom.AI.TunaJumpGoal;
import org.variety.variety_aquatic.Util.NewConfig;


public class YellowfinTunaEntity extends SchoolingVarietyFish {

    public YellowfinTunaEntity(EntityType<? extends YellowfinTunaEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(5, new TunaJumpGoal(this, 10));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.yellowfin_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.yellowfin_speed);
    }

    public int getMaxGroupSize() {
        return 4;
    }

    public static boolean canTunaSpawn(EntityType<YellowfinTunaEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getFluidState(pos.down()).isIn(FluidTags.WATER) && world.getBlockState(pos.up()).isOf(Blocks.WATER) || WaterCreatureEntity.canSpawn(type, world, reason, pos, random);
    }
}
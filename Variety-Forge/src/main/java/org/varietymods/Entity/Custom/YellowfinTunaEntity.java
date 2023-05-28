package org.varietymods.Entity.Custom;



import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.varietymods.Entity.AI.TunaJump;

import java.util.Random;
import java.util.function.Predicate;


public class YellowfinTunaEntity extends SchoolingVarietyFish {

    public YellowfinTunaEntity(EntityType<? extends YellowfinTunaEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new TunaJump(this, 10));
    }

    public static AttributeSupplier setAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 1).build();
    }



    public int getMaxGroupSize() {
        return 4;
    }

}
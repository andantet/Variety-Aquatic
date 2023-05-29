package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.Variant.SeahorseVariant;
import org.variety.variety_aquatic.Util.NewConfig;
import org.varietymods.varietyapi.API.IVariantEntity;

public class SeahorseEntity extends VarietyFish implements IVariantEntity<SeahorseVariant> {
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT = DataTracker.registerData(SeahorseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public SeahorseEntity(EntityType<? extends VarietyFish> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        super.initGoals();
        goalSelector.add(1, new MoveToKelpGoal(this));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.seahorse_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.seahorse_speed);
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (spawnReason == SpawnReason.BUCKET && entityNbt != null && entityNbt.contains("BucketVariantTag", 3)) {
            setVariant(SeahorseVariant.byId(entityNbt.getInt("BucketVariantTag")));
        }
        else {
            SeahorseVariant variant = Util.getRandom(SeahorseVariant.values(), this.random);
            setVariant(variant);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public SeahorseVariant getVariant() {
        return SeahorseVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(SeahorseVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    static class MoveToKelpGoal extends MoveToTargetPosGoal {

        public MoveToKelpGoal(SeahorseEntity mob) {
            super(mob, 1f, 6,6);
        }

        @Override
        public double getDesiredDistanceToTarget() {
            return 0d;
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            BlockState state = world.getBlockState(pos.up());
            return state.isOf(Blocks.KELP) || state.isOf(Blocks.KELP_PLANT);
        }
    }
}

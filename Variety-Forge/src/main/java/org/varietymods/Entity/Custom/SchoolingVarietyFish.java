package org.varietymods.Entity.Custom;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import org.varietymods.Entity.AI.FollowGroupLeaderGoal;

import java.util.List;
import java.util.stream.Stream;

public class SchoolingVarietyFish extends VarietyFish {

    // Literally just the Schooling fish class with some tweaks

    @Nullable
    private SchoolingVarietyFish leader;
    private int groupSize = 1;

    public SchoolingVarietyFish(EntityType<? extends AbstractFish> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowGroupLeaderGoal(this));
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag entityNbt) {
        super.finalizeSpawn(world, difficulty, spawnReason, (SpawnGroupData)entityData, entityNbt);
        if (entityData == null) {
            entityData = new FishData(this);
        } else {
            this.joinGroupOf(((FishData)entityData).leader);
        }

        return (SpawnGroupData)entityData;
    }

    public int getLimitPerChunk() {
        return this.getMaxGroupSize();
    }

    public int getMaxGroupSize() {
        return super.getMaxSpawnClusterSize();
    }

    protected boolean hasSelfControl() {
        return !this.hasLeader();
    }

    public boolean hasLeader() {
        return this.leader != null && this.leader.isAlive();
    }

    public SchoolingVarietyFish joinGroupOf(SchoolingVarietyFish groupLeader) {
        this.leader = groupLeader;
        groupLeader.increaseGroupSize();
        return groupLeader;
    }

    public void leaveGroup() {
        this.leader.decreaseGroupSize();
        this.leader = null;
    }

    private void increaseGroupSize() {
        ++this.groupSize;
    }

    private void decreaseGroupSize() {
        --this.groupSize;
    }

    public boolean canHaveMoreFishInGroup() {
        return this.hasOtherFishInGroup() && this.groupSize < this.getMaxGroupSize();
    }

    public void tick() {
        super.tick();
        if (this.hasOtherFishInGroup() && this.level.random.nextInt(200) == 1) {
            List<? extends AbstractFish> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.groupSize = 1;
            }
        }

    }

    public boolean hasOtherFishInGroup() {
        return this.groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return this.distanceToSqr(this.leader) <= 121.0;
    }

    public void moveTowardLeader() {
        if (this.hasLeader()) {
            this.getNavigation().moveTo(this.leader, 1.0);
        }
    }

    public void pullInOtherFish(Stream<? extends SchoolingVarietyFish> fish) {
        fish.limit((long)(this.getMaxGroupSize() - this.groupSize)).filter((fishx) -> {
            return fishx != this;
        }).forEach((fishx) -> {
            fishx.joinGroupOf(this);
        });
    }

    public static class FishData implements SpawnGroupData {
        public final SchoolingVarietyFish leader;

        public FishData(SchoolingVarietyFish leader) {
            this.leader = leader;
        }
    }
}
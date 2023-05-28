package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.custom.AI.FollowGroupLeaderGoal;

import java.util.List;
import java.util.stream.Stream;

public class SchoolingVarietyFish extends VarietyFish {

    // Literally just the Schooling fish class with some tweaks

    @Nullable
    private SchoolingVarietyFish leader;
    private int groupSize = 1;

    public SchoolingVarietyFish(EntityType<? extends VarietyFishFish> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(5, new FollowGroupLeaderGoal(this));
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        super.initialize(world, difficulty, spawnReason, (EntityData)entityData, entityNbt);
        if (entityData == null) {
            entityData = new FishData(this);
        } else {
            this.joinGroupOf(((FishData)entityData).leader);
        }

        return (EntityData)entityData;
    }

    public int getLimitPerChunk() {
        return this.getMaxGroupSize();
    }

    public int getMaxGroupSize() {
        return super.getLimitPerChunk();
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
        if (this.hasOtherFishInGroup() && this.world.random.nextInt(200) == 1) {
            List<? extends VarietyFishFish> list = this.world.getNonSpectatingEntities(this.getClass(), this.getBoundingBox().expand(8.0, 8.0, 8.0));
            if (list.size() <= 1) {
                this.groupSize = 1;
            }
        }

    }

    public boolean hasOtherFishInGroup() {
        return this.groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return this.squaredDistanceTo(this.leader) <= 121.0;
    }

    public void moveTowardLeader() {
        if (this.hasLeader()) {
            this.getNavigation().startMovingTo(this.leader, 1.0);
        }
    }

    public void pullInOtherFish(Stream<? extends SchoolingVarietyFish> fish) {
        fish.limit((long)(this.getMaxGroupSize() - this.groupSize)).filter((fishx) -> {
            return fishx != this;
        }).forEach((fishx) -> {
            fishx.joinGroupOf(this);
        });
    }

    public static class FishData implements EntityData {
        public final SchoolingVarietyFish leader;

        public FishData(SchoolingVarietyFish leader) {
            this.leader = leader;
        }
    }
}

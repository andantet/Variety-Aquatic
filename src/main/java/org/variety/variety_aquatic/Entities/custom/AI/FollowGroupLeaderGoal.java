package org.variety.variety_aquatic.Entities.custom.AI;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.SchoolingFishEntity;
import org.variety.variety_aquatic.Entities.custom.SchoolingVarietyFish;

import java.util.List;
import java.util.function.Predicate;

public class FollowGroupLeaderGoal extends Goal{
    private static final int MIN_SEARCH_DELAY = 200;
    private final SchoolingVarietyFish fish;
    private int moveDelay;
    private int checkSurroundingDelay;

    public FollowGroupLeaderGoal(SchoolingVarietyFish fish) {
        this.fish = fish;
        this.checkSurroundingDelay = this.getSurroundingSearchDelay(fish);
    }

    protected int getSurroundingSearchDelay(SchoolingVarietyFish fish) {
        return toGoalTicks(200 + fish.getRandom().nextInt(200) % 20);
    }

    public boolean canStart() {
        if (this.fish.hasOtherFishInGroup()) {
            return false;
        } else if (this.fish.hasLeader()) {
            return true;
        } else if (this.checkSurroundingDelay > 0) {
            --this.checkSurroundingDelay;
            return false;
        } else {
            this.checkSurroundingDelay = this.getSurroundingSearchDelay(this.fish);
            Predicate<SchoolingVarietyFish> predicate = (fish) -> {
                return fish.canHaveMoreFishInGroup() || !fish.hasLeader();
            };
            List<? extends SchoolingVarietyFish> list = this.fish.world.getEntitiesByClass(this.fish.getClass(), this.fish.getBoundingBox().expand(8.0, 8.0, 8.0), predicate);
            SchoolingVarietyFish schoolingFishEntity = DataFixUtils.orElse(list.stream().filter(SchoolingVarietyFish::canHaveMoreFishInGroup).findAny(), this.fish);
            schoolingFishEntity.pullInOtherFish(list.stream().filter((fish) -> {
                return !fish.hasLeader();
            }));
            return this.fish.hasLeader();
        }
    }

    public boolean shouldContinue() {
        return this.fish.hasLeader() && this.fish.isCloseEnoughToLeader();
    }

    public void start() {
        this.moveDelay = 0;
    }

    public void stop() {
        this.fish.leaveGroup();
    }

    public void tick() {
        if (--this.moveDelay <= 0) {
            this.moveDelay = this.getTickCount(10);
            this.fish.moveTowardLeader();
        }
    }
}

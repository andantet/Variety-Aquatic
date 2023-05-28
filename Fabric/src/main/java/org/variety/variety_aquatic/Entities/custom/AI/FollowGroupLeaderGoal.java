package org.variety.variety_aquatic.Entities.custom.AI;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.SchoolingFishEntity;
import org.variety.variety_aquatic.Entities.custom.SchoolingVarietyFish;

import java.util.List;
import java.util.function.Predicate;

public class FollowGroupLeaderGoal extends Goal {
    private static final int MIN_SEARCH_DELAY = 200;
    private final SchoolingVarietyFish fish;
    private int moveDelay;
    private int checkSurroundingDelay;

    public FollowGroupLeaderGoal(SchoolingVarietyFish fish) {
        this.fish = fish;
        this.checkSurroundingDelay = getSurroundingSearchDelay(fish);
    }

    protected int getSurroundingSearchDelay(SchoolingVarietyFish fish) {
        int randomDelay = fish.getRandom().nextInt(200) % 20;
        return toGoalTicks(MIN_SEARCH_DELAY + randomDelay);
    }

    public boolean canStart() {
        if (fish.hasOtherFishInGroup() || fish.hasLeader()) {
            return false;
        }

        if (checkSurroundingDelay > 0) {
            checkSurroundingDelay--;
            return false;
        }

        checkSurroundingDelay = getSurroundingSearchDelay(fish);

        Predicate<SchoolingVarietyFish> predicate = f -> f.canHaveMoreFishInGroup() || !f.hasLeader();
        List<? extends SchoolingVarietyFish> fishList = fish.world.getEntitiesByClass(
                fish.getClass(), fish.getBoundingBox().expand(8.0, 8.0, 8.0), predicate);

        SchoolingVarietyFish schoolingFishEntity = DataFixUtils.orElse(
                fishList.stream().filter(SchoolingVarietyFish::canHaveMoreFishInGroup).findAny(), fish);

        schoolingFishEntity.pullInOtherFish(
                fishList.stream().filter(f -> !f.hasLeader()));

        return fish.hasLeader();
    }

    public boolean shouldContinue() {
        return fish.hasLeader() && fish.isCloseEnoughToLeader();
    }

    public void start() {
        moveDelay = 0;
    }

    public void stop() {
        fish.leaveGroup();
    }

    public void tick() {
        if (--moveDelay <= 0) {
            moveDelay = getTickCount(10);
            fish.moveTowardLeader();
        }
    }
}

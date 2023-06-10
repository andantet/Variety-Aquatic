package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;

import java.util.List;

public class WhaleSharkEntity extends VarietyFish {
    private boolean isHungry = false;
    private int fishEaten = 0;

    public WhaleSharkEntity(EntityType<? extends WhaleSharkEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.whaleshark_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.whaleshark_speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, NewConfig.whaleshark_damage)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, NewConfig.whaleshark_knockback)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.whaleshark_follow);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("IsHungry", this.isHungry);
        nbt.putInt("FishEaten", this.fishEaten);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.isHungry = nbt.getBoolean("IsHungry");
        this.fishEaten = nbt.getInt("FishEaten");
    }

    protected void initGoals() {
        super.initGoals();
        if (isHungry()) {
            this.goalSelector.add(4, new MeleeAttackGoal(this, 1.2000000476837158D, true));
            this.targetSelector.add(4, new ActiveTargetGoal<>(this, FishEntity.class, 10, true, true, null));
        }
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, AnimalEntity.class, 10, true, true, null));
    }

    private boolean isHungry() {
        return this.getMoistness() < 1200;
    }

    public void eatFish() {
        this.setMoistness(Math.min(2400, this.getMoistness() + 1200));
    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }


    public void tick() {
        super.tick();
        if (!this.isAiDisabled()) {
            if (this.getTarget() == null && this.isHungry()) {
                List<FishEntity> nearbyFish = this.getWorld().getEntitiesByClass(FishEntity.class, this.getBoundingBox().expand(12.0D, 8.0D, 12.0D), entity -> entity.isTouchingWater() && entity.isAlive());
                if (!nearbyFish.isEmpty()) {
                    FishEntity targetFish = nearbyFish.get(this.random.nextInt(nearbyFish.size()));
                    this.setTarget(targetFish);
                }
            } else if (this.getTarget() instanceof FishEntity && !this.isHungry()) {
                this.setTarget(null);
            }
        }
    }
}

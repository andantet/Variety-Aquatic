package org.variety.variety_aquatic.Common.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
@SuppressWarnings({"ConstantConditions", "FieldMayBeFinal", "rawtypes"})
public class SharkEntity extends VarietyFish {
    private static final TrackedData<Integer> SHARKHUNGER;

    public SharkEntity(EntityType<? extends SharkEntity> entityType, World world) {
        super(entityType, world);
    }

    public int getSharkhunger() {
        return this.dataTracker.get(SHARKHUNGER);
    }

    public void setSharkhunger(int sharkhunger) {
        this.dataTracker.set(SHARKHUNGER, sharkhunger);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHARKHUNGER, 10);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Hunger", this.getSharkhunger());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSharkhunger(nbt.getInt("Hunger"));
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.2000000476837158D, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
        if (NewConfig.shark_attack_fish) {
            this.targetSelector.add(4, new ActiveTargetGoal<>(this, FishEntity.class, 10, true, true, null));
        }
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, AnimalEntity.class, 10, true, true, null));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.shark_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.shark_speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, NewConfig.shark_damage)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, NewConfig.shark_knockback)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.shark_follow);
    }

    static {
        SHARKHUNGER = DataTracker.registerData(SharkEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    @Override
    public <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isAttacking()){
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isDead()){
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;

        }
        event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}
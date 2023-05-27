package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Predicate;
@SuppressWarnings({"ConstantConditions", "FieldMayBeFinal", "rawtypes"})
public class SharkEntity extends VarietyFish {
    static final TargetPredicate CLOSE_PLAYER_PREDICATE;
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

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.5F;
    }

    static {
        SHARKHUNGER = DataTracker.registerData(SharkEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if(this.isAttacking()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        if(this.isDead()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", true));
            return PlayState.CONTINUE;

        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));

        return PlayState.CONTINUE;
    }
}
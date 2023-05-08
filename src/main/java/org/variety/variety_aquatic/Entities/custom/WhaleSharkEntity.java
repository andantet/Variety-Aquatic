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

import java.util.List;
import java.util.function.Predicate;

public class WhaleSharkEntity extends WaterCreatureEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);


    static final TargetPredicate CLOSE_PLAYER_PREDICATE;
    private static final TrackedData<Integer> MOISTNESS;
    private boolean isHungry = false;
    private int fishEaten = 0;

    public WhaleSharkEntity(EntityType<? extends WhaleSharkEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new YawAdjustingLookControl(this, 10);
    }
    static {
        MOISTNESS = DataTracker.registerData(WhaleSharkEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }
    public long getTimeOfDay() {
        return this.world.getTimeOfDay();
    }
    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.whaleshark_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.whaleshark_speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, NewConfig.whaleshark_damage)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, NewConfig.whaleshark_knockback)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.whaleshark_follow);
    }
    private static class InWaterPredicate implements Predicate<LivingEntity> {
        private final WhaleSharkEntity owner;

        public InWaterPredicate(WhaleSharkEntity owner) {
            this.owner = owner;
        }

        public boolean test(@Nullable LivingEntity entity) {
            return entity != null && entity.isTouchingWater();
        }
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setAir(this.getMaxAir());
        this.setPitch(0.0F);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public int getMoistness() {
        return this.dataTracker.get(MOISTNESS);
    }

    public void setMoistness(int moistness) {
        this.dataTracker.set(MOISTNESS, moistness);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOISTNESS, 2400);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Moistness", this.getMoistness());
        nbt.putBoolean("IsHungry", this.isHungry);
        nbt.putInt("FishEaten", this.fishEaten);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setMoistness(nbt.getInt("Moistness"));
        this.isHungry = nbt.getBoolean("IsHungry");
        this.fishEaten = nbt.getInt("FishEaten");
    }

    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(4, new SwimAroundGoal(this, 0.8, 10));
        this.goalSelector.add(7, new EscapeDangerGoal(this, 2.1f));

        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
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
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.5F;
    }

    public int getLookPitchSpeed() {
        return 1;
    }

    public int getBodyYawSpeed() {
        return 1;
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }

    }

    public void tick() {
        super.tick();
        if (this.isAiDisabled()) {
            this.setAir(this.getMaxAir());
        } else {
            if (this.isWet()) {
                this.setMoistness(2400);
                this.setAir(4800);
            } else {
                this.setMoistness(this.getMoistness() - 1);
                if (this.getMoistness() <= 0) {
                    this.damage(DamageSource.DRYOUT, 1.0F);
                }

                if (this.onGround) {
                    this.setVelocity(this.getVelocity().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F,
                            0.5D,
                            (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F));
                    this.setYaw(this.random.nextFloat() * 360.0F);
                    this.onGround = false;
                    this.velocityDirty = true;
                }
            }

            if (this.world.isClient && this.isTouchingWater() && this.isAttacking()) {
                Vec3d vec3d = this.getRotationVec(0.0F);
                float f = MathHelper.cos(this.getYaw() * 0.017453292F) * 0.6F;
                float g = MathHelper.sin(this.getYaw() * 0.017453292F) * 0.6F;
                float h = 0.0F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getX() - vec3d.x * (double)h + (double)f, this.getY() - vec3d.y, this.getZ() - vec3d.z * (double)h + (double)g, 0.0D, 0.0D, 0.0D);
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getX() - vec3d.x * (double)h - (double)f, this.getY() - vec3d.y, this.getZ() - vec3d.z * (double)h - (double)g, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.getTarget() == null && this.isHungry()) {
                List<FishEntity> nearbyFish = this.world.getEntitiesByClass(FishEntity.class, this.getBoundingBox().expand(12.0D, 8.0D, 12.0D), entity -> entity.isTouchingWater() && entity.isAlive());
                if (!nearbyFish.isEmpty()) {
                    FishEntity targetFish = nearbyFish.get(this.random.nextInt(nearbyFish.size()));
                    this.setTarget(targetFish);
                }
            } else if (this.getTarget() instanceof FishEntity && !this.isHungry()) {
                this.setTarget(null);
            }
        }
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("WhaleSharkSwim", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}

package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Util.AqConfig;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;


import java.util.function.Predicate;
@SuppressWarnings({"ConstantConditions", "FieldMayBeFinal", "rawtypes"})
public class SharkEntity extends WaterCreatureEntity implements GeoEntity {
    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    static final TargetPredicate CLOSE_PLAYER_PREDICATE;
    private static final TrackedData<Integer> MOISTNESS;
    //test
    private static final TrackedData<Integer> SHARKHUNGER = null;

    private static double health = AqConfig.INSTANCE.getDoubleProperty("shark.health");
    private static boolean doattack = AqConfig.INSTANCE.getBooleanProperty("shark.attackfish");
    private static double speed = AqConfig.INSTANCE.getDoubleProperty("shark.speed");
    private static double follow = AqConfig.INSTANCE.getDoubleProperty("shark.follow");
    private static double damage = AqConfig.INSTANCE.getDoubleProperty("shark.damage");
    private static double knockback = AqConfig.INSTANCE.getDoubleProperty("shark.knockback");

    public SharkEntity(EntityType<? extends SharkEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new LookControl(this);
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
    public int getSharkhunger() {
        return this.dataTracker.get(SHARKHUNGER);
    }


    public void setMoistness(int moistness) {
        this.dataTracker.set(MOISTNESS, moistness);
    }
    public void setSharkhunger(int sharkhunger) {
        this.dataTracker.set(SHARKHUNGER, sharkhunger);
    }


    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOISTNESS, 2400);
        this.dataTracker.startTracking(SHARKHUNGER, 10);

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Moistness", this.getMoistness());
        nbt.putInt("Hunger", this.getSharkhunger());

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setMoistness(nbt.getInt("Moistness"));
        this.setSharkhunger(nbt.getInt("Hunger"));

    }

    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.2000000476837158D, true));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.add(6, new SwimAroundGoal(this, 0.50, 2));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
        if (doattack==true) {
            this.targetSelector.add(4, new ActiveTargetGoal<>(this, FishEntity.class, 10, true, true, null));
        }
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, AnimalEntity.class, 10, true, true, null));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, damage)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, knockback)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, follow);
    }
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    public int getMaxAir() {
        return 4800;
    }

    protected int getNextAirOnLand(int air) {
        return this.getMaxAir();
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
        }
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isTouchingWater() ? SoundEvents.ENTITY_DOLPHIN_AMBIENT_WATER : SoundEvents.ENTITY_DOLPHIN_AMBIENT;
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_DOLPHIN_SWIM;
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(movementInput);
        }

    }

    static {
        MOISTNESS = DataTracker.registerData(SharkEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }

    private PlayState predicate(AnimationState animationState) {
        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isAttacking()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if(this.isDead()){
            animationState.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.LOOP));
        }
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    static class InWaterPredicate implements Predicate<LivingEntity> {
        private final SharkEntity owner;

        public InWaterPredicate(SharkEntity owner) {
            this.owner = owner;
        }

        public boolean test(@Nullable LivingEntity entity) {
            return entity != null && entity.isTouchingWater();
        }
    }
}
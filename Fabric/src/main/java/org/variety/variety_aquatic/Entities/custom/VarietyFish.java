package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class VarietyFish extends WaterCreatureEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Integer> MOISTNESS;
    static final TargetPredicate CLOSE_PLAYER_PREDICATE;

    public VarietyFish(EntityType<? extends VarietyFish> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FishMoveControl(this);
        this.lookControl = new YawAdjustingLookControl(this, 10);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new EscapeDangerGoal(this, 2.1f));
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(2, new SwimAroundGoal(this, 0.50, 6));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOISTNESS, 2400);
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setAir(this.getMaxAir());
        this.setPitch(0.0F);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public void tick() {
        super.tick();

        if (isAiDisabled()) {
            setAir(getMaxAir());
        } else {
            if (isWet()) {
                setMoistness(2400);
                setAir(4800);
            } else {
                setMoistness(getMoistness() - 1);
                if (getMoistness() <= 0) {
                    damage(DamageSource.DRYOUT, 1.0F);
                }

                if (onGround) {
                    float randomFloat = random.nextFloat();
                    setVelocity(getVelocity().add((randomFloat * 2.0F - 1.0F) * 0.2F, 0.5D, (random.nextFloat() * 2.0F - 1.0F) * 0.2F));
                    setYaw(randomFloat * 360.0F);
                    onGround = false;
                    velocityDirty = true;
                }
            }

            if (world.isClient && isTouchingWater() && isAttacking()) {
                Vec3d rotationVec = getRotationVec(0.0F);
                float cosYaw = MathHelper.cos(getYaw() * 0.017453292F) * 0.6F;
                float sinYaw = MathHelper.sin(getYaw() * 0.017453292F) * 0.6F;
                float offsetY = 0.0F - random.nextFloat() * 0.7F;

                for (int i = 0; i < 2; ++i) {
                    world.addParticle(ParticleTypes.BUBBLE, getX() - rotationVec.x * offsetY + cosYaw, getY() - rotationVec.y, getZ() - rotationVec.z * offsetY + sinYaw, 0.0D, 0.0D, 0.0D);
                    world.addParticle(ParticleTypes.BUBBLE, getX() - rotationVec.x * offsetY - cosYaw, getY() - rotationVec.y, getZ() - rotationVec.z * offsetY - sinYaw, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Moistness", this.getMoistness());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMoistness(nbt.getInt("Moistness"));
    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.65F;
    }
    public boolean cannotDespawn() {
        return super.cannotDespawn();
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.hasCustomName();
    }

    public int getLimitPerChunk() {
        return 8;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
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
        return SoundEvents.ENTITY_SALMON_AMBIENT;
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_DOLPHIN_SWIM;
    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    public int getMoistness() {
        return this.dataTracker.get(MOISTNESS);
    }

    public void setMoistness(int moistness) {
        this.dataTracker.set(MOISTNESS, moistness);
    }

    public int getMaxAir() {
        return 4800;
    }

    public int getNextAirOnLand(int air) {
        return this.getMaxAir();
    }

    public int getLookPitchSpeed() {
        return 1;
    }

    public int getBodyYawSpeed() {
        return 1;
    }

    public static boolean CanSpawnDeep(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() <= world.getSeaLevel() - 25  && world.getBlockState(pos).isOf(Blocks.WATER)&&WaterCreatureEntity.canSpawn(type, world, reason, pos, random);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    static {
        MOISTNESS = DataTracker.registerData(VarietyFish.class, TrackedDataHandlerRegistry.INTEGER);
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }
    public void tickMovement() {
        if (!this.isTouchingWater() && this.onGround && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4000000059604645, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.velocityDirty = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
        }

        super.tickMovement();
    }



    static class FishMoveControl extends MoveControl {
        private final VarietyFish fish;

        FishMoveControl(VarietyFish owner) {
            super(owner);
            this.fish = owner;
        }

        public void tick() {
            if (this.fish.isSubmergedIn(FluidTags.WATER)) {
                this.fish.setVelocity(this.fish.getVelocity().add(0.0, 0.005, 0.0));
            }

            if (this.state == State.MOVE_TO && !this.fish.getNavigation().isIdle()) {
                float f = (float)(this.speed * this.fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.fish.setMovementSpeed(MathHelper.lerp(0.125F, this.fish.getMovementSpeed(), f));
                double d = this.targetX - this.fish.getX();
                double e = this.targetY - this.fish.getY();
                double g = this.targetZ - this.fish.getZ();
                if (e != 0.0) {
                    double h = Math.sqrt(d * d + e * e + g * g);
                    this.fish.setVelocity(this.fish.getVelocity().add(0.0, (double)this.fish.getMovementSpeed() * (e / h) * 0.1, 0.0));
                }

                if (d != 0.0 || g != 0.0) {
                    float i = (float)(MathHelper.atan2(g, d) * 57.2957763671875) - 90.0F;
                    this.fish.setYaw(this.wrapDegrees(this.fish.getYaw(), i, 90.0F));
                    this.fish.bodyYaw = this.fish.getYaw();
                }
            } else {
                this.fish.setMovementSpeed(0.0F);
            }
        }
    }
}

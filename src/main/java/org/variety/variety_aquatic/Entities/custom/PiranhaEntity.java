package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.LookControl;
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
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;
import java.util.function.Predicate;


public class PiranhaEntity extends SchoolingFishEntity implements IAnimatable, Angerable{
    private AnimationFactory factory = new AnimationFactory(this);
    static final TargetPredicate CLOSE_PLAYER_PREDICATE;
    private static final TrackedData<Integer> MOISTNESS;
    private static final UniformIntProvider ANGER_TIME_RANGE;

    private int angerTime;
    private UUID targetUuid;


    private static double health = NewConfig.piranha_health;
    private static double speed = NewConfig.piranha_speed;
    private static double follow = NewConfig.piranha_follow;
    private static double damage = NewConfig.piranha_damage;
    private static double knockback = NewConfig.piranha_knockback;

    public PiranhaEntity(EntityType<? extends PiranhaEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new YawAdjustingLookControl(this, 10);

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
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return (world.getBiome(pos.down()).isIn(BiomeTags.IS_JUNGLE) ||world.getBiome(pos.down()).matchesKey(BiomeKeys.MANGROVE_SWAMP))&& world.getBlockState(pos).isOf(Blocks.WATER);
    }
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOISTNESS, 2400);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Moistness", this.getMoistness());
        this.writeAngerToNbt(nbt);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    public int getMaxGroupSize() {
        return 5;
    }

    public void setAngerTime(int ticks) {
        this.angerTime = ticks;
    }

    public int getAngerTime() {
        return this.angerTime;
    }

    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    public UUID getAngryAt() {
        return this.targetUuid;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.PIRANHA_BUCKET);
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setMoistness(nbt.getInt("Moistness"));
        this.readAngerFromNbt(this.world, nbt);

    }

    protected void initGoals() {
        this.goalSelector.add(2,new PiranhaEntity.AttackGoal());
        this.goalSelector.add(0, new BreatheAirGoal(this));
        this.goalSelector.add(3,new MeleeAttackGoal(this,1,true));
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(4, new SwimAroundGoal(this, 1.0, 10));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ChickenEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, RabbitEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, OcelotEntity.class, 10, true, true, null));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
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


    private class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(PiranhaEntity.this, 1.25D, true);
        }

        protected void attack(LivingEntity target, double squaredDistance) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.isCooledDown()) {
                this.resetCooldown();
                this.mob.tryAttack(target);
            }
        }

        public void stop() {
            super.stop();
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0F + entity.getWidth();
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
        return SoundEvents.ENTITY_SALMON_AMBIENT;
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

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
    }

    static {
        MOISTNESS = DataTracker.registerData(PiranhaEntity.class, TrackedDataHandlerRegistry.INTEGER);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }
        if (this.isAttacking()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("bite", true));
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

    static class InWaterPredicate implements Predicate<LivingEntity> {
        private final PiranhaEntity owner;

        public InWaterPredicate(PiranhaEntity owner) {
            this.owner = owner;
        }

        public boolean test(@Nullable LivingEntity entity) {
            return entity != null && entity.isTouchingWater();
        }
    }
}
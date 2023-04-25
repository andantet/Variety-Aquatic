package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.ModEntities;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Predicate;

public class HermitcrabEntity extends TameableEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    private static final TrackedData<BlockPos> HOME_POS = DataTracker.registerData(HermitcrabEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Boolean> DIGGING_SAND = DataTracker.registerData(HermitcrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<BlockPos> TRAVEL_POS = DataTracker.registerData(HermitcrabEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Boolean> LAND_BOUND = DataTracker.registerData(HermitcrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ACTIVELY_TRAVELING = DataTracker.registerData(HermitcrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final Ingredient BREEDING_ITEM = Ingredient.ofItems(Blocks.SEAGRASS.asItem());
    int sandDiggingCounter;
    public static final Predicate<LivingEntity> BABY_TURTLE_ON_LAND_FILTER = entity -> entity.isBaby() && !entity.isTouchingWater();

    public HermitcrabEntity(EntityType<? extends HermitcrabEntity> entityType, World world) {
        super((EntityType<? extends TameableEntity>)entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0f);
        this.setPathfindingPenalty(PathNodeType.DOOR_IRON_CLOSED, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DOOR_WOOD_CLOSED, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DOOR_OPEN, -1.0f);
        this.moveControl = new HermitcrabEntity.TurtleMoveControl(this);
        this.stepHeight = 1.0f;
    }

    public void setHomePos(BlockPos pos) {
        this.dataTracker.set(HOME_POS, pos);
    }

    BlockPos getHomePos() {
        return this.dataTracker.get(HOME_POS);
    }

    void setTravelPos(BlockPos pos) {
        this.dataTracker.set(TRAVEL_POS, pos);
    }

    BlockPos getTravelPos() {
        return this.dataTracker.get(TRAVEL_POS);
    }


    public boolean isDiggingSand() {
        return this.dataTracker.get(DIGGING_SAND);
    }

    void setDiggingSand(boolean diggingSand) {
        this.sandDiggingCounter = diggingSand ? 1 : 0;
        this.dataTracker.set(DIGGING_SAND, diggingSand);
    }

    boolean isLandBound() {
        return this.dataTracker.get(LAND_BOUND);
    }

    void setLandBound(boolean landBound) {
        this.dataTracker.set(LAND_BOUND, landBound);
    }

    boolean isActivelyTraveling() {
        return this.dataTracker.get(ACTIVELY_TRAVELING);
    }

    void setActivelyTraveling(boolean traveling) {
        this.dataTracker.set(ACTIVELY_TRAVELING, traveling);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HOME_POS, BlockPos.ORIGIN);
        this.dataTracker.startTracking(TRAVEL_POS, BlockPos.ORIGIN);
        this.dataTracker.startTracking(LAND_BOUND, false);
        this.dataTracker.startTracking(ACTIVELY_TRAVELING, false);
        this.dataTracker.startTracking(DIGGING_SAND, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("HomePosX", this.getHomePos().getX());
        nbt.putInt("HomePosY", this.getHomePos().getY());
        nbt.putInt("HomePosZ", this.getHomePos().getZ());
        nbt.putInt("TravelPosX", this.getTravelPos().getX());
        nbt.putInt("TravelPosY", this.getTravelPos().getY());
        nbt.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        int i = nbt.getInt("HomePosX");
        int j = nbt.getInt("HomePosY");
        int k = nbt.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        super.readCustomDataFromNbt(nbt);
        int l = nbt.getInt("TravelPosX");
        int m = nbt.getInt("TravelPosY");
        int n = nbt.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, m, n));
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setHomePos(this.getBlockPos());
        this.setTravelPos(BlockPos.ORIGIN);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public static boolean canSpawn(EntityType<HermitcrabEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return pos.getY() < world.getSeaLevel() + 4 && TurtleEggBlock.isSandBelow(world, pos) && HermitcrabEntity.isLightLevelValidForNaturalSpawn(world, pos);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new HermitcrabEntity.HermitcrabEscapeDangerGoal(this, 1.2));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.0D));

        this.goalSelector.add(2, new TemptGoal(this, 1.1, BREEDING_ITEM, false));
        //this.goalSelector.add(3, new HermitcrabEntity.WanderInWaterGoal(this, 1.0));
        this.goalSelector.add(4, new HermitcrabEntity.GoHomeGoal(this, 1.0));
        //this.goalSelector.add(7, new HermitcrabEntity.TravelGoal(this, 1.0));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(9, new HermitcrabEntity.WanderOnLandGoal(this, 1.0, 100));
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1);
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 200;
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        if (!this.isTouchingWater() && this.onGround && !this.isBaby()) {
            return SoundEvents.ENTITY_TURTLE_AMBIENT_LAND;
        }
        return super.getAmbientSound();
    }

    @Override
    protected void playSwimSound(float volume) {
        super.playSwimSound(volume * 1.5f);
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_TURTLE_SWIM;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        if (this.isBaby()) {
            return SoundEvents.ENTITY_TURTLE_HURT_BABY;
        }
        return SoundEvents.ENTITY_TURTLE_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        if (this.isBaby()) {
            return SoundEvents.ENTITY_TURTLE_DEATH_BABY;
        }
        return SoundEvents.ENTITY_TURTLE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        SoundEvent soundEvent = this.isBaby() ? SoundEvents.ENTITY_TURTLE_SHAMBLE_BABY : SoundEvents.ENTITY_TURTLE_SHAMBLE;
        this.playSound(soundEvent, 0.15f, 1.0f);
    }

    @Override
    public boolean canEat() {
        return super.canEat();
    }

    @Override
    protected float calculateNextStepSoundDistance() {
        return this.distanceTraveled + 0.15f;
    }

    @Override
    public float getScaleFactor() {
        return this.isBaby() ? 0.3f : 1.0f;
    }

    /*
    @Override
    protected EntityNavigation createNavigation(World world) {
        return new HermitcrabEntity.TurtleSwimNavigation(this, world);
   }
*/


    @Override


    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.HERMITCRAB.create(world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Blocks.SEAGRASS.asItem());
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        boolean bl = this.isBreedingItem(player.getStackInHand(hand));
        if (!bl && !player.shouldCancelInteraction()) {
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }


    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (!this.isLandBound() && world.getFluidState(pos).isIn(FluidTags.WATER)) {
            return 10.0f;
        }
        if (TurtleEggBlock.isSandBelow(world, pos)) {
            return 10.0f;
        }
        return world.getPhototaxisFavor(pos);
    }

    @Override
    public void tickMovement() {
        BlockPos blockPos;
        super.tickMovement();
        if (this.isAlive() && this.isDiggingSand() && this.sandDiggingCounter >= 1 && this.sandDiggingCounter % 5 == 0 && TurtleEggBlock.isSandBelow(this.world, blockPos = this.getBlockPos())) {
            this.world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(this.world.getBlockState(blockPos.down())));
        }
    }

    @Override
    protected void onGrowUp() {
        super.onGrowUp();
        if (!this.isBaby() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropItem(Items.SCUTE, 1);
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.1f, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (!(this.getTarget() != null || this.isLandBound() && this.getHomePos().isWithinDistance(this.getPos(), 20.0))) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        this.damage(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("HermitCrabWalk", true));
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


    static class TurtleMoveControl
            extends MoveControl {
        private final HermitcrabEntity hermitcrab;

        TurtleMoveControl(HermitcrabEntity hermitcrab) {
            super(hermitcrab);
            this.hermitcrab = hermitcrab;
        }

        private void updateVelocity() {
            if (this.hermitcrab.isTouchingWater()) {
                this.hermitcrab.setVelocity(this.hermitcrab.getVelocity().add(0.0, 0.005, 0.0));
                if (!this.hermitcrab.getHomePos().isWithinDistance(this.hermitcrab.getPos(), 16.0)) {
                    this.hermitcrab.setMovementSpeed(Math.max(this.hermitcrab.getMovementSpeed() / 2.0f, 0.08f));
                }
                if (this.hermitcrab.isBaby()) {
                    this.hermitcrab.setMovementSpeed(Math.max(this.hermitcrab.getMovementSpeed() / 3.0f, 0.06f));
                }
            } else if (this.hermitcrab.onGround) {
                this.hermitcrab.setMovementSpeed(Math.max(this.hermitcrab.getMovementSpeed() / 2.0f, 0.06f));
            }
        }

        @Override
        public void tick() {
            this.updateVelocity();
            if (this.state != MoveControl.State.MOVE_TO || this.hermitcrab.getNavigation().isIdle()) {
                this.hermitcrab.setMovementSpeed(0.0f);
                return;
            }
            double d = this.targetX - this.hermitcrab.getX();
            double e = this.targetY - this.hermitcrab.getY();
            double f = this.targetZ - this.hermitcrab.getZ();
            double g = Math.sqrt(d * d + e * e + f * f);
            e /= g;
            float h = (float)(MathHelper.atan2(f, d) * 57.2957763671875) - 90.0f;
            this.hermitcrab.setYaw(this.wrapDegrees(this.hermitcrab.getYaw(), h, 90.0f));
            this.hermitcrab.bodyYaw = this.hermitcrab.getYaw();
            float i = (float)(this.speed * this.hermitcrab.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            this.hermitcrab.setMovementSpeed(MathHelper.lerp(0.125f, this.hermitcrab.getMovementSpeed(), i));
            this.hermitcrab.setVelocity(this.hermitcrab.getVelocity().add(0.0, (double)this.hermitcrab.getMovementSpeed() * e * 0.1, 0.0));
        }
    }

    static class HermitcrabEscapeDangerGoal
            extends EscapeDangerGoal {
        HermitcrabEscapeDangerGoal(HermitcrabEntity hermitcrab, double speed) {
            super(hermitcrab, speed);
        }

        @Override
        public boolean canStart() {
            if (!this.isInDanger()) {
                return false;
            }
            BlockPos blockPos = this.locateClosestWater(this.mob.world, this.mob, 7);
            if (blockPos != null) {
                this.targetX = blockPos.getX();
                this.targetY = blockPos.getY();
                this.targetZ = blockPos.getZ();
                return true;
            }
            return this.findTarget();
        }
    }




/*
    static class WanderInWaterGoal
            extends MoveToTargetPosGoal {
        private static final int field_30385 = 1200;
        private final HermitcrabEntity hermitcrab;

        WanderInWaterGoal(HermitcrabEntity hermitcrab, double speed) {
            super(hermitcrab, hermitcrab.isBaby() ? 2.0 : speed, 24);
            this.hermitcrab = hermitcrab;
            this.lowestY = -1;
        }

        @Override
        public boolean shouldContinue() {
            return !this.hermitcrab.isTouchingWater() && this.tryingTime <= 1200 && this.isTargetPos(this.hermitcrab.world, this.targetPos);
        }

        @Override
        public boolean canStart() {
            if (this.hermitcrab.isBaby() && !this.hermitcrab.isTouchingWater()) {
                return super.canStart();
            }
            if (!(this.hermitcrab.isLandBound() || this.hermitcrab.isTouchingWater() || this.hermitcrab.hasEgg())) {
                return super.canStart();
            }
            return false;
        }

        @Override
        public boolean shouldResetPath() {
            return this.tryingTime % 160 == 0;
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER);
        }
    }
    */

    static class GoHomeGoal
            extends Goal {
        private final HermitcrabEntity hermitcrab;
        private final double speed;
        private boolean noPath;
        private int homeReachingTryTicks;
        private static final int MAX_TRY_TICKS = 600;

        GoHomeGoal(HermitcrabEntity hermitcrab, double speed) {
            this.hermitcrab = hermitcrab;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            if (this.hermitcrab.isBaby()) {
                return false;
            }
            if (this.hermitcrab.getRandom().nextInt(HermitcrabEntity.GoHomeGoal.toGoalTicks(700)) != 0) {
                return false;
            }
            return !this.hermitcrab.getHomePos().isWithinDistance(this.hermitcrab.getPos(), 64.0);
        }

        @Override
        public void start() {
            this.hermitcrab.setLandBound(true);
            this.noPath = false;
            this.homeReachingTryTicks = 0;
        }

        @Override
        public void stop() {
            this.hermitcrab.setLandBound(false);
        }

        @Override
        public boolean shouldContinue() {
            return !this.hermitcrab.getHomePos().isWithinDistance(this.hermitcrab.getPos(), 7.0) && !this.noPath && this.homeReachingTryTicks <= this.getTickCount(600);
        }

        @Override
        public void tick() {
            BlockPos blockPos = this.hermitcrab.getHomePos();
            boolean bl = blockPos.isWithinDistance(this.hermitcrab.getPos(), 16.0);
            if (bl) {
                ++this.homeReachingTryTicks;
            }
            if (this.hermitcrab.getNavigation().isIdle()) {
                Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
                Vec3d vec3d2 = NoPenaltyTargeting.findTo(this.hermitcrab, 16, 3, vec3d, 0.3141592741012573);
                if (vec3d2 == null) {
                    vec3d2 = NoPenaltyTargeting.findTo(this.hermitcrab, 8, 7, vec3d, 1.5707963705062866);
                }
                if (vec3d2 != null && !bl && !this.hermitcrab.world.getBlockState(new BlockPos(vec3d2)).isOf(Blocks.WATER)) {
                    vec3d2 = NoPenaltyTargeting.findTo(this.hermitcrab, 16, 5, vec3d, 1.5707963705062866);
                }
                if (vec3d2 == null) {
                    this.noPath = true;
                    return;
                }
                this.hermitcrab.getNavigation().startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
            }
        }
    }

    /*
    static class TravelGoal
            extends Goal {
        private final HermitcrabEntity hermitcrab;
        private final double speed;
        private boolean noPath;

        TravelGoal(HermitcrabEntity hermitcrab, double speed) {
            this.hermitcrab = hermitcrab;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            return !this.hermitcrab.isLandBound() && this.hermitcrab.isTouchingWater();
        }

        @Override
        public void start() {
            int i = 512;
            int j = 4;
            Random random = this.hermitcrab.random;
            int k = random.nextInt(1025) - 512;
            int l = random.nextInt(9) - 4;
            int m = random.nextInt(1025) - 512;
            if ((double)l + this.hermitcrab.getY() > (double)(this.hermitcrab.world.getSeaLevel() - 1)) {
                l = 0;
            }
            BlockPos blockPos = new BlockPos((double)k + this.hermitcrab.getX(), (double)l + this.hermitcrab.getY(), (double)m + this.hermitcrab.getZ());
            this.hermitcrab.setTravelPos(blockPos);
            this.hermitcrab.setActivelyTraveling(true);
            this.noPath = false;
        }

        @Override
        public void tick() {
            if (this.hermitcrab.getNavigation().isIdle()) {
                Vec3d vec3d = Vec3d.ofBottomCenter(this.hermitcrab.getTravelPos());
                Vec3d vec3d2 = NoPenaltyTargeting.findTo(this.hermitcrab, 16, 3, vec3d, 0.3141592741012573);
                if (vec3d2 == null) {
                    vec3d2 = NoPenaltyTargeting.findTo(this.hermitcrab, 8, 7, vec3d, 1.5707963705062866);
                }
                if (vec3d2 != null) {
                    int i = MathHelper.floor(vec3d2.x);
                    int j = MathHelper.floor(vec3d2.z);
                    int k = 34;
                    if (!this.hermitcrab.world.isRegionLoaded(i - 34, j - 34, i + 34, j + 34)) {
                        vec3d2 = null;
                    }
                }
                if (vec3d2 == null) {
                    this.noPath = true;
                    return;
                }
                this.hermitcrab.getNavigation().startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, this.speed);
            }
        }

        @Override
        public boolean shouldContinue() {
            return !this.hermitcrab.getNavigation().isIdle() && !this.noPath && !this.hermitcrab.isLandBound() && !this.hermitcrab.isInLove() && !this.hermitcrab.hasEgg();
        }

        @Override
        public void stop() {
            this.hermitcrab.setActivelyTraveling(false);
            super.stop();
        }
    }

     */

    static class WanderOnLandGoal
            extends WanderAroundGoal {
        private final HermitcrabEntity hermitcrab;

        WanderOnLandGoal(HermitcrabEntity hermitcrab, double speed, int chance) {
            super(hermitcrab, speed, chance);
            this.hermitcrab = hermitcrab;
        }

        @Override
        public boolean canStart() {
            if (!(this.mob.isTouchingWater() || this.hermitcrab.isLandBound())) {
                return super.canStart();
            }
            return false;
        }
    }
/*
    static class TurtleSwimNavigation
            extends AmphibiousSwimNavigation {
        TurtleSwimNavigation(HermitcrabEntity owner, World world) {
            super(owner, world);
        }

        @Override
        public boolean isValidPosition(BlockPos pos) {
            HermitcrabEntity hermitcrabEntity;
            MobEntity mobEntity = this.entity;
            if (mobEntity instanceof HermitcrabEntity && (hermitcrabEntity = (HermitcrabEntity)mobEntity).isActivelyTraveling()) {
                return this.world.getBlockState(pos).isOf(Blocks.WATER);
            }
            return !this.world.getBlockState(pos.down()).isAir();
        }
    }

 */
}


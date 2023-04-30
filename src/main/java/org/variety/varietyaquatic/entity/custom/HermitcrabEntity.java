package org.variety.varietyaquatic.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HermitcrabEntity extends Animal implements IAnimatable {
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(HermitcrabEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(HermitcrabEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(HermitcrabEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(HermitcrabEntity.class, EntityDataSerializers.BOOLEAN);
    private AnimationFactory factory = new AnimationFactory(this);

    public HermitcrabEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        getNavigation().setCanFloat(false);
        this.maxUpStep = 1.0F;

    }
    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 1).build();
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new Hermitcrabgotowater(this, 1.0D));
        this.goalSelector.addGoal(4, new HermitcrabGohome(this, 1.0D));
        this.goalSelector.addGoal(7, new HermitcrabTravelGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new HermitcrabRandomStrollGoal(this, 1.0D, 100));
    }

    public void setHomePos(BlockPos pHomePos) {
        this.entityData.set(HOME_POS, pHomePos);
    }

    BlockPos getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    void setTravelPos(BlockPos pTravelPos) {
        this.entityData.set(TRAVEL_POS, pTravelPos);
    }

    BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }
    boolean isGoingHome() {
        return this.entityData.get(GOING_HOME);
    }

    void setGoingHome(boolean pIsGoingHome) {
        this.entityData.set(GOING_HOME, pIsGoingHome);
    }

    boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    void setTravelling(boolean pIsTravelling) {
        this.entityData.set(TRAVELLING, pIsTravelling);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME_POS, BlockPos.ZERO);
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(GOING_HOME, false);
        this.entityData.define(TRAVELLING, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("HomePosX", this.getHomePos().getX());
        pCompound.putInt("HomePosY", this.getHomePos().getY());
        pCompound.putInt("HomePosZ", this.getHomePos().getZ());
        pCompound.putInt("TravelPosX", this.getTravelPos().getX());
        pCompound.putInt("TravelPosY", this.getTravelPos().getY());
        pCompound.putInt("TravelPosZ", this.getTravelPos().getZ());
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        int i = pCompound.getInt("HomePosX");
        int j = pCompound.getInt("HomePosY");
        int k = pCompound.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        int l = pCompound.getInt("TravelPosX");
        int i1 = pCompound.getInt("TravelPosY");
        int j1 = pCompound.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }
    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }


    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (!this.isGoingHome() && pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
            return 10.0F;

        }
        else {
            return pLevel.getPathfindingCostFromLightLevels(pPos);
        }
    }
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null && (!this.isGoingHome() || !this.getHomePos().closerToCenterThan(this.position(), 20.0D))) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    static class HermitcrabGohome extends Goal {
        private final HermitcrabEntity hermit;
        private final double speedModifier;
        private boolean stuck;
        private int closeToHomeTryTicks;
        private static final int GIVE_UP_TICKS = 600;

        HermitcrabGohome(HermitcrabEntity hermit, double pSpeedModifier) {
            this.hermit = hermit;
            this.speedModifier = pSpeedModifier;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.hermit.isBaby()) {
                return false;
            } else if (this.hermit.getRandom().nextInt(reducedTickDelay(700)) != 0) {
                return false;
            } else {
                return !this.hermit.getHomePos().closerToCenterThan(this.hermit.position(), 64.0D);
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.hermit.setGoingHome(true);
            this.stuck = false;
            this.closeToHomeTryTicks = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.hermit.setGoingHome(false);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.hermit.getHomePos().closerToCenterThan(this.hermit.position(), 7.0D) && !this.stuck && this.closeToHomeTryTicks <= this.adjustedTickDelay(600);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = this.hermit.getHomePos();
            boolean flag = blockpos.closerToCenterThan(this.hermit.position(), 16.0D);
            if (flag) {
                ++this.closeToHomeTryTicks;
            }

            if (this.hermit.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.hermit, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.hermit, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null && !flag && !this.hermit.level.getBlockState(new BlockPos(vec31)).is(Blocks.WATER)) {
                    vec31 = DefaultRandomPos.getPosTowards(this.hermit, 16, 5, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.hermit.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }
    }
    static class Hermitcrabgotowater extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final HermitcrabEntity hermit;

        Hermitcrabgotowater(HermitcrabEntity hermit, double pSpeedModifier) {
            super(hermit, hermit.isBaby() ? 2.0D : pSpeedModifier, 24);
            this.hermit = hermit;
            this.verticalSearchStart = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.hermit.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.hermit.level, this.blockPos);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.hermit.isBaby() && !this.hermit.isInWater()) {
                return super.canUse();
            } else {
                return !this.hermit.isGoingHome() && !this.hermit.isInWater();
            }
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        /**
         * Return {@code true} to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    static class HermitcrabhNavigation extends AmphibiousPathNavigation {
        HermitcrabhNavigation(HermitcrabEntity hermit, Level pLevel) {
            super(hermit, pLevel);
        }

        public boolean isStableDestination(BlockPos pPos) {
            Mob mob = this.mob;
            if (mob instanceof HermitcrabEntity hermit) {
                if (hermit.isTravelling()) {
                    return this.level.getBlockState(pPos).is(Blocks.WATER);
                }
            }

            return !this.level.getBlockState(pPos.below()).isAir();
        }
    }

    static class HermitcrabRandomStrollGoal extends RandomStrollGoal {
        private final HermitcrabEntity hermit;

        HermitcrabRandomStrollGoal(HermitcrabEntity hermit, double pSpeedModifier, int pInterval) {
            super(hermit, pSpeedModifier, pInterval);
            this.hermit = hermit;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.mob.isInWater() && !this.hermit.isGoingHome();
        }
    }

    static class HermitcrabTravelGoal extends Goal {
        private final HermitcrabEntity hermit;
        private final double speedModifier;
        private boolean stuck;

        HermitcrabTravelGoal(HermitcrabEntity hermit, double pSpeedModifier) {
            this.hermit = hermit;
            this.speedModifier = pSpeedModifier;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.hermit.isGoingHome() && this.hermit.isInWater();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            int i = 512;
            int j = 4;
            RandomSource randomsource = this.hermit.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.hermit.getY() > (double)(this.hermit.level.getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = new BlockPos((double)k + this.hermit.getX(), (double)l + this.hermit.getY(), (double)i1 + this.hermit.getZ());
            this.hermit.setTravelPos(blockpos);
            this.hermit.setTravelling(true);
            this.stuck = false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.hermit.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.hermit.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.hermit, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.hermit, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    int k = 34;
                    if (!this.hermit.level.hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.hermit.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.hermit.getNavigation().isDone() && !this.stuck && !this.hermit.isGoingHome();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.hermit.setTravelling(false);
            super.stop();
        }
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
}

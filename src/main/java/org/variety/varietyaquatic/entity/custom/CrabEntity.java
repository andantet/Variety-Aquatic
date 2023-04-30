package org.variety.varietyaquatic.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.item.ModItems;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CrabEntity extends Animal implements IAnimatable {

    AnimationFactory afactory = new AnimationFactory(this);

    private boolean songPlaying;
    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);



    @Nullable
    private BlockPos songSource;

    public CrabEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        getNavigation().setCanFloat(false);
        this.maxUpStep = 1.0F;
    }
    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new Crabgotowater (this, 1.0D));
        this.goalSelector.addGoal(7, new TemptGoal(this, 1.0D, TEMPT_INGREDIENT,false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this,1));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public MobType getMobType() {
        return MobType.WATER;
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

    static class Crabgotowater extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final CrabEntity crab;

        Crabgotowater(CrabEntity crab, double pSpeedModifier) {
            super(crab, crab.isBaby() ? 2.0D : pSpeedModifier, 24);
            this.crab = crab;
            this.verticalSearchStart = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.crab.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.crab.level, this.blockPos);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.crab.isBaby() && !this.crab.isInWater()) {
                return super.canUse();
            } else {
                return !this.crab.isInWater();
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

    public boolean isSongPlaying() {
        return this.songPlaying;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "animations", 0, this::animations));
    }

    @Override
    public AnimationFactory getFactory() {
        return afactory;
    }

    private PlayState animations(AnimationEvent<CrabEntity> event) {
        AnimationController contr = event.getController();

        if (this.songSource == null || !this.songSource.closerThan(this.getOnPos(), 15.0) || !this.level.getBlockState(this.songSource).is(Blocks.JUKEBOX)) {
            this.songPlaying = false;
            this.songSource = null;
        }
        if (isSongPlaying()) {
            contr.setAnimation(new AnimationBuilder().addAnimation("dance",true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            contr.setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;

    }

    public static boolean checkcrabspawn(EntityType<? extends Animal> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.SAND);
    }
    public static AttributeSupplier setAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.3).build();
    }


}
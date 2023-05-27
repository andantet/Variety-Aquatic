package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.Block;
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
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.function.Predicate;


public class ClownfishEntity extends VarietyFish {
    static final TargetPredicate CLOSE_PLAYER_PREDICATE;
    private boolean isAttacked;

    public ClownfishEntity(EntityType<? extends ClownfishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.CLOWNFISH_BUCKET);
    }

    protected void initGoals() {
        this.goalSelector.add(2, new GoalHideInAnemone(this));
        super.initGoals();
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.clownfish_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.clownfish_speed);
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.3F;
    }

    public static class GoalHideInAnemone extends Goal {
        private final ClownfishEntity clownfish;
        private BlockPos targetAnemonePos;

        public GoalHideInAnemone(ClownfishEntity clownfish) {
            this.clownfish = clownfish;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return this.clownfish.getAttacker() != null;
        }

        @Override
        public void start() {
            BlockPos nearestAnemonePos = findNearestAnemone();
            if (nearestAnemonePos != null) {
                this.targetAnemonePos = nearestAnemonePos;
                this.clownfish.getNavigation().startMovingTo(targetAnemonePos.getX(), targetAnemonePos.getY(), targetAnemonePos.getZ(), 1.0);
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.targetAnemonePos != null && this.clownfish.isAlive() && this.clownfish.isAttacked;
        }

        @Override
        public void tick() {
            if (this.targetAnemonePos != null) {
                this.clownfish.getNavigation().startMovingTo(targetAnemonePos.getX(), targetAnemonePos.getY(), targetAnemonePos.getZ(), 1.0);
            }
        }

        @Override
        public void stop() {
            this.targetAnemonePos = null;
            this.clownfish.getNavigation().stop();
        }

        private BlockPos findNearestAnemone() {
            BlockPos currentPos = this.clownfish.getBlockPos();
            for (int i = -8; i <= 8; i++) {
                for (int j = -8; j <= 8; j++) {
                    for (int k = -8; k <= 8; k++) {
                        BlockPos checkPos = currentPos.add(i, j, k);
                        Block block = this.clownfish.world.getBlockState(checkPos).getBlock();
                        if (block == ModBlock.ANEMONE_BLOCK) {
                            return checkPos;
                        }
                    }
                }
            }
            return null;
        }
    }

    static {
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.isSubmergedInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }
        else if (event.isMoving() && !this.isSubmergedInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flop", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }
}
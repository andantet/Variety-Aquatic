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

    public int getMaxAir() {
        return 4800;
    }

    protected int getNextAirOnLand(int air) {
        return this.getMaxAir();
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.3F;
    }

    public int getLookPitchSpeed() {
        return 1;
    }

    public int getBodyYawSpeed() {
        return 1;
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

    public void tick() {
        super.tick();
        this.isAttacked = this.getAttacker() != null;
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
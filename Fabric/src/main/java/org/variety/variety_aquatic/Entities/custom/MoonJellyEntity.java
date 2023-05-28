package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class MoonJellyEntity extends VarietyFish {

    public float tiltAngle;
    public float prevTiltAngle;
    public float rollAngle;
    public float prevRollAngle;
    public float thrustTimer;
    public float prevThrustTimer;
    public float tentacleAngle;
    public float prevTentacleAngle;
    private float swimVelocityScale;
    private float thrustTimerSpeed;
    private float turningSpeed;
    private float swimX;
    private float swimY;
    private float swimZ;

    public MoonJellyEntity(EntityType<? extends MoonJellyEntity> entityType, World world) {
        super(entityType, world);
        this.random.setSeed((long) this.getId());
        this.thrustTimerSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new MoonJellyEntity.SwimGoal(this));
        this.goalSelector.add(2, new MoonJellyEntity.EscapeAttackerGoal());
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2);
    }



    protected SoundEvent getSquirtSound() {
        return SoundEvents.ENTITY_SQUID_SQUIRT;
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return !this.isLeashed();
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.EVENTS;
    }

    public void tickMovement() {
        super.tickMovement();
        this.prevTiltAngle = this.tiltAngle;
        this.prevRollAngle = this.rollAngle;
        this.prevThrustTimer = this.thrustTimer;
        this.prevTentacleAngle = this.tentacleAngle;
        this.thrustTimer += this.thrustTimerSpeed;
        if ((double) this.thrustTimer > 6.283185307179586) {
            if (this.world.isClient) {
                this.thrustTimer = 6.2831855F;
            } else {
                this.thrustTimer -= 6.2831855F;
                if (this.random.nextInt(10) == 0) {
                    this.thrustTimerSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.sendEntityStatus(this, (byte) 19);
            }
        }

        if (this.isInsideWaterOrBubbleColumn()) {
            if (this.thrustTimer < 3.1415927F) {
                float f = this.thrustTimer / 3.1415927F;
                this.tentacleAngle = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double) f > 0.75) {
                    this.swimVelocityScale = 0.8F;
                    this.turningSpeed = 1.0F;
                } else {
                    this.turningSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.swimVelocityScale *= 0.6F;
                this.turningSpeed *= 0.99F;
            }

            if (!this.world.isClient) {
                this.setVelocity((double) (this.swimX * this.swimVelocityScale), (double) (this.swimY * this.swimVelocityScale), (double) (this.swimZ * this.swimVelocityScale));
            }

            Vec3d vec3d = this.getVelocity();
            double d = vec3d.horizontalLength();
            this.bodyYaw += (-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776F - this.bodyYaw) * 0.1F;
            this.setYaw(this.bodyYaw);
            this.rollAngle += 3.1415927F * this.turningSpeed * 1.5F;
            this.tiltAngle += (-((float) MathHelper.atan2(d, vec3d.y)) * 57.295776F - this.tiltAngle) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.thrustTimer)) * 3.1415927F * 0.25F;
            if (!this.world.isClient) {
                double e = this.getVelocity().y;
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    e = 0.05 * (double) (this.getStatusEffect(StatusEffects.LEVITATION).getAmplifier() + 1);
                } else if (!this.hasNoGravity()) {
                    e -= 0.08;
                }

                this.setVelocity(0.0, e * 0.9800000190734863, 0.0);
            }

            this.tiltAngle += (-90.0F - this.tiltAngle) * 0.02F;
        }

    }

    public boolean damage(DamageSource source, float amount) {
        if (super.damage(source, amount) && this.getAttacker() != null) {
            if (!this.world.isClient) {
                this.squirt();
            }

            return true;
        } else {
            return false;
        }
    }

    private Vec3d applyBodyRotations(Vec3d shootVector) {
        Vec3d vec3d = shootVector.rotateX(this.prevTiltAngle * 0.017453292F);
        vec3d = vec3d.rotateY(-this.prevBodyYaw * 0.017453292F);
        return vec3d;
    }

    private void squirt() {
        this.playSound(this.getSquirtSound(), this.getSoundVolume(), this.getSoundPitch());
        Vec3d vec3d = this.applyBodyRotations(new Vec3d(0.0, -1.0, 0.0)).add(this.getX(), this.getY(), this.getZ());

        for (int i = 0; i < 30; ++i) {
            Vec3d vec3d2 = this.applyBodyRotations(new Vec3d((double) this.random.nextFloat() * 0.6 - 0.3, -1.0, (double) this.random.nextFloat() * 0.6 - 0.3));
            Vec3d vec3d3 = vec3d2.multiply(0.3 + (double) (this.random.nextFloat() * 2.0F));
            ((ServerWorld) this.world).spawnParticles(this.getInkParticle(), vec3d.x, vec3d.y + 0.5, vec3d.z, 0, vec3d3.x, vec3d3.y, vec3d3.z, 0.10000000149011612);
        }

    }

    protected ParticleEffect getInkParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    public void travel(Vec3d movementInput) {
        this.move(MovementType.SELF, this.getVelocity());
    }

    public void handleStatus(byte status) {
        if (status == 19) {
            this.thrustTimer = 0.0F;
        } else {
            super.handleStatus(status);
        }

    }

    public void setSwimmingVector(float x, float y, float z) {
        this.swimX = x;
        this.swimY = y;
        this.swimZ = z;
    }

    public boolean hasSwimmingVector() {
        return this.swimX != 0.0F || this.swimY != 0.0F || this.swimZ != 0.0F;
    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }

    class SwimGoal extends Goal {
        private final MoonJellyEntity jellyfish;

        public SwimGoal(MoonJellyEntity jellyfish) {
            this.jellyfish = jellyfish;
        }

        public boolean canStart() {
            return true;
        }

        public void tick() {
            int i = this.jellyfish.getDespawnCounter();
            if (i > 100) {
                this.jellyfish.setSwimmingVector(0.0F, 0.0F, 0.0F);
            } else if (this.jellyfish.getRandom().nextInt(toGoalTicks(50)) == 0 || !this.jellyfish.touchingWater || !this.jellyfish.hasSwimmingVector()) {
                float f = this.jellyfish.getRandom().nextFloat() * 6.2831855F;
                float g = MathHelper.cos(f) * 0.2F;
                float h = -0.1F + this.jellyfish.getRandom().nextFloat() * 0.2F;
                float j = MathHelper.sin(f) * 0.2F;
                this.jellyfish.setSwimmingVector(g, h, j);
            }

        }
    }

    class EscapeAttackerGoal extends Goal {
        private static final float field_30375 = 3.0F;
        private static final float field_30376 = 5.0F;
        private static final float field_30377 = 10.0F;
        private int timer;

        EscapeAttackerGoal() {
        }

        public boolean canStart() {
            LivingEntity livingEntity = MoonJellyEntity.this.getAttacker();
            if (MoonJellyEntity.this.isTouchingWater() && livingEntity != null) {
                return MoonJellyEntity.this.squaredDistanceTo(livingEntity) < 100.0;
            } else {
                return false;
            }
        }

        public void start() {
            this.timer = 0;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            ++this.timer;
            LivingEntity livingEntity = MoonJellyEntity.this.getAttacker();
            if (livingEntity != null) {
                Vec3d vec3d = new Vec3d(MoonJellyEntity.this.getX() - livingEntity.getX(), MoonJellyEntity.this.getY() - livingEntity.getY(), MoonJellyEntity.this.getZ() - livingEntity.getZ());
                BlockState blockState = MoonJellyEntity.this.world.getBlockState(new BlockPos(MoonJellyEntity.this.getX() + vec3d.x, MoonJellyEntity.this.getY() + vec3d.y, MoonJellyEntity.this.getZ() + vec3d.z));
                FluidState fluidState = MoonJellyEntity.this.world.getFluidState(new BlockPos(MoonJellyEntity.this.getX() + vec3d.x, MoonJellyEntity.this.getY() + vec3d.y, MoonJellyEntity.this.getZ() + vec3d.z));
                if (fluidState.isIn(FluidTags.WATER) || blockState.isAir()) {
                    double d = vec3d.length();
                    if (d > 0.0) {
                        vec3d.normalize();
                        double e = 3.0;
                        if (d > 5.0) {
                            e -= (d - 5.0) / 5.0;
                        }

                        if (e > 0.0) {
                            vec3d = vec3d.multiply(e);
                        }
                    }

                    if (blockState.isAir()) {
                        vec3d = vec3d.subtract(0.0, vec3d.y, 0.0);
                    }

                    MoonJellyEntity.this.setSwimmingVector((float) vec3d.x / 20.0F, (float) vec3d.y / 20.0F, (float) vec3d.z / 20.0F);
                }

                if (this.timer % 10 == 5) {
                    MoonJellyEntity.this.world.addParticle(ParticleTypes.BUBBLE, MoonJellyEntity.this.getX(), MoonJellyEntity.this.getY(), MoonJellyEntity.this.getZ(), 0.0, 0.0, 0.0);
                }

            }
        }
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("Swim", true));
        return PlayState.CONTINUE;
    }
}

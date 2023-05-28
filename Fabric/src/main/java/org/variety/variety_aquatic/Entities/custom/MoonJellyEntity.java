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
        prevTiltAngle = tiltAngle;
        prevRollAngle = rollAngle;
        prevThrustTimer = thrustTimer;
        prevTentacleAngle = tentacleAngle;
        thrustTimer += thrustTimerSpeed;

        if (thrustTimer > Math.PI * 2) {
            if (world.isClient) {
                thrustTimer = (float) (Math.PI * 2);
            } else {
                thrustTimer -= (float) (Math.PI * 2);

                if (random.nextInt(10) == 0) {
                    thrustTimerSpeed = 1.0F / (random.nextFloat() + 1.0F) * 0.2F;
                }

                world.sendEntityStatus(this, (byte) 19);
            }
        }

        if (isInsideWaterOrBubbleColumn()) {
            if (thrustTimer < Math.PI) {
                float f = thrustTimer / (float) Math.PI;
                tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;

                if (f > 0.75) {
                    swimVelocityScale = 0.8F;
                    turningSpeed = 1.0F;
                } else {
                    turningSpeed *= 0.8F;
                }
            } else {
                tentacleAngle = 0.0F;
                swimVelocityScale *= 0.6F;
                turningSpeed *= 0.99F;
            }

            if (!world.isClient) {
                setVelocity(swimX * swimVelocityScale, swimY * swimVelocityScale, swimZ * swimVelocityScale);
            }

            Vec3d velocity = getVelocity();
            double horizontalSpeed = velocity.horizontalLength();
            bodyYaw += (-((float) MathHelper.atan2(velocity.x, velocity.z)) * (180.0 / Math.PI) - bodyYaw) * 0.1F;
            setYaw(bodyYaw);
            rollAngle += Math.PI * turningSpeed * 1.5F;
            tiltAngle += (-((float) MathHelper.atan2(horizontalSpeed, velocity.y)) * (180.0 / Math.PI) - tiltAngle) * 0.1F;
        } else {
            tentacleAngle = MathHelper.abs(MathHelper.sin(thrustTimer)) * (float) Math.PI * 0.25F;

            if (!world.isClient) {
                double verticalSpeed = getVelocity().y;

                if (hasStatusEffect(StatusEffects.LEVITATION)) {
                    verticalSpeed = 0.05 * (getStatusEffect(StatusEffects.LEVITATION).getAmplifier() + 1);
                } else if (!hasNoGravity()) {
                    verticalSpeed -= 0.08;
                }

                setVelocity(0.0, verticalSpeed * 0.9800000190734863, 0.0);
            }

            tiltAngle += (-90.0F - tiltAngle) * 0.02F;
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
        playSound(getSquirtSound(), getSoundVolume(), getSoundPitch());
        Vec3d startPos = applyBodyRotations(new Vec3d(0.0, -1.0, 0.0)).add(getX(), getY(), getZ());

        for (int i = 0; i < 30; ++i) {
            Vec3d squirtDir = applyBodyRotations(new Vec3d(random.nextFloat() * 0.6 - 0.3, -1.0, random.nextFloat() * 0.6 - 0.3));
            Vec3d squirtVelocity = squirtDir.multiply(0.3 + (random.nextFloat() * 2.0F));
            ((ServerWorld) world).spawnParticles(getInkParticle(), startPos.x, startPos.y + 0.5, startPos.z, 0, squirtVelocity.x, squirtVelocity.y, squirtVelocity.z, 0.1);
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


    class SwimGoal extends Goal {
        private final MoonJellyEntity jellyfish;

        public SwimGoal(MoonJellyEntity jellyfish) {
            this.jellyfish = jellyfish;
        }

        public boolean canStart() {
            return true;
        }

        public void tick() {
            int despawnCounter = jellyfish.getDespawnCounter();

            if (despawnCounter > 100) {
                jellyfish.setSwimmingVector(0.0F, 0.0F, 0.0F);
            } else if (jellyfish.getRandom().nextInt(toGoalTicks(50)) == 0 || !jellyfish.touchingWater || !jellyfish.hasSwimmingVector()) {
                float angle = jellyfish.getRandom().nextFloat() * 6.2F;
                float x = MathHelper.cos(angle) * 0.2F;
                float y = -0.1F + jellyfish.getRandom().nextFloat() * 0.2F;
                float z = MathHelper.sin(angle) * 0.2F;
                jellyfish.setSwimmingVector(x, y, z);
            }
        }
    }


    class EscapeAttackerGoal extends Goal {
        private static final float MIN_DISTANCE = 3.0F;
        private static final float MAX_DISTANCE = 5.0F;
        private static final float SWIMMING_MULTIPLIER = 10.0F;
        private int timer;

        public EscapeAttackerGoal() {
        }

        public boolean canStart() {
            LivingEntity attacker = MoonJellyEntity.this.getAttacker();
            return MoonJellyEntity.this.isTouchingWater() && attacker != null && MoonJellyEntity.this.squaredDistanceTo(attacker) < 100.0;
        }

        public void start() {
            timer = 0;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            timer++;
            LivingEntity attacker = MoonJellyEntity.this.getAttacker();

            if (attacker != null) {
                Vec3d difference = new Vec3d(MoonJellyEntity.this.getX() - attacker.getX(), MoonJellyEntity.this.getY() - attacker.getY(), MoonJellyEntity.this.getZ() - attacker.getZ());
                BlockState blockState = MoonJellyEntity.this.world.getBlockState(new BlockPos(MoonJellyEntity.this.getX() + difference.x, MoonJellyEntity.this.getY() + difference.y, MoonJellyEntity.this.getZ() + difference.z));
                FluidState fluidState = MoonJellyEntity.this.world.getFluidState(new BlockPos(MoonJellyEntity.this.getX() + difference.x, MoonJellyEntity.this.getY() + difference.y, MoonJellyEntity.this.getZ() + difference.z));

                if (fluidState.isIn(FluidTags.WATER) || blockState.isAir()) {
                    double distance = difference.length();

                    if (distance > 0.0) {
                        difference = difference.normalize();
                        double multiplier = MIN_DISTANCE;
                        if (distance > MAX_DISTANCE) {
                            multiplier -= (distance - MAX_DISTANCE) / MAX_DISTANCE;
                        }

                        if (multiplier > 0.0) {
                            difference = difference.multiply(multiplier);
                        }
                    }

                    if (blockState.isAir()) {
                        difference = difference.subtract(0.0, difference.y, 0.0);
                    }

                    MoonJellyEntity.this.setSwimmingVector((float) (difference.x / SWIMMING_MULTIPLIER), (float) (difference.y / SWIMMING_MULTIPLIER), (float) (difference.z / SWIMMING_MULTIPLIER));
                }

                if (timer % 10 == 5) {
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

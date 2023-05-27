package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.ProjectileEntity.BlindnessProjectile;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Predicate;


public class CuttlefishEntity extends VarietyFish {
    public float tiltAngle;
    public float prevTiltAngle;

    static final TargetPredicate CLOSE_PLAYER_PREDICATE;

    public CuttlefishEntity(EntityType<? extends CuttlefishEntity> entityType, World world) {
        super(entityType, world);
    }

    protected SoundEvent getSquirtSound() {
        return SoundEvents.ENTITY_SQUID_SQUIRT;
    }

    private Vec3d applyBodyRotations(Vec3d shootVector) {
        Vec3d vec3d = shootVector.rotateX(this.prevTiltAngle * 0.017453292F);
        vec3d = vec3d.rotateY(-this.prevBodyYaw * 0.017453292F);
        return vec3d;
    }

    private void squirt() {
        this.playSound(this.getSquirtSound(), this.getSoundVolume(), this.getSoundPitch());
        Vec3d vec3d = this.applyBodyRotations(new Vec3d(0.0, -1.0, 0.0)).add(this.getX(), this.getY(), this.getZ());

        for(int i = 0; i < 30; ++i) {
            Vec3d vec3d2 = this.applyBodyRotations(new Vec3d((double)this.random.nextFloat() * 0.6 - 0.3, -1.0, (double)this.random.nextFloat() * 0.6 - 0.3));
            Vec3d vec3d3 = vec3d2.multiply(0.3 + (double)(this.random.nextFloat() * 2.0F));
            ((ServerWorld)this.world).spawnParticles(this.getInkParticle(), vec3d.x+0.5, vec3d.y + 0.5, vec3d.z , 0, vec3d3.x, vec3d3.y, vec3d3.z, 0.10000000149011612);
        }

    }

    protected ParticleEffect getInkParticle() {
        return ParticleTypes.SQUID_INK;
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setMoistness(nbt.getInt("Moistness"));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.cuttlefish_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.cuttlefish_speed);
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.5F;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (super.damage(source, amount) && this.getAttacker() != null) {
            if (!this.world.isClient) {
                this.squirt();

                // Shoot the blindness projectile at the attacking player (if it's a player)
                if (this.getAttacker() instanceof PlayerEntity) {
                    PlayerEntity attacker = (PlayerEntity) this.getAttacker();
                    BlindnessProjectile projectileEntity = new BlindnessProjectile(world, this);

                    projectileEntity.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());

                    Vec3d direction = attacker.getPos().subtract(this.getPos()).normalize();
                    projectileEntity.setVelocity(direction.x, direction.y, direction.z, 1.5F, 1.0F);

                    world.spawnEntity(projectileEntity);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void tickMovement() {
        super.tickMovement();
        this.prevTiltAngle = this.tiltAngle;
    }

    static {
        CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }
}
package org.variety.varietyaquatic.entity.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class CuttlefishEntity extends WaterAnimal implements IAnimatable {
    public float xBodyRot;
    public float xBodyRotO;
    private AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(CuttlefishEntity.class, EntityDataSerializers.INT);

    public CuttlefishEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }


    public boolean canBreatheUnderwater() {
        return true;
    }
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (super.hurt(pSource, pAmount) && this.getLastHurtByMob() != null) {
            if (!this.level.isClientSide) {
                this.spawnInk();
                this.getLastHurtByMob().addEffect(new MobEffectInstance(MobEffects.BLINDNESS,40,0));
            }

            return true;
        } else {
            return false;
        }
    }
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }
    public static AttributeSupplier setAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 1.7)
                .add(Attributes.ATTACK_DAMAGE,5)
                .add(Attributes.ATTACK_KNOCKBACK,0.1)
                .add(Attributes.FOLLOW_RANGE, 5).build();
    }

    protected ParticleOptions getInkParticle() {
        return ParticleTypes.SQUID_INK;
    }
    public void aiStep() {
        super.aiStep();
        this.xBodyRotO = this.xBodyRot;
    }
    private Vec3 rotateVector(Vec3 pVector) {
        Vec3 vec3 = pVector.xRot(this.xBodyRotO * ((float)Math.PI / 180F));
        return vec3.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
    }
    private void spawnInk() {
        Vec3 vec3 = this.rotateVector(new Vec3(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

        for(int i = 0; i < 30; ++i) {
            Vec3 vec31 = this.rotateVector(new Vec3((double)this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double)this.random.nextFloat() * 0.6D - 0.3D));
            Vec3 vec32 = vec31.scale(0.3D + (double)(this.random.nextFloat() * 2.0F));
            ((ServerLevel)this.level).sendParticles(this.getInkParticle(), vec3.x, vec3.y + 0.5D, vec3.z, 0, vec32.x, vec32.y, vec32.z, (double)0.1F);
        }

    }

    public void setMoisntessLevel(int p_28344_) {
        this.entityData.set(MOISTNESS_LEVEL, p_28344_);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        pCompound.putInt("Moistness", this.getMoistnessLevel());
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 0.50, 2));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        this.setMoisntessLevel(pCompound.getInt("Moistness"));

    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }


}


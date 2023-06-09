package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;


public class PiranhaEntity extends SchoolingVarietyFish implements Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE;

    private int angerTime;
    private UUID targetUuid;

    public PiranhaEntity(EntityType<? extends PiranhaEntity> entityType, World world) {
        super(entityType, world);
    }

    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return (world.getBiome(pos.down()).isIn(BiomeTags.IS_JUNGLE) ||world.getBiome(pos.down()).matchesKey(BiomeKeys.MANGROVE_SWAMP))&& world.getBlockState(pos).isOf(Blocks.WATER);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
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


    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.world, nbt);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2,new PiranhaEntity.AttackGoal());
        this.goalSelector.add(3,new MeleeAttackGoal(this,1,true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ChickenEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, RabbitEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, OcelotEntity.class, 10, true, true, null));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.piranha_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.piranha_speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, NewConfig.piranha_knockback)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, NewConfig.piranha_follow);
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

    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }

    @Override
    public <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (this.isAttacking()){
            event.getController().setAnimation(RawAnimation.begin().then("bite", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }
}
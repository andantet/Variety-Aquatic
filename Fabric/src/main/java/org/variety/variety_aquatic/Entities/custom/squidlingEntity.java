package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Util.NewConfig;

import java.util.UUID;


public class squidlingEntity extends VarietyFish implements Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE;

    private int angerTime;
    private UUID targetUuid;

    public squidlingEntity(EntityType<? extends squidlingEntity> entityType, World world) {
        super(entityType, world);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
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
        this.goalSelector.add(2,new squidlingEntity.AttackGoal());
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ChickenEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, RabbitEntity.class, 10, true, true, null));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, OcelotEntity.class, 10, true, true, null));

        super.initGoals();
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.squidling_health)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.squidling_speed);

    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);

        // Create an ink cloud
        for (int i = 0; i < 20; i++) {
            this.world.addParticle(ParticleTypes.SQUID_INK, this.getX(), this.getY(), this.getZ(),
                    (this.random.nextDouble() - 0.5) * 0.2, -0.1, (this.random.nextDouble() - 0.5) * 0.2);
        }

        if (!this.world.isClient) {
            Entity attacker = source.getAttacker();
            if (attacker instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) attacker;
                // Give the player blindness effect for 2 seconds
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 2 * 20));

                // Check if the squidling is in a deep ocean biome and only water blocks are above it
                if (this.world.getRegistryManager().get(Registry.BIOME_KEY).getId(this.world.getBiome(this.getBlockPos()).value()).equals(BiomeKeys.DEEP_OCEAN.getValue())) {
                    BlockPos blockPos = this.getBlockPos();
                    if (hasOnlyWaterAbove(blockPos)) {
                        BlockState blockStateAbove = this.world.getBlockState(blockPos.up());
                        if (blockStateAbove.isAir() || blockStateAbove.isOf(Blocks.WATER)) {
                            ModEntities.GIANTGLOWINGSQUID.spawn((ServerWorld) this.world, null, null, null, this.getBlockPos(), SpawnReason.TRIGGERED, true, false);
                        }
                    }
                }
            }
        }
    }

    private boolean hasOnlyWaterAbove(BlockPos pos) {
        BlockPos.Mutable mutablePos = pos.mutableCopy();
        while (mutablePos.getY() < world.getSeaLevel()) {
            BlockState blockState = world.getBlockState(mutablePos);
            if (!blockState.isOf(Blocks.WATER)) {
                return false;
            }
            mutablePos.move(Direction.UP);
        }
        return true;
    }

    private class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(squidlingEntity.this, 1.25D, true);
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
}
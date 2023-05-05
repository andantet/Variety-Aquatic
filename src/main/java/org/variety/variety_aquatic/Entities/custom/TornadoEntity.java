package org.variety.variety_aquatic.Entities.custom;


import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Variety_Aquatic;

import java.util.List;
import java.util.Random;


public class TornadoEntity extends Entity {
    public TornadoEntity(EntityType<?> type, World world) {
        super(type, world);
        this.noClip = true;
    }

    @Override
    protected void initDataTracker() {
    }


    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
    private int getWaterDepth() {
        int waterDepth = 0;
        BlockPos.Mutable blockPos = getBlockPos().mutableCopy();
        while (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
            waterDepth++;
            blockPos.move(Direction.DOWN);
        }
        return waterDepth;
    }

    public void tick() {
        super.tick();

        // Only run client-side
        if (!world.isClient) {
            return;
        }

        MinecraftServer server = ((World) world).getServer();
        if (server != null) {
            float serverTps = server.getTickTime();
            if (serverTps < 20.0F / 10.0F) {
                return;
            }
        }
        int particleSpawnInterval = 2;
        if (world.getTime() % particleSpawnInterval != 0) {
            return;
        }
        // Precompute squared radius
        float radiusSq = 9.0F;

        // Get entities in the area and perform entity logic
        Box box = new Box(getPos().add(-3, -3, -3), getPos().add(3, 3, 3));
        world.getEntitiesByClass(LivingEntity.class, box, livingEntity -> {
            if (!livingEntity.isAlive()) {
                return false;
            }
            double dx = getPos().getX() - livingEntity.getX();
            double dy = getPos().getY() - livingEntity.getY();
            double dz = getPos().getZ() - livingEntity.getZ();
            double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq >= radiusSq) {
                return false;
            }
            double strength = 1.0 - distSq / radiusSq;

            // Check if the living entity is not in Creative or Spectator mode before applying velocity
            if (!(livingEntity instanceof PlayerEntity && (((PlayerEntity) livingEntity).isCreative() || ((PlayerEntity) livingEntity).isSpectator()))) {
                livingEntity.addVelocity(dx / distSq * strength * 0.5, dy / distSq * strength * 2, dz / distSq * strength * 0.5);
            }

            if (distSq < 1.0 && livingEntity instanceof LivingEntity) {
                livingEntity.setVelocity(dx * 0.5, livingEntity.getVelocity().getY(), dz * 0.5);
            }
            livingEntity.damage(DamageSource.IN_FIRE, 1.0F);
            return true;
        });

// Spawn particles
        if (this.isInsideWaterOrBubbleColumn()) {
            float maxY = getWaterDepth(); // Use the water depth
            float y = 0;
            float nY = 90;
            float dY = maxY / nY;
            double posX = this.getX();
            double posY = this.getY();
            double posZ = this.getZ();
            float angle = (float) (world.getTime() % 360) / 180F * (float) Math.PI; // calculate the current angle
            float angleOffset1 = (2 * (float) Math.PI) / 3;
            float angleOffset2 = (4 * (float) Math.PI) / 3;

            // First line of particles
            for (float a = 0, nA = 28 + random.nextInt(4), dA = (2 * (float) Math.PI) / nA; y < maxY; a += dA) {
                float r = y * 0.35F;
                float cosA = MathHelper.cos(a + angle) * r; // calculate x position based on angle
                float sinA = MathHelper.sin(a + angle) * r; // calculate z position based on angle
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, posX + cosA, posY + y - (maxY * 0.4), posZ + sinA, 0.0D, 0.085D, 0.0D);
                y += dY;
            }

            // Reset y value
            y = 0;

            // Second line of particles (120-degree offset)
            for (float a = 0, nA = 28 + random.nextInt(4), dA = (2 * (float) Math.PI) / nA; y < maxY; a += dA) {
                float r = y * 0.35F;
                float cosA = MathHelper.cos(a + angle + angleOffset1) * r; // calculate x position based on angle
                float sinA = MathHelper.sin(a + angle + angleOffset1) * r; // calculate z position based on angle
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, posX + cosA, posY + y - (maxY * 0.4), posZ + sinA, 0.0D, 0.085D, 0.0D);
                y += dY;
            }

            // Reset y value
            y = 0;

            // Third line of particles (240-degree offset)
            for (float a = 0, nA = 28 + random.nextInt(4), dA = (2 * (float) Math.PI) / nA; y < maxY; a += dA) {
                float r = y * 0.35F;
                float cosA = MathHelper.cos(a + angle + angleOffset2) * r; // calculate x position based on angle
                float sinA = MathHelper.sin(a + angle + angleOffset2) * r; // calculate z position based on angle
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, posX + cosA, posY + y - (maxY * 0.4), posZ + sinA, 0.0D, 0.085D, 0.0D);
                y += dY;
            }
        }
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}

package org.variety.variety_aquatic.Entities.custom;


import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Box;
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

    public void tick() {
        super.tick();
        if (!world.isClient) {
            return; // Only run client-side
        }

        float radiusSq = 9.0F; // Precompute squared radius
        Box box = new Box(getPos().add(-3, -3, -3), getPos().add(3, 3, 3));
        world.getEntitiesByClass(LivingEntity.class, box, livingEntity -> {
            if (livingEntity.isSpectator() || !livingEntity.isAlive()) {
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
            livingEntity.addVelocity(dx / distSq * strength * 0.5, dy / distSq * strength * 2, dz / distSq * strength * 0.5);
            if (distSq < 1.0 && livingEntity instanceof LivingEntity) {
                livingEntity.setVelocity(dx * 0.5, livingEntity.getVelocity().getY(), dz * 0.5);
            }
            livingEntity.damage(DamageSource.IN_FIRE, 1.0F);
            return true;
        });

        // Spawn particles
        if (this.isInsideWaterOrBubbleColumn()) {
            float maxY = (float) this.getBoundingBox().getYLength() * 1.65F;
            float y = 0;
            float nY = 90;
            float dY = maxY / nY;
            double posX = this.getX();
            double posY = this.getY();
            double posZ = this.getZ();
            for (float a = 0, nA = 28 + random.nextInt(4), dA = (2 * (float) Math.PI) / nA; y < maxY; a += dA) {
                float r = y * 0.35F;
                float cosA = MathHelper.cos(a) * r;
                float sinA = MathHelper.sin(a) * r;
                world.addParticle(ParticleTypes.BUBBLE, posX + cosA, posY + y - (maxY * 0.4), posZ + sinA, 0.0D, 0.085D, 0.0D);
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

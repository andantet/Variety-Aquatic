package org.variety.variety_aquatic.Util;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class TornadoParticleFactory implements ParticleFactory<DefaultParticleType> {
    private final SpriteProvider spriteProvider;

    public TornadoParticleFactory(SpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    @Override
    public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        TornadoParticle particle = new TornadoParticle(world, x, y, z, velocityX, velocityY, velocityZ);
        particle.setSprite(this.spriteProvider);
        return particle;
    }
}

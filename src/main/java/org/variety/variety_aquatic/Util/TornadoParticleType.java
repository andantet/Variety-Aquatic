package org.variety.variety_aquatic.Util;

import net.minecraft.particle.DefaultParticleType;

public class TornadoParticleType extends DefaultParticleType {
    public TornadoParticleType() {
        super(false); // "false" means the particle type is not always visible (i.e., not visible through blocks)
    }
}

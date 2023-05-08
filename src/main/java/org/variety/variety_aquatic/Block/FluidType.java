package org.variety.variety_aquatic.Block;

import net.minecraft.util.StringIdentifiable;

public enum FluidType implements StringIdentifiable {
    NONE("none"),
    WATER("water"),
    GLOWING_WATER("glowing_water");

    private final String name;

    FluidType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}

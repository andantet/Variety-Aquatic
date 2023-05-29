package org.variety.variety_aquatic.Util;

import net.minecraft.util.math.Vec3d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeFogConfig {
    // Enabled dimensions
    public List<String> enabledDimensions = List.of("minecraft:overworld");

    // Sky colors
    public Map<String, Vec3d> skyColorAdditions = Map.of("minecraft:overworld", new Vec3d(0.15f, 0.15f, 0.15f));
    public Map<String, Vec3d> skyColorAdditionsRain = Map.of("minecraft:overworld", new Vec3d(-0.15f, -0.15f, -0.15f));
    public Map<String, Vec3d> skyColorAdditionsNight = Map.of("minecraft:overworld", new Vec3d(0f, 0f, 0f));

    // Fog colors
    public Vec3d defaultFogColor = new Vec3d(0.68f, 0.83f, 1f);
    public Vec3d defaultFogColorRain = new Vec3d(0.4f, 0.46f, 0.54f);
    public Vec3d defaultFogColorNight = new Vec3d(0f, 0f, 0f);
    public Map<String, Vec3d> fogColors = Map.of(
            "minecraft:snowy_beach", new Vec3d(0.96f, 0.98f, 0.94f),
            "minecraft:frozen_ocean", new Vec3d(0.96f, 0.98f, 0.94f)
    );
    public Map<String, Vec3d> fogColorsRain = Map.of(
            "minecraft:snowy_beach", new Vec3d(0.96f, 0.98f, 0.94f),
            "minecraft:frozen_ocean", new Vec3d(0.96f, 0.98f, 0.94f)
    );
    public Map<String, Vec3d> fogColorsNight = Map.of(
            "minecraft:snowy_beach", new Vec3d(0.1f, 0.1f, 0.1f),
            "minecraft:frozen_ocean", new Vec3d(0.1f, 0.1f, 0.1f)
    );

    // Fog start additions and fog end additions
    public Map<String, Float> fogStartAdditions = Map.of("minecraft:overworld", 100f);
    public Map<String, Float> fogEndAdditions = Map.of(
            "minecraft:overworld", 200f,
            "minecraft:frozen_ocean", 150f
    );
    public Map<String, Float> fogStartAdditionsRain = Map.of(
            "minecraft:overworld", 100f,
            "minecraft:snowy_beach", 0f,
            "minecraft:frozen_ocean", 0f
    );
    public Map<String, Float> fogEndAdditionsRain = Map.of(
            "minecraft:overworld", 200f,
            "minecraft:snowy_beach", 0f,
            "minecraft:frozen_ocean", 0f
    );
    public Map<String, Float> fogStartAdditionsNight = Map.of(
            "minecraft:snowy_beach", 100f,
            "minecraft:frozen_ocean", 100f
    );
    public Map<String, Float> fogEndAdditionsNight = Map.of(
            "minecraft:snowy_beach", 150f,
            "minecraft:frozen_ocean", 150f
    );

    public transient Vec3d fogColor = this.defaultFogColor;
    public transient float fogColorLerpTime;
    public transient float fogStartAddition = 0f;
    public transient float fogEndAddition = 0f;
    public transient Vec3d skyColorAddition = Vec3d.ZERO;
}

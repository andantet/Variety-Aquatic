package org.variety.variety_aquatic.Util;

import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class BiomeFogConfig {
    public static List<String> enabledDimensions = List.of(
            "minecraft:overworld",
            "minecraft:the_nether"
    );

    // Sky colors
    public static Map<String, Vec3d> skyColorAdditions = Map.ofEntries(
            entry("minecraft:overworld", new Vec3d(0.15f, 0.15f, 0.15f))
    );
    public static Map<String, Vec3d> skyColorAdditionsRain = Map.ofEntries(
            entry("minecraft:overworld", new Vec3d(-0.15f, -0.15f, -0.15f))
    );
    public static Map<String, Vec3d> skyColorAdditionsNight = Map.ofEntries(
            entry("minecraft:overworld", new Vec3d(0f, 0f, 0f))
    );

    public static Vec3d defaultFogColor = new Vec3d(0.68f, 0.83f, 1f);
    public static Vec3d defaultFogColorRain = new Vec3d(0.4f, 0.46f, 0.54f);
    public static Vec3d defaultFogColorNight = new Vec3d(0f, 0f, 0f);
    public static Map<String, Vec3d> fogColors = Map.ofEntries(
            entry("minecraft:the_nether", new Vec3d(0.26f, 0f, 0f)),
            entry("minecraft:swamp", new Vec3d(0.28f, 0.28f, 0.05f)),
            entry("minecraft:mangrove_swamp", new Vec3d(0.28f, 0.28f, 0.05f)),
            entry("minecraft:jungle", new Vec3d(0.15f, 0.42f, 0.13f)),
            entry("minecraft:bamboo_jungle", new Vec3d(0.15f, 0.42f, 0.13f)),
            entry("minecraft:sparse_jungle", new Vec3d(0.15f, 0.42f, 0.13f)),
            entry("minecraft:snowy_plains", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_slopes", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_taiga", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_beach", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_peaks", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:jagged_peaks", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_ocean", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_river", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:ice_spikes", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:grove", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:desert", new Vec3d(0.84f, 0.78f, 0.6f)),
            entry("minecraft:badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:eroded_badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:wooded_badlands", new Vec3d(0.75f, 0.4f, 0.13f))
    );
    public static Map<String, Vec3d> fogColorsRain = Map.ofEntries(
            entry("minecraft:the_nether", new Vec3d(0.26f, 0f, 0f)),
            entry("minecraft:swamp", new Vec3d(0.042f, 0.042f, 0.0075f)),
            entry("minecraft:mangrove_swamp", new Vec3d(0.042f, 0.042f, 0.0075f)),
            entry("minecraft:jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:bamboo_jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:sparse_jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:snowy_plains", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_slopes", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_taiga", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:snowy_beach", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_peaks", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:jagged_peaks", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_ocean", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:frozen_river", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:ice_spikes", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:grove", new Vec3d(0.96f, 0.98f, 0.94f)),
            entry("minecraft:desert", new Vec3d(0.84f, 0.78f, 0.6f)),
            entry("minecraft:badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:eroded_badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:wooded_badlands", new Vec3d(0.75f, 0.4f, 0.13f))
    );
    public static Map<String, Vec3d> fogColorsNight = Map.ofEntries(
            entry("minecraft:the_nether", new Vec3d(0.26f, 0f, 0f)),
            entry("minecraft:swamp", new Vec3d(0.042f, 0.042f, 0.0075f)),
            entry("minecraft:mangrove_swamp", new Vec3d(0.042f, 0.042f, 0.0075f)),
            entry("minecraft:jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:bamboo_jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:sparse_jungle", new Vec3d(0.0225f, 0.063f, 0.0195f)),
            entry("minecraft:snowy_plains", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:snowy_slopes", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:snowy_taiga", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:snowy_beach", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:frozen_peaks", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:jagged_peaks", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:frozen_ocean", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:frozen_river", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:ice_spikes", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:grove", new Vec3d(0.1f, 0.1f, 0.1f)),
            entry("minecraft:desert", new Vec3d(0.84f, 0.78f, 0.6f)),
            entry("minecraft:badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:eroded_badlands", new Vec3d(0.75f, 0.4f, 0.13f)),
            entry("minecraft:wooded_badlands", new Vec3d(0.75f, 0.4f, 0.13f))
    );

    // Fog start additions and fog end additions
    public static Map<String, Float> fogStartAdditions = Map.ofEntries(
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f),
            entry("minecraft:jungle", 0f),
            entry("minecraft:bamboo_jungle", 0f),
            entry("minecraft:sparse_jungle", 100f),
            entry("minecraft:snowy_plains", 100f),
            entry("minecraft:snowy_slopes", 100f),
            entry("minecraft:snowy_taiga", 100f),
            entry("minecraft:snowy_beach", 100f),
            entry("minecraft:frozen_peaks", 100f),
            entry("minecraft:jagged_peaks", 100f),
            entry("minecraft:frozen_ocean", 100f),
            entry("minecraft:frozen_river", 100f),
            entry("minecraft:ice_spikes", 100f),
            entry("minecraft:grove", 100f),
            entry("minecraft:desert", 0f),
            entry("minecraft:badlands", 0f),
            entry("minecraft:eroded_badlands", 0f),
            entry("minecraft:wooded_badlands", 0f)
    );
    public static Map<String, Float> fogEndAdditions = Map.ofEntries(
            entry("minecraft:overworld", 100f),
            entry("minecraft:the_nether", 100f),
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f),
            entry("minecraft:jungle", 0f),
            entry("minecraft:bamboo_jungle", 0f),
            entry("minecraft:snowy_plains", 0f),
            entry("minecraft:snowy_slopes", 0f),
            entry("minecraft:snowy_taiga", 0f),
            entry("minecraft:snowy_beach", 0f),
            entry("minecraft:frozen_peaks", 0f),
            entry("minecraft:jagged_peaks", 0f),
            entry("minecraft:frozen_ocean", 0f),
            entry("minecraft:frozen_river", 0f),
            entry("minecraft:ice_spikes", 0f),
            entry("minecraft:grove", 0f),
            entry("minecraft:desert", 0f),
            entry("minecraft:badlands", 0f),
            entry("minecraft:eroded_badlands", 0f),
            entry("minecraft:wooded_badlands", 0f)
    );
    public static Map<String, Float> fogStartAdditionsRain = Map.ofEntries(
            entry("minecraft:overworld", 100f),
            entry("minecraft:the_nether", 100f),
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f)
            // other entries...
    );
    public static Map<String, Float> fogEndAdditionsRain = Map.ofEntries(
            entry("minecraft:overworld", 200f),
            entry("minecraft:the_nether", 200f),
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f),
            entry("minecraft:jungle", 0f),
            entry("minecraft:bamboo_jungle", 0f),
            entry("minecraft:snowy_plains", 0f),
            entry("minecraft:snowy_slopes", 0f),
            entry("minecraft:snowy_taiga", 0f),
            entry("minecraft:snowy_beach", 0f),
            entry("minecraft:frozen_peaks", 0f),
            entry("minecraft:jagged_peaks", 0f),
            entry("minecraft:frozen_ocean", 0f),
            entry("minecraft:frozen_river", 0f),
            entry("minecraft:ice_spikes", 0f),
            entry("minecraft:grove", 0f),
            entry("minecraft:desert", 0f),
            entry("minecraft:badlands", 0f),
            entry("minecraft:eroded_badlands", 0f),
            entry("minecraft:wooded_badlands", 0f)
    );
    public static Map<String, Float> fogStartAdditionsNight = Map.ofEntries(
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f),
            entry("minecraft:jungle", 0f),
            entry("minecraft:bamboo_jungle", 0f),
            entry("minecraft:sparse_jungle", 100f),
            entry("minecraft:snowy_plains", 100f),
            entry("minecraft:snowy_slopes", 100f),
            entry("minecraft:snowy_taiga", 100f),
            entry("minecraft:snowy_beach", 100f),
            entry("minecraft:frozen_peaks", 100f),
            entry("minecraft:jagged_peaks", 100f),
            entry("minecraft:frozen_ocean", 100f),
            entry("minecraft:frozen_river", 100f),
            entry("minecraft:ice_spikes", 100f),
            entry("minecraft:grove", 100f),
            entry("minecraft:desert", 0f),
            entry("minecraft:badlands", 0f),
            entry("minecraft:eroded_badlands", 0f),
            entry("minecraft:wooded_badlands", 0f)
    );
    public static Map<String, Float> fogEndAdditionsNight = Map.ofEntries(
            entry("minecraft:swamp", 0f),
            entry("minecraft:mangrove_swamp", 0f),
            entry("minecraft:jungle", 0f),
            entry("minecraft:bamboo_jungle", 0f),
            entry("minecraft:sparse_jungle", 200f),
            entry("minecraft:snowy_plains", 150f),
            entry("minecraft:snowy_slopes", 150f),
            entry("minecraft:snowy_taiga", 150f),
            entry("minecraft:snowy_beach", 150f),
            entry("minecraft:frozen_peaks", 150f),
            entry("minecraft:jagged_peaks", 150f),
            entry("minecraft:frozen_ocean", 150f),
            entry("minecraft:frozen_river", 150f),
            entry("minecraft:ice_spikes", 150f),
            entry("minecraft:grove", 150f),
            entry("minecraft:desert", 0f),
            entry("minecraft:badlands", 0f),
            entry("minecraft:eroded_badlands", 0f),
            entry("minecraft:wooded_badlands", 0f)
    );

    public static Vec3d fogColor = defaultFogColor;
    public static float fogStartAddition = 0f;
    public static float fogEndAddition = 0f;
    public static Vec3d skyColorAddition = Vec3d.ZERO;
}

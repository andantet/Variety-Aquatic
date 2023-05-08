package org.variety.variety_aquatic.Biome;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
public final class BiomeMaps {
    // Mixin cannot handle aastore in initializers so it's been moved here
    public static final RegistryKey<Biome>[][] RR_RIVER_BIOMES = new RegistryKey[][]{
            // Handled by vanilla already
            {null, null, null, null, null},
            {OceanBiomes.GRAVELLY, OceanBiomes.GRAVELLY, OceanBiomes.GRAVELLY, OceanBiomes.GRAVELLY, OceanBiomes.GRAVELLY},
            {BiomeKeys.RIVER, BiomeKeys.RIVER, BiomeKeys.RIVER, BiomeKeys.RIVER, BiomeKeys.RIVER},
            {BiomeKeys.RIVER, BiomeKeys.RIVER, BiomeKeys.RIVER, OceanBiomes.TROPICAL, OceanBiomes.TROPICAL},
            {OceanBiomes.SANDY, OceanBiomes.SANDY, OceanBiomes.SANDY, OceanBiomes.SANDY, OceanBiomes.SANDY}
    }; // TODO: carved rivers
}
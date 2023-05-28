package org.variety.variety_aquatic.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import org.variety.variety_aquatic.Variety_Aquatic;

public class WorldUtil {
    private static final Identifier ERROR_IDENTIFIER_BIOME = Identifier.of(Variety_Aquatic.MOD_ID, "error_fetching_biome");
    private static final Identifier ERROR_IDENTIFIER_DIMENSION = Identifier.of(Variety_Aquatic.MOD_ID, "error_fetching_dimension");

    public static RegistryKey<Biome> GetBiomeBelowCamera(Camera camera) {
        if (camera == null) return RegistryKey.of(Registry.BIOME_KEY, ERROR_IDENTIFIER_BIOME);

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return RegistryKey.of(Registry.BIOME_KEY, ERROR_IDENTIFIER_BIOME);

        var biomeBelowCamera = world.getBiome(camera.getBlockPos()).getKey();
        return biomeBelowCamera.orElse(RegistryKey.of(Registry.BIOME_KEY, ERROR_IDENTIFIER_BIOME));
    }

    public static RegistryKey<DimensionType> GetDimension() {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return RegistryKey.of(Registry.DIMENSION_TYPE_KEY, ERROR_IDENTIFIER_DIMENSION);

        var dimension = world.getDimensionKey();
        return (dimension != null) ? dimension : RegistryKey.of(Registry.DIMENSION_TYPE_KEY, ERROR_IDENTIFIER_DIMENSION);
    }
}

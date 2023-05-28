package org.variety.variety_aquatic.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

public class WorldUtil {
    public static Identifier GetBiomeBelowCamera(Camera camera) {
        var errorIdentifier = Identifier.of(Variety_Aquatic.MOD_ID, "error_fetching_biome");
        if (camera == null) return errorIdentifier;

        var world = MinecraftClient.getInstance().world;
        if (world == null) return errorIdentifier;

        var biomeBelowCamera = MinecraftClient.getInstance().world.getBiome(camera.getBlockPos()).getKey();
        if (biomeBelowCamera.isEmpty()) return errorIdentifier;

        return biomeBelowCamera.get().getValue();
    }

    public static Identifier GetDimension() {
        var errorIdentifier = Identifier.of(Variety_Aquatic.MOD_ID, "error_fetching_dimension");

        var world = MinecraftClient.getInstance().world;
        if (world == null) return errorIdentifier;

        var dimension = MinecraftClient.getInstance().world.getDimensionKey();
        if (dimension == null) return errorIdentifier;

        return dimension.getValue();
    }
}
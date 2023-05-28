package org.variety.variety_aquatic.Mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.variety.variety_aquatic.Client.Variety_AquaticClient;
import org.variety.variety_aquatic.Util.*;

import java.util.Map;


@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
    private static final float LERP_DELTA = 0.001f;

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFogInject(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        World world = minecraftClient.world;
        if (world == null || world.getBiome(camera.getBlockPos()).getKey().isEmpty() || !camera.getSubmersionType().equals(CameraSubmersionType.NONE)) {
            return;
        }

        // Get current biome and dimension (at the camera's position)
        String currentBiome = WorldUtil.GetBiomeBelowCamera(camera).toString();
        String currentDimension = WorldUtil.GetDimension().toString();
        Variety_AquaticClient.currentBiome = currentBiome;
        Variety_AquaticClient.currentDimension = currentDimension;

        // Check if current dimension is enabled
        if (!BiomeFogConfig.enabledDimensions.contains(currentDimension) && !BiomeFogConfig.enabledDimensions.contains("all")) {
            return;
        }

        // Set custom fog and sky color
        Vec3d currentBiomeFogColor;
        Float fogStartAddition, fogEndAddition;
        Map<String, Vec3d> fogColors;
        Map<String, Float> fogStartAdditions, fogEndAdditions;

        if (world.isRaining() || world.isThundering()) {
            fogStartAdditions = BiomeFogConfig.fogStartAdditionsRain;
            fogEndAdditions = BiomeFogConfig.fogEndAdditionsRain;
            fogColors = BiomeFogConfig.fogColorsRain;
            currentBiomeFogColor = BiomeFogConfig.defaultFogColorRain;
        } else if (TimeUtil.isNight((ClientWorld) world)) {
            fogStartAdditions = BiomeFogConfig.fogStartAdditionsNight;
            fogEndAdditions = BiomeFogConfig.fogEndAdditionsNight;
            fogColors = BiomeFogConfig.fogColorsNight;
            currentBiomeFogColor = BiomeFogConfig.defaultFogColorNight;
        } else {
            fogStartAdditions = BiomeFogConfig.fogStartAdditions;
            fogEndAdditions = BiomeFogConfig.fogEndAdditions;
            fogColors = BiomeFogConfig.fogColors;
            currentBiomeFogColor = BiomeFogConfig.defaultFogColor;
        }

        fogStartAddition = MathUtil.lerp(BiomeFogConfig.fogStartAddition, fogStartAdditions.getOrDefault(currentBiome, fogStartAdditions.getOrDefault(currentDimension, 0f)), LERP_DELTA);
        fogEndAddition = MathUtil.lerp(BiomeFogConfig.fogEndAddition, fogEndAdditions.getOrDefault(currentBiome, fogEndAdditions.getOrDefault(currentDimension, 0f)), LERP_DELTA);

        if (fogColors.containsKey(currentBiome)) {
            currentBiomeFogColor = fogColors.get(currentBiome);
        } else if (fogColors.containsKey(currentDimension)) {
            currentBiomeFogColor = fogColors.get(currentDimension);
        }

            if (fogStartAdditions.containsKey(currentBiome) || fogEndAdditions.containsKey(currentBiome) || fogColors.containsKey(currentBiome) || fogStartAdditions.containsKey(currentDimension) || fogEndAdditions.containsKey(currentDimension) || fogColors.containsKey(currentDimension)) {
                BiomeFogConfig.fogStartAddition = fogStartAddition;
                BiomeFogConfig.fogEndAddition = fogEndAddition;
            } else {
                BiomeFogConfig.fogStartAddition = MathUtil.lerp(BiomeFogConfig.fogStartAddition, 0f, LERP_DELTA);
                BiomeFogConfig.fogEndAddition = MathUtil.lerp(BiomeFogConfig.fogEndAddition, 0f, LERP_DELTA);
            }
        }


        private static float vanillaFogStart(float viewDistance) {
        float f = MathUtil.clamp(viewDistance / 10.0f, 4.0f, 64.0f);
        return viewDistance - f;
    }

    @Shadow
    public static void clearFog() {
    }

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private static void renderInject(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        RenderSystemUtil.setClearColor(BiomeFogConfig.fogColor);
        clearFog();
        ci.cancel();
    }

    // Changes the color of the seam/transition in the sky
    @Inject(method = "setFogBlack", at = @At("HEAD"), cancellable = true)
    private static void setFogBlackInject(CallbackInfo ci) {
        RenderSystemUtil.setShaderFogColor(BiomeFogConfig.fogColor);
        ci.cancel();
    }
}
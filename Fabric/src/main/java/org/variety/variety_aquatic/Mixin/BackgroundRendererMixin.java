package org.variety.variety_aquatic.Mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.variety.variety_aquatic.Client.Variety_AquaticClient;
import org.variety.variety_aquatic.Util.*;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
    @Shadow
    public static void clearFog() {
    }

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFogInject(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        var world = MinecraftClient.getInstance().world;
        if (world == null || world.getBiome(camera.getBlockPos()).getKey().isEmpty() || !camera.getSubmersionType().equals(CameraSubmersionType.NONE)) {
            return;
        }

        // Get current biome and dimension (at the camera's position)
        Variety_AquaticClient.currentBiome = WorldUtil.GetBiomeBelowCamera(camera).toString();
        Variety_AquaticClient.currentDimension = WorldUtil.GetDimension().toString();

        // Check if current dimension is enabled
        if (!BiomeFogConfigLoader.CONFIG.enabledDimensions.contains(Variety_AquaticClient.currentDimension) && !BiomeFogConfigLoader.CONFIG.enabledDimensions.contains("all")) {
            return;
        }

        // Set custom fog and sky color
        Vec3d currentBiomeFogColor;
        float fogStartAddition;
        float fogEndAddition;

        if (world.isRaining() || world.isThundering()) {
            fogStartAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogStartAddition, BiomeFogConfigLoader.CONFIG.fogStartAdditionsRain.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogStartAdditionsRain.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            fogEndAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogEndAddition, BiomeFogConfigLoader.CONFIG.fogEndAdditionsRain.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogEndAdditionsRain.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            currentBiomeFogColor = BiomeFogConfigLoader.CONFIG.fogColorsRain.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogColorsRain.getOrDefault(Variety_AquaticClient.currentDimension, BiomeFogConfigLoader.CONFIG.defaultFogColorRain));
        } else if (TimeUtil.isNight(world)) {
            fogStartAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogStartAddition, BiomeFogConfigLoader.CONFIG.fogStartAdditionsNight.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogStartAdditionsNight.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            fogEndAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogEndAddition, BiomeFogConfigLoader.CONFIG.fogEndAdditionsNight.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogEndAdditionsNight.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            currentBiomeFogColor = BiomeFogConfigLoader.CONFIG.fogColorsNight.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogColorsNight.getOrDefault(Variety_AquaticClient.currentDimension, BiomeFogConfigLoader.CONFIG.defaultFogColorNight));
        } else {
            fogStartAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogStartAddition, BiomeFogConfigLoader.CONFIG.fogStartAdditions.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogStartAdditions.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            fogEndAddition = MathUtil.lerp(BiomeFogConfigLoader.CONFIG.fogEndAddition, BiomeFogConfigLoader.CONFIG.fogEndAdditions.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogEndAdditions.getOrDefault(Variety_AquaticClient.currentDimension, 0f)), 0.001f);
            currentBiomeFogColor = BiomeFogConfigLoader.CONFIG.fogColors.getOrDefault(Variety_AquaticClient.currentBiome, BiomeFogConfigLoader.CONFIG.fogColors.getOrDefault(Variety_AquaticClient.currentDimension, BiomeFogConfigLoader.CONFIG.defaultFogColor));
        }

        // Linearly interpolate fog color
        BiomeFogConfigLoader.CONFIG.fogColor = BiomeFogConfigLoader.CONFIG.fogColor.lerp(currentBiomeFogColor, 0.001f);

        // Update fog
        RenderSystem.setShaderFogStart(MathUtil.lerp(vanillaFogStart(viewDistance), 0f + fogStartAddition, BiomeFogConfigLoader.CONFIG.fogColorLerpTime));
        RenderSystem.setShaderFogEnd(MathUtil.lerp(viewDistance, viewDistance / 3 + fogEndAddition, BiomeFogConfigLoader.CONFIG.fogColorLerpTime));
        RenderSystemUtil.setShaderFogColor(BiomeFogConfigLoader.CONFIG.fogColor);

        // Re-render light and dark skies to update WorldRendererMixin changes
        MinecraftClient.getInstance().worldRenderer.renderLightSky();
        MinecraftClient.getInstance().worldRenderer.renderDarkSky();
    }

    private static float vanillaFogStart(float viewDistance) {
        float f = MathUtil.clamp(viewDistance / 10.0f, 4.0f, 64.0f);
        return viewDistance - f;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private static void renderInject(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        RenderSystemUtil.setClearColor(BiomeFogConfigLoader.CONFIG.fogColor);
        clearFog();
        ci.cancel();
    }

    @Inject(method = "setFogBlack", at = @At("HEAD"), cancellable = true)
    private static void setFogBlackInject(CallbackInfo ci) {
        RenderSystemUtil.setShaderFogColor(BiomeFogConfigLoader.CONFIG.fogColor);
        ci.cancel();
    }
}

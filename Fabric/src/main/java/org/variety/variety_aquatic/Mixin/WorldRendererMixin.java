package org.variety.variety_aquatic.Mixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.variety.variety_aquatic.Client.Variety_AquaticClient;
import org.variety.variety_aquatic.Util.BiomeFogConfigLoader;
import org.variety.variety_aquatic.Util.TimeUtil;

import java.util.Map;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyVariable(method = "renderSky(Lnet/minecraft/client/render/BufferBuilder;F)Lnet/minecraft/client/render/BufferBuilder$BuiltBuffer;",
            at = @At(value = "INVOKE_ASSIGN", target = "Ljava/lang/Math;signum(F)F"),
            argsOnly = true)
    private static float renderSkyBufferBuilderInject(float g) {
        return Math.signum(16.0f) * 8.0f;
    }

    @ModifyVariable(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/world/ClientWorld;getSkyColor(Lnet/minecraft/util/math/Vec3d;F)Lnet/minecraft/util/math/Vec3d;"))
    @SuppressWarnings("InvalidInjectorMethodSignature")
    public Vec3d renderSkyInject(Vec3d original) {
        var world = MinecraftClient.getInstance().world;
        if (world == null) return original;

        Vec3d skyColorAddition = getSkyColorAddition();

        if (world.isRaining() || world.isThundering()) {
            skyColorAddition = getSkyColorAdditionRain();
        } else if (TimeUtil.isNight(world)) {
            skyColorAddition = getSkyColorAdditionNight();
        }

        return original.add(skyColorAddition);
    }

    private Vec3d getSkyColorAddition() {
        return lerpSkyColor(BiomeFogConfigLoader.CONFIG.skyColorAdditions);
    }

    private Vec3d getSkyColorAdditionRain() {
        return lerpSkyColor(BiomeFogConfigLoader.CONFIG.skyColorAdditionsRain);
    }

    private Vec3d getSkyColorAdditionNight() {
        return lerpSkyColor(BiomeFogConfigLoader.CONFIG.skyColorAdditionsNight);
    }

    private Vec3d lerpSkyColor(Map<String, Vec3d> skyColorAdditions) {
        return BiomeFogConfigLoader.CONFIG.skyColorAddition.lerp(
                skyColorAdditions.getOrDefault(
                        Variety_AquaticClient.currentBiome,
                        skyColorAdditions.getOrDefault(
                                Variety_AquaticClient.currentDimension, Vec3d.ZERO)),
                0.001f);
    }
}
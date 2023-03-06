package org.variety.varietyaquatic.entity.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SharkRenderer extends GeoEntityRenderer<SharkEntity> {
    public SharkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SharkModel());
        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(SharkEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/shark_texture.png");
    }

    @Override
    public RenderType getRenderType(SharkEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
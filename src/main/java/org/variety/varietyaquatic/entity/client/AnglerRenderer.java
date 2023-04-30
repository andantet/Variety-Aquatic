package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;


public class AnglerRenderer extends GeoEntityRenderer<AnglerfishEntity> {
    public AnglerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnglerModel());
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderType::entityTranslucentEmissive));

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(AnglerfishEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/anglerfish_texture.png");
    }

    @Override
    public RenderType getRenderType(AnglerfishEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.3f, 1.3f, 1.3f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
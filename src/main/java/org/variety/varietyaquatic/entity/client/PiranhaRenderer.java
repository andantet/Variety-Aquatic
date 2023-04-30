package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import org.variety.varietyaquatic.entity.custom.Piranha;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class PiranhaRenderer extends GeoEntityRenderer<Piranha> {
    public PiranhaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PiranhaModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(Piranha instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/piranha_texture.png");
    }

    @Override
    public RenderType getRenderType(Piranha animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
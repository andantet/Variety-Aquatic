package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SpottedStingrayEntity;
import org.variety.varietyaquatic.entity.custom.TetraEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class StingrayRenderer extends GeoEntityRenderer<SpottedStingrayEntity> {
    public StingrayRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StingrayModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(SpottedStingrayEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/spottedstingray_texture.png");
    }

    @Override
    public RenderType getRenderType(SpottedStingrayEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
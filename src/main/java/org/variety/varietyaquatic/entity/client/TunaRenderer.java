package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.CuttlefishEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class TunaRenderer extends GeoEntityRenderer<YellowFinTuna> {
    public TunaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TunaModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(YellowFinTuna instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/yellowfintuna_texture.png");
    }

    @Override
    public RenderType getRenderType(YellowFinTuna animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
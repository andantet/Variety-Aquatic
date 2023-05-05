package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

import java.util.function.Function;

// Your imports go here

public class WhaleSharkRenderer extends GeoEntityRenderer<WhaleSharkEntity> {

    public WhaleSharkRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new WhaleSharkModel());
        this.shadowRadius = 0.4f;
        this.addLayer(new NighttimeGlowLayer<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
    }

    @Override
    public Identifier getTextureResource(WhaleSharkEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/whaleshark_texture.png");
    }

    @Override
    public RenderLayer getRenderType(WhaleSharkEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.3f, 1.3f, 1.3f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    private static class NighttimeGlowLayer<T extends WhaleSharkEntity> extends LayerGlowingAreasGeo<T> {

        public NighttimeGlowLayer(GeoEntityRenderer<T> renderer, Function<T, Identifier> textureFunction, Function<T, Identifier> modelFunction, Function<Identifier, RenderLayer> layerFunction) {
            super(renderer, textureFunction, modelFunction, layerFunction);
        }

        @Override
        public void render(MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int packedLightIn, T animatable, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            long timeOfDay = animatable.world.getTimeOfDay() % 24000;
            if (timeOfDay >= 12000 && timeOfDay < 24000) {
                super.render(matrixStack, renderTypeBuffer, packedLightIn, animatable, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
            }
        }
    }
}

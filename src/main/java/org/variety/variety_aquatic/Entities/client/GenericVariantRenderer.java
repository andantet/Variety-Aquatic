package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GenericVariantRenderer<T extends LivingEntity & IVariantEntity & IAnimatable, V extends Enum<V> & IVariant> extends GeoEntityRenderer<T> {
    public GenericVariantRenderer(EntityRendererFactory.Context ctx, AnimatedGeoModel<T> model) {
        super(ctx, model);
    }

    @Override
    public Identifier getTextureResource(T instance) {
        return instance.getVariant().getTextureResource();
    }

    @Override
    public RenderLayer getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}

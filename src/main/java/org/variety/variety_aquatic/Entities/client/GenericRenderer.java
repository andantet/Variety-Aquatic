package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class GenericRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

    private final float scale;
    private final float scalebaby;
    private final String texturePath;
    private final boolean isTranslucent;


    public GenericRenderer(EntityRendererFactory.Context ctx, AnimatedGeoModel model, String texturePath, float scale,float scalebaby, boolean isTranslucent, boolean hasGlowLayer) {
        super(ctx, model);
        this.texturePath = texturePath;
        this.scale = scale;
        this.scalebaby = scalebaby;

        this.isTranslucent = isTranslucent;

        this.shadowRadius = 0.4f;
        //fix later
      //  if(hasGlowLayer) {
        //    this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        //}
    }

    @Override
    public Identifier getTextureResource(T instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, texturePath);
    }

    @Override
    public RenderLayer getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(scalebaby, scalebaby, scalebaby);
        }
        else stack.scale(scale, scale, scale);
        return isTranslucent ? RenderLayer.getEntityTranslucent(getTextureResource(animatable))
                : super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
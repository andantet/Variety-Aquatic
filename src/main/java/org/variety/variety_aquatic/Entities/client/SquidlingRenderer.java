package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.VampireSquidEntity;
import org.variety.variety_aquatic.Entities.custom.squidlingEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class SquidlingRenderer extends GeoEntityRenderer<squidlingEntity> {
    public SquidlingRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new SquidlingModel());
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(squidlingEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/squidling_texture.png");
    }


    @Override
    public RenderLayer getRenderType(squidlingEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.0f, 1.0f, 1.0f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}

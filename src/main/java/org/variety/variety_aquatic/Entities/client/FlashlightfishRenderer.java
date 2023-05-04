package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BarreleyeEntity;
import org.variety.variety_aquatic.Entities.custom.FlashlightfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class FlashlightfishRenderer extends GeoEntityRenderer<FlashlightfishEntity> {
    public FlashlightfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new FlashlightfishModel());
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(FlashlightfishEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/flashlightfish_texture.png");
    }


    @Override
    public RenderLayer getRenderType(FlashlightfishEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.0f, 1.0f, 1.0f);
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}

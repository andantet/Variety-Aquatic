package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.AnglerFishEntity;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class AnglerFishRenderer extends GeoEntityRenderer<AnglerFishEntity> {
    public AnglerFishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AnglerFishModel());
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureResource, getGeoModelProvider()::getModelResource, RenderLayer::getEntityTranslucentEmissive));
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(AnglerFishEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/anglerfish_texture.png");
    }


    @Override
    public RenderLayer getRenderType(AnglerFishEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.3f, 1.3f, 1.3f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}

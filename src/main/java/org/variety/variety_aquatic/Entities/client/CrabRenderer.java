package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.CrabEntity;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    public CrabRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CrabModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(CrabEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/crab_texture.png");
    }





    @Override
    public RenderLayer getRenderType(CrabEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.7f, 0.7f, 0.7f);
        }
        else stack.scale(1.8f, 1.8f, 1.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}

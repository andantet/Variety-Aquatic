package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class HermitcrabRenderer extends GeoEntityRenderer<HermitcrabEntity> {
    public HermitcrabRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HermitcrabModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(HermitcrabEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/hermitcrab_texture.png");
    }





    @Override
    public RenderLayer getRenderType(HermitcrabEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.7f, 0.7f, 0.7f);
        }
        else stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}

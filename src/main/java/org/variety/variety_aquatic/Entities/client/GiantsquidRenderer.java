package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.GiantsquidEntity;
import org.variety.variety_aquatic.Entities.custom.WhaleSharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GiantsquidRenderer extends GeoEntityRenderer<GiantsquidEntity> {
    public GiantsquidRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GiantsquidModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(GiantsquidEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/giantsquid_texture.png");
    }


    @Override
    public RenderLayer getRenderType(GiantsquidEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.3f, 1.3f, 1.3f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}

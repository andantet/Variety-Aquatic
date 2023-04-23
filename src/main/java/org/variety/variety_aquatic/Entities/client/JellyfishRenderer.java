package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.JellyfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JellyfishRenderer extends GeoEntityRenderer<JellyfishEntity> {
    public JellyfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new JellyfishModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(JellyfishEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/jellyfish_texture.png");
    }

    @Override
    public RenderLayer getRenderType(JellyfishEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
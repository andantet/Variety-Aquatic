package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.BluefinTuna;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ClownfishRenderer extends GeoEntityRenderer<ClownfishEntity> {
    public ClownfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ClownfishModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(ClownfishEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/clownfish_texture.png");
    }


    @Override
    public RenderLayer getRenderType(ClownfishEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
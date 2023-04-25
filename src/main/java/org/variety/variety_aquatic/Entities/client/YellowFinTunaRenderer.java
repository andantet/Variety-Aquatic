package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.YellowfinTunaEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class YellowFinTunaRenderer extends GeoEntityRenderer<YellowfinTunaEntity> {
    public YellowFinTunaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new YelllowFinTunaModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(YellowfinTunaEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/bluefintuna_texture.png");
    }


    @Override
    public RenderLayer getRenderType(YellowfinTunaEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(1f, 1f, 1f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
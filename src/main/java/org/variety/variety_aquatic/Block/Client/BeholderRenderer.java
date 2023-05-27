package org.variety.variety_aquatic.Block.Client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.BeholderTileEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class BeholderRenderer extends GeoBlockRenderer<BeholderTileEntity> {
    public BeholderRenderer() {
        super(new BeholderModel());

    }

    @Override
    public RenderLayer getRenderType(BeholderTileEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
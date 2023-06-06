package org.variety.variety_aquatic.Block.Client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.LeviathanTrophyTileEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class LeviathanTrophyRenderer extends GeoBlockRenderer<LeviathanTrophyTileEntity> {
    public LeviathanTrophyRenderer() {
        super(new LeviathanTrophyModel());
    }

    @Override
    public RenderLayer getRenderType(LeviathanTrophyTileEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}
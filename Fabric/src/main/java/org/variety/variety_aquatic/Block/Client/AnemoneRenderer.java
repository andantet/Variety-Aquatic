package org.variety.variety_aquatic.Block.Client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnemoneRenderer extends GeoBlockRenderer<AnemoneTileEntity> {
    public AnemoneRenderer() {
        super(new AnemoneModel());
    }

    @Override
    public RenderLayer render(AnemoneTileEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}
package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.custom.SunfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SunfishRenderer extends GeoEntityRenderer<SunfishEntity> {
    public SunfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new SunfishModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(SunfishEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/sunfish_texture.png");
    }


    @Override
    public void preRender(MatrixStack poseStack, SunfishEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.scale(2.0F, 2.0F, 2.0F); }

}
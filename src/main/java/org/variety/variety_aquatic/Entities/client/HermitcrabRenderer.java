package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Entities.custom.SharkEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HermitcrabRenderer extends GeoEntityRenderer<HermitcrabEntity> {
    public HermitcrabRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HermitcrabModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(HermitcrabEntity instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/hermitcrab_texture.png");
    }


    @Override
    public void preRender(MatrixStack poseStack, HermitcrabEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        if (animatable.isBaby()) { poseStack.scale(0.7F, 0.7F, 0.7F); }
        else poseStack.scale(1.2F, 1.2F, 1.2F); }


}
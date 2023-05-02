package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import org.variety.varietyaquatic.entity.custom.WhaleSharkEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class WhalesharkRenderer extends GeoEntityRenderer<WhaleSharkEntity> {
    public WhalesharkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WhalesharkModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(WhaleSharkEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/whaleshark_texture.png");
    }

    @Override
    public void render(WhaleSharkEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
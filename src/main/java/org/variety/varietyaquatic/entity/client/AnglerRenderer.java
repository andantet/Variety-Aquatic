package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;


public class AnglerRenderer extends GeoEntityRenderer<AnglerfishEntity> {
    public AnglerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnglerModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(AnglerfishEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/anglerfish_texture.png");
    }

    @Override
    public void render(AnglerfishEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.3f, 1.3f, 1.3f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
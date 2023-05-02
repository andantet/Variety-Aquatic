package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.CrabEntity;
import org.variety.varietyaquatic.entity.custom.HermitcrabEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class HermitcrabRenderer extends GeoEntityRenderer<HermitcrabEntity> {
    public HermitcrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HermitcrabModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(HermitcrabEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/hermitcrab_texture.png");
    }

    @Override
    public void render(HermitcrabEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
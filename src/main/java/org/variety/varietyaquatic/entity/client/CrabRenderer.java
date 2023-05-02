package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.ClownfishEntity;
import org.variety.varietyaquatic.entity.custom.CrabEntity;
import org.variety.varietyaquatic.entity.custom.YellowFinTuna;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    public CrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CrabModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(CrabEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/crab_texture.png");
    }

    @Override
    public void render(CrabEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.6f, 1.6f, 1.6f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
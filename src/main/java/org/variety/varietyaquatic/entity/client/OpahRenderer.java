package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.Jellyfish;
import org.variety.varietyaquatic.entity.custom.MoonJelly;
import org.variety.varietyaquatic.entity.custom.OpahEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class OpahRenderer extends GeoEntityRenderer<OpahEntity> {
    public OpahRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OpahModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(OpahEntity instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/opah_texture.png");
    }

    @Override
    public void render(OpahEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
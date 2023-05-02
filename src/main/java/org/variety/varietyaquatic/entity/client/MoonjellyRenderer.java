package org.variety.varietyaquatic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.custom.LionfishEntity;
import org.variety.varietyaquatic.entity.custom.MoonJelly;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;


public class MoonjellyRenderer extends GeoEntityRenderer<MoonJelly> {
    public MoonjellyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Moonjellymodel());
        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(MoonJelly instance) {
        return new ResourceLocation(VarietyAquatic.MODID, "textures/entity/moonjelly_texture.png");
    }

    @Override
    public void render(MoonJelly entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
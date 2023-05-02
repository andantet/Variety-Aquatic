package org.variety.varietyaquatic.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.Varient.BettaVariant;
import org.variety.varietyaquatic.entity.custom.AnglerfishEntity;
import org.variety.varietyaquatic.entity.custom.BettaEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


import java.util.Map;


public class BettaRenderer extends GeoEntityRenderer<BettaEntity> {

    public static final Map<BettaVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BettaVariant.class), (p_114874_) -> {
                p_114874_.put(BettaVariant.DEFAULT,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/red_betta_texture.png"));
                p_114874_.put(BettaVariant.PINK_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/pink_betta_texture.png"));
                p_114874_.put(BettaVariant.BLUE_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/blue_betta_texture.png"));
                p_114874_.put(BettaVariant.GREEN_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/green_betta_texture.png"));
                p_114874_.put(BettaVariant.RED_BLUE_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/red_blue_betta_texture.png"));
                p_114874_.put(BettaVariant.BLUE_YELLOW_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/blue_yellow_betta_texture.png"));
                p_114874_.put(BettaVariant.WHITE_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/white_betta_texture.png"));
                p_114874_.put(BettaVariant.BLACK_BETTA,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/black_betta_texture.png"));
            });
    public BettaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BettaModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(BettaEntity object) {
        return BettaRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public void render(BettaEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.3f, 1.3f, 1.3f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
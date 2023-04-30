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
import org.variety.varietyaquatic.entity.Varient.SeahorseVariant;
import org.variety.varietyaquatic.entity.custom.BettaEntity;
import org.variety.varietyaquatic.entity.custom.SeahorseEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;


public class SeahorseRenderer extends GeoEntityRenderer<SeahorseEntity> {

    public static final Map<SeahorseVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SeahorseVariant.class), (p_114874_) -> {
                p_114874_.put(SeahorseVariant.DEFAULT,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/pink_seahorse_texture.png"));
                p_114874_.put(SeahorseVariant.ORANGE_SEAHORSE,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/orange_seahorse_texture.png"));
                p_114874_.put(SeahorseVariant.YELLOW_SEAHORSE,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/yellow_seahorse_texture.png"));
                p_114874_.put(SeahorseVariant.GREEN_SEAHORSE,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/green_seahorse_texture.png"));
                p_114874_.put(SeahorseVariant.PURPLE_SEAHORSE,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/purple_seahorse_texture.png"));
                p_114874_.put(SeahorseVariant.BLUE_SEAHORSE,
                        new ResourceLocation(VarietyAquatic.MODID, "textures/entity/blue_seahorse_texture.png"));
            });
    public SeahorseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SeahorseModel());

        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(SeahorseEntity object) {
        return SeahorseRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public RenderType getRenderType(SeahorseEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
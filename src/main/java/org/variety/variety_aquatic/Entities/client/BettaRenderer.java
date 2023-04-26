package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import com.google.common.collect.Maps;

import org.variety.variety_aquatic.Entities.Variant.BettaVariant;
import org.variety.variety_aquatic.Entities.custom.BettaEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class BettaRenderer extends GeoEntityRenderer<BettaEntity> {
    public static final Map<BettaVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BettaVariant.class), (map) -> {
                map.put(BettaVariant.DEFAULT,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/red_betta.png"));
                map.put(BettaVariant.PINK_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/pink_betta.png"));
                map.put(BettaVariant.BLUE_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/blue_betta.png"));
                map.put(BettaVariant.GREEN_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/green_betta.png"));
                map.put(BettaVariant.RED_BLUE_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/red_blue_betta.png"));
                map.put(BettaVariant.BLUE_YELLOW_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/blue_yellow_betta.png"));
                map.put(BettaVariant.WHITE_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/white_betta.png"));
                map.put(BettaVariant.BLACK_BETTA,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/black_betta.png"));
            });


    public BettaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BettaModel());
    }

    @Override
    public Identifier getTextureResource(BettaEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }
    @Override
    public RenderLayer getRenderType(BettaEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {


        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}
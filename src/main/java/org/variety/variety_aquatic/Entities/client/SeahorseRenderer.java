package org.variety.variety_aquatic.Entities.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.variety.variety_aquatic.Entities.Variant.SeahorseVariant;
import org.variety.variety_aquatic.Entities.custom.SeahorseEntity;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class SeahorseRenderer extends GeoEntityRenderer<SeahorseEntity> {
    public static final Map<SeahorseVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SeahorseVariant.class), (map) -> {
                map.put(SeahorseVariant.DEFAULT,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/blue_seahorse_texture.png"));
                map.put(SeahorseVariant.GREEN_SEAHORSE,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/green_seahorse_texture.png"));
                map.put(SeahorseVariant.ORANGE_SEAHORSE,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/orange_seahorse_texture.png"));
                map.put(SeahorseVariant.PINK_SEAHORSE,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/pink_seahorse_texture.png"));
                map.put(SeahorseVariant.PURPLE_SEAHORSE,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/purple_seahorse_texture.png"));
                map.put(SeahorseVariant.YELLOW_SEAHORSE,
                        new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/yellow_seahorse_texture.png"));
            });


    public SeahorseRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new SeahorseModel());
    }

    @Override
    public Identifier getTextureResource(SeahorseEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }
    @Override
    public RenderLayer getRenderType(SeahorseEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {


        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}
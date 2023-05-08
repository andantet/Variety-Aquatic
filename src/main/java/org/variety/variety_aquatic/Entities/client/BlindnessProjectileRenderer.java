package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.ProjectileEntity.BlindnessProjectile;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;
public class BlindnessProjectileRenderer extends GeoProjectilesRenderer<BlindnessProjectile> {

    // CONSTRUCTOR //
    public BlindnessProjectileRenderer(Context ctx) {
        super(ctx, new BlindnessProjectileModel());
    }
    @Override
    public Identifier getTexture(BlindnessProjectile instance) {
        return new Identifier(Variety_Aquatic.MOD_ID, "textures/entity/blindnessprojectile.png");
    }


    public RenderLayer getRenderType(BlindnessProjectile animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
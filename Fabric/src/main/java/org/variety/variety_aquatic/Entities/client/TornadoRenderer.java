package org.variety.variety_aquatic.Entities.client;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.TornadoEntity;

public class TornadoRenderer extends EntityRenderer<TornadoEntity> {
    public TornadoRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(TornadoEntity entity) {
        return null; // Return null as we don't need a texture for the entity
    }

    @Override
    public boolean shouldRender(TornadoEntity entity, Frustum frustum, double x, double y, double z) {
        return super.shouldRender(entity, frustum, x, y, z);
    }
}
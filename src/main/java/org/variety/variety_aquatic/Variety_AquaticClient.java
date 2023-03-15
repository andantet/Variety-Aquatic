package org.variety.variety_aquatic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.HermitcrabRenderer;
import org.variety.variety_aquatic.Entities.client.SharkRenderer;
import org.variety.variety_aquatic.Entities.client.SunfishRenderer;

public class Variety_AquaticClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SHARK, SharkRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUNFISH, SunfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.HERMITCRAB, HermitcrabRenderer::new);

    }
}

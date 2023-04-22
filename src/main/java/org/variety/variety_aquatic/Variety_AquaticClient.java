package org.variety.variety_aquatic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.*;
import org.variety.variety_aquatic.Entities.custom.CuttlefishEntity;

public class Variety_AquaticClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SHARK, SharkRenderer::new);
        EntityRendererRegistry.register(ModEntities.WHALESHARK, WhaleSharkRenderer::new);

        EntityRendererRegistry.register(ModEntities.SUNFISH, SunfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.HERMITCRAB, HermitcrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.JELLYFISH, JellyfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOONJELLY, MoonJellyRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLUEFIN, BlueFinTunaRenderer::new);
        EntityRendererRegistry.register(ModEntities.CUTTLEFISH, CuttlefishRenderer::new);



    }
}

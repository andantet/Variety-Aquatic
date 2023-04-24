package org.variety.variety_aquatic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.variety.variety_aquatic.Block.Client.AnemoneRenderer;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Block.Tile.ModTileRegistry;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.*;
import org.variety.variety_aquatic.Entities.custom.CuttlefishEntity;
import software.bernie.example.client.renderer.tile.HabitatTileRenderer;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.example.registry.TileRegistry;

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
        EntityRendererRegistry.register(ModEntities.OPAH, OpahRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIONFISH, LionfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLOWNFISH, ClownfishRenderer::new);

        BlockEntityRendererRegistry.register(ModTileRegistry.ANEMONE_TILE,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new AnemoneRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANEMONE_BLOCK, RenderLayer.getTranslucent());


    }
}

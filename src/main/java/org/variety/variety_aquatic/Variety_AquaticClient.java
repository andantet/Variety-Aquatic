package org.variety.variety_aquatic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Client.AnemoneRenderer;
import org.variety.variety_aquatic.Block.Client.BeholderRenderer;
import org.variety.variety_aquatic.Block.Client.LeviathanTrophyRenderer;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.*;
import org.variety.variety_aquatic.Fluid.ModFluid;

public class Variety_AquaticClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SHARK, SharkRenderer::new);
        EntityRendererRegistry.register(ModEntities.WHALESHARK, WhaleSharkRenderer::new);
        EntityRendererRegistry.register(ModEntities.TORNADO,TornadoRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUNFISH, SunfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.SQUIDLING, SquidlingRenderer::new);

        EntityRendererRegistry.register(ModEntities.HERMITCRAB, HermitcrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.JELLYFISH, JellyfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOONJELLY, MoonJellyRenderer::new);
        EntityRendererRegistry.register(ModEntities.YELLOWFIN, YellowFinTunaRenderer::new);
        EntityRendererRegistry.register(ModEntities.PIRANHA, PiranhaRenderer::new);
        EntityRendererRegistry.register(ModEntities.VAMPIRESQUID, VampireSquidRenderer::new);
        EntityRendererRegistry.register(ModEntities.OARFISH, OarfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.BARRELEE, BarreleeRenderer::new);
        EntityRendererRegistry.register(ModEntities.FLASHLIGHTFISH, FlashlightfishRenderer::new);

        EntityRendererRegistry.register(ModEntities.CUTTLEFISH, CuttlefishRenderer::new);
        EntityRendererRegistry.register(ModEntities.OPAH, OpahRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIONFISH, LionfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.GIANTGLOWINGSQUID, GiantsquidRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPOTTEDSTINGRAY, SpottedStingrayRenderer::new);
        EntityRendererRegistry.register(ModEntities.LEVIATHAN, SpermwhaleRenderer::new);
        EntityRendererRegistry.register(ModEntities.TETRA, TetraRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLOWNFISH, ClownfishRenderer::new);
        EntityRendererRegistry.register(ModEntities.BETTA, BettaRenderer::new);
        EntityRendererRegistry.register(ModEntities.ANGLERFISH, AnglerFishRenderer::new);
        EntityRendererRegistry.register(ModEntities.SEAHORSE, SeahorseRenderer::new);
        EntityRendererRegistry.register(ModEntities.CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.SEAANGLE, SeaangleRenderer::new);


        FluidRenderHandlerRegistry.INSTANCE.register(ModFluid.STILL_GLOWING_WATER, ModFluid.FLOWING_GLOWING_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x00ccff
                ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluid.STILL_GLOWING_WATER, ModFluid.FLOWING_GLOWING_WATER);

        BlockEntityRendererRegistry.register(ModTileEntity.ANEMONE,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new AnemoneRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANEMONE_BLOCK, RenderLayer.getTranslucent());
        BlockEntityRendererRegistry.register(ModTileEntity.LEVIATHAN,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new LeviathanTrophyRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.LEVIATHAN_TROPHY_BLOCK, RenderLayer.getTranslucent());

        BlockEntityRendererRegistry.register(ModTileEntity.BEHOLDER,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BeholderRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.BEHOLDER_BLOCK, RenderLayer.getTranslucent());

        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANGLER_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.WALL_ANGLER_TORCH, RenderLayer.getCutout());



    }
}

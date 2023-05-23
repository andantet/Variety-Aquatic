package org.variety.variety_aquatic.Client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.lwjgl.opengl.GL11;
import org.variety.variety_aquatic.Block.Client.AnemoneRenderer;
import org.variety.variety_aquatic.Block.Client.BeholderRenderer;
import org.variety.variety_aquatic.Block.Client.GiantGlowingSquidTrophyRenderer;
import org.variety.variety_aquatic.Block.Client.LeviathanTrophyRenderer;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.*;
import org.variety.variety_aquatic.Entities.custom.TetraEntity;
import org.variety.variety_aquatic.Fluid.ModFluid;
import org.variety.variety_aquatic.Variety_Aquatic;

import static net.minecraft.client.render.BackgroundRenderer.applyFog;

public class Variety_AquaticClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SharkModel(), "textures/entity/shark_texture.png", 2.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.WHALESHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new WhaleSharkModel(), "textures/entity/whaleshark_texture.png", 1.3f,1.2f, false,false)
        );


        EntityRendererRegistry.register(ModEntities.TORNADO,TornadoRenderer::new); //TODO SWITCH

        EntityRendererRegistry.register(ModEntities.SUNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SunfishModel(), "textures/entity/sunfish_texture.png", 2.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.SQUIDLING, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SunfishModel(), "textures/entity/squidling_texture.png", 1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.HERMITCRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new HermitcrabModel(), "textures/entity/hermitcrab_texture.png", 1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.JELLYFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new JellyfishModel(), "textures/entity/jellyfish_texture.png", 1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.MOONJELLY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new MoonJellyModel(), "textures/entity/moonjelly_texture.png", 1.0f,1.2f, true,true)
        );
        EntityRendererRegistry.register(ModEntities.YELLOWFIN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new YelllowFinTunaModel(), "textures/entity/yellowfintuna_texture.png", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.PIRANHA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new PiranhaModel(), "textures/entity/piranha_texture.png", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.VAMPIRESQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new VampireSquidModel(), "textures/entity/vampiresquid_texture.png", 1.0f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.OARFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new OarfishModel(), "textures/entity/oarfish_texture.png", 1.0f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.BARRELEE, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new BarreleModel(), "textures/entity/barreleye_texture.png", 1.0f,1.2f,true,true)
        );
        EntityRendererRegistry.register(ModEntities.FLASHLIGHTFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new FlashlightfishModel(), "textures/entity/flashlightfish_texture.png", 1.0f,1.2f,false,true)
        );
        EntityRendererRegistry.register(ModEntities.CUTTLEFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new CuttlefishModel(), "textures/entity/cuttlefish_texture.png", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.OPAH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new OpahModel(), "textures/entity/opah_texture.png", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.LIONFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new LionfishModel(), "textures/entity/lionfish_texture.png", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.GIANTGLOWINGSQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GiantsquidModel(), "textures/entity/giantsquid_texture.png", 1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.SPOTTEDSTINGRAY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SpottedStingrayModel(), "textures/entity/spottedstingray_texture.png", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.LEVIATHAN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SpermwhaleModel(), "textures/entity/spermwhale_texture.png", 1.1f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.TETRA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new TetraModel(), "textures/entity/tetra_texture.png", 1.2f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.CLOWNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new ClownfishModel(), "textures/entity/clownfish_texture.png", 1.2f,1.2f, false,false)
        );


        EntityRendererRegistry.register(ModEntities.BETTA, BettaRenderer::new); //TODO LATER
        EntityRendererRegistry.register(ModEntities.SEAHORSE, SeahorseRenderer::new); //TODO LATER


        EntityRendererRegistry.register(ModEntities.ANGLERFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new AnglerFishModel(), "textures/entity/anglerfish_texture.png", 1.2f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.CRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new CrabModel(), "textures/entity/crab_texture.png", 1.6f,0.7f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.SEAANGLE, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new SeaAngleModel(), "textures/entity/seaangle_texture.png", 1.0f,1.0f, false,true)
        );



        EntityRendererRegistry.register(ModEntities.BLINDNESS_PROJECTILE_ENTITY_TYPE, BlindnessProjectileRenderer::new); //TODO LATER

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluid.STILL_GLOWING_WATER, ModFluid.FLOWING_GLOWING_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x00ccff
                ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluid.STILL_GLOWING_WATER, ModFluid.FLOWING_GLOWING_WATER);

        HandledScreens.register(Variety_Aquatic.BOX_SCREEN_HANDLER, PositionedScreen::new);


        BlockEntityRendererRegistry.register(ModTileEntity.ANEMONE,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new AnemoneRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANEMONE_BLOCK, RenderLayer.getTranslucent());
        BlockEntityRendererRegistry.register(ModTileEntity.LEVIATHAN,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new LeviathanTrophyRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.LEVIATHAN_TROPHY_BLOCK, RenderLayer.getTranslucent());

        BlockEntityRendererRegistry.register(ModTileEntity.GIANTSQUID,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new GiantGlowingSquidTrophyRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.GIANTSQUID_TROPHY_BLOCK, RenderLayer.getTranslucent());


        BlockEntityRendererRegistry.register(ModTileEntity.BEHOLDER,
                (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BeholderRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.BEHOLDER_BLOCK, RenderLayer.getTranslucent());

        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANGLER_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.WALL_ANGLER_TORCH, RenderLayer.getCutout());



    }
}

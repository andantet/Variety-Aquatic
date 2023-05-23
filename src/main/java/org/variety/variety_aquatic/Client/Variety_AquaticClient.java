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

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluid.STILL_GLOWING_WATER, ModFluid.FLOWING_GLOWING_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x00ccff
                ));

        EntityRendererRegistry.register(ModEntities.SHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("shark.geo","shark_texture","shark.animation"), "shark_texture",2.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.WHALESHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("whaleshark.geo","whaleshark_texture","whaleshark.animation"), "whaleshark_texture", 1.3f,1.2f, false,false)
        );


        EntityRendererRegistry.register(ModEntities.TORNADO,TornadoRenderer::new); //TODO SWITCH

        EntityRendererRegistry.register(ModEntities.SUNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("sunfish.geo","sunfish_texture","sunfish.animation"), "sunfish_texture", 2.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.SQUIDLING, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("squidling.geo","squidling_texture","squidling.animation"), "squidling_texture", 1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.HERMITCRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("hermitcrab.geo","hermitcrab_texture","hermitcrab.animation"), "hermitcrab_texture", 1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.JELLYFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("jellyfish.geo","jellyfish_texture","jellyfish.animation"), "jellyfish_texture", 1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.MOONJELLY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("moonjelly.geo","moonjelly_texture","moonjelly.animation"), "moonjelly_texture", 1.0f,1.2f, true,true)
        );
        EntityRendererRegistry.register(ModEntities.YELLOWFIN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("yellowfintuna.geo","yellowfintuna_texture","yellowfintuna.animation"), "yellowfintuna_texture", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.PIRANHA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("piranha.geo","piranha_texture","piranha.animation"), "piranha_texture", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.VAMPIRESQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("vampiresquid.geo","vampiresquid_texture","vampiresquid.animation"), "vampiresquid_texture", 1.0f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.OARFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("oarfish.geo","oarfish_texture","oarfish.animation"), "oarfish_texture", 1.0f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.BARRELEE, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("barreleye.geo","barreleye_texture","barreleye.animation"), "barreleye_texture", 1.0f,1.2f,true,true)
        );
        EntityRendererRegistry.register(ModEntities.FLASHLIGHTFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("flashlightfish.geo","flashlightfish_texture","flashlightfish.animation"), "flashlightfish_texture", 1.0f,1.2f,false,true)
        );
        EntityRendererRegistry.register(ModEntities.CUTTLEFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("cuttlefish.geo","cuttlefish_texture","cuttlefish.animation"), "cuttlefish_texture", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.OPAH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("opah.geo","opah_texture","opah.animation"), "opah_texture", 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.LIONFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("lionfish.geo","lionfish_texture","lionfish.animation"), "lionfish_texture", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.GIANTGLOWINGSQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("giantsquid.geo","giantsquid_texture","giantsquid.animation"), "giantsquid_texture", 1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.SPOTTEDSTINGRAY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("spottedstingray.geo","spottedstingray_texture","spottedstingray.animation"), "spottedstingray_texture", 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.LEVIATHAN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("spermwhale.geo","spermwhale_texture","spermwhale.animation"), "spermwhale_texture", 1.1f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.TETRA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("tetra.geo","tetra_texture","tetra.animation"), "tetra_texture", 1.2f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.CLOWNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("clownfish.geo","clownfish_texture","clownfish.animation"), "clownfish_texture", 1.2f,1.2f, false,false)
        );


        EntityRendererRegistry.register(ModEntities.BETTA, BettaRenderer::new); //TODO LATER
        EntityRendererRegistry.register(ModEntities.SEAHORSE, SeahorseRenderer::new); //TODO LATER


        EntityRendererRegistry.register(ModEntities.ANGLERFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("anglerfish.geo","anglerfish_texture","anglerfish.animation"), "anglerfish_texture", 1.2f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.CRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("crab.geo","crab_texture","crab.animation"), "crab_texture", 1.6f,0.7f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.SEAANGLE, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel("seaangle.geo","seaangle_texture","seaangle.animation"), "seaangle_texture", 1.0f,1.0f, false,true)
        );



        EntityRendererRegistry.register(ModEntities.BLINDNESS_PROJECTILE_ENTITY_TYPE, BlindnessProjectileRenderer::new); //TODO LATER


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

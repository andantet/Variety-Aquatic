package org.variety.variety_aquatic.Client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.variety.variety_aquatic.Block.Client.AnemoneRenderer;
import org.variety.variety_aquatic.Block.Client.BeholderRenderer;
import org.variety.variety_aquatic.Block.Client.GiantGlowingSquidTrophyRenderer;
import org.variety.variety_aquatic.Block.Client.LeviathanTrophyRenderer;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.client.TornadoRenderer;
import org.variety.variety_aquatic.Items.Custom.TrophyItemRenderer;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Variety_Aquatic;
import org.varietymods.varietyapi.API.*;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Variety_AquaticClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SEAHORSE,
                (EntityRendererFactory.Context context) -> new GenericVariantRenderer<>(context,new GenericVariantModel<>(Variety_Aquatic.MOD_ID,"seahorse.geo", "seahorse.animation"),1.0f,1.0f,false,false));

        EntityRendererRegistry.register(ModEntities.BETTA,
                (EntityRendererFactory.Context context) -> new GenericVariantRenderer<>(context, new GenericVariantModel<>(Variety_Aquatic.MOD_ID,"betta.geo", "betta.animation"),1.0f,1.0f,false,false));

        EntityRendererRegistry.register(ModEntities.SHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"shark.geo","shark_texture","shark.animation",false),2.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.WHALESHARK, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"whaleshark.geo","whaleshark_texture","whaleshark.animation",false), 1.3f,1.2f, false,false)
        );


        EntityRendererRegistry.register(ModEntities.TORNADO,TornadoRenderer::new); //TODO SWITCH

        EntityRendererRegistry.register(ModEntities.SUNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"sunfish.geo","sunfish_texture","sunfish.animation",false), 2.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.SQUIDLING, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"squidling.geo","squidling_texture","squidling.animation",false),1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.HERMITCRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"hermitcrab.geo","hermitcrab_texture","hermitcrab.animation",false), 1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.JELLYFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"jellyfish.geo","jellyfish_texture","jellyfish.animation",false),  1.2f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.MOONJELLY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"moonjelly.geo","moonjelly_texture","moonjelly.animation",false), 1.0f,1.2f, true,true)
        );
        EntityRendererRegistry.register(ModEntities.YELLOWFIN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"yellowfintuna.geo","yellowfintuna_texture","yellowfintuna.animation" ,false), 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.PIRANHA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"piranha.geo","piranha_texture","piranha.animation",false),1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.VAMPIRESQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"vampiresquid.geo","vampiresquid_texture","vampiresquid.animation",false),1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.OARFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"oarfish.geo","oarfish_texture","oarfish.animation",false), 1.0f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.BARRELEE, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"barreleye.geo","barreleye_texture","barreleye.animation",false),1.0f,1.2f,true,true)
        );
        EntityRendererRegistry.register(ModEntities.FLASHLIGHTFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"flashlightfish.geo","flashlightfish_texture","flashlightfish.animation",false), 1.0f,1.2f,false,true)
        );
        EntityRendererRegistry.register(ModEntities.CUTTLEFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"cuttlefish.geo","cuttlefish_texture","cuttlefish.animation",false), 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.OPAH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"opah.geo","opah_texture","opah.animation",false), 1.0f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.LIONFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"lionfish.geo","lionfish_texture","lionfish.animation",false), 1.0f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.GIANTGLOWINGSQUID, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"giantsquid.geo","giantsquid_texture","giantsquid.animation",false), 1.0f,1.2f, false,true)
        );

        EntityRendererRegistry.register(ModEntities.SPOTTEDSTINGRAY, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"spottedstingray.geo","spottedstingray_texture","spottedstingray.animation",false), 1.1f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.LEVIATHAN, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"spermwhale.geo","spermwhale_texture","spermwhale.animation",false), 1.1f,1.2f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.TETRA, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"tetra.geo","tetra_texture","tetra.animation",false), 1.2f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.CLOWNFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"clownfish.geo","clownfish_texture","clownfish.animation",false), 1.2f,1.2f, false,false)
        );

        EntityRendererRegistry.register(ModEntities.BLINDNESS_PROJECTILE_ENTITY_TYPE, (EntityRendererFactory.Context ctx) ->
                new GenericProjectileRenderer<>(ctx, new GenericProjectileModel(Variety_Aquatic.MOD_ID,"blindnessprojectile.geo","blindnessprojectile","null"),Variety_Aquatic.MOD_ID,"blindnessprojectile", false,false)
        );




        EntityRendererRegistry.register(ModEntities.ANGLERFISH, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"anglerfish.geo","anglerfish_texture","anglerfish.animation",false), 1.2f,1.2f, false,true)
        );
        EntityRendererRegistry.register(ModEntities.CRAB, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"crab.geo","crab_texture","crab.animation",false), 1.6f,0.7f, false,false)
        );
        EntityRendererRegistry.register(ModEntities.SEAANGEL, (EntityRendererFactory.Context ctx) ->
                new GenericRenderer<>(ctx, new GenericModel(Variety_Aquatic.MOD_ID,"seaangle.geo","seaangle_texture","seaangle.animation",false),1.0f, 1.0f,true, true)
        );




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
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.BEHOLDER, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.ANGLER_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlock.WALL_ANGLER_TORCH, RenderLayer.getCutout());

        GeoItemRenderer.registerItemRenderer(ModItems.GIANTSQUID_TROPHY_BLOCK_ITEM, new TrophyItemRenderer("glowingsquid"));
        GeoItemRenderer.registerItemRenderer(ModItems.LEVIATHAN_TROPHY_BLOCK_ITEM, new TrophyItemRenderer("leviathan"));

    }
}

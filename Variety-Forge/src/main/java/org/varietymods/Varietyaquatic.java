package org.varietymods;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;


import org.varietymods.Entity.ModEntityTypes;
import org.varietymods.varietyapi.API.GenericModel;
import org.varietymods.varietyapi.API.GenericRenderer;
import software.bernie.geckolib3.GeckoLib;

@Mod(Varietyaquatic.MODID)
public class Varietyaquatic {
    public static final String MODID = "varietyaquatic";
    // Very Important Comment
    public Varietyaquatic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        GeckoLib.initialize();
        ModEntityTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.YELLOWFIN.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });


    }



    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.YELLOWFIN.get(), (EntityRendererProvider.Context ctx) ->
                    new GenericRenderer<>(ctx, new GenericModel(Varietyaquatic.MODID,"yellowfintuna.geo","yellowfintuna_texture","yellowfintuna.animation"),Varietyaquatic.MODID, "yellowfintuna_texture", 1.0f,1.2f, false,false)
            );
        }
    }
}
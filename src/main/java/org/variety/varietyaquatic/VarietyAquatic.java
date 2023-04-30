package org.variety.varietyaquatic;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.variety.varietyaquatic.Block.ModBlock;
import org.variety.varietyaquatic.ModSound.ModSound;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import org.variety.varietyaquatic.entity.client.*;
import org.variety.varietyaquatic.entity.custom.*;
import org.variety.varietyaquatic.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;


import software.bernie.geckolib3.GeckoLib;

import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(VarietyAquatic.MODID)
public class VarietyAquatic {
    public static final String MODID = "varietyaquaticforge";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Very Important Comment
    public VarietyAquatic() {
            IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlock.register(modEventBus);
        ModSound.register(modEventBus);


        ModEntityTypes.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.SUNFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.SHARK.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.ANGLERFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    AnglerfishEntity::checkAnglerfishSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.BETTAFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    BettaEntity::checkBettaSpawn);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.CLOWNFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.CRAB.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    CrabEntity::checkcrabspawn);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.CUTTLEFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.YELLOWFIN.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.TETRA.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    TetraEntity::checkTetraSpawn);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.HERMITCRAB.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);
        });

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.MOONJELLY.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.JELLYFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.OPAH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.PIRANHA.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Piranha::checkPiranhaspawn);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.SEAHORSE.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.WHALESHARK.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });


    }



            @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntityTypes.SUNFISH.get(), SunFishRenderer::new);
            EntityRenderers.register(ModEntityTypes.SHARK.get(), SharkRenderer::new);
            EntityRenderers.register(ModEntityTypes.ANGLERFISH.get(), AnglerRenderer::new);
            EntityRenderers.register(ModEntityTypes.BETTAFISH.get(), BettaRenderer::new);
            EntityRenderers.register(ModEntityTypes.CLOWNFISH.get(), ClownfishRenderer::new);
            EntityRenderers.register(ModEntityTypes.CUTTLEFISH.get(), CuttlefishRenderer::new);
            EntityRenderers.register(ModEntityTypes.YELLOWFIN.get(), TunaRenderer::new);
            EntityRenderers.register(ModEntityTypes.TETRA.get(), TetraRenderer::new);
            EntityRenderers.register(ModEntityTypes.HERMITCRAB.get(), HermitcrabRenderer::new);
            EntityRenderers.register(ModEntityTypes.CRAB.get(), CrabRenderer::new);
            EntityRenderers.register(ModEntityTypes.LIONFISH.get(), LionfishRenderer::new);
            EntityRenderers.register(ModEntityTypes.JELLYFISH.get(), JellyfishRenderer::new);
            EntityRenderers.register(ModEntityTypes.MOONJELLY.get(), MoonjellyRenderer::new);
            EntityRenderers.register(ModEntityTypes.OPAH.get(), OpahRenderer::new);
            EntityRenderers.register(ModEntityTypes.PIRANHA.get(), PiranhaRenderer::new);
            EntityRenderers.register(ModEntityTypes.SEAHORSE.get(), SeahorseRenderer::new);
            EntityRenderers.register(ModEntityTypes.STINGRAY.get(), StingrayRenderer::new);
            EntityRenderers.register(ModEntityTypes.WHALESHARK.get(), WhalesharkRenderer::new);






        }
    }
}
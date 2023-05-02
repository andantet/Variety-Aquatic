package org.variety.varietyaquatic;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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
import org.variety.varietyaquatic.item.ModCreative;
import org.variety.varietyaquatic.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;



import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;


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
        modEventBus.addListener(this::addCreative);

    }
    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreative.VarietyAquaticTab) {
            event.accept(ModItems.GLOWTORCH_ITEM);
            event.accept(ModItems.SHARK_EGG);
            event.accept(ModItems.ANGLER_BULB);
            event.accept(ModItems.ANGLER_EGG);
            event.accept(ModItems.BETTA_EGG);
            event.accept(ModItems.CLOWNFISH_EGG);
            event.accept(ModItems.COOKED_TUNA);
            event.accept(ModItems.CRAB_EGG);
            event.accept(ModItems.CRAB_RAVE_MUSIC_DISC);
            event.accept(ModItems.HERMITCRAB_EGG);
            event.accept(ModItems.JELLYFISH_EGG);
            event.accept(ModItems.LIONFISH_COOKED);
            event.accept(ModItems.LIONFISH_EGG);
            event.accept(ModItems.MOONJELLY_EGG);
            event.accept(ModItems.OPAH_EGG);
            event.accept(ModItems.PIRANHA_EGG);
            event.accept(ModItems.RAW_BETTA);
            event.accept(ModItems.RAW_LIONFISH);
            event.accept(ModItems.RAW_PIRANHA);
            event.accept(ModItems.RAW_TETRA);
            event.accept(ModItems.RAW_TUNA);
            event.accept(ModItems.SEAHORSE_EGG);
            event.accept(ModItems.STINGRAY_EGG);
            event.accept(ModItems.SUNFISH_EGG);
            event.accept(ModItems.TETRA_EGG);
            event.accept(ModItems.WHALESHARK_EGG);







        }

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
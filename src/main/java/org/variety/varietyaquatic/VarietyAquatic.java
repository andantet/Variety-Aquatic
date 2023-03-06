package org.variety.varietyaquatic;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import org.variety.varietyaquatic.entity.client.SharkRenderer;
import org.variety.varietyaquatic.entity.client.SunFishRenderer;
import org.variety.varietyaquatic.item.ModItems;

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


        ModEntityTypes.register(modEventBus);

        GeckoLib.initialize();
        modEventBus.addListener(this::addCreative);

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
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {

        if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.SUNFISH_SPAWN_EGG);
            event.accept(ModItems.SHARK_SPAWN_EGG);

        }
    }

            @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntityTypes.SUNFISH.get(), SunFishRenderer::new);
            EntityRenderers.register(ModEntityTypes.SHARK.get(), SharkRenderer::new);

        }
    }
}
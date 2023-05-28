package org.varietymods;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.varietymods.Entity.ModEntityTypes;
import software.bernie.geckolib3.GeckoLib;

@Mod(VarietyAquatic.MODID)
public class VarietyAquatic {
    public static final String MODID = "varietyaquaticforge";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Very Important Comment
    public VarietyAquatic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

      //  ModItems.register(modEventBus);
        //ModBlock.register(modEventBus);
        //ModSound.register(modEventBus);


        ModEntityTypes.register(modEventBus);

        GeckoLib.initialize();

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
            EntityRenderers.register(ModEntityTypes.YELLOWFIN.get(), TunaRenderer::new);
        }
    }
}
package org.variety.variety_aquatic;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Screen.BoxScreenHandler;
import org.variety.variety_aquatic.Sound.ModSound;
import org.variety.variety_aquatic.Util.NewConfig;
import org.variety.variety_aquatic.world.ModWorldGen;
import software.bernie.geckolib3.GeckoLib;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Variety_Aquatic implements ModInitializer {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(BoxScreenHandler::new);
    @Override
    public void onInitialize() {
        GeckoLib.initialize();

        MidnightConfig.init(MOD_ID, NewConfig.class);

        ModItems.registerModItems();
        ModSound.initializeSounds();
        ModBlock.registerModBlocks();
        ModTileEntity.registerBlockEntities();
        ModWorldGen.generateWorldGen();



        FabricDefaultAttributeRegistry.register(ModEntities.SEAANGLE, SeaangleEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SHARK, SharkEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.WHALESHARK, WhaleSharkEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.VAMPIRESQUID, VampireSquidEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.OARFISH, OarfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SQUIDLING, squidlingEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SUNFISH, SunfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HERMITCRAB, HermitcrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.JELLYFISH, JellyfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.MOONJELLY, MoonJellyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.YELLOWFIN, YellowfinTunaEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CUTTLEFISH, CuttlefishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.OPAH, OpahEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LIONFISH, LionfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CLOWNFISH, ClownfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SPOTTEDSTINGRAY, SpottedStingray.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.GIANTGLOWINGSQUID, GiantsquidEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PIRANHA, PiranhaEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LEVIATHAN, LeviathanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.TETRA, TetraEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BETTA, BettaEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ANGLERFISH, AnglerFishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SEAHORSE, SeahorseEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRAB, CrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BARRELEE, BarreleyeEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FLASHLIGHTFISH, FlashlightfishEntity.setAttributes());
        Registry.register(Registry.SCREEN_HANDLER, id("box"), BOX_SCREEN_HANDLER);


    }

}

package org.variety.variety_aquatic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Sound.ModSound;
import org.variety.variety_aquatic.world.ModWorldGen;
import software.bernie.geckolib3.GeckoLib;

public class Variety_Aquatic implements ModInitializer {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        ModItems.registerModItems();
        ModSound.initializeSounds();
        ModBlock.registerModBlocks();
        ModTileEntity.registerBlockEntities();
        ModWorldGen.generateWorldGen();

        FabricDefaultAttributeRegistry.register(ModEntities.SHARK, SharkEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.WHALESHARK, WhaleSharkEntity.setAttributes());
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
    }
}

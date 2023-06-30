package org.variety.variety_aquatic;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Common.Block.ModBlock;
import org.variety.variety_aquatic.Common.Block.ModTileEntity;
import org.variety.variety_aquatic.Common.Entities.ModEntities;
import org.variety.variety_aquatic.Common.Entities.custom.*;
import org.variety.variety_aquatic.Common.Items.ModItemGroup;
import org.variety.variety_aquatic.Common.Items.ModItems;
import org.variety.variety_aquatic.Util.NewConfig;
import org.variety.variety_aquatic.Common.World.ModWorldGen;
import software.bernie.geckolib.GeckoLib;

public class Variety_Aquatic implements ModInitializer {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        ModItemGroup.registerItemGroups();
        GeckoLib.initialize();
        MidnightConfig.init(MOD_ID, NewConfig.class);
        ModBlock.registerModBlocks();
        ModItems.registerModItems();
        ModTileEntity.registerBlockEntities();
        ModWorldGen.generateWorldGen();
        ModEntities.registerModEntities();

    }

}

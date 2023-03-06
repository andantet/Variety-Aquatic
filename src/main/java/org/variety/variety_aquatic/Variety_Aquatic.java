package org.variety.variety_aquatic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.SharkEntity;
import org.variety.variety_aquatic.Entities.custom.SunfishEntity;
import org.variety.variety_aquatic.world.ModWorldGen;
import software.bernie.geckolib.GeckoLib;

import java.util.Arrays;

public class Variety_Aquatic implements ModInitializer {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        ModWorldGen.generateWorldGen();

        FabricDefaultAttributeRegistry.register(ModEntities.SHARK, SharkEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SUNFISH, SunfishEntity.setAttributes());

    }
}

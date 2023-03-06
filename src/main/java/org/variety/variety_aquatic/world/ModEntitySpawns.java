package org.variety.variety_aquatic.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.SharkEntity;


public class ModEntitySpawns {


    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SHARK, 60, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SUNFISH, 60, 1, 2);

        SpawnRestriction.register(
                ModEntities.SHARK, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.SUNFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

    }
}
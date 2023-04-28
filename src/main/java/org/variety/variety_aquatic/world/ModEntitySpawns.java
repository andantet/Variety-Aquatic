package org.variety.variety_aquatic.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Util.NewConfig;


public class ModEntitySpawns {




    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SHARK, NewConfig.shark_spawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.CLOWNFISH, NewConfig.clownfish_spawnweight, 1, 2);
        SpawnRestriction.register(
                ModEntities.LEVIATHAN, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.WHALESHARK, NewConfig.whaleshark_spawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SUNFISH, NewConfig.sunfish_spawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.OPAH, NewConfig.opah_spawnweight, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.YELLOWFIN, NewConfig.yellowfin_spawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.CUTTLEFISH, NewConfig.cuttlefish_spawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.LIONFISH, NewConfig.lionfish_spawnweight, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SPOTTEDSTINGRAY, NewConfig.spottedstingray_spawnweight, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.JELLYFISH, NewConfig.jellyfish_spawnweight, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.MOONJELLY, NewConfig.moonjelly_spawnweight, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.LEVIATHAN, NewConfig.spermwhale_spawnweight, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE),SpawnGroup.WATER_CREATURE,
                ModEntities.PIRANHA, NewConfig.piranha_spawnweight, 3, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE,BiomeKeys.MANGROVE_SWAMP),SpawnGroup.WATER_CREATURE,
                ModEntities.BETTA, NewConfig.betta_spawnweight, 3, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE,BiomeKeys.MANGROVE_SWAMP),SpawnGroup.WATER_CREATURE,
                ModEntities.TETRA, NewConfig.tetra_spawnweight, 3, 5);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH), SpawnGroup.CREATURE,
                ModEntities.HERMITCRAB, NewConfig.hermitcrab_spawnweight, 1, 2);

        SpawnRestriction.register(
                ModEntities.SHARK, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.PIRANHA, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.TETRA, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.WHALESHARK, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.SUNFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.YELLOWFIN, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.CUTTLEFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.JELLYFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.MOONJELLY, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.SPOTTEDSTINGRAY, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.HERMITCRAB, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HermitcrabEntity::canMobSpawn);

    }
}
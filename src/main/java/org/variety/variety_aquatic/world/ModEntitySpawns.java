package org.variety.variety_aquatic.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Util.AqConfig;


public class ModEntitySpawns {
    private static int sharkspawnweight = AqConfig.INSTANCE.getNumberProperty("shark.spawnweight");
    private static int sunfishspawnweight = AqConfig.INSTANCE.getNumberProperty("sunfish.spawnweight");
    private static int jellyfishspawnweight = AqConfig.INSTANCE.getNumberProperty("jellyfish.spawnweight");
    private static int herbitcrabspawnweight = AqConfig.INSTANCE.getNumberProperty("hermitcrab.spawnweight");



    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SHARK, sharkspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SUNFISH, sunfishspawnweight, 1, 2);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.JELLYFISH, jellyfishspawnweight, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH), SpawnGroup.CREATURE,
                ModEntities.HERMITCRAB, herbitcrabspawnweight, 1, 2);

        SpawnRestriction.register(
                ModEntities.SHARK, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.SUNFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.JELLYFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        SpawnRestriction.register(
                ModEntities.HERMITCRAB, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HermitcrabEntity::canMobSpawn);

    }
}
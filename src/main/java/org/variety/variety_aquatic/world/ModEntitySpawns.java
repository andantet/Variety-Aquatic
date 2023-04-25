package org.variety.variety_aquatic.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
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
    private static int whalesharkspawnweight = AqConfig.INSTANCE.getNumberProperty("whaleshark.spawnweight");
    private static int moonjellyspawnweight = AqConfig.INSTANCE.getNumberProperty("moonjelly.spawnweight");
    private static int yellowfinspawnweight = AqConfig.INSTANCE.getNumberProperty("yellowfin.spawnweight");
    private static int cuttlefishspawnweight = AqConfig.INSTANCE.getNumberProperty("cuttlefish.spawnweight");
    private static int opahspawnweight = AqConfig.INSTANCE.getNumberProperty("opah.spawnweight");
    private static int lionfishspawnweight = AqConfig.INSTANCE.getNumberProperty("lionfish.spawnweight");
    private static int clownfishspawnweight = AqConfig.INSTANCE.getNumberProperty("clownfish.spawnweight");

    private static int spottedastingray = AqConfig.INSTANCE.getNumberProperty("spottedstingray.spawnweight");
    private static int piranhaspawnweight = AqConfig.INSTANCE.getNumberProperty("piranha.spawnweight");
    private static int spermwhalespawnweight = AqConfig.INSTANCE.getNumberProperty("spermwhale.spawnweight");








    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SHARK, sharkspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.CLOWNFISH, clownfishspawnweight, 1, 2);
        SpawnRestriction.register(
                ModEntities.SPERMWHALE, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.WHALESHARK, whalesharkspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SUNFISH, sunfishspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.OPAH, opahspawnweight, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.YELLOWFIN, yellowfinspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.CUTTLEFISH, cuttlefishspawnweight, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.LIONFISH, lionfishspawnweight, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SPOTTEDSTINGRAY, spottedastingray, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.JELLYFISH, jellyfishspawnweight, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.MOONJELLY, moonjellyspawnweight, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN,BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN,BiomeKeys.DEEP_LUKEWARM_OCEAN,BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE,
                ModEntities.SPERMWHALE, spermwhalespawnweight, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE),SpawnGroup.WATER_CREATURE,
                ModEntities.PIRANHA, piranhaspawnweight, 3, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH), SpawnGroup.CREATURE,
                ModEntities.HERMITCRAB, herbitcrabspawnweight, 1, 2);

        SpawnRestriction.register(
                ModEntities.SHARK, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
        SpawnRestriction.register(
                ModEntities.PIRANHA, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canMobSpawn);
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
package org.variety.variety_aquatic.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.CrabEntity;
import org.variety.variety_aquatic.Entities.custom.HermitcrabEntity;
import org.variety.variety_aquatic.Util.NewConfig;

import java.util.Arrays;
import java.util.List;

public class ModEntitySpawns {

    public static void addEntitySpawn() {
        List<RegistryKey<Biome>> waterBiomesList = Arrays.asList(
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:ocean")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:warm_ocean")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:lukewarm_ocean")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:deep_lukewarm_ocean")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:deep_ocean"))
        );

        List<RegistryKey<Biome>> otherBiomesList = Arrays.asList(
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:beach")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:jungle")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:sparse_jungle")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:swamp")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:mangrove_swamp")),
                RegistryKey.of(Registry.BIOME_KEY, new Identifier("minecraft:deep_frozen_ocean"))
        );
        RegistryKey<Biome>[] waterBiomes = waterBiomesList.toArray(new RegistryKey[0]);
        RegistryKey<Biome>[] otherBiomes = otherBiomesList.toArray(new RegistryKey[0]);


        EntityType[] waterEntities = { ModEntities.SHARK, ModEntities.CLOWNFISH, ModEntities.WHALESHARK, ModEntities.SUNFISH, ModEntities.OPAH, ModEntities.YELLOWFIN,
                ModEntities.CUTTLEFISH, ModEntities.LIONFISH, ModEntities.SPOTTEDSTINGRAY, ModEntities.JELLYFISH, ModEntities.MOONJELLY,
                ModEntities.GIANTGLOWINGSQUID };

        int[] waterSpawnWeights = { NewConfig.shark_spawnweight, NewConfig.clownfish_spawnweight, NewConfig.whaleshark_spawnweight, NewConfig.sunfish_spawnweight,
                NewConfig.opah_spawnweight, NewConfig.yellowfin_spawnweight, NewConfig.cuttlefish_spawnweight, NewConfig.lionfish_spawnweight,
                NewConfig.spottedstingray_spawnweight, NewConfig.jellyfish_spawnweight, NewConfig.moonjelly_spawnweight, NewConfig.giantsquid_spawnweight };

        int[] minCounts = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 };
        int[] maxCounts = { 2, 4, 2, 3, 1, 2, 2, 1, 1, 4, 1, 1 };

        for (int i = 0; i < waterEntities.length; i++) {
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(waterBiomes), SpawnGroup.WATER_CREATURE, waterEntities[i], waterSpawnWeights[i], minCounts[i], maxCounts[i]);
        }

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH), SpawnGroup.CREATURE, ModEntities.CRAB, NewConfig.shark_spawnweight, 1, 4);

        EntityType[] landEntities = { ModEntities.PIRANHA, ModEntities.BETTA, ModEntities.TETRA, ModEntities.ANGLERFISH, ModEntities.OARFISH, ModEntities.VAMPIRESQUID,
                ModEntities.SEAHORSE };

        int[] landSpawnWeights = { NewConfig.piranha_spawnweight, NewConfig.betta_spawnweight, NewConfig.tetra_spawnweight, NewConfig.anglerfish_spawnweight,
                NewConfig.oarfish_spawnweight, NewConfig.vampiresquid_spawnweight, NewConfig.seahorse_spawnweight, NewConfig.hermitcrab_spawnweight };

        for (int i = 0; i < landEntities.length; i++) {
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(otherBiomes), SpawnGroup.WATER_CREATURE, landEntities[i], landSpawnWeights[i], 3, 5);
        }

        SpawnRestriction.Location[] spawnLocations = { SpawnRestriction.Location.IN_WATER, SpawnRestriction.Location.ON_GROUND };
        Heightmap.Type[] heightmaps = { Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES };

        for (int i = 0; i < waterEntities.length; i++) {
            SpawnRestriction.register(waterEntities[i], spawnLocations[0], heightmaps[0], WaterCreatureEntity::canMobSpawn);
        }

        for (int i = 0; i < landEntities.length; i++) {
            SpawnRestriction.register(landEntities[i], spawnLocations[0], heightmaps[0], WaterCreatureEntity::canMobSpawn);
        }

        SpawnRestriction.register(ModEntities.CRAB, spawnLocations[1], heightmaps[0], CrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.HERMITCRAB, spawnLocations[1], heightmaps[0], HermitcrabEntity::canMobSpawn);    }
}

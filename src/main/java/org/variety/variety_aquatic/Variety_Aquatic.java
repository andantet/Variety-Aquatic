package org.variety.variety_aquatic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.Tile.ModTileRegistry;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.world.Feature.AnemoneFeature;
import org.variety.variety_aquatic.world.ModWorldGen;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;

public class Variety_Aquatic implements ModInitializer {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier ANEONE_FEATURE_ID = new Identifier("varietyaquatic_ameone", "ameone");
    public static Feature<CountConfig> ANEONE_FEATURE = new AnemoneFeature(CountConfig.CODEC);
    public static ConfiguredFeature<CountConfig, AnemoneFeature> ANEONE_FEATURE_CONFIGURED = new ConfiguredFeature<>(
            (AnemoneFeature) ANEONE_FEATURE,
            new CountConfig(1)
    );

    // our PlacedFeature. this is what gets passed to the biome modification API to add to the biome.
    public static PlacedFeature ANEONE_FEATURE_PLACED = new PlacedFeature(
            RegistryEntry.of(
                    ANEONE_FEATURE_CONFIGURED
//                    the SquarePlacementModifier makes the feature generate a cluster of pillars each time
            ), List.of(SquarePlacementModifier.of())
    );

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        ModItems.registerModItems();
        new ModTileRegistry();
        new ModBlock();
        Registry.register(Registry.FEATURE, ANEONE_FEATURE_ID, ANEONE_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ANEONE_FEATURE_ID, ANEONE_FEATURE_CONFIGURED);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, ANEONE_FEATURE_ID, ANEONE_FEATURE_PLACED);

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                // the feature is to be added while flowers and trees are being generated
                GenerationStep.Feature.LAKES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, ANEONE_FEATURE_ID));


        ModWorldGen.generateWorldGen();

        FabricDefaultAttributeRegistry.register(ModEntities.SHARK, SharkEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.WHALESHARK, WhaleSharkEntity.setAttributes());

        FabricDefaultAttributeRegistry.register(ModEntities.SUNFISH, SunfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HERMITCRAB, HermitcrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.JELLYFISH, JellyfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.MOONJELLY, MoonJellyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BLUEFIN, MoonJellyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CUTTLEFISH, MoonJellyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.OPAH, OpahEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LIONFISH, LionfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CLOWNFISH, ClownfishEntity.setAttributes());







    }
}

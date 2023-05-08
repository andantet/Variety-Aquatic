package org.variety.variety_aquatic;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.variety.variety_aquatic.Biome.OceanBiomes;
import org.variety.variety_aquatic.Biome.RiverRegion;
import org.variety.variety_aquatic.Biome.RiverSurfaceRules;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Fluid.ModFluid;
import org.variety.variety_aquatic.Items.ModItems;
import org.variety.variety_aquatic.Sound.ModSound;
import org.variety.variety_aquatic.Util.NewConfig;
import org.variety.variety_aquatic.Util.TornadoParticleFactory;
import org.variety.variety_aquatic.Util.TornadoParticleType;
import org.variety.variety_aquatic.world.ModWorldGen;
import software.bernie.geckolib3.GeckoLib;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class Variety_Aquatic implements ModInitializer, TerraBlenderApi {
    public static final String MOD_ID = "varietyaquatic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TornadoParticleType TORNADO_PARTICLE = Registry.register(Registry.PARTICLE_TYPE, new Identifier("varietyaquatic", "tornado_particle"), new TornadoParticleType());

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        OceanBiomes.init();

        MidnightConfig.init(MOD_ID, NewConfig.class);

        ModItems.registerModItems();
        ModSound.initializeSounds();
        ModBlock.registerModBlocks();
        ModTileEntity.registerBlockEntities();
        ModFluid.register();
        ModWorldGen.generateWorldGen();
        ParticleFactoryRegistry.getInstance().register(TORNADO_PARTICLE, TornadoParticleFactory::new);
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


    }
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new RiverRegion(new Identifier(Variety_Aquatic.MOD_ID, "overworld"), 1));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Variety_Aquatic.MOD_ID, RiverSurfaceRules.build());
    }
}

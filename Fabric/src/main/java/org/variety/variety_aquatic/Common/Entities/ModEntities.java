package org.variety.variety_aquatic.Common.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Common.Entities.ProjectileEntity.BlindnessProjectile;
import org.variety.variety_aquatic.Common.Entities.custom.*;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModEntities {

    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(ModEntities.SEAANGEL, SeaangleEntity.setAttributes());
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

    public static final EntityType<BlindnessProjectile> BLINDNESS_PROJECTILE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Variety_Aquatic.MOD_ID, "blindness_projectile"),
            FabricEntityTypeBuilder.<BlindnessProjectile>create(SpawnGroup.MISC, (entityType, world) -> new BlindnessProjectile(entityType, world))
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build());



    public static final EntityType<SharkEntity> SHARK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "shark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SharkEntity::new)
                    .dimensions(EntityDimensions.fixed(1.6f, 1.6f)).trackRangeBlocks(50).build());
    public static final EntityType<LeviathanEntity> LEVIATHAN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "leviathan"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LeviathanEntity::new)
                    .dimensions(EntityDimensions.fixed(4f, 3f)).trackRangeBlocks(50).build());
    public static final EntityType<WhaleSharkEntity> WHALESHARK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "whaleshark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, WhaleSharkEntity::new)
                    .dimensions(EntityDimensions.fixed(5f, 2f)).trackRangeBlocks(50).build());
    public static final EntityType<GiantsquidEntity> GIANTGLOWINGSQUID = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "giantglowingsquid"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, GiantsquidEntity::new)
                    .dimensions(EntityDimensions.fixed(3.5f, 3f)).trackRangeBlocks(50).build());
    public static final EntityType<squidlingEntity> SQUIDLING = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "squidling"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, squidlingEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<SunfishEntity> SUNFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "sunfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SunfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 1.5f)).trackRangeBlocks(50).build());
    public static final EntityType<JellyfishEntity> JELLYFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "jellyfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, JellyfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.9f)).trackRangeBlocks(50).build());
    public static final EntityType<SeaangleEntity> SEAANGEL = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "seaangel"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SeaangleEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<BettaEntity> BETTA = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "betta"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BettaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.4f)).trackRangeBlocks(50).build());


    public static final EntityType<SeahorseEntity> SEAHORSE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "seahorse"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SeahorseEntity::new)
                    .dimensions(EntityDimensions.changing(0.35f, 0.6f)).trackRangeBlocks(50).build());

    public static final EntityType<MoonJellyEntity> MOONJELLY = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "moonjelly"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, MoonJellyEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.6f)).trackRangeBlocks(50).build());
    public static final EntityType<FlashlightfishEntity> FLASHLIGHTFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "flashlightfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, FlashlightfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.6f)).trackRangeBlocks(50).build());

    public static final EntityType<HermitcrabEntity> HERMITCRAB = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "hermitcrab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HermitcrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());
    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());

    public static final EntityType<CuttlefishEntity> CUTTLEFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "cuttlefish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CuttlefishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());
    public static final EntityType<OpahEntity> OPAH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "opah"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OpahEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.3f)).trackRangeBlocks(50).build());
    public static final EntityType<LionfishEntity> LIONFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "lionfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LionfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).trackRangeBlocks(50).build());
    public static final EntityType<SpottedStingray> SPOTTEDSTINGRAY = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "spottedstingray"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpottedStingray::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 0.5f)).trackRangeBlocks(50).build());

    public static final EntityType<YellowfinTunaEntity> YELLOWFIN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "yellowfin"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, YellowfinTunaEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 1.0f)).trackRangeBlocks(50).build());

    public static final EntityType<ClownfishEntity> CLOWNFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "clownfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, ClownfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)).trackRangeBlocks(50).build());

    public static final EntityType<PiranhaEntity> PIRANHA = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "piranha"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, PiranhaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 0.7f)).trackRangeBlocks(50).build());
    public static final EntityType<VampireSquidEntity> VAMPIRESQUID = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "vampiresquid"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, VampireSquidEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<OarfishEntity> OARFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "oarfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, OarfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.2f)).trackRangeBlocks(50).build());

    public static final EntityType<BarreleyeEntity> BARRELEE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "barreleye"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BarreleyeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 0.5f)).trackRangeBlocks(50).build());
    public static final EntityType<TetraEntity> TETRA = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "tetra"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, TetraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(50).build());

    public static final EntityType<AnglerFishEntity> ANGLERFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "anglerfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.UNDERGROUND_WATER_CREATURE, AnglerFishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(50).build());
}
package org.variety.variety_aquatic.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Entities.ProjectileEntity.BlindnessProjectile;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Entities.custom.OarfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModEntities {
    public static final EntityType<BlindnessProjectile> BLINDNESS_PROJECTILE_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Variety_Aquatic.MOD_ID, "blindness_projectile"),
            FabricEntityTypeBuilder.<BlindnessProjectile>create(SpawnGroup.MISC, (entityType, world) -> new BlindnessProjectile(entityType, world))
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build());



    public static final EntityType<SharkEntity> SHARK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "shark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SharkEntity::new)
                    .dimensions(EntityDimensions.fixed(1.6f, 1.6f)).trackRangeBlocks(50).build());
    public static final EntityType<LeviathanEntity> LEVIATHAN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "leviathan"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LeviathanEntity::new)
                    .dimensions(EntityDimensions.fixed(4f, 3f)).trackRangeBlocks(50).build());
    public static final EntityType<WhaleSharkEntity> WHALESHARK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "whaleshark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, WhaleSharkEntity::new)
                    .dimensions(EntityDimensions.fixed(5f, 2f)).trackRangeBlocks(50).build());
    public static final EntityType<GiantsquidEntity> GIANTGLOWINGSQUID = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "giantglowingsquid"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, GiantsquidEntity::new)
                    .dimensions(EntityDimensions.fixed(3.5f, 3f)).trackRangeBlocks(50).build());
    public static final EntityType<squidlingEntity> SQUIDLING = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "squidling"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, squidlingEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<SunfishEntity> SUNFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "sunfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SunfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 1.5f)).trackRangeBlocks(50).build());
    public static final EntityType<JellyfishEntity> JELLYFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "jellyfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, JellyfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.9f)).trackRangeBlocks(50).build());
    public static final EntityType<SeaangleEntity> SEAANGLE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "seaangle"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SeaangleEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<BettaEntity> BETTA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "betta"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BettaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.4f)).trackRangeBlocks(50).build());


    public static final EntityType<SeahorseEntity> SEAHORSE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "seahorse"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SeahorseEntity::new)
                    .dimensions(EntityDimensions.changing(0.35f, 0.6f)).trackRangeBlocks(50).build());

    public static final EntityType<MoonJellyEntity> MOONJELLY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "moonjelly"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, MoonJellyEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.6f)).trackRangeBlocks(50).build());
    public static final EntityType<FlashlightfishEntity> FLASHLIGHTFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "flashlightfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, FlashlightfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.6f)).trackRangeBlocks(50).build());

    public static final EntityType<HermitcrabEntity> HERMITCRAB = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "hermitcrab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HermitcrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());
    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());

    public static final EntityType<CuttlefishEntity> CUTTLEFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "cuttlefish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CuttlefishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).trackRangeBlocks(50).build());
    public static final EntityType<OpahEntity> OPAH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "opah"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OpahEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.3f)).trackRangeBlocks(50).build());
    public static final EntityType<LionfishEntity> LIONFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "lionfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LionfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).trackRangeBlocks(50).build());
    public static final EntityType<SpottedStingray> SPOTTEDSTINGRAY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "spottedstingray"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpottedStingray::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 0.5f)).trackRangeBlocks(50).build());

    public static final EntityType<YellowfinTunaEntity> YELLOWFIN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "yellowfin"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, YellowfinTunaEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 1.7f)).trackRangeBlocks(50).build());

    public static final EntityType<ClownfishEntity> CLOWNFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "clownfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, ClownfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)).trackRangeBlocks(50).build());

    public static final EntityType<PiranhaEntity> PIRANHA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "piranha"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, PiranhaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 0.7f)).trackRangeBlocks(50).build());
    public static final EntityType<VampireSquidEntity> VAMPIRESQUID = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "vampiresquid"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, VampireSquidEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<OarfishEntity> OARFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "oarfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, OarfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.2f)).trackRangeBlocks(50).build());
    public static final EntityType<TornadoEntity> TORNADO = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "tornado"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, TornadoEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 5f)).trackRangeBlocks(50).build());
    public static final EntityType<BarreleyeEntity> BARRELEE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "barreleye"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BarreleyeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 0.5f)).trackRangeBlocks(50).build());
    public static final EntityType<TetraEntity> TETRA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "tetra"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, TetraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(50).build());

    public static final EntityType<AnglerFishEntity> ANGLERFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "anglerfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.UNDERGROUND_WATER_CREATURE, AnglerFishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(50).build());
}
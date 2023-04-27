package org.variety.variety_aquatic.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModEntities {

    public static final EntityType<SharkEntity> SHARK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "shark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SharkEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3f, 1.6f)).build());
    public static final EntityType<LeviathanEntity> LEVIATHAN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "leviathan"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LeviathanEntity::new)
                    .dimensions(EntityDimensions.fixed(4f, 3f)).build());
    public static final EntityType<WhaleSharkEntity> WHALESHARK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "whaleshark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, WhaleSharkEntity::new)
                    .dimensions(EntityDimensions.fixed(5f, 2f)).build());
    public static final EntityType<GiantsquidEntity> GIANTGLOWINGSQUID = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "giantglowingsquid"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, GiantsquidEntity::new)
                    .dimensions(EntityDimensions.fixed(3.5f, -6f)).build());

    public static final EntityType<SunfishEntity> SUNFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "sunfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SunfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 2.3f)).build());
    public static final EntityType<JellyfishEntity> JELLYFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "jellyfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, JellyfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).build());
    public static final EntityType<BettaEntity> BETTA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "betta"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BettaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.4f)).build());

    public static final EntityType<MoonJellyEntity> MOONJELLY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "moonjelly"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, MoonJellyEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).build());

    public static final EntityType<HermitcrabEntity> HERMITCRAB = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "hermitcrab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HermitcrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).build());
    public static final EntityType<CuttlefishEntity> CUTTLEFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "cuttlefish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CuttlefishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f)).build());
    public static final EntityType<OpahEntity> OPAH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "opah"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OpahEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.3f)).build());
    public static final EntityType<LionfishEntity> LIONFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "lionfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LionfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
    public static final EntityType<SpottedStingray> SPOTTEDSTINGRAY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "spottedstingray"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpottedStingray::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 0.5f)).build());

    public static final EntityType<YellowfinTunaEntity> YELLOWFIN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "yellowfin"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, YellowfinTunaEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 1.7f)).build());

    public static final EntityType<ClownfishEntity> CLOWNFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "clownfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, ClownfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 0.7f)).build());

    public static final EntityType<PiranhaEntity> PIRANHA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "piranha"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, PiranhaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 0.7f)).build());

    public static final EntityType<TetraEntity> TETRA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "tetra"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, TetraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<AnglerFishEntity> ANGLERFISH = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "anglerfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, AnglerFishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
}
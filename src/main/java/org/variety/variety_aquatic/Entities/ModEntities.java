package org.variety.variety_aquatic.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.*;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModEntities {

    public static final EntityType<SharkEntity> SHARK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "shark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SharkEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3f, 1.6f)).build());
    public static final EntityType<WhaleSharkEntity> WHALESHARK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "whaleshark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, WhaleSharkEntity::new)
                    .dimensions(EntityDimensions.fixed(5f, 2f)).build());

    public static final EntityType<SunfishEntity> SUNFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "sunfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SunfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3f, 2f)).build());
    public static final EntityType<JellyfishEntity> JELLYFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "jellyfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, JellyfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).build());

    public static final EntityType<MoonJellyEntity> MOONJELLY = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "moonjelly"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, MoonJellyEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.2f)).build());

    public static final EntityType<HermitcrabEntity> HERMITCRAB = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "hermitcrab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HermitcrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<CuttlefishEntity> CUTTLEFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "cuttlefish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CuttlefishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<OpahEntity> OPAH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "opah"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OpahEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<LionfishEntity> LIONFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "lionfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LionfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<BluefinTuna> BLUEFIN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "bluefin"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BluefinTuna::new)
                    .dimensions(EntityDimensions.fixed(1.3f, 2f)).build());

}
package org.variety.variety_aquatic.Entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Entities.custom.SharkEntity;
import org.variety.variety_aquatic.Entities.custom.SunfishEntity;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModEntities {

    public static final EntityType<SharkEntity> SHARK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "shark"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SharkEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1.5f)).build());

    public static final EntityType<SunfishEntity> SUNFISH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, "sunfish"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SunfishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

}
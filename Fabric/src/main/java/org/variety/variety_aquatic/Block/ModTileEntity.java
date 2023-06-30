package org.variety.variety_aquatic.Common.Block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Common.Block.Tile.*;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModTileEntity {

    public static BlockEntityType<AnemoneTileEntity> ANEMONE;
    public static BlockEntityType<LeviathanTrophyTileEntity> LEVIATHAN;
    public static BlockEntityType<GiantGlowingSquidTileEntity> GIANTSQUID;

    public static BlockEntityType<BeholderTileEntity> BEHOLDER;



    public static void registerBlockEntities() {
        ANEMONE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "anemone"),
                FabricBlockEntityTypeBuilder.create(AnemoneTileEntity::new,
                        ModBlock.ANEMONE_BLOCK).build(null));
        LEVIATHAN = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "leviathan"),
                FabricBlockEntityTypeBuilder.create(LeviathanTrophyTileEntity::new,
                        ModBlock.LEVIATHAN_TROPHY_BLOCK).build(null));
        GIANTSQUID = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "giantsquidtrophy"),
                FabricBlockEntityTypeBuilder.create(GiantGlowingSquidTileEntity::new,
                        ModBlock.GIANTSQUID_TROPHY_BLOCK).build(null));

        BEHOLDER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "beholder"),
                FabricBlockEntityTypeBuilder.create(BeholderTileEntity::new,
                        ModBlock.BEHOLDER).build(null));

    }
}

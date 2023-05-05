package org.variety.variety_aquatic.Block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Block.Tile.LeviathanTileEntity;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModTileEntity {

    public static BlockEntityType<AnemoneTileEntity> ANEMONE;
    public static BlockEntityType<LeviathanTileEntity> LEVIATHANTROPHY;


    public static void registerBlockEntities() {
        ANEMONE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "anemone"),
                FabricBlockEntityTypeBuilder.create(AnemoneTileEntity::new,
                        ModBlock.ANEMONE_BLOCK).build(null));

        LEVIATHANTROPHY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Variety_Aquatic.MOD_ID, "leviathan"),
                FabricBlockEntityTypeBuilder.create(LeviathanTileEntity::new,
                        ModBlock.LEVIATHANTROPHYBLOCK).build(null));
    }
}

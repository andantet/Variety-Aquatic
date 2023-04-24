package org.variety.variety_aquatic.Block.Tile;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Block.ModBlock;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModTileRegistry {
    public static final BlockEntityType<AnemoneTileEntity> ANEMONE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            Variety_Aquatic.MOD_ID + ":anemonetile",
            FabricBlockEntityTypeBuilder.create(AnemoneTileEntity::new, ModBlock.ANEMONE_BLOCK).build(null));
}

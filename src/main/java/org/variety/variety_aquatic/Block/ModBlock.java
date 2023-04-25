package org.variety.variety_aquatic.Block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModBlock {

    public static final Block ANEMONE_BLOCK = registerBlock("anemone_block",
            new AnemoneBlock(FabricBlockSettings.of(Material.UNDERWATER_PLANT)
                    .strength(4f).requiresTool().nonOpaque()), ItemGroup.MISC);

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Variety_Aquatic.LOGGER.debug("Registering ModBlocks for " + Variety_Aquatic.MOD_ID);
    }
}
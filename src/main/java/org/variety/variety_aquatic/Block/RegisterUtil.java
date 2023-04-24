package org.variety.variety_aquatic.Block;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Variety_Aquatic;

public class RegisterUtil {

    public static <B extends Block> B register(String name, B block) {
        return register(block, new Identifier(Variety_Aquatic.MOD_ID, name), ItemGroup.DECORATIONS);
    }



    public static <B extends Block> B register(B block, Identifier name, ItemGroup itemGroup) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            Registry.register(Registry.BLOCK, name, block);
            BlockItem item = new BlockItem(block, (new Settings()).group(itemGroup));
            item.appendBlocks(Item.BLOCK_ITEMS, item);
            Registry.register(Registry.ITEM, name, item);
        }
        return block;
    }

}

package org.variety.variety_aquatic.Block;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityType.Builder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Variety_Aquatic;

public class RegisterUtil {

    public static <B extends Block> B register(B block, Identifier name) {
        return register(block, name, ItemGroup.DECORATIONS);
    }

    public static <B extends Block> B register(String name, B block) {
        return register(block, new Identifier(Variety_Aquatic.MOD_ID, name), ItemGroup.DECORATIONS);
    }

    public static <B extends Block> B register(String name, B block, ItemGroup itemGroup) {
        return register(block, new Identifier(Variety_Aquatic.MOD_ID, name), itemGroup);
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

    public static <B extends Block> B registerBlockWithoutItem(B block, Identifier identifier) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            Registry.register(Registry.BLOCK, identifier, block);
        }
        return block;
    }

    public static <B extends Block> B registerBlockWithoutItem(String name, B block) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            Registry.register(Registry.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
        }
        return block;
    }

    public static <I extends Item> I registerItem(I item, Identifier name) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return Registry.register(Registry.ITEM, name, item);
        }
        return null;
    }

    public static <I extends Item> I registerItem(String name, I item) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return Registry.register(Registry.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name), item);
        }
        return null;
    }

    public static Block registerBlockWithWallBlock(Block block, Block wallBlock, Identifier name) {
        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name,
                new WallStandingBlockItem(block, wallBlock, new Settings().group(ItemGroup.DECORATIONS)));
        return block;
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, Builder<T> builder) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            BlockEntityType<T> blockEntityType = builder.build(null);
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Variety_Aquatic.MOD_ID, name), blockEntityType);
            return blockEntityType;
        }
        return null;
    }

    public static Block registerNetherStem(Identifier name, MapColor MapColor) {
        return register(new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> MapColor)
                .strength(1.0F).sounds(BlockSoundGroup.NETHER_STEM)), name);
    }

    public static Block registerLog(Identifier name, MapColor MapColor, MapColor MapColor2) {
        return register(new PillarBlock(AbstractBlock.Settings.of(Material.WOOD,
                        (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2)
                .strength(2.0F).sounds(BlockSoundGroup.WOOD)), name);
    }

    public static Block registerNetherStem(String name, MapColor MapColor) {
        return register(
                new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> MapColor).strength(1.0F)
                        .sounds(BlockSoundGroup.NETHER_STEM)),
                new Identifier(Variety_Aquatic.MOD_ID, name), ItemGroup.BUILDING_BLOCKS);
    }

    public static Block registerLog(String name, MapColor MapColor, MapColor MapColor2) {
        return register(
                new PillarBlock(AbstractBlock.Settings.of(Material.WOOD,
                                (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor
                                        : MapColor2)
                        .strength(2.0F).sounds(BlockSoundGroup.WOOD)),
                new Identifier(Variety_Aquatic.MOD_ID, name));
    }

}

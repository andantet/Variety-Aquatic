package org.variety.variety_aquatic.Block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Block.Custom.*;
import org.variety.variety_aquatic.Items.ModItemGroup;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModBlock {



    public static final Block ANEMONE_BLOCK = registerBlock("anemone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(4.0f).requiresTool()),ModItemGroup.VARIETYAQUATIC);


    public static final Block LEVIATHAN_TROPHY_BLOCK = registerTrophyBlock("leviathan",
            new LeviathanTrophyBlock(FabricBlockSettings.of(Material.DECORATION)
                    .strength(4f).nonOpaque()));
    public static final Block GIANTSQUID_TROPHY_BLOCK = registerTrophyBlock("giantsquidtrophy",
            new GiantGlowingSquidTrophyBlock(FabricBlockSettings.of(Material.DECORATION)
                    .strength(4f).nonOpaque()));
    public static final Block BEHOLDER = registerBlock("beholder",
            new Beholder(FabricBlockSettings.of(Material.DECORATION)
                    .strength(4f).nonOpaque().luminance(state -> state.get(Beholder.CURRENT_STATE).getLightLevel())),ModItemGroup.VARIETYAQUATIC);



    public static final Block SEA_URCHIN_BLOCK = registerBlock("sea_urchin_block",
            new SeaUrchinBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque().breakInstantly()),ModItemGroup.VARIETYAQUATIC);
    public static final Block ANGLER_TORCH = Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, "angler_torch_ground"),
            new AnglerTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD), ParticleTypes.BUBBLE));
    public static final Block WALL_ANGLER_TORCH = Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, "wall_angler_torch"),
            new WallAnglerTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD).dropsLike(ModBlock.ANGLER_TORCH), ParticleTypes.BUBBLE));



    private static Block registerTrophyBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        Variety_Aquatic.LOGGER.debug("Registering ModBlocks for " + Variety_Aquatic.MOD_ID);
    }
}
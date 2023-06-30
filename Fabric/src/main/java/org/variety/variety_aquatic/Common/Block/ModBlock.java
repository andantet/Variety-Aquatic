package org.variety.variety_aquatic.Common.Block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Common.Block.Custom.*;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModBlock {



    public static final Block ANEMONE_BLOCK = registerBlock("anemone",
            new AnemoneBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(4.0f).nonOpaque().requiresTool().noBlockBreakParticles()));


    public static final Block LEVIATHAN_TROPHY_BLOCK = registerTrophyBlock("leviathan",
            new LeviathanTrophyBlock(FabricBlockSettings.copyOf(Blocks.STONE)
                    .strength(4f).nonOpaque().noBlockBreakParticles()));
    public static final Block GIANTSQUID_TROPHY_BLOCK = registerTrophyBlock("giantsquidtrophy",
            new GiantGlowingSquidTrophyBlock(FabricBlockSettings.copyOf(Blocks.STONE)
                    .strength(4f).nonOpaque().noBlockBreakParticles()));
    public static final Block BEHOLDER = registerBlock("beholder",
            new Beholder(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)
                    .strength(4f).nonOpaque().luminance(state -> state.get(Beholder.CURRENT_STATE).getLightLevel()).noBlockBreakParticles()));



    public static final Block SEA_URCHIN_BLOCK = registerBlock("sea_urchin_block",
            new SeaUrchinBlock(FabricBlockSettings.copyOf(Blocks.KELP)
                    .strength(4f).nonOpaque().breakInstantly().noBlockBreakParticles()));
    public static final Block ANGLER_TORCH = Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, "angler_torch_ground"),
            new AnglerTorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH).noCollision().noBlockBreakParticles().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD), ParticleTypes.BUBBLE));
    public static final Block WALL_ANGLER_TORCH = Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, "wall_angler_torch"),
            new WallAnglerTorchBlock(FabricBlockSettings.copyOf(Blocks.WALL_TORCH).noBlockBreakParticles().noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD).dropsLike(ModBlock.ANGLER_TORCH), ParticleTypes.BUBBLE));



    private static Block registerTrophyBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Variety_Aquatic.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }


    public static void registerModBlocks() {
        Variety_Aquatic.LOGGER.debug("Registering ModBlocks for " + Variety_Aquatic.MOD_ID);
    }
}
package org.variety.variety_aquatic.Block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.HoverEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Block.Custom.*;
import org.variety.variety_aquatic.Items.ModItemgroup;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModBlock {

    public static final Block ANEMONE_BLOCK = registerBlock("anemone",
            new AnemoneBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque()), ModItemgroup.VARIETY_AQUATIC);
    public static final Block LEVIATHAN_TROPHY_BLOCK = registerBlock("leviathan",
            new LeviathanTrophyBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque()), ModItemgroup.VARIETY_AQUATIC);
    public static final Block GIANTSQUID_TROPHY_BLOCK = registerBlock("giantsquidtrophy",
            new GiantGlowingSquidTrophyBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque()), ModItemgroup.VARIETY_AQUATIC);
    public static final Block BEHOLDER_BLOCK = registerBlock("beholder",
            new BeholderBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque()), ModItemgroup.VARIETY_AQUATIC);
    public static final Block AQUARIUM_BLOCK  = registerBlock("aquarium_block",
            new AquariumBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD)),ModItemgroup.VARIETY_AQUATIC);




    public static final Block SEA_URCHIN_BLOCK = registerBlock("sea_urchin_block",
            new SeaUrchinBlock(FabricBlockSettings.of(Material.REPLACEABLE_UNDERWATER_PLANT)
                    .strength(4f).nonOpaque().breakInstantly()), ModItemgroup.VARIETY_AQUATIC);
    public static final Block ANGLER_TORCH= registerBlock("angler_torch_ground",
            new AnglerTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD), ParticleTypes.BUBBLE),null);
    public static final Block WALL_ANGLER_TORCH = registerBlock("wall_angler_torch",
            new WallAnglerTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {
                return 14;
            }).sounds(BlockSoundGroup.WOOD).dropsLike(ModBlock.ANGLER_TORCH), ParticleTypes.BUBBLE),null);



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
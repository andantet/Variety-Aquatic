package org.variety.varietyaquatic.Block;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.Block.Custom.GlowtorchBlock;
import org.variety.varietyaquatic.Block.Custom.WallGlowtorchBlock;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.item.ModItems;

import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VarietyAquatic.MODID);
    public static final RegistryObject<GlowtorchBlock> GLOWTORCH = BLOCKS.register("glowtorch", () -> new GlowtorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.0F).lightLevel(state -> 14).sound(SoundType.MOSS), ParticleTypes.BUBBLE));
    public static final RegistryObject<WallGlowtorchBlock> WALL_GLOWTORCH = BLOCKS.register("wall_glowtorch", () -> new WallGlowtorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.0F).lightLevel(state -> 14).sound(SoundType.MOSS), ParticleTypes.BUBBLE));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

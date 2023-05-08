package org.variety.variety_aquatic.Fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Items.ModItemgroup;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

public class ModFluid {
    public static FlowableFluid STILL_GLOWING_WATER;
    public static FlowableFluid FLOWING_GLOWING_WATER;
    public static Block GLOWING_WATER_BLOCK;
    public static Item GLOWING_WATER_BUCKET;

    public static void register() {
        FabricBlockSettings glowingWaterBlockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
                .luminance(10);
        STILL_GLOWING_WATER = Registry.register(Registry.FLUID,
                new Identifier(Variety_Aquatic.MOD_ID, "glowing_water"), new GlowingWaterFluid.Still());
        FLOWING_GLOWING_WATER = Registry.register(Registry.FLUID,
                new Identifier(Variety_Aquatic.MOD_ID, "flowing_glowing_water"), new GlowingWaterFluid.Flowing());

        GLOWING_WATER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Variety_Aquatic.MOD_ID, "glowing_water_block"),
                new FluidBlock(ModFluid.STILL_GLOWING_WATER, glowingWaterBlockSettings){ });
        GLOWING_WATER_BUCKET = Registry.register(Registry.ITEM, new Identifier(Variety_Aquatic.MOD_ID, "glowing_water_bucket"),
                new BucketItem(ModFluid.STILL_GLOWING_WATER, new FabricItemSettings().group(ModItemgroup.VARIETY_AQUATIC).recipeRemainder(Items.BUCKET).maxCount(1)));
    }
}
package org.variety.variety_aquatic.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.variety.variety_aquatic.Variety_Aquatic;
import org.variety.variety_aquatic.world.Feature.FeatureAnemone;

public abstract class FeatureHandler {
    public static final Feature<CountConfig> ANEMONE = new FeatureAnemone(CountConfig.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, Variety_Aquatic.id("anemone"), ANEMONE);
    }
    public static boolean isWater(BlockState state) {
        return state.isOf(Blocks.WATER);
    }

    public static boolean isAir(BlockState state) {
        return state.isOf(Blocks.AIR) || state.isOf(Blocks.CAVE_AIR);
    }

    public static boolean isCaveAir(BlockState state) {
        return state.isOf(Blocks.CAVE_AIR);
    }

    public static boolean isAirOrWater(BlockState state, boolean cave) {
        if (cave) {
            return (isCaveAir(state) || isWater(state));
        } else {
            return (isCaveAir(state) || isAir(state) || isWater(state));
        }
    }

    public static boolean isOnFloor(StructureWorldAccess worldAccess, BlockPos pos) {
        try {
            return isAir(worldAccess.getBlockState(pos)) && !isAirOrWater(worldAccess.getBlockState(pos.add(0, -1, 0)), false);
        } catch (RuntimeException ignored) {
//           a RuntimeException can be thrown if a feature placer random grouping tries to place a feature in an unloaded chunk. i just ignore it
        }
        return false;
    }

    public static BlockPos findNextFloor(FeatureContext fc) {
        return findNextFloor(fc.getWorld(), fc.getOrigin());
    }
    public static BlockPos findNextFloor(StructureWorldAccess world, BlockPos start) {
        return findNextFloor(world, start, world.getTopY());
    }
    public static BlockPos findNextFloor(StructureWorldAccess world, BlockPos start, int maxY, Block floorType) {
        try {
            for (int i = 0; i < maxY; i++) {
                if (
                        !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.POWDER_SNOW) &&
                                !world.getBlockState(start.add(0, i, 0)).isSolidBlock(world, start.add(0, i, 0))
                                && world.getBlockState(start.add(0,  i - 1, 0)).isOf(floorType)
                ) {
                    return start.add(0, i, 0);
                }
            }
        } catch (RuntimeException ignored) {
        }
        return start;
    }
    public static BlockPos findNextFloor(StructureWorldAccess world, BlockPos start, int maxY, TagKey<Block> floorType) {
        try {
            for (int i = 0; i < maxY; i++) {
                if (
                        !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.POWDER_SNOW)
                                && !world.getBlockState(start.add(0, i, 0)).isSolidBlock(world, start.add(0, i, 0))
                                && world.getBlockState(start.add(0,  i - 1, 0)).isIn(floorType)
                ) {
                    return start.add(0, i, 0);
                }
            }
        } catch (RuntimeException ignored) {
        }
        return start;
    }
    public static BlockPos findNextFloor(StructureWorldAccess world, BlockPos start, int maxY) {
        try {
            for (int i = 0; i < maxY; i++) {
                if (
                        !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.POWDER_SNOW) &&
                                !world.getBlockState(start.add(0, i, 0)).isSolidBlock(world, start.add(0, i, 0))
                ) {
                    return start.add(0, i, 0);
                }
            }
        } catch (RuntimeException ignored) {
        }
        return start;
    }

    public static BlockPos findNextCeiling(StructureWorldAccess world, BlockPos start) {
        try {
            for (int i = 10; i < world.getHeight(); i++) {
                if (
                        !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.AIR) &&
                                !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.CAVE_AIR) &&
                                !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.WATER) &&
                                !world.getBlockState(start.add(0, i, 0)).isOf(Blocks.LAVA)
                ) {
                    if (world.getBlockState(start.add(0, i - 1, 0)).isOf(Blocks.CAVE_AIR) || world.getBlockState(start.add(0, i - 1, 0)).isOf(Blocks.AIR))
                        return start.add(0, i - 1, 0);
                }
            }
        } catch (RuntimeException ignored) {
//            this actually should never happen but 1am em does not want to change it and have it break two months later
//            and i'll never be able to find out what went wrong
        }
        return BlockPos.ORIGIN;
    }
}

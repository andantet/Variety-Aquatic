package org.variety.variety_aquatic.world.Feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.variety.variety_aquatic.Block.ModBlock;

public class FeatureAnemone extends Feature<CountConfig> {

    public FeatureAnemone(Codec<CountConfig> codec) {
        super(CountConfig.CODEC);
    }
    public boolean generate(FeatureContext<CountConfig> context) {
        int i = 0;
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        int j = ((CountConfig)context.getConfig()).getCount().get(random);

        for(int k = 0; k < j; ++k) {
            int l = random.nextInt(8) - random.nextInt(8);
            int m = random.nextInt(8) - random.nextInt(8);
            int n = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.getX() + l, blockPos.getZ() + m);
            BlockPos blockPos2 = new BlockPos(blockPos.getX() + l, n, blockPos.getZ() + m);
            BlockState blockState = ModBlock.ANEMONE_BLOCK.getDefaultState();
            if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER) && blockState.canPlaceAt(structureWorldAccess, blockPos2)) {
                structureWorldAccess.setBlockState(blockPos2, blockState, 2);
                ++i;
            }
        }

        return i > 0;
    }
}


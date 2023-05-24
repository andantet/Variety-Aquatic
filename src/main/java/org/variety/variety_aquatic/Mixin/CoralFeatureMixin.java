package org.variety.variety_aquatic.Mixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.CoralFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.variety.variety_aquatic.Block.ModBlock;
@Mixin(CoralFeature.class)
public class CoralFeatureMixin {
    /*
    @Inject(method = "generateCoralPiece", at = @At("RETURN"))
    protected void generateCoralPiece(WorldAccess world, Random random, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(cir.getReturnValue()) {
            Direction.stream()
                    .filter(d -> random.nextFloat() < 0.0125d && canPlace(pos.offset(d), world))
                    .forEach(d -> {
                        world.setBlockState(pos.offset(d),
                                ModBlock.ANEMONE_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
                    });
            if(random.nextFloat() < 0.025f && canPlace(pos.up(), world)) {
                BlockState seaUrchinState = random.nextBoolean() ? ModBlock.SEA_URCHIN_BLOCK.getDefaultState() : ModBlock.ANEMONE_BLOCK.getDefaultState();
                if(seaUrchinState.isIn(BlockTags.CORALS) || seaUrchinState.isIn(BlockTags.WALL_CORALS)) {
                    seaUrchinState = seaUrchinState.with(Properties.WATERLOGGED, true);
                }
                world.setBlockState(pos.up(), seaUrchinState, Block.NOTIFY_LISTENERS);
            }
        }
    }

    private boolean canPlace(BlockPos pos, WorldAccess world) {
        BlockState state = world.getBlockState(pos);
        return state.isIn(BlockTags.CORALS) || (state.isIn(BlockTags.WALL_CORALS) && state.getBlock() != ModBlock.ANEMONE_BLOCK) || state.isOf(Blocks.WATER);

    }
    */
}


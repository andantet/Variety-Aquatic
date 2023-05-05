package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.Tile.LeviathanTileEntity;

public class LeviathanTrophyBlock extends BlockWithEntity implements BlockEntityProvider {
    public LeviathanTrophyBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    public static final DirectionProperty FACING = Properties.FACING;
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LeviathanTileEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction) state.get(FACING);
        switch (direction) {
            case NORTH: {
                return Block.createCuboidShape(0, 0, 0, 32, 16, 16);
            }
            case SOUTH: {
                return Block.createCuboidShape(-16, 0, 0, 16, 16, 16);
            }
            case WEST: {
                return Block.createCuboidShape(0, 0, -16, 16, 16, 16);
            }
            default:
                return Block.createCuboidShape(0, 0, 0, 16, 16, 32);
        }
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (BlockPos testPos : BlockPos.iterate(pos,
                pos.offset((Direction) state.get(FACING).rotateYClockwise(), 2))) {
            if (!testPos.equals(pos) && !world.getBlockState(testPos).isAir())
                return false;
        }
        return true;
    }




}

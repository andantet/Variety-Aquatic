package org.variety.variety_aquatic.Common.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Common.Block.Tile.LeviathanTrophyTileEntity;

public class LeviathanTrophyBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape FULL_BLOCK_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public LeviathanTrophyBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FULL_BLOCK_SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction playerLookDirection = ctx.getPlayerLookDirection();

        Direction facing = playerLookDirection.getAxis() == Direction.Axis.Y ? Direction.NORTH : playerLookDirection.getOpposite();

        return getDefaultState()
                .with(FACING, facing);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LeviathanTrophyTileEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
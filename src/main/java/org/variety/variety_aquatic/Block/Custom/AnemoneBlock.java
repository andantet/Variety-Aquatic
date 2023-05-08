package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.FluidType;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Block.Tile.AnemoneTileEntity;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Fluid.ModFluid;

public class AnemoneBlock extends PlantBlock implements BlockEntityProvider,Waterloggable {
    public static final EnumProperty<FluidType> FLUID = EnumProperty.of("fluid", FluidType.class);

    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.createCuboidShape(5, 0, 5, 11, 9, 11);

    public AnemoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(WATERLOGGED, false));
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, pos, Direction.UP);
    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.CACTUS, 1.0F);
    }
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos down = pos.down();
        BlockState downState = world.getBlockState(down);
        Fluid downFluid = downState.getFluidState().getFluid();
        return sideCoversSmallSquare(world, down, Direction.UP) ||
                (downFluid == Fluids.WATER && downState.isOf(Blocks.WATER)) ||
                downFluid == ModFluid.STILL_GLOWING_WATER;
    }
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if(!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = worldView.getFluidState(blockPos);
        boolean waterlogged = fluidState.getFluid() == Fluids.WATER || fluidState.getFluid() == ModFluid.STILL_GLOWING_WATER;
        FluidType fluidType = FluidType.NONE;
        if (fluidState.getFluid() == Fluids.WATER) {
            fluidType = FluidType.WATER;
        } else if (fluidState.getFluid() == ModFluid.STILL_GLOWING_WATER) {
            fluidType = FluidType.GLOWING_WATER;
        }
        return this.getDefaultState().with(WATERLOGGED, waterlogged).with(FLUID, fluidType);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(Properties.WATERLOGGED)) {
            switch (state.get(FLUID)) {
                case WATER:
                    return Fluids.WATER.getStill(false);
                case GLOWING_WATER:
                    return ModFluid.STILL_GLOWING_WATER.getDefaultState();
                default:
                    break;
            }
        }
        return super.getFluidState(state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AnemoneTileEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FLUID);
    }
}
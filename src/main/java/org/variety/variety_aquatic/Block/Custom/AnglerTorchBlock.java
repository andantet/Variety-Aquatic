package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.variety.variety_aquatic.Block.FluidType;
import org.variety.variety_aquatic.Fluid.ModFluid;


public class AnglerTorchBlock extends Block implements FluidFillable, Waterloggable {
    public static final EnumProperty<FluidType> FLUID = EnumProperty.of("fluid", FluidType.class);

    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleEffect particle;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public AnglerTorchBlock(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
        this.setDefaultState(this.getStateManager().getDefaultState().with(WATERLOGGED, false));
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
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


    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BOUNDING_SHAPE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FLUID);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos down = pos.down();
        BlockState downState = world.getBlockState(down);
        Fluid downFluid = downState.getFluidState().getFluid();
        return sideCoversSmallSquare(world, down, Direction.UP) ||
                (downFluid == Fluids.WATER && downState.isOf(Blocks.WATER)) ||
                downFluid == ModFluid.STILL_GLOWING_WATER;
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


    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + 0.5;
        double e = (double)pos.getY() + 0.7;
        double f = (double)pos.getZ() + 0.5;
        world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d, e, f, 0.0, 0.0, 0.0);
        world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
    }
}


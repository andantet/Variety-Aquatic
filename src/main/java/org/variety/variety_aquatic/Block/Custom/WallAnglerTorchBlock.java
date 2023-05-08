package org.variety.variety_aquatic.Block.Custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.FluidType;
import org.variety.variety_aquatic.Fluid.ModFluid;

import java.util.Map;

import static net.minecraft.util.registry.Registry.FLUID;

public class WallAnglerTorchBlock extends WallTorchBlock implements FluidFillable,Waterloggable {
    public static final EnumProperty<FluidType> FLUID = EnumProperty.of("fluid", FluidType.class);

    public static final DirectionProperty FACING;
    protected static final float field_31285 = 2.5F;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;


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

    public WallAnglerTorchBlock(AbstractBlock.Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
        this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    public String getTranslationKey() {
        return this.asItem().getTranslationKey();
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return (VoxelShape) BOUNDING_SHAPES.get(state.get(FACING));
    }





    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = (Direction) state.get(FACING);
        double d = (double) pos.getX() + 0.5;
        double e = (double) pos.getY() + 0.7;
        double f = (double) pos.getZ() + 0.5;
        double g = 0.22;
        double h = 0.27;
        Direction direction2 = direction.getOpposite();
        world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d + 0.27 * (double) direction2.getOffsetX(), e + 0.22, f + 0.27 * (double) direction2.getOffsetZ(), 0.0, 0.0, 0.0);
        world.addParticle(this.particle, d + 0.27 * (double) direction2.getOffsetX(), e + 0.22, f + 0.27 * (double) direction2.getOffsetZ(), 0.0, 0.0, 0.0);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, FLUID);
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

    static {
        FACING = HorizontalFacingBlock.FACING;
        BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5, 3.0, 11.0, 10.5, 13.0, 16.0), Direction.SOUTH, Block.createCuboidShape(5.5, 3.0, 0.0, 10.5, 13.0, 5.0), Direction.WEST, Block.createCuboidShape(11.0, 3.0, 5.5, 16.0, 13.0, 10.5), Direction.EAST, Block.createCuboidShape(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)));
    }
}
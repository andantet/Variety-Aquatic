package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Block.Tile.BeholderTileEntity;
import org.variety.variety_aquatic.Sound.ModSound;

public class Beholder extends BlockWithEntity implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final EnumProperty<State> CURRENT_STATE = EnumProperty.of("current_state", State.class);
    private static final VoxelShape SLAB_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 8, 16);
    private static final VoxelShape MOUTH_SHAPE = Block.createCuboidShape(3,8,4,13,16,12);
    public enum State implements StringIdentifiable {
        OFF(0),
        LOW(4),
        MEDIUM(8),
        HIGH(12);

        private final int lightLevel;

        State(int lightLevel) {
            this.lightLevel = lightLevel;
        }

        public int getLightLevel() {
            return lightLevel;
        }

        @Override
        public String asString() {
            return this.name().toLowerCase();
        }
    }



    public Beholder(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(CURRENT_STATE, State.OFF)
                .with(WATERLOGGED, false));


    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(SLAB_SHAPE, MOUTH_SHAPE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(SLAB_SHAPE, MOUTH_SHAPE);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            world.playSound(null, pos, ModSound.BEHOLDER_CLICK, SoundCategory.BLOCKS, 0.5F, 1.0F);
            State currentState = state.get(CURRENT_STATE);
            System.out.println("Current state: " + currentState.toString()); // Debugging code

            State newState;
            switch (currentState) {
                case OFF:
                    newState = State.LOW;
                    break;
                case LOW:
                    newState = State.MEDIUM;
                    break;
                case MEDIUM:
                    newState = State.HIGH;
                    break;
                case HIGH:
                    newState = State.OFF;
                    break;
                default:
                    newState = State.OFF;
                    break;
            }

            // Update the light level property based on the new state
            int newLightLevel = newState.getLightLevel();

            // Set the active state and light level of the block entity
            world.setBlockState(pos, state.with(CURRENT_STATE, newState), 3);
            ((BeholderTileEntity) world.getBlockEntity(pos)).setActiveState(newState);

            // Update the block's light level
            world.getChunkManager().getLightingProvider().checkBlock(pos);
            world.updateListeners(pos, state, state, 3);

            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        FluidState fluidState = world.getFluidState(pos);
        return super.canPlaceAt(state, world, pos) || blockState.getMaterial().isReplaceable() || fluidState.isIn(FluidTags.WATER);
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BeholderTileEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModTileEntity.BEHOLDER, world.isClient ? BeholderTileEntity::clientTick : BeholderTileEntity::serverTick);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, CURRENT_STATE, WATERLOGGED);
    }
}

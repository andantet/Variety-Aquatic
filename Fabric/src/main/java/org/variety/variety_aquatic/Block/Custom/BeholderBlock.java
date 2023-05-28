package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
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
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Block.Tile.BeholderTileEntity;
import org.variety.variety_aquatic.Sound.ModSound;

public class BeholderBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<State> CURRENT_STATE = EnumProperty.of("current_state", State.class);
    private static final VoxelShape SLAB_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 8, 16);
    private static final VoxelShape MOUTH_SHAPE = Block.createCuboidShape(3,8,4,13,16,12);

    // Enum class for the states
    public enum State implements StringIdentifiable {
        OFF,
        LOW,
        MEDIUM,
        HIGH;

        @Override
        public String asString() {
            return this.name().toLowerCase();
        }
    }

    public BeholderBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(CURRENT_STATE, State.OFF));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(SLAB_SHAPE, MOUTH_SHAPE);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(SLAB_SHAPE, MOUTH_SHAPE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
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

            System.out.println("New state: " + newState.toString()); // Debugging code

            // Set the active state of the block entity
            world.setBlockState(pos, state.with(CURRENT_STATE, newState), 3);
            ((BeholderTileEntity) world.getBlockEntity(pos)).setActiveState(newState);


            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
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
        builder.add(FACING, CURRENT_STATE);
    }
}

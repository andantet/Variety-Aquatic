package org.variety.variety_aquatic.Block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.Tile.ModTileRegistry;

public class AnemoneBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.FACING;
    protected static final VoxelShape COLLISION_SHAPE;


    public AnemoneBlock() {
        super(AbstractBlock.Settings.of(Material.UNDERWATER_PLANT).nonOpaque());
    }

    /*
     * Hides the normal block and only shows the block entity created below
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    /*
     * Adds that our block is faceable
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /*
     * Sets the correct facing, needed to flip this block on the 180, should have
     * done in the model in BB but eh
     */
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().rotateYClockwise().rotateYClockwise());
    }

    /*
     * Creates the block entity that we have playing our animations and rendering
     * the block
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModTileRegistry.ANEMONE_TILE.instantiate(pos, state);
    }

    /*
     * Sets the correct shape depending on your facing
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction) state.get(FACING);
        switch (direction) {
            default:
                return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
        }
    }

    /*
     * Tests for air 1 block out from the facing pos to ensure it's air so the block
     * doesn't place into another block
     */
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (BlockPos testPos : BlockPos.iterate(pos,
                pos.offset((Direction) state.get(FACING).rotateYClockwise(), 2))) {
            if (!testPos.equals(pos) && !world.getBlockState(testPos).isAir())
                return false;
        }
        return true;
    }
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.CACTUS, 1.0F);
    }

    static {
        COLLISION_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    }
}

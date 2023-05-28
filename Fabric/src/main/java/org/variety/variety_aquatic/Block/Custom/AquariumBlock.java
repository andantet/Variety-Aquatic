package org.variety.variety_aquatic.Block.Custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Block.Tile.AquariumBlockEntity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AquariumBlock extends BlockWithEntity {
    private static final VoxelShape FULL_BLOCK_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public AquariumBlock(Settings settings) {
        super(settings
                .emissiveLighting((state, reader, pos) -> {
                    BlockEntity blockEntity = reader.getBlockEntity(pos);
                    if (blockEntity instanceof AquariumBlockEntity) {
                        return ((AquariumBlockEntity) blockEntity).hasAquariumLight();
                    }
                    return false;
                })
                .dynamicBounds()
        );
    }
    

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FULL_BLOCK_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AquariumBlockEntity(pos, state);
    }



    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        world.setBlockState(pos, state);

        Set<BlockPos> connectedBlocks = floodFill(world, pos);

        AquariumBlockEntity ourEntity = (AquariumBlockEntity) world.getBlockEntity(pos);
        for (BlockPos connectedPos : connectedBlocks) {
            BlockEntity entity = world.getBlockEntity(connectedPos);
            if (entity instanceof AquariumBlockEntity) {
                ((AquariumBlockEntity) entity).addBlock(pos);
                ourEntity.addBlock(connectedPos);
            }
        }
    }


    public Set<BlockPos> floodFill(World world, BlockPos startPos) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(startPos);

        while (!queue.isEmpty()) {
            BlockPos pos = queue.remove();
            visited.add(pos);

            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = pos.offset(dir);
                if (!visited.contains(neighborPos) && world.getBlockState(neighborPos).getBlock() == this) {
                    queue.add(neighborPos);
                }
            }
        }

        return visited;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);

            if (be instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory) be);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }


}

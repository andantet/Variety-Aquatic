package org.variety.variety_aquatic.Block.Tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Entities.custom.ClownfishEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AnemoneTileEntity extends BlockEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final int HIDE_DURATION = 200;
    private static final int COOLDOWN_DURATION = 40;
    private int hideTimer;
    private int cooldownTimer;
    private ClownfishEntity hiddenClownfish;
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }



    public AnemoneTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntity.ANEMONE, pos, state);
    }
    public static void clientTick(World world, BlockPos pos, BlockState state, AnemoneTileEntity blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, AnemoneTileEntity blockEntity) {
        blockEntity.tick(world, pos, state, blockEntity);
    }

    public void tick(World world, BlockPos pos, BlockState state, AnemoneTileEntity blockEntity) {
        if (world == null || world.isClient) {
            return;
        }

        if (hideTimer > 0) {
            hideTimer--;
            if (hideTimer <= 0 && hiddenClownfish != null) {
                releaseClownfish();
            }
        } else if (cooldownTimer > 0) {
            cooldownTimer--;
        }
    }



    public boolean hasClownfish() {
        return hiddenClownfish != null;
    }

    public void setClownfish(ClownfishEntity clownfish) {
        if (cooldownTimer <= 0) {
            hiddenClownfish = clownfish;
            hideTimer = HIDE_DURATION;
            cooldownTimer = COOLDOWN_DURATION;
        }
    }

    private void releaseClownfish() {
        if (world != null && hiddenClownfish != null) {
            hiddenClownfish.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            world.spawnEntity(hiddenClownfish);
            hiddenClownfish = null;
        }
    }



    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sway", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<AnemoneTileEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}

package org.variety.variety_aquatic.Common.Block.Tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Common.Block.ModTileEntity;
import org.variety.variety_aquatic.Common.Entities.custom.ClownfishEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;


public class AnemoneTileEntity extends BlockEntity implements GeoAnimatable {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
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
            System.out.println("Hide Timer: " + hideTimer);
            hideTimer--;
            if (hideTimer <= 0 && hiddenClownfish != null) {
                System.out.println("Releasing clownfish...");
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
            System.out.println("Clownfish set in anemone. Hide Timer: " + hideTimer + ", Cooldown Timer: " + cooldownTimer);
        }
    }

    private void releaseClownfish() {
        if (world != null && hiddenClownfish != null) {
            hiddenClownfish.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            world.spawnEntity(hiddenClownfish);
            hiddenClownfish = null;
            System.out.println("Clownfish released from anemone.");
        }
    }



    private <E extends BlockEntity & GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().then("sway", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    @Override
    public double getTick(Object o) {
        return RenderUtils.getCurrentTick();
    }
}

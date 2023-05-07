package org.variety.variety_aquatic.Block.Tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.Custom.BeholderBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class BeholderTileEntity extends BlockEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    // Active state field
    private BeholderBlock.State activeState = BeholderBlock.State.OFF;

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public BeholderTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntity.BEHOLDER, pos, state);
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        switch (activeState) {
            case OFF:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Off", true));
                break;
            case LOW:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Low", true));
                break;
            case MEDIUM:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Medium", true));
                break;
            case HIGH:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("High", true));
                break;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BeholderTileEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    private static void tick(World world, BlockPos pos, BlockState state, BeholderTileEntity blockEntity, BeholderTileEntity bellEffect) {
        // Do something
    }

    private static void tick(World world, BlockPos pos, BlockState state, BeholderTileEntity blockEntity) {
        blockEntity.applyGlowEffectToHostileEntities(world, pos);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, BeholderTileEntity blockEntity) {
        tick(world, pos, state, blockEntity);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, BeholderTileEntity blockEntity) {
        tick(world, pos, state, blockEntity);
    }

    // Method to set the active state
    public void setActiveState(BeholderBlock.State state) {
        this.activeState = state;
    }

    // Modified method to apply the glow effect based on the active state
    private void applyGlowEffectToHostileEntities(World world, BlockPos pos) {
        if (activeState == BeholderBlock.State.OFF) {
            return; // Do nothing if the active state is OFF
        }

        double radius = 0.0;
        if (activeState == BeholderBlock.State.LOW) {
            radius = 10.0;
        } else if (activeState == BeholderBlock.State.MEDIUM) {
            radius = 20.0;
        } else if (activeState == BeholderBlock.State.HIGH) {
            radius = 40.0;
        }

        Box searchArea = new Box(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius)); // Search area with a radius based on the active state
        List<Entity> entities = world.getEntitiesByClass(Entity.class, searchArea, entity -> entity instanceof HostileEntity);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                double distance = entity.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()); // Distance between the tile entity and the hostile entity
                if (distance <= radius * radius) {
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 20, 0));
                }
            }
        }
    }
}
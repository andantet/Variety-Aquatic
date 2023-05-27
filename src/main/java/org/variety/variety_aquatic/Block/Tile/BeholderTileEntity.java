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
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Block.Custom.BeholderBlock;
import org.variety.variety_aquatic.Block.ModTileEntity;
import org.variety.variety_aquatic.Util.NewConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class BeholderTileEntity extends BlockEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

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
        BeholderBlock.State activeState = getCachedState().get(BeholderBlock.CURRENT_STATE);
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

    private Map<UUID, Long> affectedEntities = new HashMap<>();

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
        System.out.println("Setting active state to: " + state.toString()); // Debugging code
        if (world != null) {
            world.setBlockState(pos, getCachedState().with(BeholderBlock.CURRENT_STATE, state), 3);
            markDirty();
        }
    }

    // Modified method to apply the glow effect based on the active state
    private void applyGlowEffectToHostileEntities(World world, BlockPos pos) {
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            MinecraftServer server = serverWorld.getServer();

            float meanTickTime = server.getTickTime();
            double tps = meanTickTime > 0 ? 1_000.0 / meanTickTime : 20.0;

            if (tps < NewConfig.beholder_tps) {
                return; // Disable the applyGlowEffectToHostileEntities if the TPS is below 15
            }
        }

        BeholderBlock.State activeState = getCachedState().get(BeholderBlock.CURRENT_STATE);
        double radius;

        if (activeState == BeholderBlock.State.OFF) {
            radius = 0.0;
        } else if (activeState == BeholderBlock.State.LOW) {
            radius = Math.round(NewConfig.beholder_max_range / 4);
        } else if (activeState == BeholderBlock.State.MEDIUM) {
            radius = Math.round(NewConfig.beholder_max_range / 2);
        } else if (activeState == BeholderBlock.State.HIGH) {
            radius = Math.round(NewConfig.beholder_max_range);
        } else {
            radius = 0.0;
        }


        Box searchArea = new Box(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
        List<Entity> entities = world.getEntitiesByClass(Entity.class, searchArea, entity -> entity instanceof HostileEntity);
        Vec3d blockPos = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                double distanceSquared = blockPos.squaredDistanceTo(livingEntity.getPos());
                if (distanceSquared <= radius * radius) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 20, 0));
                    affectedEntities.put(entity.getUuid(), world.getTime() + 20 * 20); // Store the entity UUID and effect expiry timestamp
                }
            }
        }

        if (!world.isClient()) { // Ensure we're on the server side
            ServerWorld serverWorld = (ServerWorld) world;
            // Remove the effect from entities that are no longer within the radius or if the effect has expired
            affectedEntities.entrySet().removeIf(entry -> {
                UUID entityId = entry.getKey();
                Entity affectedEntity = serverWorld.getEntity(entityId);
                if (affectedEntity instanceof LivingEntity) {
                    double distanceSquared = blockPos.squaredDistanceTo(affectedEntity.getPos());
                    if (distanceSquared > radius * radius || world.getTime() >= entry.getValue()) {
                        ((LivingEntity) affectedEntity).removeStatusEffect(StatusEffects.GLOWING);
                        return true; // Remove the entry from the map
                    }
                }
                return false;
            });
        }
    }
}
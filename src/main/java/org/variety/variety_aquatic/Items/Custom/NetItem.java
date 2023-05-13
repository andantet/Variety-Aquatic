package org.variety.variety_aquatic.Items.Custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;


public class NetItem extends Item {
    public NetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient && player.isSneaking()) {
            NbtCompound itemTag = stack.getOrCreateNbt();
            if (itemTag.contains("pickedEntity")) {
                // Spawn the stored entity
                NbtCompound entityTag = itemTag.getCompound("pickedEntity");
                EntityType<?> type = Registry.ENTITY_TYPE.get(Identifier.tryParse(entityTag.getString("id")));
                if (type != null) {
                    Entity entity = type.create(world);
                    if (entity != null) {
                        entity.readNbt(entityTag);
                        entity.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                        world.spawnEntity(entity);
                        itemTag.remove("pickedEntity");
                        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
                    }
                }
            } else {
                // Pick up an entity
                Entity targetedEntity = rayTraceEntity(player, 5.0);
                if (targetedEntity != null) {
                    NbtCompound entityTag = new NbtCompound();
                    targetedEntity.saveNbt(entityTag);
                    itemTag.put("pickedEntity", entityTag);
                    targetedEntity.remove(Entity.RemovalReason.DISCARDED);
                    return new TypedActionResult<>(ActionResult.SUCCESS, stack);
                }
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }


    private Entity rayTraceEntity(PlayerEntity player, double maxDistance) {
        Vec3d startVec = player.getCameraPosVec(1.0F);
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endVec = startVec.add(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);
        Box box = new Box(startVec, endVec);
        List<Entity> entities = player.world.getOtherEntities(player, box);
        Entity nearestEntity = null;
        double nearestDistanceSq = maxDistance * maxDistance;
        for (Entity entity : entities) {
            double distanceSq = player.getPos().squaredDistanceTo(entity.getPos());
            if (distanceSq < nearestDistanceSq) {
                nearestEntity = entity;
                nearestDistanceSq = distanceSq;
                // Early exit if the entity is close enough
                if (nearestDistanceSq < 1.0) {
                    break;
                }
            }
        }
        return nearestEntity;
    }


}

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
import org.variety.variety_aquatic.Entities.ModEntities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NetItem extends Item {
    public NetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
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

        Set<EntityType<?>> allowedTypes = new HashSet<>();
        allowedTypes.add(ModEntities.BETTA);
        allowedTypes.add(ModEntities.BARRELEE);
        allowedTypes.add(ModEntities.SEAANGLE);
        allowedTypes.add(ModEntities.CRAB);
        allowedTypes.add(ModEntities.ANGLERFISH);
        allowedTypes.add(ModEntities.SEAHORSE);
        allowedTypes.add(ModEntities.CLOWNFISH);
        allowedTypes.add(ModEntities.CUTTLEFISH);
        allowedTypes.add(ModEntities.FLASHLIGHTFISH);
        allowedTypes.add(ModEntities.HERMITCRAB);
        allowedTypes.add(ModEntities.JELLYFISH);
        allowedTypes.add(ModEntities.LIONFISH);
        allowedTypes.add(ModEntities.MOONJELLY);
        allowedTypes.add(ModEntities.OPAH);
        allowedTypes.add(ModEntities.PIRANHA);
        allowedTypes.add(ModEntities.YELLOWFIN);
        allowedTypes.add(ModEntities.VAMPIRESQUID);
        allowedTypes.add(ModEntities.TETRA);
        allowedTypes.add(ModEntities.SUNFISH);
        allowedTypes.add(EntityType.TADPOLE);
        allowedTypes.add(EntityType.TROPICAL_FISH);
        allowedTypes.add(EntityType.PUFFERFISH);
        allowedTypes.add(EntityType.SALMON);


        for (Entity entity : entities) {
            if (allowedTypes.contains(entity.getType())) {
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
        }

        return nearestEntity;
    }



}

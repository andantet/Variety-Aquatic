package org.variety.variety_aquatic.Block.Custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class GlowingBlock extends Block {

    public GlowingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient()) {
            applyGlowEffectToHostileEntities(world, pos);
        }
    }

    private void applyGlowEffectToHostileEntities(World world, BlockPos pos) {
        Box searchArea = new Box(pos.add(-20, -20, -20), pos.add(20, 20, 20));
        List<Entity> entities = world.getEntitiesByClass(Entity.class, searchArea, entity -> entity instanceof HostileEntity);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 20, 0));
            }
        }
    }
}

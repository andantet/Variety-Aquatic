package org.variety.variety_aquatic.Entities.custom.AI;

import net.minecraft.entity.ai.goal.DiveJumpingGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvents;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.variety.variety_aquatic.Entities.custom.YellowfinTunaEntity;

public class TunaJumpGoal extends DiveJumpingGoal {
    private static final int[] OFFSET_MULTIPLIERS = {0, 1, 4, 5, 6, 7};
    private final YellowfinTunaEntity yellowfin;
    private final int chance;
    private boolean inWater;

    public TunaJumpGoal(YellowfinTunaEntity yellowfin, int chance) {
        this.yellowfin = yellowfin;
        this.chance = toGoalTicks(chance);
    }

    public boolean canStart() {
        return yellowfin.getRandom().nextInt(chance) == 0 && checkWaterAndAirBlocks();
    }

    private boolean checkWaterAndAirBlocks() {
        Direction direction = yellowfin.getMovementDirection();
        int offsetX = direction.getOffsetX();
        int offsetZ = direction.getOffsetZ();
        BlockPos blockPos = yellowfin.getBlockPos();

        for (int multiplier : OFFSET_MULTIPLIERS) {
            BlockPos offsetPos = blockPos.add(offsetX * multiplier, 0, offsetZ * multiplier);
            if (!isWater(offsetPos) || !isAirAbove(offsetPos))
                return false;
        }

        return true;
    }

    private boolean isWater(BlockPos pos) {
        FluidState fluidState = yellowfin.world.getFluidState(pos);
        return fluidState.isIn(FluidTags.WATER) && !yellowfin.world.getBlockState(pos).getMaterial().blocksMovement();
    }

    private boolean isAirAbove(BlockPos pos) {
        BlockPos abovePos = pos.up();
        return yellowfin.world.getBlockState(abovePos).isAir() && yellowfin.world.getBlockState(abovePos.up()).isAir();
    }

    public boolean shouldContinue() {
        double velocityY = yellowfin.getVelocity().y;
        float pitch = yellowfin.getPitch();
        return velocityY * velocityY >= 0.03 || Math.abs(pitch) >= 10.0F || !yellowfin.isTouchingWater() || yellowfin.isOnGround();
    }

    public boolean canStop() {
        return false;
    }

    public void start() {
        Direction direction = yellowfin.getMovementDirection();
        double offsetX = direction.getOffsetX() * 0.6;
        double offsetZ = direction.getOffsetZ() * 0.6;
        yellowfin.setVelocity(yellowfin.getVelocity().add(offsetX, 0.7, offsetZ));
        yellowfin.getNavigation().stop();
    }

    public void stop() {
        yellowfin.setPitch(0.0F);
    }

    public void tick() {
        boolean wasInWater = inWater;
        inWater = yellowfin.world.getFluidState(yellowfin.getBlockPos()).isIn(FluidTags.WATER);

        if (inWater && !wasInWater)
            yellowfin.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0F, 1.0F);

        Vec3d velocity = yellowfin.getVelocity();
        double velocityY = velocity.y;

        if (velocityY * velocityY < 0.03 && yellowfin.getPitch() != 0.0F)
            yellowfin.setPitch(MathHelper.lerpAngleDegrees(yellowfin.getPitch(), 0.0F, 0.2F));
        else if (velocity.length() > 0.00001) {
            double horizontalLength = velocity.horizontalLength();
            double pitch = Math.atan2(-velocityY, horizontalLength) * 57.2957763671875;
            yellowfin.setPitch((float) pitch);
        }
    }
}


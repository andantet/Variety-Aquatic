package org.varietymods.Entity.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.varietymods.Entity.Custom.YellowfinTunaEntity;

public class TunaJump extends JumpGoal {
    private static final int[] STEPS_TO_CHECK = {0, 1, 4, 5, 6, 7};
    private final YellowfinTunaEntity tuna;
    private final int interval;
    private boolean breached;

    public TunaJump(YellowfinTunaEntity tuna, int interval) {
        this.tuna = tuna;
        this.interval = reducedTickDelay(interval);
    }

    public boolean canUse() {
        if (tuna.getRandom().nextInt(interval) != 0) {
            return false;
        }

        Direction direction = tuna.getMotionDirection();
        int dx = direction.getStepX();
        int dz = direction.getStepZ();
        BlockPos blockPos = tuna.blockPosition();

        for (int step : STEPS_TO_CHECK) {
            if (!waterAndSurfaceAreClear(blockPos, dx, dz, step)) {
                return false;
            }
        }

        return true;
    }

    private boolean waterAndSurfaceAreClear(BlockPos pos, int dx, int dz, int scale) {
        BlockPos waterPos = pos.offset(dx * scale, 0, dz * scale);
        BlockPos surfacePos1 = pos.offset(dx * scale, 1, dz * scale);
        BlockPos surfacePos2 = pos.offset(dx * scale, 2, dz * scale);

        FluidState fluidState = tuna.level.getFluidState(waterPos);
        return fluidState.is(FluidTags.WATER) && !tuna.level.getBlockState(waterPos).getMaterial().blocksMotion()
                && tuna.level.getBlockState(surfacePos1).isAir() && tuna.level.getBlockState(surfacePos2).isAir();
    }

    public boolean canContinueToUse() {
        double deltaY = tuna.getDeltaMovement().y;
        return deltaY * deltaY >= 0.03F || Math.abs(tuna.getXRot()) >= 10.0F || !tuna.isInWater() || tuna.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    public void start() {
        Direction direction = tuna.getMotionDirection();
        tuna.setDeltaMovement(tuna.getDeltaMovement().add(direction.getStepX() * 0.6D, 0.7D, direction.getStepZ() * 0.6D));
        tuna.getNavigation().stop();
    }

    public void stop() {
        tuna.setXRot(0.0F);
    }

    public void tick() {
        boolean previouslyBreached = breached;
        if (!previouslyBreached) {
            FluidState fluidState = tuna.level.getFluidState(tuna.blockPosition());
            breached = fluidState.is(FluidTags.WATER);
        }

        if (breached && !previouslyBreached) {
            tuna.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vec3 motion = tuna.getDeltaMovement();
        if (motion.y * motion.y < 0.03F && tuna.getXRot() != 0.0F) {
            tuna.setXRot(Mth.rotlerp(tuna.getXRot(), 0.0F, 0.2F));
        } else if (motion.lengthSqr() > 1.0E-5F) {
            double horizontalDistance = motion.horizontalDistance();
            double rotation = Math.atan2(-motion.y, horizontalDistance) * (180F / (float) Math.PI);
            tuna.setXRot((float) rotation);
        }
    }
}

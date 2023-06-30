package org.variety.variety_aquatic.Common.Entities.custom.AI;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.variety.variety_aquatic.Common.Entities.custom.LeviathanEntity;

import java.util.EnumSet;

public class SonicBoomAttackGoal extends Goal {
    private final LeviathanEntity mob; // Update MobEntity to WardenEntity
    private final int requiredAttacks;
    private final float sonicBoomRange;
    private final int sonicBoomDuration;
    private final float attackDamage;
    private int attackCounter;

    public SonicBoomAttackGoal(LeviathanEntity mob, int requiredAttacks, float sonicBoomRange, int sonicBoomDuration, float attackDamage) { // Update MobEntity to WardenEntity in constructor parameter
        this.mob = mob;
        this.requiredAttacks = requiredAttacks;
        this.sonicBoomRange = sonicBoomRange;
        this.sonicBoomDuration = sonicBoomDuration;
        this.attackDamage = attackDamage;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        // Add conditions specific to the Warden entity
        return mob.getTarget() != null && mob.isInsideWaterOrBubbleColumn(); // Assume the Warden has a method called isInSufficientDarkness
    }

    @Override
    public void start() {
        attackCounter = 0;
    }

    @Override
    public void tick() {
        if (mob.getTarget() == null || !mob.isInsideWaterOrBubbleColumn()) { // Check if Warden is in sufficient darkness
            return;
        }

        attackCounter++;

        if (attackCounter >= requiredAttacks) {
            Vec3d targetDirection = mob.getTarget().getPos().subtract(mob.getPos()).normalize();
            performSonicBoomAttack(targetDirection);
            attackCounter = 0;
        }
    }

    @Override
    public boolean shouldContinue() {
        return mob.getTarget() != null && mob.isInsideWaterOrBubbleColumn(); // Check if Warden is in sufficient darkness
    }

    private void performSonicBoomAttack(Vec3d targetDirection) {
        mob.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        Box sonicBoomEffectArea = new Box(mob.getPos().add(-sonicBoomRange, -sonicBoomRange, -sonicBoomRange), mob.getPos().add(sonicBoomRange, sonicBoomRange, sonicBoomRange));

        for (LivingEntity entity : mob.getEntityWorld().getEntitiesByClass(LivingEntity.class, sonicBoomEffectArea, entity -> entity instanceof LivingEntity)) {
            if (entity == mob.getTarget()) {
                continue;
            }
            Vec3d entityDirection = entity.getPos().subtract(mob.getPos()).normalize();
            double dotProduct = targetDirection.dotProduct(entityDirection);
            if (dotProduct > 0.9) {
                double distance = mob.distanceTo(entity);
                entity.addVelocity(targetDirection.x * (sonicBoomRange / distance), targetDirection.y * (sonicBoomRange / distance), targetDirection.z * (sonicBoomRange / distance));
                mob.getWorld().addParticle(ParticleTypes.EXPLOSION, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
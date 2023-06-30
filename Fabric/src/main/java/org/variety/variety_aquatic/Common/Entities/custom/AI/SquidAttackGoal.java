package org.variety.variety_aquatic.Common.Entities.custom.AI;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import org.variety.variety_aquatic.Common.Entities.custom.GiantsquidEntity;

import java.util.EnumSet;


public class SquidAttackGoal extends MeleeAttackGoal {
    private final GiantsquidEntity squid;
    private LivingEntity target;
    private int attackTime;
    private boolean holdingPlayer = false;

    public SquidAttackGoal(GiantsquidEntity squid, double speed, boolean pauseWhenMobIdle) {
        super(squid, speed, pauseWhenMobIdle);
        System.out.println("Checking if SquidAttackGoal can start");
        this.squid = squid;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public boolean canStart() {
        System.out.println("Checking if SquidAttackGoal can start");
        if (this.squid.isBaby() || holdingPlayer) {
            return false;
        } else {
            this.target = this.squid.getTarget();
            if (!(this.target instanceof PlayerEntity) && !(this.target instanceof AnimalEntity)) {
                return false;
            } else {
                double d = this.squid.squaredDistanceTo(this.target);
                return d >= 1.0D && d <= 24.0D && this.squid.getRandom().nextInt(10) == 0;
            }
        }
    }

    public void start() {
        super.start();
        this.attackTime = 90;
        this.holdingPlayer = true;

        // Set the player as a passenger of the squid
        if (this.target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.target;
            player.startRiding(this.squid, true);
        }
    }

    public void stop() {
        LivingEntity tempTarget = this.target;
        super.stop();
        System.out.println("Stopping SquidAttackGoal");

        this.target = null;
        this.holdingPlayer = false;

        // Release the player from riding the squid
        if (tempTarget instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) tempTarget;
            player.stopRiding();
        }
    }

    public void tick() {
        super.tick();
        System.out.println("SquidAttackGoal ticking with attackTime: " + this.attackTime);

        this.squid.getLookControl().lookAt(this.target, 30.0F, 30.0F);
        if (holdingPlayer) {
            holdPlayer();
        } else {
            if ((this.attackTime > 0 && this.attackTime <= 100) || this.squid.getRandom().nextInt(100) == 0) {
                this.squid.getWorld().addParticle(ParticleTypes.BUBBLE, this.target.getX(), this.target.getY() + this.target.getHeight() / 2.0F, this.target.getZ(), 0.0D, 0.0D, 0.0D);
            }
            if (this.attackTime > 0) {
                --this.attackTime;
                if (this.attackTime == 90) {
                    if (this.target instanceof PlayerEntity) {
                        this.target.startRiding(this.squid, true); // set dismountType to true
                    }
                } else if (this.attackTime == 0) {
                    this.squid.setTarget(null);
                    //this.target.damage(DamageSource.mob(this.squid), (float) this.squid.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
                    if (this.target instanceof PlayerEntity) {
                        this.target.stopRiding();
                    }
                }
            }
        }
    }

    private void holdPlayer() {
        // Move squid towards the bottom of the ocean
        System.out.println("Holding player");

        this.squid.setVelocity(this.squid.getVelocity().add(0, -0.2, 0));
        this.squid.move(MovementType.SELF, new Vec3d(0, -0.2, 0));

        // Prevent player from moving away
        if (this.target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.target;
            player.addVelocity(0, -0.1, 0);
            player.velocityDirty = true;
            player.fallDistance = 0;
        }

        // Damage player and apply poison effect
        //this.target.damage(DamageSource.mob(this.squid), (float) this.squid.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));

        // Check if the squid has reached the bottom of the ocean
        if (this.squid.getY() < 5) {
            // Release player and reset attack time
            releasePlayer();
            this.attackTime = 0;
            return;
        }

        // Check if attack time has elapsed
        if (this.attackTime == 0) {
            // Start attacking player
            this.attackTime = 100;
            this.squid.setTarget(this.target);
        } else if (this.attackTime <= 10) {
            // Apply damage to player every tick for the last 10 ticks
            //this.target.damage(DamageSource.mob(this.squid), (float) this.squid.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
        }
    }

    private void releasePlayer() {
        System.out.println("Releasing player");

        // Move squid towards the surface of the ocean
        this.squid.setVelocity(this.squid.getVelocity().add(0, 0.2, 0));
        this.squid.move(MovementType.SELF, new Vec3d(0, 0.2, 0));

        // Release player
        if (this.target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.target;
            player.stopRiding();
            player.addVelocity(0, 0.2, 0);
            player.velocityDirty = true;
        }
    }

}

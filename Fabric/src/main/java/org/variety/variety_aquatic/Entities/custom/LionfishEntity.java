package org.variety.variety_aquatic.Entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.variety.variety_aquatic.Util.NewConfig;


public class LionfishEntity extends VarietyFish {

    public LionfishEntity(EntityType<? extends LionfishEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, NewConfig.lionfish_health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NewConfig.lionfish_speed);
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && player.damage(this.getDamageSources().mobAttack(this), (float)(2))) {
            if (!this.isSilent()) {
                ((ServerPlayerEntity)player).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PUFFERFISH_STING, 0.0F));
            }
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0), this);
        }
    }
}
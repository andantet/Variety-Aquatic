package org.variety.variety_aquatic.Entities.custom;


import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Entities.ModEntities;
import org.variety.variety_aquatic.Items.ModItems;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class HermitcrabEntity extends AnimalEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    private static final Ingredient TAMING_INGREDIENT;

    public HermitcrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canSpawn(EntityType<HermitcrabEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return pos.getY() < world.getSeaLevel() + 4 && isLightLevelValidForNaturalSpawn(world, pos);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new AnimalMateGoal(this,0.75D));
        this.goalSelector.add(2, new TemptGoal(this, 0.75D,TAMING_INGREDIENT,false));
        this.goalSelector.add(3, new FollowParentGoal(this, 0.85D));
        this.goalSelector.add(4, new LookAtEntityGoal(this,PlayerEntity.class,6F));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 0.8f));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0, 100));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }


    public boolean isPushedByFluids() {
        return false;
    }

    public boolean canBreatheInWater() {
        return true;
    }

    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        HermitcrabEntity baby = ModEntities.HERMITCRAB.create(world);
        return baby;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.RAW_LIONFISH;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("HermitCrabWalk", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }



    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }





    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    static {
        TAMING_INGREDIENT = Ingredient.ofItems(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
    }
}

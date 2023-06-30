package org.variety.variety_aquatic.Common.Entities.custom;


import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.variety.variety_aquatic.Common.Entities.ModEntities;
import org.variety.variety_aquatic.Common.Items.ModItems;
import org.variety.variety_aquatic.Common.Sound.ModSound;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;


import java.util.Random;


public class HermitcrabEntity extends AnimalEntity implements GeoAnimatable {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private static final Ingredient TAMING_INGREDIENT;
    private boolean isHiding;

    private boolean isUnhiding;
    private boolean canMove=true;


    public HermitcrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        setPathfindingPenalty(PathNodeType.WATER, 0);
        getNavigation().setCanSwim(false);
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
        this.goalSelector.add(1, new AnimalMateGoal(this, 0.75D));
        this.goalSelector.add(2, new TemptGoal(this, 0.75D, TAMING_INGREDIENT, false));
        this.goalSelector.add(3, new FollowParentGoal(this, 0.85D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6F));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0, 100) {
            @Override
            public boolean canStart() {
                return canMove && super.canStart();
            }
        });
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 0.8f) {
            @Override
            public boolean canStart() {
                return canMove && super.canStart();
            }
        });
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

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        AnimationController controller = event.getController();

        if (isHiding) {
            event.getController().setAnimation(RawAnimation.begin().then("Hide", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (isUnhiding) {
            event.getController().setAnimation(RawAnimation.begin().then("Unhide", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }

    private int hidingTime;

    public void setHidingTime(int time) {
        this.hidingTime = time;
    }

    public int getHidingTime() {
        return hidingTime;
    }

    @Override
    public void tick() {
        super.tick();


        if (getHidingTime() > 0) {
            setHidingTime(getHidingTime() - 1);
            canMove = false;

            if (getHidingTime() == 0) {
                removeStatusEffect(StatusEffects.RESISTANCE);
                removeStatusEffect(StatusEffects.ABSORPTION);
                isUnhiding = true;
                canMove = false;
            }
        } else if (isUnhiding) {
            canMove = false;
        } else {
            canMove = true;
        }
    }



    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        // Check if the damage source is not from the player
        if (pDamageSource.getAttacker() instanceof PlayerEntity) {
            // Set the hiding animation

            // Set knockback resistance and armor
            addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
            addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 0, false, false));

            // Set a random hiding time between 5-10 seconds
            int hidingTime = 5 + new Random().nextInt(6);
            setHidingTime(hidingTime * 20);
            // Return the hiding sound
            isHiding = true;
            canMove = false;
        }
        return ModSound.CRAB_HURT;

    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }



    static {
        TAMING_INGREDIENT = Ingredient.ofItems(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
    }
}

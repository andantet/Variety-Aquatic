package org.variety.variety_aquatic.Items.Custom;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;


public class TrophyItem extends BlockItem implements GeoAnimatable {
  private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

  public TrophyItem(Block block, Settings settings) {
    super(block, settings);
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return factory;
  }

  @Override
  public double getTick(Object o) {
    return 0;
  }
}

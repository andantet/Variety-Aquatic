package org.variety.variety_aquatic.Items.Custom;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.ISyncable;

public class TrophyItem extends BlockItem implements IAnimatable, ISyncable {
  public AnimationFactory factory = new AnimationFactory(this);

  public TrophyItem(Block block, Settings settings) {
    super(block, settings);
  }

  @Override
  public void registerControllers(AnimationData animationData) {
  }

  @Override
  public AnimationFactory getFactory() {
    return factory;
  }

  @Override
  public void onAnimationSync(int id, int state) {
  }
}

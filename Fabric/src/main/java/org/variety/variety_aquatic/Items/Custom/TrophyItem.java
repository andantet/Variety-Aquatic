package org.variety.variety_aquatic.Items.Custom;

import net.minecraft.block.Block;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.BlockItem;
import org.variety.variety_aquatic.Variety_Aquatic;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TrophyItem extends BlockItem implements GeoItem {
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

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


    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final TrophyItemRenderer renderer = new TrophyItemRenderer(Variety_Aquatic.MOD_ID);

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }


    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }


}
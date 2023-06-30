package org.variety.variety_aquatic.Common.Items.Custom;

import net.minecraft.block.Block;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class TrophyItem extends BlockItem implements GeoItem {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer( this);

    private final String mobID;

    public TrophyItem(Block block, Settings settings, String itemName) {
        super(block, settings);
        this.mobID = itemName;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final TrophyItemRenderer renderer = new TrophyItemRenderer(mobID);

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }
    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }
}
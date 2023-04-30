package org.variety.varietyaquatic.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreative {
    public static final CreativeModeTab VARIETY_AQUATIC_TAB = new CreativeModeTab("variety_aquatic") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SHARK_EGG.get());
        }
    };
}


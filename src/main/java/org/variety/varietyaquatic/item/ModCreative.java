package org.variety.varietyaquatic.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.variety.varietyaquatic.VarietyAquatic;
@Mod.EventBusSubscriber(modid = VarietyAquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModCreative {

    public static CreativeModeTab VarietyAquaticTab;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        VarietyAquaticTab = event.registerCreativeModeTab(new ResourceLocation(VarietyAquatic.MODID, "variety_aquatic"),
                builder -> builder.icon(() -> new ItemStack(ModItems.COOKED_TUNA.get()))
                        .title(Component.translatable("varietyaquaic.tab")));
    }
}



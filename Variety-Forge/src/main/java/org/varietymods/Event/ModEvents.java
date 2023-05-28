package org.varietymods.Event;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.varietymods.Entity.Custom.YellowfinTunaEntity;
import org.varietymods.Entity.ModEntityTypes;
import org.varietymods.Varietyaquatic;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Varietyaquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.YELLOWFIN.get(), YellowfinTunaEntity.setAttributes());
        }

    }
}
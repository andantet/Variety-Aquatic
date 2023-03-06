package org.variety.varietyaquatic.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import org.variety.varietyaquatic.entity.custom.SharkEntity;
import org.variety.varietyaquatic.entity.custom.SunfishEntity;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = VarietyAquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.SUNFISH.get(), SunfishEntity.setAttributes());
            event.put(ModEntityTypes.SHARK.get(), SharkEntity.setAttributes());

        }

    }
}

package org.variety.varietyaquatic.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.variety.varietyaquatic.VarietyAquatic;
import org.variety.varietyaquatic.entity.ModEntityTypes;
import org.variety.varietyaquatic.entity.client.TunaRenderer;
import org.variety.varietyaquatic.entity.custom.*;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = VarietyAquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.SUNFISH.get(), SunfishEntity.setAttributes());
            event.put(ModEntityTypes.SHARK.get(), SharkEntity.setAttributes());
            event.put(ModEntityTypes.ANGLERFISH.get(), AnglerfishEntity.setAttributes());
            event.put(ModEntityTypes.BETTAFISH.get(), BettaEntity.setAttributes());
            event.put(ModEntityTypes.CLOWNFISH.get(), ClownfishEntity.setAttributes());
            event.put(ModEntityTypes.CUTTLEFISH.get(), CuttlefishEntity.setAttributes());
            event.put(ModEntityTypes.YELLOWFIN.get(), YellowFinTuna.setAttributes());
            event.put(ModEntityTypes.TETRA.get(), TetraEntity.setAttributes());
            event.put(ModEntityTypes.HERMITCRAB.get(), HermitcrabEntity.setAttributes());






        }

    }
}

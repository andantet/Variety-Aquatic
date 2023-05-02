package org.variety.varietyaquatic.ModSound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.variety.varietyaquatic.VarietyAquatic;

public class ModSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VarietyAquatic.MODID);


    public static RegistryObject<SoundEvent> WHALE_AMBIENT = registerSoundEvent("whale_ambient");

    public static RegistryObject<SoundEvent> DEEP_GROWL = registerSoundEvent("deep_growl");

    public static RegistryObject<SoundEvent> WHALE_HURT = registerSoundEvent("whale_hurt");

    public static RegistryObject<SoundEvent> GIANTSQUID_AMBIENT = registerSoundEvent("giantsquid_ambient");

    public static RegistryObject<SoundEvent> GIANTSQUID_HURT = registerSoundEvent("giantsquid_hurt");

    public static RegistryObject<SoundEvent> SPERMWHALE_DEATH = registerSoundEvent("spermwhale_death");

    public static RegistryObject<SoundEvent> CRAB_RAVE = registerSoundEvent("crab_rave");




    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(VarietyAquatic.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
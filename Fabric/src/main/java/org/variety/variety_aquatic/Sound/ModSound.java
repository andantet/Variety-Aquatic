package org.variety.variety_aquatic.Common.Sound;


import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModSound {

    public static SoundEvent WHALE_AMBIENT = registerSoundEvent("whale_ambient");
    public static SoundEvent DEEP_GROWL = registerSoundEvent("deep_growl");
    public static SoundEvent WHALE_DEATH = registerSoundEvent("whale_death");
    public static SoundEvent WHALE_HURT = registerSoundEvent("whale_hurt");
    public static SoundEvent GIANTSQUID_AMBIENT = registerSoundEvent("giantsquid_ambient");
    public static SoundEvent GIANTSQUID_HURT = registerSoundEvent("giantsquid_hurt");
    public static SoundEvent CRAB_HURT = registerSoundEvent("crab_hurt");
    public static SoundEvent BEHOLDER_CLICK = registerSoundEvent("beholder_click");


    public static SoundEvent SPERMWHALE_DEATH = registerSoundEvent("spermwhale_death");

    public static SoundEvent CRAB_RAVE = registerSoundEvent("crab_rave");




    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Variety_Aquatic.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
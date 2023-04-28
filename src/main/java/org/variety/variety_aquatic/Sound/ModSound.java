package org.variety.variety_aquatic.Sound;


import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.variety.variety_aquatic.Variety_Aquatic;

public class ModSound {

    public static SoundEvent WHALE_AMBIENT = registerSound("whale_ambient");
    public static SoundEvent DEEP_GROWL = registerSound("deep_growl");
    public static SoundEvent WHALE_DEATH = registerSound("whale_death");
    public static SoundEvent WHALE_HURT = registerSound("whale_hurt");
    public static SoundEvent GIANTSQUID_AMBIENT = registerSound("giantsquid_ambient");
    public static SoundEvent GIANTSQUID_HURT = registerSound("giantsquid_hurt");
    public static SoundEvent SPERMWHALE_DEATH = registerSound("spermwhale_death");






    // actual registration of all the custom SoundEvents
    static SoundEvent registerSound(String id) {
        SoundEvent sound = new SoundEvent(new Identifier(Variety_Aquatic.MOD_ID, id));
        return Registry.register(Registry.SOUND_EVENT, new Identifier(Variety_Aquatic.MOD_ID, id), sound);
    }

    // called in the ModInitializer implementing class
    // to initialize the ExampleModSounds class
    public static void initializeSounds() {
        Variety_Aquatic.LOGGER.info("Registering " + Variety_Aquatic.MOD_ID + " Sounds");
    }
}
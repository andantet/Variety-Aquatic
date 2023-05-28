package org.variety.variety_aquatic.Util;

import net.minecraft.client.world.ClientWorld;

public class TimeUtil {
    public static boolean isNight(ClientWorld world) {
        long timeOfDay = world.getTimeOfDay();
        return timeOfDay >= 13000 && timeOfDay <= 23000;
    }
}
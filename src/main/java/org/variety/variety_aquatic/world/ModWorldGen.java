package org.variety.variety_aquatic.world;

import org.variety.variety_aquatic.world.Feature.FeatureAnemone;

public class ModWorldGen {
    public static void generateWorldGen() {

        ModEntitySpawns.addEntitySpawn();
        FeatureHandler.register();

    }
}
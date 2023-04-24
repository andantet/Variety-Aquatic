package org.variety.variety_aquatic.Util;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.FishEntity;

import java.io.IOException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AqConfig {
    private AqConfig() {
    }

    public static final AqConfig INSTANCE = new AqConfig();

    private boolean loaded;
    private Properties aqprop = new Properties();


    public boolean getBooleanProperty(String key) {
        if (!loaded) load();
        return Boolean.parseBoolean(aqprop.getProperty(key));
    }

    public String getStringProperty(String key) {
        if (!loaded) load();
        return aqprop.getProperty(key);
    }

    public int getNumberProperty(String key) {
        if (!loaded) load();
        return Integer.parseInt(aqprop.getProperty(key));
    }

    public double getDoubleProperty(String key) {
        if (!loaded) load();
        return Double.parseDouble(aqprop.getProperty(key));
    }


    private final File file = new File("./config/TexMods/Aquatic.config");

    private void load() {
        loaded = true;
        try {
            Files.createDirectories(Paths.get("./config/TexMods/"));

            if (file.exists() && file.length() != 0) {
                var reader = new FileReader(file);
                aqprop.load(reader);
                reader.close();

                double configversion = getDoubleProperty("config.version");
                if (configversion != 1.3) {
                    file.delete();
                    file.createNewFile();
                    var writer = new FileOutputStream(file);
                    aqprop.setProperty("config.version", "1.3");
                    aqprop.setProperty("jellyfish.spawnweight", "50");
                    aqprop.setProperty("hermitcrab.spawnweight", "50");
                    aqprop.setProperty("sunfish.health", "30.0");
                    aqprop.setProperty("sunfish.speed", "2.5");
                    aqprop.setProperty("sunfish.spawnweight", "50");
                    aqprop.setProperty("shark.health", "30.0");
                    aqprop.setProperty("shark.attackfish", "true");
                    aqprop.setProperty("shark.spawnweight", "50");
                    aqprop.setProperty("shark.speed", "2.5");
                    aqprop.setProperty("shark.attack_speed", "4.0");
                    aqprop.setProperty("shark.follow", "32.0");
                    aqprop.setProperty("shark.damage", "8.0");
                    aqprop.setProperty("shark.knockback", "0.1");
                    aqprop.setProperty("whaleshark.spawnweight", "50");
                    aqprop.setProperty("whaleshark.health", "30.0");
                    aqprop.setProperty("whaleshark.attackfish", "false");
                    aqprop.setProperty("whaleshark.spawnweight", "50");
                    aqprop.setProperty("whaleshark.speed", "2.0");
                    aqprop.setProperty("whaleshark.attack_speed", "4.0");
                    aqprop.setProperty("whaleshark.follow", "32.0");
                    aqprop.setProperty("whaleshark.damage", "8.0");
                    aqprop.setProperty("whaleshark.knockback", "0.1");
                    aqprop.setProperty("moonjelly.spawnweight", "50");
                    aqprop.setProperty("bluefin.health", "15.0");
                    aqprop.setProperty("bluefin.speed", "2.0");
                    aqprop.setProperty("bluefin.spawnweight", "50");
                    aqprop.setProperty("cuttlefish.health", "15.0");
                    aqprop.setProperty("cuttlefish.speed", "2.0");
                    aqprop.setProperty("cuttlefish.spawnweight", "50");
                    aqprop.setProperty("opah.health", "15.0");
                    aqprop.setProperty("opah.speed", "2.0");
                    aqprop.setProperty("opah.spawnweight", "50");
                    aqprop.setProperty("lionfish.health", "15.0");
                    aqprop.setProperty("lionfish.speed", "2.0");
                    aqprop.setProperty("lionfish.spawnweight", "50");
                    aqprop.setProperty("clownfish.health", "15.0");
                    aqprop.setProperty("clownfish.speed", "2.0");
                    aqprop.setProperty("clownfish.spawnweight", "50");
                    aqprop.setProperty("spottedstingray.health", "15.0");
                    aqprop.setProperty("spottedstingray.speed", "2.0");
                    aqprop.setProperty("spottedstingray.spawnweight", "50");

                    aqprop.store(writer, "Configuration file for aquatic from Aqmods");
                    writer.close();
                }
            } else {
                var writer = new FileOutputStream(file);
                file.createNewFile();
                aqprop.setProperty("config.version", "1.3");
                aqprop.setProperty("jellyfish.spawnweight", "50");
                aqprop.setProperty("hermitcrab.spawnweight", "50");
                aqprop.setProperty("sunfish.health", "30.0");
                aqprop.setProperty("sunfish.speed", "2.5");
                aqprop.setProperty("sunfish.spawnweight", "50");
                aqprop.setProperty("shark.health", "30.0");
                aqprop.setProperty("shark.attackfish", "true");
                aqprop.setProperty("shark.spawnweight", "50");
                aqprop.setProperty("shark.speed", "2.5");
                aqprop.setProperty("shark.attack_speed", "4.0");
                aqprop.setProperty("shark.follow", "32.0");
                aqprop.setProperty("shark.damage", "8.0");
                aqprop.setProperty("shark.knockback", "0.1");
                aqprop.setProperty("whaleshark.spawnweight", "50");
                aqprop.setProperty("whaleshark.health", "30.0");
                aqprop.setProperty("whaleshark.attackfish", "false");
                aqprop.setProperty("whaleshark.spawnweight", "50");
                aqprop.setProperty("whaleshark.speed", "2.0");
                aqprop.setProperty("whaleshark.attack_speed", "4.0");
                aqprop.setProperty("whaleshark.follow", "32.0");
                aqprop.setProperty("whaleshark.damage", "8.0");
                aqprop.setProperty("whaleshark.knockback", "0.1");
                aqprop.setProperty("moonjelly.spawnweight", "50");
                aqprop.setProperty("bluefin.health", "15.0");
                aqprop.setProperty("bluefin.speed", "2.0");
                aqprop.setProperty("bluefin.spawnweight", "50");
                aqprop.setProperty("cuttlefish.health", "15.0");
                aqprop.setProperty("cuttlefish.speed", "2.0");
                aqprop.setProperty("cuttlefish.spawnweight", "50");
                aqprop.setProperty("opah.health", "15.0");
                aqprop.setProperty("opah.speed", "2.0");
                aqprop.setProperty("opah.spawnweight", "50");
                aqprop.setProperty("lionfish.health", "15.0");
                aqprop.setProperty("lionfish.speed", "2.0");
                aqprop.setProperty("lionfish.spawnweight", "50");
                aqprop.setProperty("clownfish.health", "15.0");
                aqprop.setProperty("clownfish.speed", "2.0");
                aqprop.setProperty("clownfish.spawnweight", "50");
                aqprop.setProperty("spottedstingray.health", "15.0");
                aqprop.setProperty("spottedstingray.speed", "2.0");
                aqprop.setProperty("spottedstingray.spawnweight", "50");
                aqprop.store(writer, "Configuration file for aquatic from Aqmods");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



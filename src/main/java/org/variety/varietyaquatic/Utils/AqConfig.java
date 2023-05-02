package org.variety.varietyaquatic.Utils;

import java.io.*;
import java.util.Properties;

public class AqConfig {
    private AqConfig() {}

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

    private File file = new File("./config/TexMods/Variety-Aquatic.config");

    private void load() {
        loaded = true;
        try {
            new File("./config/TexMods").mkdir();

            if(file.exists()) {
                var reader = new FileReader(file);
                aqprop.load(reader);
                reader.close();
            } else {
                var writer = new FileOutputStream(file);
                file.createNewFile();
                aqprop.setProperty("config.version","1");
                aqprop.setProperty("shark.health","30.0");
                aqprop.setProperty("shark.speed","2.0");
                aqprop.setProperty("shark.attack_speed","4.0");
                aqprop.setProperty("shark.follow","32.0");
                aqprop.setProperty("shark.damage","8.0");
                aqprop.setProperty("shark.knockback","0.1");
                aqprop.store(writer, "Configuration file for Variety Savanna mod");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
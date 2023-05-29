package org.variety.variety_aquatic.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;
import org.variety.variety_aquatic.Variety_Aquatic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

public class BiomeFogConfigLoader {
    public static final String CONFIG_FILE_NAME = "biome_fog.json";
    public static final String CONFIG_FOLDER_NAME = "config";
    public static final Path CONFIG_FILE_PATH = Path.of(MessageFormat.format("./{0}/{1}", CONFIG_FOLDER_NAME, CONFIG_FILE_NAME));

    public static final BiomeFogConfig DEFAULT_CONFIG = new BiomeFogConfig();
    public static BiomeFogConfig CONFIG;

    public static BiomeFogConfig load() {
        if (!Files.exists(CONFIG_FILE_PATH)) {
            CONFIG = createConfigurationFile();
            return CONFIG;
        }

        try (InputStream stream = Files.newInputStream(CONFIG_FILE_PATH)) {
            Gson gson = createGson();
            CONFIG = gson.fromJson(new JsonReader(new BufferedReader(new InputStreamReader(stream))), BiomeFogConfig.class);

            Variety_Aquatic.LOGGER.info("Loaded Biome Fog configuration, located at: {}", CONFIG_FILE_PATH);

            return CONFIG;
        } catch (IOException e) {
            Variety_Aquatic.LOGGER.warn("Unable to read Biome Fog configuration file, located at {}. Loading default configuration. Check if the formatting is correct. See stack trace below:", CONFIG_FILE_PATH);
            e.printStackTrace();

            // Worst case scenario, return the default configuration
            CONFIG = DEFAULT_CONFIG;
            return DEFAULT_CONFIG;
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH.toString())) {
            Gson gson = createGson();
            String json = gson.toJson(CONFIG);
            writer.write(json);
        } catch (IOException e) {
            Variety_Aquatic.LOGGER.warn("Unable to save Biome Fog configuration file, located at {}. See stack trace below:", CONFIG_FILE_PATH);
            e.printStackTrace();
        }
    }

    private static @NotNull Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.setPrettyPrinting().create();
    }

    private static BiomeFogConfig createConfigurationFile() {
        if (!new File(CONFIG_FOLDER_NAME).exists()) {
            Variety_Aquatic.LOGGER.warn("Unable to create Biome Fog configuration file at {}. Configuration folder (/config) doesn't exist. Loading default configuration.", CONFIG_FILE_PATH);

            // Worst case scenario, return the default configuration
            return BiomeFogConfigLoader.DEFAULT_CONFIG;
        }

        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH.toString())) {
            Gson gson = createGson();
            String json = gson.toJson(DEFAULT_CONFIG);
            writer.write(json);

            Variety_Aquatic.LOGGER.info("Created Biome Fog configuration file, located at {}", CONFIG_FILE_PATH);

            return DEFAULT_CONFIG;
        } catch (IOException e) {
            Variety_Aquatic.LOGGER.warn("Unable to create Biome Fog configuration file at {}. Loading default configuration. See stack trace below:", CONFIG_FILE_PATH);
            e.printStackTrace();

            // Worst case scenario, return the default configuration
            return BiomeFogConfigLoader.DEFAULT_CONFIG;
        }
    }
}

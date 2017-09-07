package com.shepherdjerred.riotbase;

import com.shepherdjerred.riotbase.metrics.MetricsLite;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class RiotBase extends JavaPlugin {

    /**
     * Start plugin metrics
     */
    protected void startMetrics() {
        try {
            new MetricsLite(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a configuration from the filesystem
     *
     * @param path The path to the configuration file
     * @return A configuration object
     */
    protected Configuration loadConfig(String path) {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File(path));
            return config;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Copies a file from the jar to the plugin's data folder
     *
     * @param file The InputStream to copy from
     * @param dest The location to copy it to
     */
    protected void copyFile(InputStream file, String dest) {
        File outputFile = new File(dest);
        outputFile.getParentFile().mkdirs();
        if (!outputFile.exists()) {
            try {
                Files.copy(file, Paths.get(dest));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

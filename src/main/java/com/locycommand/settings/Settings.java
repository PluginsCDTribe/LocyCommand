package com.locycommand.settings;

import org.bukkit.configuration.file.FileConfiguration;


public class Settings {
    public static FileConfiguration config;
    public static FileConfiguration getEntry() {
        return config;
    }
}

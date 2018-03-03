package com.locycommand;

import org.bukkit.plugin.java.JavaPlugin;

public class LocyCommand extends JavaPlugin {
    public static LocyCommand instance;
    @Override
    public void onEnable() {
        instance = this;
    }
    @Override
    public void onDisable() {

    }
    public static LocyCommand getInstance() {
        return instance;
    }
}

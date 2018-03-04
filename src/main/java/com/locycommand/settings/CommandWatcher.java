package com.locycommand.settings;

import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class CommandWatcher extends BukkitRunnable {
    @Override
    public void run() {
        try {
            Settings.getEntry().save(new File(".//plugins//LocyCommand//commands.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

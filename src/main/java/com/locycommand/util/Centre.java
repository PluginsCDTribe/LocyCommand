package com.locycommand.util;

import com.locycommand.LocyCommand;
import com.locycommand.settings.Settings;
import org.bukkit.Bukkit;

public class Centre {
    public static boolean isRegistered(String commandName) {
        return Bukkit.getPluginCommand(commandName) != null;
    }

    public static Cmd getCmd(String cmd) {
        if (Settings.getEntry().getString(cmd + ".usage", "null").equals("null")) {
            return null;
        }
        return new Cmd(cmd);
    }

    public static Cmd newCommand(String cmdName) {
        if (isRegistered(cmdName)) {
            return null;
        }
        CommandInvoker.registerCommand(cmdName);
        if (Bukkit.getPluginCommand(cmdName) == null) {
            return null;
        }
        Bukkit.getPluginCommand(cmdName).setExecutor(LocyCommand.base);
        return new Cmd(cmdName);
    }
}

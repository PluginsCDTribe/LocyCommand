package com.locycommand.util;

import com.locycommand.LocyCommand;
import org.bukkit.Bukkit;

public class Centre {
    public static boolean isRegistered(String commandName) {
        return Bukkit.getPluginCommand(commandName) != null;
    }
    public static Cmd getCmd(String cmd) {
        return new Cmd(cmd);
    }
    public static Cmd newCommand(String cmdName) {
        if (isRegistered(cmdName)) {
            return null;
        }
        CommandInvoker.registerCommand(cmdName);
        Bukkit.getPluginCommand(cmdName).setExecutor(LocyCommand.base);
        return new Cmd(cmdName);
    }
}

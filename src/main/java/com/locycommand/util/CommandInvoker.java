package com.locycommand.util;

import com.locycommand.LocyCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInvoker {
    public static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];;
    public static boolean registerCommand(String cmdName) {
        try {
            Object craftServer = Class.forName("org.bukkit.craftbukkit." + version + ".CraftServer").cast(Bukkit.getServer());
            Field mapField = craftServer.getClass().getField("commandMap");
            mapField.setAccessible(true);
            Object commandMap = mapField.get(craftServer);
            Constructor pluginCommandCon = PluginCommand.class.getConstructor(new Class[]{String.class, Plugin.class});
            pluginCommandCon.setAccessible(true);
            Object newCommand = pluginCommandCon.newInstance(cmdName, (org.bukkit.plugin.Plugin)LocyCommand.getInstance());
            commandMap.getClass().getMethod("register", new Class[]{String.class, Command.class}).invoke(commandMap, new Object[]{"locyitem", newCommand});
            mapField.set(craftServer, commandMap);
            return true;
        } catch (Exception exc) {
            LocyCommand.getInstance().getLogger().info("插件不支持你服务器的版本");
            LocyCommand.getInstance().getServer().getPluginManager().disablePlugin(LocyCommand.getInstance());
            return false;
        }
    }
}

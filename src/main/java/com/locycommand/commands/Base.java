package com.locycommand.commands;

import com.locycommand.events.BeforeCommandUseEvent;
import com.locycommand.events.CommandInterruptLayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Base implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Bukkit.getPluginManager().callEvent(new BeforeCommandUseEvent((Player) sender, cmd.getName(), args));
            Bukkit.getPluginManager().callEvent(new CommandInterruptLayer((Player) sender, cmd.getName(), args));
        }
        return false;
    }
}

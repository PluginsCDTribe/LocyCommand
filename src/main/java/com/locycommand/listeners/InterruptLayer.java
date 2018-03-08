package com.locycommand.listeners;

import com.locycommand.LocyCommand;
import com.locycommand.events.CommandInterruptLayer;
import com.locycommand.events.ThenCall;
import com.locycommand.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InterruptLayer implements Listener {
    @EventHandler
    public void interrupt(CommandInterruptLayer call) {
        Cmd cmd = call.getCmd();
        try {
            for (Flag flag : cmd.getFlags()) {
                ArgsPapi.replaceAll(flag.serialize().split("\\*", 2)[1], call.getArgs());
            }
        } catch (Exception exc) {
            call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', cmd.getUsage()));
            return;
        }
        for (Flag flag : cmd.getFlags()) {
            if (flag.getHead().equals(Obj.hasPermission)) {
                if (!call.getPlayer().hasPermission(flag.getArgs()[0])) {
                    call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', LocyCommand.langConfig.getString("NoPermission", "§e你没有使用这个指令的权限.")));
                    return;
                }
            } else if (flag.getHead().equals(Obj.hasItem)) {
                int id = Integer.valueOf(flag.getArgs()[0]);
                boolean hasItem = false;
                for (ItemStack item : call.getPlayer().getInventory()) {
                    if (item != null && item.getType() != Material.AIR) {
                        if (item.getTypeId() == id) {
                            hasItem = true;
                            break;
                        }
                    }
                }
                if (hasItem == false) {
                    call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', LocyCommand.langConfig.getString("NoItem", "§e你没有足够的物品来使用这个指令.")));
                    return;
                }
            }
        }
        Bukkit.getPluginManager().callEvent(new ThenCall(call.getPlayer(), call.getCommand(), call.getArgs()));
    }
}

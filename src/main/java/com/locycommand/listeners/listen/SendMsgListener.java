package com.locycommand.listeners.listen;

import com.locycommand.LocyCommand;
import com.locycommand.events.ThenCall;
import com.locycommand.settings.CommandWatcher;
import com.locycommand.settings.Settings;
import com.locycommand.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SendMsgListener implements Listener {
    boolean before = false;
    @EventHandler
    public void onCall(ThenCall call) {
        Cmd cmd = call.getCmd();
        try {
            for (Flag flag : cmd.getFlags()) {
                if (flag.getHead().equals(Obj.sendMessage)) {
                    String msg = PAPIInvoker.doInvoke(call.getPlayer(), flag.getArgs()[0]);
                    msg = msg.replace("%player%", call.getPlayer().getName());
                    msg = ArgsPapi.replaceAll(msg, call.getArgs());
                    call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        } catch (Exception exc) {
            call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', cmd.getUsage()));
        }
    }
    @EventHandler
    public void register(PlayerJoinEvent e) {
        if (before == false) {
            for (String keys : Settings.getEntry().getKeys(false)) {
                CommandInvoker.registerCommand(keys);
                Bukkit.getPluginCommand(keys).setExecutor(LocyCommand.base);
            }
            before = true;
        }
    }
}

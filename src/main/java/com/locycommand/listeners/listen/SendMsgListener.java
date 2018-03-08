package com.locycommand.listeners.listen;

import com.locycommand.LocyCommand;
import com.locycommand.events.OptionCall;
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
    public void onCall(OptionCall call) {
        if (call.getObj().equals(Obj.sendMessage)) {
            String msg = PAPIInvoker.doInvoke(call.getPlayer(), call.getArgs()[0]);
            msg = msg.replace("%player%", call.getPlayer().getName());
            msg = ArgsPapi.replaceAll(msg, call.getCommandArgs());
            call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
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

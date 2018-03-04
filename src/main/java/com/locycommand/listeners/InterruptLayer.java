package com.locycommand.listeners;

import com.locycommand.events.CommandInterruptLayer;
import com.locycommand.events.ThenCall;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InterruptLayer implements Listener {
    @EventHandler
    public void interrupt(CommandInterruptLayer e) {
        //do sth.
        Bukkit.getPluginManager().callEvent(new ThenCall(e.getPlayer(), e.getCommand(), e.getArgs()));
    }
}

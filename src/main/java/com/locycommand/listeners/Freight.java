package com.locycommand.listeners;

import com.locycommand.LocyCommand;
import com.locycommand.events.AfterCommandUseEvent;
import com.locycommand.events.CommandUseEvent;
import com.locycommand.events.OptionCall;
import com.locycommand.events.ThenCall;
import com.locycommand.util.Cmd;
import com.locycommand.util.Flag;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Freight implements Listener {
    @EventHandler
    public void onCall(ThenCall call) {
        Cmd command = call.getCmd();
		Bukkit.getPluginManager().callEvent(new CommandUseEvent(call.getPlayer(), call.getCommand(), call.getArgs()));
        Thread callThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Flag flag : command.getFlags()) {
                    Bukkit.getScheduler().runTask(LocyCommand.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getPluginManager().callEvent(new OptionCall(call.getPlayer(), flag.getHead(), flag.getArgs(), call.getArgs()));
                        }
                    });
                }
            }
        });
        callThread.start();
        Bukkit.getPluginManager().callEvent(new AfterCommandUseEvent(call.getPlayer(), call.getCommand(), call.getArgs()));
    }
}

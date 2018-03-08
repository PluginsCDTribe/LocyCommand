package com.locycommand.listeners;

import com.locycommand.LocyCommand;
import com.locycommand.events.AfterCommandUseEvent;
import com.locycommand.events.CommandUseEvent;
import com.locycommand.events.OptionCall;
import com.locycommand.events.ThenCall;
import com.locycommand.util.Cmd;
import com.locycommand.util.Flag;
import com.locycommand.util.Obj;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Freight implements Listener {
    List<String> notDoneYet = new ArrayList<>();
    @EventHandler
    public void onCall(ThenCall call) {
        for (String s : notDoneYet) {
            if (s.equalsIgnoreCase(call.getPlayer().getName())) {
                call.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', LocyCommand.langConfig.getString("NotDone", "&e指令未完成.")));
                return;
            }
        }
        notDoneYet.add(call.getPlayer().getName());
        Cmd command = call.getCmd();
		Bukkit.getPluginManager().callEvent(new CommandUseEvent(call.getPlayer(), call.getCommand(), call.getArgs()));
        Thread callThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Flag flag : command.getFlags()) {
                    if (flag.getHead().equals(Obj.delay)) {
                        try {
                            Thread.sleep(Integer.valueOf(flag.getArgs()[0]) * 1000);
                        } catch (Exception exc) {
                            call.getPlayer().sendMessage("§c指令出错.请联系管理员.");
                            removeNotDone(call.getPlayer());
                            return;
                        }
                    }
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
        removeNotDone(call.getPlayer());
    }
    public void removeNotDone(Player user) {
        for (int i = 0;i < notDoneYet.size();i++) {
            if (notDoneYet.get(i).equalsIgnoreCase(user.getName())) {
                notDoneYet.remove(i);
            }
        }
    }
}

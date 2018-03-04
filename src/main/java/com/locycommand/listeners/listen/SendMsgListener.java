package com.locycommand.listeners.listen;

import com.locycommand.events.ThenCall;
import com.locycommand.util.*;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SendMsgListener implements Listener {
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

        }
    }
}

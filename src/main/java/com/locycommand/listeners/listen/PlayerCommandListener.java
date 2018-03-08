package com.locycommand.listeners.listen;

import com.locycommand.events.OptionCall;
import com.locycommand.util.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class PlayerCommandListener implements Listener {
    @EventHandler
    public void onCall(OptionCall call) {
        if (call.getObj().equals(Obj.playerCmd)) {
            String msg = PAPIInvoker.doInvoke(call.getPlayer(), call.getArgs()[0]);
            msg = msg.replace("%player%", call.getPlayer().getName());
            msg = ArgsPapi.replaceAll(msg, call.getCommandArgs());
            call.getPlayer().chat(msg);
        }
    }
}

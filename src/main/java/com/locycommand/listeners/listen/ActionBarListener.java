package com.locycommand.listeners.listen;

import com.locycommand.events.OptionCall;
import com.locycommand.settings.Usage;
import com.locycommand.util.ActionBar;
import com.locycommand.util.ArgsPapi;
import com.locycommand.util.Obj;
import com.locycommand.util.PAPIInvoker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ActionBarListener implements Listener {
	@EventHandler
	public void onCall(OptionCall call) {
		if (call.getObj().equals(Obj.actionBar) && Usage.useActionBar) {
			String msg = PAPIInvoker.doInvoke(call.getPlayer(), call.getArgs()[0]);
			msg = msg.replace("%player%", call.getPlayer().getName());
			msg = ArgsPapi.replaceAll(msg, call.getCommandArgs());
			ActionBar.sendActionBar(call.getPlayer(), msg);
		}
	}
}

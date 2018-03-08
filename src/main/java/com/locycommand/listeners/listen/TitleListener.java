package com.locycommand.listeners.listen;

import com.locycommand.events.OptionCall;
import com.locycommand.settings.Usage;
import com.locycommand.util.ArgsPapi;
import com.locycommand.util.Obj;
import com.locycommand.util.PAPIInvoker;
import com.locycommand.util.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TitleListener implements Listener {
	@EventHandler
	public void onCall(OptionCall call) {
		if (call.getObj().equals(Obj.title) && Usage.useTitle) {
			String args1 = PAPIInvoker.doInvoke(call.getPlayer(), call.getArgs()[0]);
			String args2 = PAPIInvoker.doInvoke(call.getPlayer(), call.getArgs()[1]);
			args1 = args1.replace("%player%", call.getPlayer().getName());
			args1 = ArgsPapi.replaceAll(args1, call.getCommandArgs());
			args2 = args2.replace("%player%", call.getPlayer().getName());
			args2 = ArgsPapi.replaceAll(args2, call.getCommandArgs());
			Title.sendTitle(call.getPlayer(), Integer.valueOf(call.getArgs()[2]), Integer.valueOf(call.getArgs()[3]), Integer.valueOf(call.getArgs()[4]), args1, args2);
		}
	}
}

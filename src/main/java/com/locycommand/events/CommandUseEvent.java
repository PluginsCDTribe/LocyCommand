package com.locycommand.events;

import com.locycommand.util.Centre;
import com.locycommand.util.Cmd;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CommandUseEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	Player target;
	String command;
	String args[];

	public CommandUseEvent(Player target, String command, String args[]) {
		this.target = target;
		this.command = command;
		this.args = args;
	}

	public String[] getArgs() {
		return this.args;
	}

	public Player getPlayer() {
		return this.target;
	}

	public String getCommand() {
		return this.command;
	}

	public Cmd getCmd() {
		return Centre.getCmd(this.command);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}

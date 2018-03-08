package com.locycommand.events;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OptionCall extends Event {
    private static final HandlerList handlers = new HandlerList();
    Player target;
    String obj;
    String args[];
    String commandArgs[];

    public OptionCall(Player target, String obj, String args[], String commandArgs[]) {
        this.target = target;
        this.obj = obj;
        this.args = args;
        this.commandArgs = commandArgs;
    }

    public String[] getCommandArgs() {
        return this.commandArgs;
    }

    public String[] getArgs() {
        return this.args;
    }

    public Player getPlayer() {
        return this.target;
    }

    public String getObj() {
        return this.obj;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

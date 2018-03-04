package com.locycommand.util;

import com.locycommand.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cmd {
    private String commandName;
    private List<Flag> flagList = new ArrayList<>();
    protected Cmd(String commandName) {
        this.commandName = commandName;
        for (String s : Settings.getEntry().getStringList(this.commandName+".flags")) {
            flagList.add(Flag.deSerialize(s));
        }
        if (flagList.isEmpty()) {
            Settings.getEntry().set(this.commandName+".usage", "using");
        }
    }
    public List<Flag> getFlags() {
        return this.flagList;
    }
    public String getCommand() {
        return this.commandName;
    }
    public void save() {
        List<String> format = new ArrayList<>();
        for (Flag flag : this.flagList) {
            format.add(flag.serialize());
        }
        Settings.getEntry().set(this.commandName+".flags", format);
        try {
            Settings.getEntry().save(new File(".//plugins//LocyCommand//commands.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addFlag(Flag flag) {
        flagList.add(flag);
        save();
    }
    public List<String[]> getArgs(String flagHead) {
        List<String[]> args = new ArrayList<>();
        for (Flag flag : getFlags()) {
            if (flag.getHead().equals(flagHead)) {
                args.add(flag.getArgs());
            }
        }
        return args;
    }
    public void removeFlag(String flagHead) {
        List<Flag> newList = new ArrayList<>();
        for (Flag flag : getFlags()) {
            if (!flag.getHead().equals(flagHead)) {
                newList.add(flag);
            }
        }
        save();
    }
    public void cleanFlags() {
        this.flagList.clear();
        save();
    }
}

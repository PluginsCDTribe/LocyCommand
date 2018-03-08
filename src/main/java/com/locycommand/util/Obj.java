package com.locycommand.util;

import java.util.ArrayList;
import java.util.List;

public class Obj {
    public static List<String> list = new ArrayList<>();
    public static final String sendMessage = "SENDMESSAGE";
    public static final String hasPermission = "PERMISSION";
    public static final String consoleCmd = "CONSOLECMD";
    public static final String playerCmd = "PLAYERCMD";
    public static final String hasItem = "HASITEM";
    public static final String delay = "DELAY";
    public static final String cost = "COSTMONEY";
    public static final String title = "TITLE";
    public static final String actionBar = "ACTIONBAR";

    public static void addOne(String s) {
        list.add(s);
    }
}

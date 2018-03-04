package com.locycommand.util;

import java.util.ArrayList;
import java.util.List;

public class Obj {
    public static List<String> list = new ArrayList<>();
    public static final String sendMessage = "SENDMESSAGE";
    public static void addOne(String s) {
        list.add(s);
    }
}

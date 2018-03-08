package com.locycommand.util;

public class ArgsPapi {
    public static String replaceAll(String input, String[] args) {
        while (input.indexOf("%args:") != -1) {
            int loc = input.charAt(input.indexOf("%args:") + 6) - '0';
            input = input.replace("%args:" + loc + "%", args[loc - 1]);
        }
        return input;
    }
}

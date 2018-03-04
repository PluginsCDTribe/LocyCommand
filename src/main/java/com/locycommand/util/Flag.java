package com.locycommand.util;
import java.util.Arrays;
import java.util.List;

public class Flag {
    private String head;
    private String args[];
    public Flag(String head, String[] args) {
        this.head = head;
        this.args = args;
    }
    public String serialize() {
        StringBuilder builder = new StringBuilder();
        builder.append(head+"*");
        for (String s : args) {
            builder.append(s+"~");
        }
        String toString = builder.toString();
        if (toString.endsWith("~")) {
            toString = toString.substring(0, toString.length()-1);
        }
        return toString;
    }
    public static Flag deSerialize(String info) {
        return new Flag(info.split("\\*", 2)[0], info.split("\\*")[1].split("~"));
    }
    public String getHead() {
        return this.head;
    }
    public String[] getArgs() {
        return this.args;
    }
    public List<String> getArgsAsList() {
        return Arrays.asList(this.args);
    }
}

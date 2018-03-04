package com.locycommand.commands;

import com.locycommand.util.Centre;
import com.locycommand.util.Cmd;
import com.locycommand.util.Flag;
import com.locycommand.util.Obj;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.isOp()) {
            return false;
        }
        if (args.length <= 0) {
            sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd create [指令名] ——创建一个空的指令.");
            sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd clean [指令名] ——清理一个指令内所有内容.");
            sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd removeItem [指令名] [内容ID] ——清理一个指令内某条内容.");
            sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd itemList 查看所有内容");
            return false;
        }
        if (args[0].equalsIgnoreCase("create")) {
            if (args.length == 2) {
                String info = args[1];
                while (info.startsWith("/")) {
                    info = info.substring(1);
                }
                if (Centre.newCommand(info) == null) {
                    sender.sendMessage("§7[§bLocyCommand§7]该指令已经存在于这个服务器了.");
                } else {
                    sender.sendMessage("§7[§bLocyCommand§7]创建指令"+info+"成功，现在你可以给指令添加内容了。使用 /"+info+" 执行指令.");
                }
            } else {
                sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd create [指令名] ——创建一个空的指令.");
            }
        } else if (args[0].equalsIgnoreCase("clean")) {
            if (args.length == 2) {
                String info = args[1];
                while (info.startsWith("/")) {
                    info = info.substring(1);
                }
                Cmd cmd = Centre.getCmd(info);
                if (cmd == null) {
                    sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
                    return false;
                }
                cmd.cleanFlags();
                sender.sendMessage("§7[§bLocyCommand§7]成功清除了指令内所有内容.");
            } else {
                sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd clean [指令名] ——清理一个指令内所有内容.");
            }
        } else if (args[0].equalsIgnoreCase("removeItem")) {
            if (args.length == 3) {
                String info = args[1];
                while (info.startsWith("/")) {
                    info = info.substring(1);
                }
                Cmd cmd = Centre.getCmd(info);
                if (cmd == null) {
                    sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
                    return false;
                }
                if (!Obj.list.contains(args[2])) {
                    sender.sendMessage("§7[§bLocyCommand§7]ID不存在.");
                    return false;
                }
                cmd.removeFlag(args[2]);
                sender.sendMessage("§7[§bLocyCommand§7]成功清除了这条内容.");
            } else {
                sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd removeItem [指令名] [内容ID] ——清理一个指令内某条内容.");
                sender.sendMessage("§7使用/lcmd itemList 查看所有内容");
            }
        } else if (args[0].equalsIgnoreCase("itemList")) {
            sender.sendMessage(Obj.list.toString());
        } else if (args[0].equalsIgnoreCase("sendmsg")) {
            if (args.length == 3) {
                String info = args[1];
                while (info.startsWith("/")) {
                    info = info.substring(1);
                }
                Cmd cmd = Centre.getCmd(info);
                if (cmd == null) {
                    sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
                    return false;
                }
                Flag flag = new Flag(Obj.sendMessage, new String[]{args[2].replace("_", " ")});
                cmd.addFlag(flag);
                sender.sendMessage("§7[§bLocyCommand§7]成功添加了呐.");
            } else {
                sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd sendmsg [指令名] [发送的信息] ——执行指令时发送信息.");
                sender.sendMessage("§7空格请用_代替，可以使用PAPI变量，以及%player%玩家名字变量，以及 %args:x% 变量(x必须替换为第几个参数,如/xxx a,a就是第一个参数)");
            }
        } else if (args[0].equalsIgnoreCase("usage")) {
            if (args.length == 3) {
                String info = args[1];
                while (info.startsWith("/")) {
                    info = info.substring(1);
                }
                Cmd cmd = Centre.getCmd(info);
                if (cmd == null) {
                    sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
                    return false;
                }
                cmd.setUsage(args[2].replace("_", " "));
                sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
            } else {
                sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd usage [指令名] [发送的信息] ——执行指令时参数不够时发送的信息");
                sender.sendMessage("§7空格请用_代替");
            }
        }
        return false;
    }
}

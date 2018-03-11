package com.locycommand.commands;

import com.locycommand.LocyCommand;
import com.locycommand.settings.Usage;
import com.locycommand.util.Centre;
import com.locycommand.util.Cmd;
import com.locycommand.util.Flag;
import com.locycommand.util.Obj;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

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
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd usage [指令名] [发送的信息] ——执行指令时参数不够时发送的信息");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd sendmsg [指令名] [发送的信息] ——执行指令时发送信息.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd ccommand [指令名] [发送的信息] ——执行指令时执行控制台指令");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd pcommand [指令名] [发送的信息] ——执行指令时执行为玩家指令");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd haspermission [指令名] [权限] ——执行指令时所需的权限");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd hasItem [指令名] [物品id] ——执行指令时所需的物品");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd jobs [指令名] ——查看该指令所有需要完成的工作.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd delay [指令名] [序号] [秒数] ——在该指令的指定序号的工作后面停顿秒数.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd actionbar [指令名] [信息] ——发送小字ActionBar.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd cost [指令名] [金钱] ——设置指令扣钱.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd title [指令名] [大标题] [小标题] [淡入时间] [停留时间] [淡出时间] ——发送全屏Title.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd isInt [指令名] [第几个参数] ——判断指令中第几个参数必须是个数字.");
			sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd isPlayer [指令名] [第几个参数] ——判断指令中第几个参数必须是个玩家.");
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
					sender.sendMessage("§7[§bLocyCommand§7]创建指令" + info + "成功，现在你可以给指令添加内容了。使用 /" + info + " 执行指令.");
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
		} else if (args[0].equalsIgnoreCase("ccommand")) {
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
				while (args[2].startsWith("/")) {
					args[2] = args[2].substring(1);
				}
				Flag flag = new Flag(Obj.consoleCmd, new String[]{args[2].replace("_", " ")});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd ccommand [指令名] [发送的信息] ——执行指令时执行控制台指令");
				sender.sendMessage("§7空格请用_代替，可以使用PAPI变量，以及%player%玩家名字变量，以及 %args:x% 变量(x必须替换为第几个参数,如/xxx a,a就是第一个参数)");
			}
		} else if (args[0].equalsIgnoreCase("pcommand")) {
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
				if (!args[2].startsWith("/")) {
					args[2] = "/" + args[2];
				}
				Flag flag = new Flag(Obj.playerCmd, new String[]{args[2].replace("_", " ")});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd pcommand [指令名] [发送的信息] ——执行指令时执行为玩家指令");
				sender.sendMessage("§7空格请用_代替，可以使用PAPI变量，以及%player%玩家名字变量，以及 %args:x% 变量(x必须替换为第几个参数,如/xxx a,a就是第一个参数)");
			}
		} else if (args[0].equalsIgnoreCase("haspermission")) {
			if (args.length == 3) {
				args[2] = "LocyCommand." + args[2];
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				Flag flag = new Flag(Obj.hasPermission, new String[]{args[2]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]现在需要 " + args[2] + " 这个权限才能执行指令啦.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd haspermission [指令名] [权限] ——执行指令时所需的权限");
				sender.sendMessage("§7如/lcmd haspermission 指令 permission ——即为需要&c&lLocyCommand.permission 的权限.");
			}
		} else if (args[0].equalsIgnoreCase("hasItem")) {
			if (args.length == 3) {
				if (!isInt(args[2])) {
					sender.sendMessage("§7[§bLocyCommand§7]" + args[2] + " 不是数字.");
					return false;
				}
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				Flag flag = new Flag(Obj.hasItem, new String[]{args[2]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]现在需要 " + Material.getMaterial(Integer.valueOf(args[2])).toString() + " 这个物品才能执行指令啦.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd hasItem [指令名] [物品id] ——执行指令时所需的物品");
			}
		} else if (args[0].equalsIgnoreCase("delay")) {
			if (args.length == 4) {
				if (!isInt(args[2]) || !isInt(args[3])) {
					sender.sendMessage("§7[§bLocyCommand§7]不是数字.");
					return false;
				}
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				Flag flag = new Flag(Obj.delay, new String[]{args[3]});
				List<Flag> flagList = cmd.getFlags();
				flagList.add(Integer.valueOf(args[2]), flag);
				cmd.setFlags(flagList);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd delay [指令名] [序号] [秒数] ——在该指令的指定序号的工作后面停顿秒数.");
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd jobs [指令名] ——查看该指令所有需要完成的工作.");
			}
		} else if (args[0].equalsIgnoreCase("jobs")) {
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
				for (int i = 0; i < cmd.getFlags().size(); i++) {
					int z = i;
					z++;
					sender.sendMessage("§7任务序号: §b" + z);
					sender.sendMessage("§7执行内容: §b" + cmd.getFlags().get(i).getHead());
					sender.sendMessage("§7执行属性: §b" + Arrays.asList(cmd.getFlags().get(i).getArgs()).toString());
				}
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd jobs [指令名] ——查看该指令所有需要完成的工作.");
			}
		} else if (args[0].equalsIgnoreCase("cost")) {
			if (args.length == 3) {
				if (LocyCommand.isUseValut() == false) {
					sender.sendMessage("§c§l你的服务器没有 Vault 经济插件，无法使用扣钱功能.");
					return false;
				}
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				if (!isInt(args[2])) {
					sender.sendMessage("§7[§bLocyCommand§7]不是数字.");
					return false;
				}
				Flag flag = new Flag(Obj.cost, new String[]{args[2]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd cost [指令名] [金钱] ——设置指令扣钱.");
			}
		} else if (args[0].equalsIgnoreCase("title")) {
			if (args.length == 7) {
				if (Usage.useTitle == false) {
					sender.sendMessage("§7[§bLocyCommand§7]你的服务器版本并不支持全屏Title.");
					return false;
				}
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				if (!isInt(args[4]) || !isInt(args[5]) || !isInt(args[6])) {
					sender.sendMessage("§7[§bLocyCommand§7]不是数字.");
					return false;
				}
				Flag flag = new Flag(Obj.title, new String[]{args[2].replace("_", " "), args[3].replace("_", " "), args[4], args[5], args[6]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd title [指令名] [大标题] [小标题] [淡入时间] [停留时间] [淡出时间] ——发送全屏Title.");
				sender.sendMessage("§7空格请用_代替，标题可以使用PAPI变量，以及%player%玩家名字变量，以及 %args:x% 变量(x必须替换为第几个参数,如/xxx a,a就是第一个参数)");
			}
		} else if (args[0].equalsIgnoreCase("actionbar")) {
			if (args.length == 3) {
				if (Usage.useActionBar == false) {
					sender.sendMessage("§7[§bLocyCommand§7]你的服务器版本并不支持小字ActionBar.");
					return false;
				}
				String info = args[1];
				while (info.startsWith("/")) {
					info = info.substring(1);
				}
				Cmd cmd = Centre.getCmd(info);
				if (cmd == null) {
					sender.sendMessage("§7[§bLocyCommand§7]该指令不存在.");
					return false;
				}
				Flag flag = new Flag(Obj.actionBar, new String[]{args[2].replace("_", " ")});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd actionbar [指令名] [信息] ——发送小字ActionBar.");
				sender.sendMessage("§7空格请用_代替，可以使用PAPI变量，以及%player%玩家名字变量，以及 %args:x% 变量(x必须替换为第几个参数,如/xxx a,a就是第一个参数)");
			}
		} else if (args[0].equalsIgnoreCase("isInt")) {
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
				if (!isInt(args[2])) {
					sender.sendMessage(ChatColor.RED+args[2]+"不是数字.");
                    return false;
				}
				Flag flag = new Flag(Obj.isInt, new String[]{args[2]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd isInt [指令名] [第几个参数] ——判断指令中第几个参数必须是个数字.");
			}
		} else if (args[0].equalsIgnoreCase("isPlayer")) {
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
				Flag flag = new Flag(Obj.isPlayer, new String[]{args[2]});
				cmd.addFlag(flag);
				sender.sendMessage("§7[§bLocyCommand§7]成功设置了呐.");
			} else {
				sender.sendMessage("§7[§bLocyCommand§7]使用/lcmd isPlayer [指令名] [第几个参数] ——判断指令中第几个参数必须是个玩家.");
			}
		}
		return false;
	}

	public boolean isInt(String isInt) {
		try {
			Integer.valueOf(isInt);
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
}

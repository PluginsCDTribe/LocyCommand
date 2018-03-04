package com.locycommand;

import com.locycommand.commands.Base;
import com.locycommand.commands.Commands;
import com.locycommand.listeners.InterruptLayer;
import com.locycommand.listeners.listen.SendMsgListener;
import com.locycommand.settings.CommandWatcher;
import com.locycommand.settings.Settings;
import com.locycommand.util.CommandInvoker;
import com.locycommand.util.Obj;
import com.locycommand.util.PAPIInvoker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocyCommand extends JavaPlugin {
    public static LocyCommand instance;
    public static FileConfiguration commands;
    List<String> msgList = new ArrayList<>();
    public static Base base = new Base();
    @Override
    public void onEnable() {
        addOnEnableMsg("===========[LocyCommand]===========");
        addOnEnableMsg("欢迎使用自定义指令插件 LocyCommand");
        addOnEnableMsg("作者: LocyDragon QQ2424441676");
        addOnEnableMsg("论坛id: MagicLocyDragon");
        addOnEnableMsg("请诸位听我一句诗: ");
        addOnEnableMsg("苟利国家生死以,岂因祸福避趋之");
        addOnEnableMsg("当你看到这个消息，你的寿命已经-1s了.");
        addOnEnableMsg("§bPluginsCDTribe-Group");
        for (String msg : msgList) {
            Bukkit.getConsoleSender().sendMessage(msg);
        }
        commands = registerNewConfiguration("commands", null);
        Settings.config = commands;
        new CommandWatcher().runTaskTimer(this, 0, 20*5);
        for (String keys : Settings.getEntry().getKeys(false)) {
            CommandInvoker.registerCommand(keys);
            Bukkit.getPluginCommand(keys).setExecutor(base);
        }
        Bukkit.getPluginCommand("lcmd").setExecutor(new Commands());
        Obj.addOne("SENDMESSAGE");
        Bukkit.getPluginManager().registerEvents(new InterruptLayer(), this);
        Bukkit.getPluginManager().registerEvents(new SendMsgListener(), this);
        if (PAPIInvoker.hasPAPI()) {
            getLogger().info("已经和papi挂钩.");
        } else {
            getLogger().info("服务器未安装papi.");
        }
        instance = this;
    }
    @Override
    public void onDisable() {}
    public static LocyCommand getInstance() {
        return instance;
    }
    public void addOnEnableMsg(String msg) { msgList.add(msg); }
    private FileConfiguration registerNewConfiguration(String configName, HashMap<String,Object> defaults) {
        File f = new File(".//plugins//LocyCommand//"+configName+".yml");
        FileConfiguration config = null;
        if (!f.exists()) {
            try {
                f.createNewFile();
                config = YamlConfiguration.loadConfiguration(f);
                if (!(defaults == null)) {
                    for (String key : defaults.keySet()) {
                        config.set(key, defaults.get(key));
                    }
                }
                config.save(f);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            config = YamlConfiguration.loadConfiguration(f);
            if (!(defaults == null)) {
                for (Map.Entry<String, Object> entry : defaults.entrySet()) {
                    if (config.get(entry.getKey(), new NullAbleClass()).getClass().equals(NullAbleClass.class) || config.get(entry.getKey(), new NullAbleClass()).getClass() == NullAbleClass.class) {
                        config.set(entry.getKey(), entry.getValue());
                    }
                }
            }
            try {
                config.save(f);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
        return config;
    }
}

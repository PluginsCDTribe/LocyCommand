package com.locycommand;

import com.locycommand.commands.Base;
import com.locycommand.commands.Commands;
import com.locycommand.listeners.Freight;
import com.locycommand.listeners.InterruptLayer;
import com.locycommand.listeners.listen.ConsoleCommandListener;
import com.locycommand.listeners.listen.PlayerCommandListener;
import com.locycommand.listeners.listen.SendMsgListener;
import com.locycommand.settings.Settings;
import com.locycommand.settings.Usage;
import com.locycommand.util.BukkitVersionManager;
import com.locycommand.util.Obj;
import com.locycommand.util.PAPIInvoker;
import com.locycommand.util.VaultManager;
import net.milkbowl.vault.Vault;
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
    public static FileConfiguration langConfig;
    List<String> msgList = new ArrayList<>();
    public static Base base = new Base();
    private static boolean useValut = false;
    public static BukkitVersionManager vm;
    @Override
    public void onEnable() {
        vm = new BukkitVersionManager();
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
        Bukkit.getPluginCommand("lcmd").setExecutor(new Commands());
        HashMap<String,Object> lang = new HashMap<>();
        lang.put("NoPermission", "&e你没有使用这个指令的权限.");
        lang.put("NoItem", "&e你没有足够的物品来使用这个指令.");
        lang.put("NotDone", "&e指令未完成.");
        lang.put("NoSuchMoney", "&e你的金币不足.");
        langConfig = registerNewConfiguration("lang", lang);
        Obj.addOne("SENDMESSAGE");
        Obj.addOne("PERMISSION");
        Obj.addOne("CONSOLECMD");
        Obj.addOne("PLAYERCMD");
        Obj.addOne("HASITEM");
        Obj.addOne("DELAY");
        Bukkit.getPluginManager().registerEvents(new InterruptLayer(), this);
        Bukkit.getPluginManager().registerEvents(new Freight(), this);
        Bukkit.getPluginManager().registerEvents(new SendMsgListener(), this);
        Bukkit.getPluginManager().registerEvents(new ConsoleCommandListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandListener(), this);
        if (!(vm.version.startsWith("v1_4") || vm.version.startsWith("v1_5") || vm.version.startsWith("v1_6") || vm.version.startsWith("v1_7"))) {
            Usage.useTitle = true;
            getLogger().info("启用title功能.");
            if (!vm.version.startsWith("v1_8")) {
                Usage.useActionBar = true;
                getLogger().info("启用actionbar功能.");
            }
        }
        if (PAPIInvoker.hasPAPI()) {
            getLogger().info("已经和papi挂钩.");
        } else {
            getLogger().info("服务器未安装papi.");
        }
        if (VaultManager.loadLibrary()) {
            useValut = true;
            getLogger().info("已经和vault挂钩.");
        } else {
            useValut = false;
            getLogger().info("找不到vault.无法使用关于经济的功能.");
        }
        instance = this;
    }
    public static boolean isUseValut() {
        return useValut;
    }
    @Override
    public void onDisable() {
    }

    public static LocyCommand getInstance() {
        return instance;
    }

    public void addOnEnableMsg(String msg) {
        msgList.add(msg);
    }

    private FileConfiguration registerNewConfiguration(String configName, HashMap<String, Object> defaults) {
        File f = new File(".//plugins//LocyCommand//" + configName + ".yml");
        f.getParentFile().mkdirs();
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

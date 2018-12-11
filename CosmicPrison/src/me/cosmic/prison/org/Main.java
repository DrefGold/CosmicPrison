package me.cosmic.prison.org;

import me.cosmic.prison.org.Listeners.BreakEvent;
import me.cosmic.prison.org.Listeners.JoinEvent;
import me.cosmic.prison.org.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public class Main extends JavaPlugin {

    private static Main plugin;

    private Utils utils;

    private FileManager userdata;

    public void onEnable(){
        plugin = this;
        System.out.println("CosmicPrison enabled");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerEvents();
        userdata = new FileManager(getDataFolder(), "userdata.yml");
        registerIstances();
    }
    private void registerIstances(){
        utils = new Utils(this);
    }
    public void registerEvents(){
            PluginManager pm = Bukkit.getPluginManager();

            pm.registerEvents(new BreakEvent(this), this);
            pm.registerEvents(new JoinEvent(this), this);
        }
    public Utils getUtils() {
        return utils;
    }
    public FileManager getData(){
        return userdata;
    }

    public static Main getInstance(){
        return plugin;
    }
}

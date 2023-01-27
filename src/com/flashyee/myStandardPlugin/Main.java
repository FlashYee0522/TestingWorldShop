package com.flashyee.myStandardPlugin;

import com.flashyee.myStandardPlugin.command.commandListener;
import com.flashyee.myStandardPlugin.data.Load;
import com.flashyee.myStandardPlugin.data.Save;
import com.flashyee.myStandardPlugin.event.eventListener;
import com.flashyee.myStandardPlugin.thread.AutoSave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
        AutoSave autoSave = new AutoSave();

        @Override
        public void onEnable(){
            Bukkit.getPluginCommand("shop").setExecutor(new commandListener());
            Bukkit.getPluginCommand("money").setExecutor(new commandListener());
            Bukkit.getPluginCommand("sell").setExecutor(new commandListener());
            Bukkit.getPluginManager().registerEvents(new eventListener(),this);
            Bukkit.getConsoleSender().sendMessage("MyStandardPlugin_V1.0 is working!!");

            if(new Load().getPlayerData()){
                Bukkit.getConsoleSender().sendMessage("Player_data is successfully loaded!");
            }else{
                Bukkit.getConsoleSender().sendMessage("Can't find Player_data");
            }

            if(new Load().getShopData()){
                Bukkit.getConsoleSender().sendMessage("Shop_data is successfully loaded!");
            }else{
                Bukkit.getConsoleSender().sendMessage("Can't find Shop_data");
            }

            autoSave.start();
        }

        @Override
        public void onDisable(){

            if(new Save().savePlayerData()){
                Bukkit.getConsoleSender().sendMessage("Player_data is successfully saved!");
            }else{
                Bukkit.getConsoleSender().sendMessage("Failed to save Player_data");
            }

            if(new Save().saveShopData()){
                Bukkit.getConsoleSender().sendMessage("Shop_data is successfully saved!");
            }else{
                Bukkit.getConsoleSender().sendMessage("Failed to save Shop_data");
            }

            getLogger().info("MyStandardPlugin_V1.0 is closed...");
        }

    }


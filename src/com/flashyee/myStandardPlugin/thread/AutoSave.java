package com.flashyee.myStandardPlugin.thread;

import com.flashyee.myStandardPlugin.data.Save;
import org.bukkit.Bukkit;

public class AutoSave extends Thread{

    @Override
    public void run(){

        while(1 == 1){
            try {

                Thread.sleep(300000);

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

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }
}

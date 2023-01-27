package com.flashyee.myStandardPlugin.data;

import com.flashyee.myStandardPlugin.player.PlayerInfo;
import com.flashyee.myStandardPlugin.shop.Commodity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.FileReader;

public class Load {

    public boolean getPlayerData(){
        String[] data = getData("Player_data");

        if(data == null){
            return false;
        }

        int data_amount = (data.length - 2) / 3;
        for(int i=1;i<=data_amount;i++){
            OnlineData.player_info.put( readStringAfterColon(data[(i*3)-1]) , new PlayerInfo( readStringAfterColon(data[(i*3)-1]) , readIntAfterColon(data[i*3]) ) );
        }
        return true;
    }

    public boolean getShopData(){
        String[] data = getData("Shop_data");

        if(data == null){
            return false;
        }

        int data_amount = (data.length - 2) / 5;

        ItemStack is;
        for(int i=1;i<=data_amount;i++){
            is = new ItemStack( Material.getMaterial( readStringAfterColon( data[(i*5)-1] ) ), readIntAfterColon(data[i*5]) );
            OnlineData.shop_info.set( (i-1) , new Commodity( readStringAfterColon( data[(i*5)-3] ) , readIntAfterColon( data[(i*5)-2] ) , is ) );
        }
        return true;
    }

    public String[] getData(String file_name){
        try {
            FileReader fr = new FileReader("data/"+file_name+".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] out =  new String[readIntInMiddle(br.readLine())];
            int i = 0;
            while( (line = br.readLine()) != null){
                out[i] = line;
                i++;
            }
            return out;
        }catch (Exception e){
            return null;
        }

    }

    public int readIntInMiddle(String str){
        String number = str.substring( str.indexOf('(')+1 , str.indexOf(')'));
        return Integer.valueOf(number);
    }

    public String readStringAfterColon(String str){
        return str.substring(str.indexOf(':')+1);
    }

    public int readIntAfterColon(String str){
        return Integer.valueOf( readStringAfterColon(str) );
    }
}

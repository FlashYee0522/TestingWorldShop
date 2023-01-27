package com.flashyee.myStandardPlugin.data;

import com.flashyee.myStandardPlugin.player.PlayerInfo;
import com.flashyee.myStandardPlugin.shop.Commodity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class Save {
    /*
    PlayerData

    (5) <---長度
    (1) <---序號
    player: <---玩家名稱
    money:  <---玩家持有金錢
    (0) <---文件尾端

    -----------------------------
    ShopData

    (?) <---長度
    (1) <---序號
    seller_name: <---賣家名稱
    price: <---價格
    item: <---物品種類
    amount: <---數量
    (0) <---文件尾端

     */
    public boolean savePlayerData(){


            int size = ( OnlineData.player_info.size() * 3) + 2;
            String[] data = new String[size];

            data[0] = "("+size+")";

            int number = 1;
            int line = 1;
            PlayerInfo playerInfo;
            for(Map.Entry<String, PlayerInfo> entry:OnlineData.player_info.entrySet()){
                playerInfo = entry.getValue();
                data[line] = "("+number+")";
                number++;
                line++;
                data[line] = "player:"+playerInfo.getPlayer_name();
                line++;
                data[line] = "money:"+playerInfo.getMoney();
                line++;
            }

            data[size-1] = "(0)";

            return outPut("Player_data",data);
    }

    public boolean saveShopData(){

        int size = ( OnlineData.shop_info.size() * 5) + 2;
        String[] data = new String[size];

        data[0] = "("+size+")";

        int number = 1;
        int line = 1;
        Commodity commodity;

        for(int i=0;i<OnlineData.shop_info.size();i++){
            commodity = OnlineData.shop_info.get(i);
            data[line] = "("+number+")";
            number++;
            line++;
            data[line] = "seller_name:"+commodity.getSeller_name();
            line++;
            data[line] = "price:"+commodity.getPrice();
            line++;
            data[line] = "item:"+commodity.getItemStack().getType();
            line++;
            data[line] = "amount:"+commodity.getItemStack().getAmount();
            line++;
        }

        data[size-1] = "(0)";

        return outPut("Shop_data",data);
    }

    public boolean outPut(String file_name,String[] output){
        try{

            File dir = new File("data");
            dir.mkdirs();
            FileWriter fw = new FileWriter("data/"+file_name+".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("("+output.length+")");
            bw.newLine();
            for(int i=0;i<output.length;i++){
                bw.write(output[i]);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

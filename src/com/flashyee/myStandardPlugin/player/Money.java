package com.flashyee.myStandardPlugin.player;

import com.flashyee.myStandardPlugin.data.OnlineData;

public class Money {

    public boolean pay(String payer,String taker,int value){

        PlayerInfo payer_playerInfo = OnlineData.player_info.get(payer);
        PlayerInfo taker_playerInfo = OnlineData.player_info.get(taker);

        if(payer_playerInfo.getMoney() < value){
            return false;
        }else {
            payer_playerInfo.setMoney(payer_playerInfo.getMoney() - value);
            taker_playerInfo.setMoney(taker_playerInfo.getMoney() + value);
            OnlineData.player_info.put(payer_playerInfo.getPlayer_name(),payer_playerInfo);
            OnlineData.player_info.put(taker_playerInfo.getPlayer_name(),taker_playerInfo);
            return true;
        }
    }
}

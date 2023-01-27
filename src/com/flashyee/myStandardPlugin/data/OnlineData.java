package com.flashyee.myStandardPlugin.data;

import com.flashyee.myStandardPlugin.player.PlayerInfo;
import com.flashyee.myStandardPlugin.shop.Commodity;

import java.util.HashMap;
import java.util.LinkedList;

public class OnlineData {
    public static HashMap<String, PlayerInfo> player_info = new HashMap<String, PlayerInfo>();
    public static LinkedList<Commodity> shop_info = new LinkedList<Commodity>();
}

package com.flashyee.myStandardPlugin.player;

public class PlayerInfo {
    String player_name;
    int money;

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public PlayerInfo(String player_name, int money) {
        this.player_name = player_name;
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public int getMoney() {
        return money;
    }
}

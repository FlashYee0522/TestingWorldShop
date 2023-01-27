package com.flashyee.myStandardPlugin.command;


import com.flashyee.myStandardPlugin.data.OnlineData;
import com.flashyee.myStandardPlugin.gui.ShopGUI;
import com.flashyee.myStandardPlugin.shop.Sell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandListener implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args){
        Player player = (Player) sender;
        if(str.equalsIgnoreCase("shop")){
            new ShopGUI().callMenu(player,1);
        }
        if(str.equalsIgnoreCase("money")) {
            player.sendMessage("你的金錢餘額為：" + OnlineData.player_info.get(player.getName()).getMoney() + "元");
        }
        if(str.equalsIgnoreCase("sell")){
            return new Sell().sell(player,Integer.valueOf(args[0]));
        }
        return false;
    }
}

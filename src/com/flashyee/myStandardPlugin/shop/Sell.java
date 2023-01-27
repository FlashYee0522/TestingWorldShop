package com.flashyee.myStandardPlugin.shop;

import com.flashyee.myStandardPlugin.data.OnlineData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sell {

    public boolean sell(Player p,int price){
        try{
            if(p.getInventory().getItemInMainHand() != null){
                ItemStack itemStack = p.getInventory().getItemInMainHand();
                OnlineData.shop_info.add(new Commodity(p.getName(),price,itemStack));
                p.getInventory().setItemInMainHand(null);
                p.sendMessage(ChatColor.GREEN+"成功上架"+itemStack.getAmount()+"個"+itemStack.getItemMeta().getDisplayName()+"，價格為"+price+"元");
                return true;
            }else{
                p.sendMessage(ChatColor.RED+"無法上架物品");
                return false;
            }
        }catch (Exception e){
            p.sendMessage(ChatColor.RED+"無法上架物品");
            return false;
        }
    }
}

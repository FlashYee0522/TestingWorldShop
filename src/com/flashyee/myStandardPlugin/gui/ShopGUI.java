package com.flashyee.myStandardPlugin.gui;

import com.flashyee.myStandardPlugin.data.OnlineData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopGUI {

    public static ItemStack modelItem = new ItemStack(Material.SHIELD);
    ItemStack pageItem = new ItemStack(Material.PAPER);
    static {
        ItemMeta im = modelItem.getItemMeta();
        im.setDisplayName(ChatColor.RED+"點擊即購買");
        im.setLore(Arrays.asList((ChatColor.WHITE + "請空手再點擊購買")));
        modelItem.setItemMeta(im);
    }

    public boolean callMenu(Player player,int page){

        Inventory shopMenu = Bukkit.createInventory(
                null , 63, ChatColor.GREEN+"商店第"+page+"頁");

        shopMenu.setItem(58,modelItem);

        ItemMeta im = pageItem.getItemMeta();
        im.setDisplayName(""+page);
        pageItem.setItemMeta(im);
        shopMenu.setItem(59,pageItem);

        if(page == 1){
            return true;
        }else{
            int size = OnlineData.shop_info.size();
            if( (size/50) >= page && (size%50) != 0){
                return true;
            }else{
                return false;
            }

        }
    }

    public void menuSet(Inventory inventory,int page){

        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;


        int start = (page * 50)-50;
        int end;

        int size = OnlineData.shop_info.size();
        if( size > (page * 50) ){
            end = (page * 50) - 1;
        }else{
            end = size - 1;
        }

        int pos = 0;
        for(int i = start; i <= end ; i++){
            itemStack = OnlineData.shop_info.get(i).getItemStack();
            itemMeta = itemStack.getItemMeta();
            lore = itemMeta.getLore();
            lore.add("賣家："+ OnlineData.shop_info.get(i).getSeller_name() );
            lore.add("價格："+ OnlineData.shop_info.get(i).getPrice() );
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(pos,itemStack);
        }

        itemStack = new ItemStack(Material.PAPER);
        itemMeta = itemStack.getItemMeta();

        if(page != 1){
            itemMeta.setDisplayName(ChatColor.WHITE+"上一頁");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(54,itemStack);
        }

        if(size > (page * 50)){
            itemMeta.setDisplayName(ChatColor.WHITE+"下一頁");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(62,itemStack);
        }

    }

}

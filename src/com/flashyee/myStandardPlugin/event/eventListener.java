package com.flashyee.myStandardPlugin.event;


import com.flashyee.myStandardPlugin.data.OnlineData;
import com.flashyee.myStandardPlugin.gui.ShopGUI;
import com.flashyee.myStandardPlugin.player.Money;
import com.flashyee.myStandardPlugin.player.PlayerInfo;
import com.flashyee.myStandardPlugin.shop.Commodity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class eventListener implements Listener{

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        if(OnlineData.player_info.containsKey(p.getName()) == false){
            OnlineData.player_info.put(p.getName(),new PlayerInfo(p.getName(),0));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inventory = e.getClickedInventory();
        if(inventory.getItem(58).equals(ShopGUI.modelItem)){
            e.setCancelled(true);
        }
        if(e.getClick() == ClickType.LEFT && e.getCurrentItem() != null){

            ItemStack itemStack = e.getCurrentItem();
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lore = itemMeta.getLore();
            lore.remove(lore.size()-1);
            lore.remove(lore.size()-1);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            int page = Integer.valueOf(inventory.getItem(59).getItemMeta().getDisplayName());
            int pos = ( (page * 50) -49 ) + e.getSlot();

            if( OnlineData.shop_info.get(pos).getItemStack().equals(itemStack) ){
                Commodity commodity = OnlineData.shop_info.get(pos);
                if( new Money().pay( p.getName() , commodity.getSeller_name() , commodity.getPrice()) == true ){
                    p.getInventory().setItemInMainHand(itemStack);
                    p.sendMessage(ChatColor.GREEN+"你成功花費"+commodity.getPrice()+"元購買了"+commodity.getSeller_name()+"的"+itemMeta.getDisplayName());
                    OnlineData.shop_info.remove(pos);
                }
            }else{
                p.closeInventory();
                p.sendMessage(ChatColor.RED+"商店已經更新，購買失敗");
            }

        }

    }

    @EventHandler
    public void onInventoryTakeOut(InventoryMoveItemEvent e){
        if( e.getDestination().getItem(58).equals(ShopGUI.modelItem) || e.getInitiator().getItem(58).equals(ShopGUI.modelItem)){
            e.setCancelled(true);
        }
    }

}

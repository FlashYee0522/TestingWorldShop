package com.flashyee.myStandardPlugin.shop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commodity {
    String seller_name;
    int price;
    ItemStack itemStack;

    public Commodity(String seller_name, int price, ItemStack itemStack) {
        this.seller_name = seller_name;
        this.price = price;
        this.itemStack = itemStack;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}

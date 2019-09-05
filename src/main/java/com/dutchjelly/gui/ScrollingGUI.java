package com.dutchjelly.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScrollingGUI {

    private List<Inventory> pages = new ArrayList<Inventory>();
    private List<ItemStack> contents = new ArrayList<ItemStack>();

    private int currentPage = 0;

    public void add(ItemStack item){
        contents.add(item);
    }

    public void add(List<ItemStack> items){
        for(ItemStack item : items)
            add(item);
    }

    public void updateInventories(){

    }
}

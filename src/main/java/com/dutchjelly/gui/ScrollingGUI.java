package com.dutchjelly.gui;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.util.GUIButtons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScrollingGUI {
    private int pageContentSize = 4*9;

    private List<Inventory> pages = new ArrayList<Inventory>();
    private List<ItemStack> contents = new ArrayList<ItemStack>();

    String name = "scrolling gui";

    private int currentPage = 0;

    public void add(ItemStack item){
        contents.add(item);
    }

    public void add(List<ItemStack> items){
        for(ItemStack item : items)
            add(item);
    }

    public void updateInventories(){
        pages.clear();
        int pageAmount = calculatePageAmount();
        int itemIndex = 0;
        for(int i = 0; i < pageAmount; i++){
            Inventory page = create();
            while(page.firstEmpty() != -1 && itemIndex++ < contents.size())
                page.addItem(contents.get(itemIndex-1));
            pages.add(page);
        }
    }

    private int calculatePageAmount(){
        int defaultAmount = (int)Math.ceil(contents.size() / pageContentSize);
        return defaultAmount == 0 ? 1 : defaultAmount;
    }

    private Inventory create() {
        Inventory inv = Bukkit.createInventory(null, pageContentSize + 9, name);
        for (int i = pageContentSize; i < pageContentSize + 9; i++) {
            inv.setItem(i, GUIButtons.filling);
        }
        inv.setItem(pageContentSize + 3, GUIButtons.previous);
        inv.setItem(pageContentSize + 5, GUIButtons.next);
        //xxxMxMxxx

        return inv;
    }

    public boolean isEventSource(Inventory inv){
        return pages.contains(inv);
    }

    //This needs to be overwritten by a child class, but should be called.
    public void handleEvent(InventoryClickEvent e){
        //Stuff that requires inventory to be reopened.
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().equals(GUIButtons.next))
            scroll(1);
        if(e.getCurrentItem().equals(GUIButtons.previous))
            scroll(-1);
        InventoryRevive.self().getGuiManager().open(this, (Player)e.getWhoClicked());
    }

    public void scroll(int scrollAmount){
        currentPage = (currentPage + scrollAmount) % pages.size();
        if(currentPage < 0){
            currentPage = pages.size()-1;
            //scroll(currentPage + 1); only required if scrollAmount is smaller than -1.
        }
    }

    public Inventory getCurrentPage(){
        return pages.get(currentPage); //shouldn't throw an exception, but could add debug check
    }

}

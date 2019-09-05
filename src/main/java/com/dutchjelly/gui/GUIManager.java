package com.dutchjelly.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private List<ScrollingGUI> openScrollGUIs = new ArrayList<ScrollingGUI>();

    public void open(ScrollingGUI gui, Player p){
        p.openInventory(gui.getCurrentPage());
        if(!openScrollGUIs.contains(gui))
            openScrollGUIs.add(gui);
    }

    public void close(ScrollingGUI gui, Player p){
        p.closeInventory();
        if(openScrollGUIs.contains(gui))
            openScrollGUIs.remove(gui);
    }

    public void handleGUIClose(Inventory inv){
        ScrollingGUI gui = getScrollingGUI(inv);
        if(gui != null && openScrollGUIs.contains(gui))
            openScrollGUIs.remove(gui);
    }


    public void openOverlay(Inventory inv, Player p){

    }

    public boolean isGUI(Inventory inv){
        return getScrollingGUI(inv) != null;
    }

    public void tryAssignHandler(InventoryClickEvent e){
        ScrollingGUI gui = getScrollingGUI(e.getView().getTopInventory());
        if(gui == null) return;
        gui.handleEvent(e);
    }

    public ScrollingGUI getScrollingGUI(Inventory inv){
        for(ScrollingGUI gui : openScrollGUIs){
            if(gui.isEventSource(inv))
                return gui;
        }
        return null;
    }


}

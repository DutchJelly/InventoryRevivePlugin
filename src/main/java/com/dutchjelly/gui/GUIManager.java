package com.dutchjelly.gui;

import com.dutchjelly.InventoryRevive;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIManager {

    private List<ScrollingGUI> openScrollGUIs = new ArrayList<ScrollingGUI>();
    private Map<Inventory, ScrollingGUI> openPreviews = new HashMap<Inventory, ScrollingGUI>();

    public void open(ScrollingGUI gui, Player p){
        //if(gui == null) return; //prevents weird stacktrace.. have to debug this..
        p.openInventory(gui.getCurrentPage());
        if(!openScrollGUIs.contains(gui)){
            openScrollGUIs.add(gui);
        }

    }

    public void close(ScrollingGUI gui, Player p){
        p.closeInventory();
        if(openScrollGUIs.contains(gui))
            openScrollGUIs.remove(gui);
    }

    public void handleGUIClose(final Inventory inv){
        ScrollingGUI gui = getScrollingGUI(inv);
        if(gui != null && openScrollGUIs.contains(gui))
            openScrollGUIs.remove(gui);
        else if(openPreviews.containsKey(inv)){
            final Player p = (Player)inv.getViewers().get(0);
            final ScrollingGUI linkedGUI = openPreviews.get(inv);
            //need to set 1 tick delay for opening an inventory again..
            InventoryRevive.self().getServer().getScheduler().scheduleSyncDelayedTask(InventoryRevive.self(), new Runnable() {
                @Override
                public void run() {

                    openPreviews.remove(inv);
                    open(linkedGUI, p);
                }
            }, 5L);

        }

    }


    public void openOverlay(ScrollingGUI gui, Inventory inv, Player p){
        p.openInventory(inv);
        openPreviews.put(inv, gui);
    }

    public boolean isGUI(Inventory inv){
        return getScrollingGUI(inv) != null
                || openPreviews.containsKey(inv);
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

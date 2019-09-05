package com.dutchjelly.events;

import com.dutchjelly.InventoryRevive;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        InventoryRevive.self().getGuiManager().tryAssignHandler(e);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        //Dragging items is annoying to deal with so just cancel the entire event for GUI's.
        if(InventoryRevive.self().getGuiManager().isGUI(e.getInventory()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(InventoryRevive.self().getGuiManager().isGUI(e.getInventory()))
            InventoryRevive.self().getGuiManager().handleGUIClose(e.getInventory());
    }
}

package com.dutchjelly.gui;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.files.FileManager;
import com.dutchjelly.util.GUIButtons;
import javafx.util.Pair;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ReviveGUI extends ScrollingGUI {

    Map<ItemStack, Pair<Player, PlayerInventory>> contentMeta =
            new HashMap<ItemStack, Pair<Player, PlayerInventory>>();

    public ReviveGUI(){
        FileManager fm = InventoryRevive.self().fm();
        List<String> keys = fm.listKeys("data.yml");
        for(String key : keys){
            Player p = null;
            for(Player online : InventoryRevive.self().getServer().getOnlinePlayers()){
                if(online.getUniqueId().equals(UUID.fromString(key))){
                    p = online;
                    break;
                }
            }
            if(p == null) continue;
            PlayerInventory inv = (PlayerInventory)fm.get("data.yml", key);
            ItemStack skull = new ItemStack(Material.LEGACY_SKULL, 1);
            SkullMeta meta = (SkullMeta)skull.getItemMeta();
            meta.setOwningPlayer(p);
            skull.setItemMeta(meta);
            contentMeta.put(skull, new Pair(p, inv));
            add(skull);
        }
        updateInventories();
    }

    @Override
    public void handleEvent(InventoryClickEvent e){
        if(!isEventSource(e.getView().getTopInventory()))
            return;
        e.setCancelled(true);


        if(contentMeta.containsKey(e.getCurrentItem())){
            Pair<Player, PlayerInventory> clickedMeta = contentMeta.get(e.getCurrentItem());
            clickedMeta.getKey().getInventory().setArmorContents(clickedMeta.getValue().getArmorContents());
            clickedMeta.getKey().getInventory().setContents(clickedMeta.getValue().getContents());
            clickedMeta.getKey().getInventory().setExtraContents(clickedMeta.getValue().getExtraContents());
            clickedMeta.getKey().updateInventory();
        }

        //Stuff that requires inventory to be reopened.
        if(e.getCurrentItem().equals(GUIButtons.next))
            scroll(1);
        if(e.getCurrentItem().equals(GUIButtons.previous))
            scroll(-1);
        InventoryRevive.self().getGuiManager().open(this, (Player)e.getWhoClicked());
    }
}

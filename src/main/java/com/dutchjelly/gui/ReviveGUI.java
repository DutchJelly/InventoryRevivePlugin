package com.dutchjelly.gui;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.files.FileManager;
import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ReviveGUI extends ScrollingGUI {

    private Map<ItemStack, Pair<Player, ItemStack[]>> contentMeta =
            new HashMap<ItemStack, Pair<Player, ItemStack[]>>();

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
            List<ItemStack> temp = (List<ItemStack>)fm.get("data.yml", key);
            ItemStack[] contents = temp.toArray(new ItemStack[temp.size()]);
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta)skull.getItemMeta();
            meta.setOwningPlayer(p);
            skull.setItemMeta(styleHeadMeta(meta, p));
            contentMeta.put(skull, new Pair(p, contents));
            add(skull);
        }
        this.name = "Revive Inventory GUI";
        updateInventories();
    }

    public SkullMeta styleHeadMeta(SkullMeta meta, Player p){
        meta.setDisplayName(p.getDisplayName());
        meta.setLore(Arrays.asList("----------------------",
                "Click to restore the player's inventory",
                "Middle click to preview the inventory",
                "----------------------"));
        return meta;
    }

    @Override
    public void handleEvent(InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;

        if(!isEventSource(e.getView().getTopInventory()))
            return;
        e.setCancelled(true);

        if(contentMeta.containsKey(e.getCurrentItem())){
            Pair<Player, ItemStack[]> clickedMeta = contentMeta.get(e.getCurrentItem());

            if(e.getClick() == ClickType.MIDDLE){
                Inventory preview = Bukkit.createInventory(null, 5*9, clickedMeta.getKey().getDisplayName() + "'s inventory.");
                preview.setContents(clickedMeta.getValue());
                InventoryRevive.self().getGuiManager().openOverlay(this, preview, (Player)e.getWhoClicked());
                return;
            }

            clickedMeta.getKey().getInventory().setContents(clickedMeta.getValue());
            clickedMeta.getKey().updateInventory();
            e.getWhoClicked().sendMessage("You successfully restored " + clickedMeta.getKey().getDisplayName() + "'s inventory.");
            clickedMeta.getKey().sendMessage("Your inventory was restored by " + e.getWhoClicked().getName() + "!");
            return;
        }
        super.handleEvent(e);
    }
}

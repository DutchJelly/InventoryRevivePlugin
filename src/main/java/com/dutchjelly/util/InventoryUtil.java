package com.dutchjelly.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class InventoryUtil {


    public static Inventory deserialize(Map<String, Object> serialized){

        return null;
    }


    public static boolean IsEmpty(PlayerInventory inventory){
        return IsEmpty(inventory.getArmorContents())
                && IsEmpty(inventory.getExtraContents())
                && IsEmpty(inventory.getContents());
    }

    public static boolean IsEmpty(ItemStack[] items){
        for(ItemStack item : items){
            if(item != null && !item.getType().equals(Material.AIR))
                return false;
        }
        return true;
    }
}

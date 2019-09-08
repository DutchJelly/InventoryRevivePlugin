package com.dutchjelly.model;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

//Redundant, but maybe useful if you want to add xp, potion effects,
// location and other stuff to the restore function.
@SerializableAs("PlayerInventory")
public class CustomPlayerInventory implements ConfigurationSerializable {

    private ItemStack mainHand;
    private ItemStack offHand;
    private ItemStack[] armor;
    private ItemStack[] contents;

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    public static CustomPlayerInventory deserialize(Map<String, Object> args){

        return null;
    }

    public static CustomPlayerInventory fromPlayerInv(PlayerInventory inv){
        CustomPlayerInventory customInv = new CustomPlayerInventory();
        customInv.armor = inv.getArmorContents();
        customInv.offHand = inv.getItemInOffHand();
        customInv.mainHand = inv.getItemInMainHand();
        customInv.contents = inv.getStorageContents();
        return null;
    }

    public void setPlayerInv(Player p){

    }

}

package com.dutchjelly.model;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class CustomPlayerInventory implements ConfigurationSerializable {
    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    public static CustomPlayerInventory fromPlayerInv(PlayerInventory inv){
        return null;
    }

    public void setPlayerInv(Player p){

    }

}

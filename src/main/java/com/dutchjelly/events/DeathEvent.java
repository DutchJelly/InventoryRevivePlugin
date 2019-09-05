package com.dutchjelly.events;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.util.InventoryUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(InventoryUtil.IsEmpty(e.getEntity().getInventory()))
            return;

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        InventoryRevive.self().fm().save("data.yml",
                e.getEntity().getUniqueId().toString(),
                e.getEntity().getInventory()
        );
    }

}

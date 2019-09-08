package com.dutchjelly.commands;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.gui.ReviveGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ir implements CommandExecutor{


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        Player p = (Player)commandSender;
//        for(ItemStack elem : p.getInventory().getContents()){
//            System.out.println(elem == null ? "null" : elem.getType().name());
//        }
        InventoryRevive.self().getGuiManager().open(new ReviveGUI(), p);

        return true;
    }
}

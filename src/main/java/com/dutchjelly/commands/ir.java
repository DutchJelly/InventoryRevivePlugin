package com.dutchjelly.commands;

import com.dutchjelly.InventoryRevive;
import com.dutchjelly.gui.ReviveGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ir implements CommandExecutor{


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        Player p = (Player)commandSender;

        InventoryRevive.self().getGuiManager().open(new ReviveGUI(), p);

        return true;
    }
}

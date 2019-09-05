package com.dutchjelly;

import com.dutchjelly.events.DeathEvent;
import com.dutchjelly.events.InventoryEvents;
import com.dutchjelly.files.FileManager;
import com.dutchjelly.gui.GUIManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.dutchjelly.commands.ir;


public class InventoryRevive extends JavaPlugin {
    private static InventoryRevive self;
    public static InventoryRevive self(){
        return self;
    }

    private FileManager fm;
    public FileManager fm(){
        return fm;
    }

    private GUIManager guiManager;
    public GUIManager getGuiManager() {
        return guiManager;
    }

    @Override
    public void onEnable(){
        self = this;
        fm = FileManager.init(this);
        fm.manage("data.yml");

       this.getCommand("ir").setExecutor(new ir());
       this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
       this.getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
    }

    @Override
    public void onDisable(){

    }


}

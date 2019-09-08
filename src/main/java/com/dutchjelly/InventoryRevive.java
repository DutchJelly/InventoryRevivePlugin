package com.dutchjelly;

import com.dutchjelly.events.DeathEvent;
import com.dutchjelly.events.InventoryEvents;
import com.dutchjelly.files.FileManager;
import com.dutchjelly.gui.GUIManager;
import com.dutchjelly.model.CustomPlayerInventory;
import com.dutchjelly.util.GUIButtons;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
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
        //Register the serialization of the player inventory class.
        ConfigurationSerialization.registerClass(CustomPlayerInventory.class, "PlayerInventory");



        fm = FileManager.init(this);
        fm.manage("data.yml");

        guiManager = new GUIManager();
        GUIButtons.init(); //weird call to ensure that static itemstacks are created.. should be changed..

       this.getCommand("ir").setExecutor(new ir());
       this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
       this.getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
    }

    @Override
    public void onDisable(){

    }


}

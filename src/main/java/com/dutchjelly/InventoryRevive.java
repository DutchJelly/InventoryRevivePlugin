package com.dutchjelly;

import com.dutchjelly.events.DeathEvent;
import com.dutchjelly.files.FileManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.dutchjelly.commands.ir;


public class InventoryRevive extends JavaPlugin {

    //Keep track of objects that always have one instance
    private static InventoryRevive self;
    private static FileManager fm;
    public static InventoryRevive self(){
        return self;
    }
    public static FileManager fm(){
        return fm;
    }

    @Override
    public void onEnable(){
        self = this;
        fm = FileManager.init(this);
        fm.manage("data.yml");

       this.getCommand("ir").setExecutor(new ir());
       this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
    }

    @Override
    public void onDisable(){

    }


}

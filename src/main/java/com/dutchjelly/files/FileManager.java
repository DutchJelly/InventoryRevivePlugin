package com.dutchjelly.files;

import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class FileManager {

    private File dataFolder;

    private Map<String, Pair<FileConfiguration, File>> files =
            new HashMap<String, Pair<FileConfiguration, File>>();



    public static FileManager init(JavaPlugin plugin){
        FileManager manager = new FileManager();
        manager.dataFolder = plugin.getDataFolder();
        manager.dataFolder.mkdir();

        return manager;
    }

    public void manage(String fileName){
        File file = ensureCreated(getFile(fileName));
        FileConfiguration config = getYamlConfig(file);
        files.put(fileName, new Pair(config,file));
    }

    public void save(String file, String key, Object content){
        files.get(file).getKey().set(key, content);
        saveConfig(file);
    }

    public void remove(String file, String key){
        files.get(file).getKey().set(key, null);
        saveConfig(file);
    }

    public List<String> listKeys(String file){
        return new ArrayList<String>(files.get(file).getKey().getKeys(false));
    }

    public Object get(String file, String key){
        return files.get(file).getKey().get(key);
    }

    private void saveConfig(String key){
        Pair<FileConfiguration, File> filePair = files.get(key);
        try {
            filePair.getKey().save(filePair.getValue());
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Error saving a file config!");
        }
    }

    private File ensureCreated(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }
        return file;
    }

    private File getFile(String name){
        File file = new File(dataFolder.getPath() + File.separator + name);
        ensureCreated(file);
        return file;
    }

    private FileConfiguration getYamlConfig(File file){
        return YamlConfiguration.loadConfiguration(file);
    }



}

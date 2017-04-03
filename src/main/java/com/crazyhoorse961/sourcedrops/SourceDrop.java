package com.crazyhoorse961.sourcedrops;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.serializer.ChestSerializer;
import com.crazyhoorse961.sourcedrops.serializer.ItemSerializer;
import com.crazyhoorse961.sourcedrops.utils.Dumper;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author crazyhoorse961
 */
public class SourceDrop extends JavaPlugin
{

    private Dumper dumper;
    private ChestSerializer chestSerializer;
    private File cache;
    private ItemSerializer itemSerializer;

    @Override
    public void onEnable(){
        saveDefaultConfig();
        createCache();
        dumper = new Dumper();
        chestSerializer = new ChestSerializer(this);
        itemSerializer = new ItemSerializer(this);
        System.out.println(itemSerializer);
    }

    @Override
    public void onDisable(){
        saveConfig();
    }

    public Dumper getDumper() {
        return dumper;
    }

    public ChestSerializer getChestSerializer() {
        return chestSerializer;
    }

    public File getCache() {
        return cache;
    }

    public ItemSerializer getItemSerializer() {
        return itemSerializer;
    }

    private void createCache() {
        cache = new File(getDataFolder() + "cache.src");
        if (!cache.exists()) {
            try {
                if(!cache.getParentFile().exists()){
                    cache.getParentFile().mkdirs();
                }
                cache.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

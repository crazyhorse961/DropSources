package com.crazyhoorse961.sourcedrops;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.commands.DumpCommand;
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
    private File cache;
    private ItemSerializer itemSerializer;

    @Override
    public void onEnable(){
        saveDefaultConfig();
        createCache();
        dumper = new Dumper();
        itemSerializer = new ItemSerializer(this);
        new DumpCommand(this);

    }

    @Override
    public void onDisable(){
        saveConfig();
    }

    public Dumper getDumper() {
        return dumper;
    }

    public File getCache() {
        return cache;
    }

    public ItemSerializer getItemSerializer() {
        return itemSerializer;
    }

    private void createCache() {
        cache = new File(getDataFolder() + File.separator + "cache.src");
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

package com.crazyhoorse961.sourcedrops;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.commands.DumpCommand;
import com.crazyhoorse961.sourcedrops.commands.SourceCommand;
import com.crazyhoorse961.sourcedrops.serializer.ItemSerializer;
import com.crazyhoorse961.sourcedrops.utils.ClickActions;
import com.crazyhoorse961.sourcedrops.utils.Dumper;
import com.crazyhoorse961.sourcedrops.utils.logging.FileLogger;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

/**
 * @author crazyhoorse961
 */
public class SourceDrop extends JavaPlugin {

    private Dumper dumper;
    private File cache;
    private ItemSerializer itemSerializer;
    private FileLogger fileLogger;

    @Override
    public void onLoad(){
        saveDefaultConfig();
        fileLogger = new FileLogger(this);
        fileLogger.debug(Level.INFO, "--------------------------------", true);
        fileLogger.debug(Level.INFO, String.valueOf(System.currentTimeMillis()), true);
        Properties prop = System.getProperties();
        Set<Object> set = prop.keySet();
        for(Object obj : set){
            fileLogger.debug(Level.INFO, obj + " = " + prop.getProperty((String) obj), true);
        }
    }

    @Override
    public void onEnable() {
        ClickActions.initialize(this);
        createCache();
        dumper = new Dumper();
        itemSerializer = new ItemSerializer(this);
        new DumpCommand(this);
        new SourceCommand(this);
    }

    @Override
    public void onDisable() {
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

    public FileLogger getFileLogger() {
        return fileLogger;
    }

    public ClickActions getClickable() {
        return ClickActions.getInstance();
    }

    private void createCache() {
        cache = new File(getDataFolder() + File.separator + "cache.src");
        if (!cache.exists()) {
            try {
                if (!cache.getParentFile().exists()) {
                    cache.getParentFile().mkdirs();
                }
                cache.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

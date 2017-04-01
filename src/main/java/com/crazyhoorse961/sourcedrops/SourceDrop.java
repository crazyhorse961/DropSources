package com.crazyhoorse961.sourcedrops;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.serializer.ChestSerializer;
import com.crazyhoorse961.sourcedrops.utils.Dumper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author crazyhoorse961
 */
public class SourceDrop extends JavaPlugin
{

    private Dumper dumper;
    private ChestSerializer chestSerializer;

    @Override
    public void onEnable(){
        dumper = new Dumper();
        chestSerializer = new ChestSerializer(this);
    }

    public Dumper getDumper() {
        return dumper;
    }

    public ChestSerializer getChestSerializer() {
        return chestSerializer;
    }
}

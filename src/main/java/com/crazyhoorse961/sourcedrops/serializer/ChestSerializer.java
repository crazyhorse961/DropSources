package com.crazyhoorse961.sourcedrops.serializer;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyhoorse961
 */
public class ChestSerializer {

    private final SourceDrop plugin;

    public ChestSerializer(SourceDrop plugin){
        this.plugin = plugin;
    }

    public String serialize(Chest chest){
        List<String> serial = new ArrayList<String>();
        for(ItemStack i : chest.getInventory().getContents()){

        }
        return "";
    }

}

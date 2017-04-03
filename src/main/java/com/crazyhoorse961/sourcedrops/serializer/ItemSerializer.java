package com.crazyhoorse961.sourcedrops.serializer;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;

import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;
import org.bukkit.inventory.ItemStack;


import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author crazyhoorse961
 */
public class ItemSerializer
{
    private final SourceDrop plugin;

    public ItemSerializer(SourceDrop plugin){
        this.plugin = plugin;
    }

    public String serialize(ItemStack is){
        Map<String,Object> values = is.serialize();
        try {
            PrintWriter pw = new PrintWriter(plugin.getDataFolder() + File.separator + "cache.src");
            pw.write("cache-item " + values);
            pw.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return getCache();
    }

    public ItemStack deserialize(String des){
        return null;
    }

    private String getCache(){
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(plugin.getDataFolder() + File.separator + "cache.src"));
            System.out.println(encoded);
            List<String> lines = FileUtils.readLines(new File(plugin.getDataFolder() + File.separator + "cache.src"), Charset.defaultCharset());
            List<String> updatedLines = lines.stream().filter(s -> !s.contains("cache-item")).collect(Collectors.toList());
            FileUtils.writeLines(new File(plugin.getDataFolder() + File.separator + "cache.src"), updatedLines, false);
            return String.valueOf(encoded).split(" ")[1];
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }
}

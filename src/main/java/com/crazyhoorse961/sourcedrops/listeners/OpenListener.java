package com.crazyhoorse961.sourcedrops.listeners;/**
 * Created by nini7 on 01.04.2017.
 */


import com.crazyhoorse961.sourcedrops.SourceDrop;
import com.crazyhoorse961.sourcedrops.events.EventFactory;
import com.crazyhoorse961.sourcedrops.utils.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author crazyhoorse961
 */
public class OpenListener implements Listener {

    private final SourceDrop plugin;
    private boolean hasData = false;
    private String meta;

    public OpenListener(SourceDrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getInventory().getType() == InventoryType.CHEST) {
            Chest c = (Chest) e.getInventory();
            plugin.getConfig().getConfigurationSection("chests").getKeys(false).forEach(s -> {
                if(c.hasMetadata(s)){
                    lambda(() -> hasData = true);
                    lambda(() -> meta = s);
                }
            });
            UUID pId = p.getUniqueId();
            if(Cooldown.isInCooldown(pId, meta + "-" + p.getName())){
                p.sendMessage(ChatColor.GREEN  + "Please wait " + ChatColor.YELLOW  + Cooldown.getTimeLeft(pId, meta + "-" + p.getName()) + ChatColor.GREEN + " seconds");
                return;
            }
            Cooldown cooldown = new Cooldown(pId, meta + "-" + p.getName(), 3);
            cooldown.start();
            EventFactory.OPEN.init(p, c.getLocation()).call();

        }
    }
    private void lambda(Callable c){
        try {
            c.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


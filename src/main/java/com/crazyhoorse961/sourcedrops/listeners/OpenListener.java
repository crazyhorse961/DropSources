package com.crazyhoorse961.sourcedrops.listeners;/**
 * Created by nini7 on 01.04.2017.
 */


import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * @author crazyhoorse961
 */
public class OpenListener implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent e){
        Player p = (Player) e.getPlayer();
        if(e.getInventory().getType() == InventoryType.CHEST){
            Chest c = (Chest) e.getInventory();
        }
    }
}

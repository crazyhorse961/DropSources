package com.crazyhoorse961.sourcedrops.listeners;/**
 * Created by nini7 on 06.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.Callable;

/**
 * @author crazyhoorse961
 */
public class FallListener implements Listener {

    private final SourceDrop plugin;

    private boolean hasData = false;

    private String meta;

    public FallListener(SourceDrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFall(EntityChangeBlockEvent e) {
        if (e.getEntityType() == EntityType.FALLING_BLOCK && e.getEntity() instanceof FallingBlock) {
            FallingBlock fallingBlock = (FallingBlock) e.getEntity();
            if (fallingBlock.getMaterial() == Material.CHEST && e.getBlock().getState() instanceof Chest) {
                plugin.getConfig().getConfigurationSection("chests").getKeys(false).forEach(s -> {
                    if (fallingBlock.hasMetadata(s)) {
                        lambda(() -> hasData = true);
                        lambda(() -> meta = s);
                    }
                });
                Chest c = (Chest) e.getBlock().getState();
                Inventory toFill = c.getInventory();
                if (hasData) {
                    hasData = false;
                    fallingBlock.setDropItem(false);
                    //Filling items
                    //'meta' is the chest name for recognizing
                    ItemStack[] items = plugin.getItemSerializer().stringToItems(plugin.getConfig().getString("chests." + meta + ".items"));
                    c.setCustomName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chests." + meta + ".name")));
                    toFill.setContents(items);
                }
            }
        }
    }

    private void lambda(Callable c) {
        try {
            c.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

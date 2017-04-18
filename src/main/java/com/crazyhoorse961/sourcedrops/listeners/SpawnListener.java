package com.crazyhoorse961.sourcedrops.listeners;/**
 * Created by nini7 on 04.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import com.crazyhoorse961.sourcedrops.utils.HiddenStringUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * @author crazyhoorse961
 */
public class SpawnListener implements Listener {

    private final SourceDrop plugin;

    public SpawnListener(SourceDrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasLore()) {
            String chestName = null;
            for (String str : e.getItem().getItemMeta().getLore()) {
                if (HiddenStringUtil.hasHiddenString(str)) {
                    if (HiddenStringUtil.extractHiddenString(str).startsWith("chest ")) {
                        chestName = HiddenStringUtil.extractHiddenString(str).split(" ")[1];
                        break;
                    }
                }
            }
            if (chestName.isEmpty() || chestName == null) {
                return;
            }
            Location newLoc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 30, p.getLocation().getZ());
            FallingBlock block = p.getWorld().spawnFallingBlock(newLoc, new MaterialData(Material.CHEST));
            block.setMetadata(chestName, new FixedMetadataValue(plugin, chestName));
            return;
        }
    }
}

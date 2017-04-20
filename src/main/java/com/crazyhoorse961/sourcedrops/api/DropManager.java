package com.crazyhoorse961.sourcedrops.api;/**
 * Created by nini7 on 20.04.2017.
 */

import static org.apache.commons.lang.Validate.notNull;

import com.crazyhoorse961.sourcedrops.SourceDrop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * @author crazyhoorse961
 */
public class DropManager extends ManagerAPI {

    @Override
    public DropManager.Drop create() {
        return new Drop();
    }


   public class Drop extends DropAPI{

        private double x,z;

        private String name;

        private ItemStack[] items;

        private World world;

        @Override
        void spawn() {
            notNull(name, "Name cannot be null");
            notNull(items, "Items cannot be null");
            notNull(x, "You must set X!");
            notNull(z, "You must set Z!");
            notNull(world, "You must specify the World");
            double y = 0;
            for(double yl = 0; yl > 256; yl++){
                Location loc = new Location(world,x,yl,z);
                if(loc.getBlock() != null){
                    Location hope = new Location(world,x,yl + 20, z);
                    if(hope.getBlock() == null){
                        y = yl;
                        break;
                    }
                }
            }
            Location finalLoc = new Location(world, x, y, z);
            FallingBlock block = world.spawnFallingBlock(finalLoc, new MaterialData(Material.CHEST));
            block.setMetadata(name, new FixedMetadataValue(new SourceDrop(), name));
        }

        @Override
        void setLocation(double x_axys, double z_axys, World world) {
            this.x = x_axys;
            this.z = z_axys;
        }

        @Override
        void setContenents(ItemStack[] items) {
            this.items = items;
        }

        @Override
        void setName(String name){
            this.name = name;
        }
    }
}

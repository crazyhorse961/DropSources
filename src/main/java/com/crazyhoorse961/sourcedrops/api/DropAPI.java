package com.crazyhoorse961.sourcedrops.api;/**
 * Created by nini7 on 20.04.2017.
 */

import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

/**
 * @author crazyhoorse961
 */

public abstract class DropAPI {


    /**
     *
     * Spawns the @Drop object
     * @return type void
     * @since 1.0
     *
     */

    abstract void spawn();

    /**
     * Sets the X and the Z axys
     * @param x_axys
     * @param z_axys
     * @param world
     * @return type void
     * @since 1.0
     */

    abstract void setLocation(double x_axys, double z_axys, World world);

    /**
     * Sets the Items
     * @param items
     * @return type void
     * @since 1.0
     */

    abstract void setContenents(ItemStack[] items);

    /**
     * Sets the name
     * @param name
     * @return type void
     * @since 1.0
     */

    abstract void setName(String name);

}

package com.crazyhoorse961.sourcedrops.events; /**
 * Created by nini7 on 01.04.2017.
 */

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author crazyhoorse961
 */
public class PlayerOpenDropEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Location dropLoc;
    private boolean cancelled = false;

    private PlayerOpenDropEvent() {
    }

    public PlayerOpenDropEvent(Player player, Location dropLoc) {
        this.player = player;
        this.dropLoc = dropLoc;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getDropLocation() {
        return dropLoc;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}

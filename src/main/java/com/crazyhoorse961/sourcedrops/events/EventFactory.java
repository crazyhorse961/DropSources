package com.crazyhoorse961.sourcedrops.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Created by nini7 on 01.04.2017.
 */
public enum EventFactory {
    OPEN, SPAWN, REMOVE;

    private Event event;

    public void call() {
        Bukkit.getPluginManager().callEvent(event);

    }

    public EventFactory init(Player p, Location dropLoc) {
        switch (this) {
            case OPEN:
                this.event = new PlayerOpenDropEvent(p, dropLoc);
                return OPEN;
            case SPAWN:
                return SPAWN;
            case REMOVE:
                return REMOVE;
        }
        return null;
    }
}

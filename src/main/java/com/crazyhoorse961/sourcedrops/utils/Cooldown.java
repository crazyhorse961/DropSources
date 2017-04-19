package com.crazyhoorse961.sourcedrops.utils;/**
 * Created by nini7 on 19.04.2017.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author crazyhoorse961
 */
public class Cooldown {

    private final int timeInSeconds;
    private final UUID id;
    private final String cooldownName;
    private static Map<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private long start;

    public Cooldown(final UUID id, final String cooldownName, final int timeInSeconds) {
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    private static void stop(final UUID id, final String cooldownName) {
        cooldowns.remove(id + cooldownName);
    }

    private static Cooldown getCooldown(final UUID id, final String cooldownName) {
        return cooldowns.get(id.toString() + cooldownName);
    }

    public static int getTimeLeft(final UUID id, final String cooldownName) {
        final Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;
        if (cooldown != null) {
            final long now = System.currentTimeMillis();
            final long cooldownTime = cooldown.start;
            final int totalTime = cooldown.timeInSeconds;
            final int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * -1;
        }
        return f;
    }

    public void start() {
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.toString() + this.cooldownName, this);
    }

    public static boolean isInCooldown(final UUID id, final String cooldownName) {
        if (getTimeLeft(id, cooldownName) >= 1) {
            return true;
        }
        stop(id, cooldownName);
        return false;
    }
}

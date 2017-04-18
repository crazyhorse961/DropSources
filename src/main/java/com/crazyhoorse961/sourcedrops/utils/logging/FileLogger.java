package com.crazyhoorse961.sourcedrops.utils.logging;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by nini7 on 18.04.2017.
 */

    /*
 * Copyright (C) 2012 p000ison
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of
 * this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send
 * a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco,
 * California, 94105, USA.
 *
 */
public class FileLogger {


    private final Logger debugLogger = Logger.getLogger("Logging");
    /**
     * This should be get initialised when the plugin loads (It contains the Logging
     * system)
     *
     * @author Max
     */


    private Logger log;
    private FileHandler debugFileHandler;

    /**
     * Gets the logger for your plugin
     *
     * @param plugin The plugin to apply
     */
    public FileLogger(JavaPlugin plugin) {

        log = plugin.getLogger();

        try {

            debugFileHandler = new FileHandler(plugin.getDataFolder().getAbsolutePath() + File.pathSeparator + plugin.getName() + ".log", true);
            SimpleFormatter formatter = new SimpleFormatter();

            debugLogger.addHandler(debugFileHandler);
            debugFileHandler.setFormatter(formatter);

        } catch (IOException ex) {
            debug(null, ex, false);
        } catch (SecurityException ex) {
            debug(null, ex, false);
        }
    }

    /**
     * Logs a message
     *
     * @param level  The level to log
     * @param msg    The message to log
     * @param toFile log to own log?
     */
    public void debug(Level level, String msg, boolean toFile) {
        if (toFile) {
            if (debugLogger != null) {
                debugLogger.log(level, msg);
            }
        }
        log.log(level, msg);
    }

    /**
     * Logs a Exception
     *
     * @param msg       The message to log
     * @param exception the exception
     * @param toFile    log to own log?
     */
    public void debug(String msg, Throwable exception, boolean toFile) {
        if (toFile) {
            if (debugLogger != null) {
                debugLogger.log(Level.SEVERE, msg, exception);
            }
        }
        log.log(Level.SEVERE, msg, exception);
    }
}


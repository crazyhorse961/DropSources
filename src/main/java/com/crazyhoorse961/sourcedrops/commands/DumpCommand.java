package com.crazyhoorse961.sourcedrops.commands;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import com.crazyhoorse961.sourcedrops.utils.ClickActions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * @author crazyhoorse961
 */
public class DumpCommand implements CommandExecutor {

    private final SourceDrop plugin;

    public DumpCommand(SourceDrop plugin) {
        this.plugin = plugin;
        plugin.getCommand("dump").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            try {
                commandSender.sendMessage(ChatColor.GREEN + "Here you dump! " + plugin.getDumper().generateHaste());
            }catch(IOException e){
                e.printStackTrace();
            }
            return true;
        } else {
            commandSender.sendMessage(ChatColor.RED + "You can dump only from the console");
            return true;
        }
    }
}

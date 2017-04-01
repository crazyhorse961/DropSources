package com.crazyhoorse961.sourcedrops.commands;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.utils.Dumper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author crazyhoorse961
 */
public class DumpCommand implements CommandExecutor
{

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof ConsoleCommandSender){
            commandSender.sendMessage(ChatColor.GREEN + "Here you dump! " + new Dumper().generateHaste());
            return true;
        }else{
            commandSender.sendMessage(ChatColor.RED + "You can dump only from the console");
            return true;
        }
    }
}

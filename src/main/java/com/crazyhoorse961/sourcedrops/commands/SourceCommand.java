package com.crazyhoorse961.sourcedrops.commands;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * @author crazyhoorse961
 */
public class SourceCommand implements CommandExecutor
{

    private final SourceDrop plugin;

    public SourceCommand(SourceDrop plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "You shall be a player to execute this command");
            return true;
        }
        Player player = (Player) commandSender;
        switch(strings.length){
            case 0:
                sendHelp(player);
                return true;
            case 1:
                sendHelp(player);
            case 2:
                switch(strings[0]){
                    case "create":
                        if(!player.hasPermission("source.create")){
                            player.sendMessage(ChatColor.RED + "Missing permission");
                            return true;
                        }
                        Set<Material> empty = new HashSet<Material>();
                        Block checkChest = player.getTargetBlock(empty, 10);
                        if(checkChest.getType() == Material.CHEST){
                            Chest chest = (Chest) checkChest.getState();
                            plugin.getConfig().set("chests." + strings[1] + ".items", plugin.getItemSerializer().itemsToString(chest.getInventory().getContents()));
                            plugin.getConfig().set("chests." + strings[1] + ".name", ChatColor.translateAlternateColorCodes('&', strings[1]));
                            plugin.saveConfig();
                            player.sendMessage(ChatColor.GREEN + "Chest created !");
                        }
                    case "remove":
                        if(!player.hasPermission("source.remove")){
                            player.sendMessage(ChatColor.RED + "Missing permission");
                            return true;
                        }
                        plugin.getConfig().set("chests." + strings[1], null);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "AirDrop removed!");
                        return true;
                }
        }
        return false;
    }

    private void sendHelp(Player p){

    }
}

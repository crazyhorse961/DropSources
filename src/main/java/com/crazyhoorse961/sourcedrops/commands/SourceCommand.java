package com.crazyhoorse961.sourcedrops.commands;/**
 * Created by nini7 on 01.04.2017.
 */

import com.crazyhoorse961.sourcedrops.SourceDrop;
import com.sun.istack.internal.Nullable;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author crazyhoorse961
 */
public class SourceCommand implements CommandExecutor {

    private final SourceDrop plugin;

    public SourceCommand(SourceDrop plugin) {
        this.plugin = plugin;
        plugin.getCommand("sourcedrops").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You should be a player to execute this command");
            return true;
        }
        Player player = (Player) commandSender;
        switch (strings.length) {
            case 0:
                sendHelp(player);
                return true;
            case 1:
                sendHelp(player);
                return true;
            case 2:
                switch (strings[0]) {
                    case "create":
                        if (!player.hasPermission("source.create")) {
                            player.sendMessage(ChatColor.RED + "Missing permission");
                            return true;
                        }
                        Block checkChest = player.getTargetBlock(Collections.emptySet(), 10);
                        if (checkChest.getType() == Material.CHEST) {
                            Chest chest = (Chest) checkChest.getState();
                            plugin.getConfig().set("chests." + strings[1] + ".items", plugin.getItemSerializer().itemsToString(chest.getInventory().getContents()));
                            plugin.getConfig().set("chests." + strings[1] + ".name", ChatColor.translateAlternateColorCodes('&', strings[1]));
                            plugin.saveConfig();
                            player.sendMessage(ChatColor.GREEN + "Chest created or eventually re-write");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "You should face a chest!");
                            return true;
                        }
                    case "remove":
                        if (!player.hasPermission("source.remove")) {
                            player.sendMessage(ChatColor.RED + "Missing permission");
                            return true;
                        }
                        plugin.getConfig().set("chests." + strings[1], null);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "AirDrop removed!");
                        return true;
                    case "get":
                        if (!player.hasPermission("source.get")) {
                            player.sendMessage(ChatColor.RED + "Missing permission");
                            return true;
                        }
                        giveKey(player, "testing", 1);

                }
        }
        return false;
    }

    private void sendHelp(Player p) {

    }

    private void giveKey(Player p, String keyName, int amount){
        int newAmount = amount == 0 ? 1 : amount;
        ItemStack key = new ItemStack(Material.valueOf(plugin.getConfig().getString("keys-generic.material")), newAmount);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("keys-generic.name").replace("%KEY%", keyName).replace("%AMOUNT%", String.valueOf(newAmount))));
        keyMeta.setLore(Arrays.asList(plugin.getConfig().getString("keys-generic.lore").replace("%KEY%", keyName).replace("%AMOUNT%", String.valueOf(newAmount)), "chest " + keyName));
        if(plugin.getConfig().getBoolean("keys-generic.glowing")){
            keyMeta.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
            keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        key.setItemMeta(keyMeta);
        p.getInventory().addItem(key);
        p.sendMessage(ChatColor.GREEN + "You got " + newAmount + " keys!");
        return;
    }
}

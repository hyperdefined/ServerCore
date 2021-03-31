package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "/party help - List party chat commands.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/kill - You already know what this does.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/msg <player> <message> - Message another player.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/r <message> - Quick reply to a message.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/ignore <player> - Ignore a player's chat messages.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/ignorelist - See who you ignored.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/vote - For for the server.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/donate - Donation information.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/info - Server information.");
        sender.sendMessage(ChatColor.DARK_AQUA + "/player <player> - See join and last login time.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

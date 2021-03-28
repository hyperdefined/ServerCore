package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandColors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.BLACK + "&0 " + ChatColor.WHITE + "--> " + ChatColor.BLACK + "Black");
        sender.sendMessage(ChatColor.DARK_BLUE + "&1 " + ChatColor.WHITE + "--> " + ChatColor.DARK_BLUE + "Dark Blue");
        sender.sendMessage(ChatColor.DARK_GREEN + "&2 " + ChatColor.WHITE + "--> " + ChatColor.DARK_GREEN + "Dark Green");
        sender.sendMessage(ChatColor.DARK_AQUA + "&3 " + ChatColor.WHITE + "--> " + ChatColor.DARK_AQUA + "Dark Aqua");
        sender.sendMessage(ChatColor.DARK_RED + "&4 " + ChatColor.WHITE + "--> " + ChatColor.DARK_RED + "Dark Red");
        sender.sendMessage(ChatColor.DARK_PURPLE + "&5 " + ChatColor.WHITE + "--> " + ChatColor.DARK_PURPLE + "Purple");
        sender.sendMessage(ChatColor.GOLD + "&6 " + ChatColor.WHITE + "--> " + ChatColor.GOLD + "Gold");
        sender.sendMessage(ChatColor.GRAY + "&7 " + ChatColor.WHITE + "--> " + ChatColor.GRAY + "Gray");
        sender.sendMessage(ChatColor.DARK_GRAY + "&8 " + ChatColor.WHITE + "--> " + ChatColor.DARK_GRAY + "Dark Gray");
        sender.sendMessage(ChatColor.BLUE + "&9 " + ChatColor.WHITE + "--> " + ChatColor.BLUE + "Blue");
        sender.sendMessage(ChatColor.GREEN + "&a " + ChatColor.WHITE + "--> " + ChatColor.GREEN + "Green");
        sender.sendMessage(ChatColor.AQUA + "&b " + ChatColor.WHITE + "--> " + ChatColor.AQUA + "Aqua");
        sender.sendMessage(ChatColor.RED + "&c " + ChatColor.WHITE + "--> " + ChatColor.RED + "Red");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "&d " + ChatColor.WHITE + "--> " + ChatColor.LIGHT_PURPLE + "Pink");
        sender.sendMessage(ChatColor.YELLOW + "&e " + ChatColor.WHITE + "--> " + ChatColor.YELLOW + "Yellow");
        sender.sendMessage(ChatColor.WHITE + "&f " + ChatColor.WHITE + "--> " + ChatColor.WHITE + "White");
        sender.sendMessage(ChatColor.WHITE + "&k " + ChatColor.WHITE + "--> " + ChatColor.MAGIC + "Magic");
        sender.sendMessage(ChatColor.WHITE + "&l " + ChatColor.WHITE + "--> " + ChatColor.BOLD + "Bold");
        sender.sendMessage(ChatColor.WHITE + "&m " + ChatColor.WHITE + "--> " + ChatColor.STRIKETHROUGH + "Strikethrough");
        sender.sendMessage(ChatColor.WHITE + "&n " + ChatColor.WHITE + "--> " + ChatColor.UNDERLINE + "Underline");
        sender.sendMessage(ChatColor.WHITE + "&o " + ChatColor.WHITE + "--> " + ChatColor.ITALIC + "Italic");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

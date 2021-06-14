package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandColors implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.BLACK + "&0 " + ChatColor.WHITE + "--> " + ChatColor.BLACK + "Black "
                + ChatColor.WHITE + " (ID: black)");
        sender.sendMessage(ChatColor.DARK_BLUE + "&1 " + ChatColor.WHITE + "--> " + ChatColor.DARK_BLUE + "Dark Blue"
                + ChatColor.WHITE + " (ID: darkblue)");
        sender.sendMessage(ChatColor.DARK_GREEN + "&2 " + ChatColor.WHITE + "--> " + ChatColor.DARK_GREEN + "Dark Green"
                + ChatColor.WHITE + " (ID: darkgreen)");
        sender.sendMessage(ChatColor.DARK_AQUA + "&3 " + ChatColor.WHITE + "--> " + ChatColor.DARK_AQUA + "Dark Aqua"
                + ChatColor.WHITE + " (ID: darkaqua)");
        sender.sendMessage(ChatColor.DARK_RED + "&4 " + ChatColor.WHITE + "--> " + ChatColor.DARK_RED + "Dark Red"
                + ChatColor.WHITE + " (ID: darkred)");
        sender.sendMessage(ChatColor.DARK_PURPLE + "&5 " + ChatColor.WHITE + "--> " + ChatColor.DARK_PURPLE + "Purple"
                + ChatColor.WHITE + " (ID: purple)");
        sender.sendMessage(ChatColor.GOLD + "&6 " + ChatColor.WHITE + "--> " + ChatColor.GOLD + "Gold" + ChatColor.WHITE
                + " (ID: gold)");
        sender.sendMessage(ChatColor.GRAY + "&7 " + ChatColor.WHITE + "--> " + ChatColor.GRAY + "Gray" + ChatColor.WHITE
                + " (ID: gray)");
        sender.sendMessage(ChatColor.DARK_GRAY + "&8 " + ChatColor.WHITE + "--> " + ChatColor.DARK_GRAY + "Dark Gray"
                + ChatColor.WHITE + " (ID: darkgray)");
        sender.sendMessage(ChatColor.BLUE + "&9 " + ChatColor.WHITE + "--> " + ChatColor.BLUE + "Blue" + ChatColor.WHITE
                + " (ID: blue");
        sender.sendMessage(ChatColor.GREEN + "&a " + ChatColor.WHITE + "--> " + ChatColor.GREEN + "Green"
                + ChatColor.WHITE + " (ID: green)");
        sender.sendMessage(ChatColor.AQUA + "&b " + ChatColor.WHITE + "--> " + ChatColor.AQUA + "Aqua" + ChatColor.WHITE
                + " (ID: aqua)");
        sender.sendMessage(ChatColor.RED + "&c " + ChatColor.WHITE + "--> " + ChatColor.RED + "Red" + ChatColor.WHITE
                + " (ID: red)");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "&d " + ChatColor.WHITE + "--> " + ChatColor.LIGHT_PURPLE + "Pink"
                + ChatColor.WHITE + " (ID: black)");
        sender.sendMessage(ChatColor.YELLOW + "&e " + ChatColor.WHITE + "--> " + ChatColor.YELLOW + "Yellow"
                + ChatColor.WHITE + " (ID: yellow)");
        sender.sendMessage(ChatColor.WHITE + "&f " + ChatColor.WHITE + "--> " + ChatColor.WHITE + "White"
                + ChatColor.WHITE + " (ID: white)");
        sender.sendMessage(ChatColor.WHITE + "&k " + ChatColor.WHITE + "--> " + ChatColor.MAGIC + "Magic");
        sender.sendMessage(ChatColor.WHITE + "&l " + ChatColor.WHITE + "--> " + ChatColor.BOLD + "Bold");
        sender.sendMessage(
                ChatColor.WHITE + "&m " + ChatColor.WHITE + "--> " + ChatColor.STRIKETHROUGH + "Strikethrough");
        sender.sendMessage(ChatColor.WHITE + "&n " + ChatColor.WHITE + "--> " + ChatColor.UNDERLINE + "Underline");
        sender.sendMessage(ChatColor.WHITE + "&o " + ChatColor.WHITE + "--> " + ChatColor.ITALIC + "Italic");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

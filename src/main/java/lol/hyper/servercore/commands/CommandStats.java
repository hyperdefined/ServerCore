package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandStats implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "https://stats.destroymc.net");
        sender.sendMessage(ChatColor.DARK_AQUA + "View player statistics here. This site will pull information from the server every 5 minutes.");
        sender.sendMessage(ChatColor.DARK_AQUA + "However, player data is not saved instantly, so you will not see the data that fast. Give it a few more minutes if you want results instantly.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

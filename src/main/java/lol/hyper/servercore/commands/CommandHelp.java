package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "The commands list is too big to fit here.");
        sender.sendMessage(ChatColor.DARK_AQUA + "Visit https://destroymc.net/commands to see all commands.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

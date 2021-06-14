package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandBroadcast implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        // Check if sender is a player.
        if (!(sender instanceof Player)) {
            // Check for valid syntax.
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Invalid syntax. Try /br <message> instead.");
            } else {
                // Broadcast the message to everyone.
                Bukkit.broadcastMessage(ChatColor.GOLD + "[SERVER] " + String.join(" ", args));
            }
        }
        return true;
    }
}

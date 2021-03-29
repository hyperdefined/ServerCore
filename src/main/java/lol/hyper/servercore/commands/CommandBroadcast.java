package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if sender is a player.
        if (!(sender instanceof Player)) {
            // Check for valid syntax.
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Invalid syntax. Try /br <message> instead.");
            } else {

                // Get each arg from the command and put it into 1 message.
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg);
                    message.append(" ");
                }
                // Broadcast the message to everyone.
                Bukkit.broadcastMessage(ChatColor.GOLD + "[SERVER] " + message.toString());
            }
        }
        return true;
    }
}

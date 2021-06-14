package lol.hyper.servercore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandKill implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player for this command.");
        } else {
            // Damage and set health to zero. This fixes the bug of showing the death message as last damage taken.
            ((Player) sender).damage(0.1D);
            ((Player) sender).setHealth(0.0D);
        }
        return true;
    }
}

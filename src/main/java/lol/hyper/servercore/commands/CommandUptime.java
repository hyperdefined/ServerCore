package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record CommandUptime(ServerCore serverCore) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String uptimeText;
        if (sender instanceof ConsoleCommandSender) {
            uptimeText = PlaceholderAPI.setPlaceholders(
                    Bukkit.getOfflinePlayer(ServerCore.hyperdefined), serverCore.config.getString("uptime"));
        } else {
            uptimeText = PlaceholderAPI.setPlaceholders((Player) sender, serverCore.config.getString("uptime"));
        }
        sender.sendMessage(uptimeText);
        return true;
    }
}

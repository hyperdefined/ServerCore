package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandDelaySend implements CommandExecutor {

    private final ServerCore serverCore;

    public CommandDelaySend(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Get player and messages.
        Player player = Bukkit.getPlayerExact(args[0]);
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());
        ArrayList<String> messages = new ArrayList<>(Arrays.asList(args));
        // Remove the first command arg since it's the player's name.
        messages.remove(0);

        StringBuilder playerMessage = new StringBuilder();

        for (String arg : messages) {
            playerMessage.append(arg);
            playerMessage.append(" ");
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(serverCore, () -> {
            String finalMessage = PlaceholderAPI.setPlaceholders(offlinePlayer, playerMessage.toString());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', finalMessage));
        }, 20);
        return true;
    }
}

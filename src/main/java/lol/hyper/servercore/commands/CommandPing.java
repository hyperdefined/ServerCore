package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {

    private final ServerCore serverCore;

    public CommandPing(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                Player player = (Player) sender;
                String pingYou = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("ping-you").replace("{PLAYER}", player.getName()));
                sender.sendMessage(PlaceholderAPI.setPlaceholders(player, pingYou));
                return true;
            case 1:
                Player playerOther = Bukkit.getPlayerExact(args[0]);
                if (Bukkit.getPlayerExact(args[0]) != null && !ServerCore.isVanished(args[0])) {
                    String pingOther = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("ping-other").replace("{PLAYER}", playerOther.getName()));
                    sender.sendMessage(PlaceholderAPI.setPlaceholders(playerOther, pingOther));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("player-not-found")));
                }
                return true;
        }
        return true;
    }
}

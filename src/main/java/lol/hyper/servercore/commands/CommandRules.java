package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandRules implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(
                ChatColor.DARK_AQUA
                        + "The server blocks game breaking “exploits,” such as extreme speeds and flight. Players are able to do anything they want, just don’t be dumb. It’s a block game.");
        sender.sendMessage(ChatColor.DARK_AQUA + "Any extreme cases could lead into a ban from the server.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

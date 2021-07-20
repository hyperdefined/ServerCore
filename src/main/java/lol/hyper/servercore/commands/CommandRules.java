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
                        + "Cheating/hacking is allowed. Just don't do anything illegal.");
        sender.sendMessage(ChatColor.DARK_AQUA + "Any extreme cases could lead into a ban from the server.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandFeedback implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "https://github.com/limitedsurvival/Feedback/issues");
        sender.sendMessage(ChatColor.DARK_AQUA + "Track any of the current tasks on the GitHub above.");
        sender.sendMessage(ChatColor.DARK_AQUA + "Feel free to make a new issue for any suggestions or bugs.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

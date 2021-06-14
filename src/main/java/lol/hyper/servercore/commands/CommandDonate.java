package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandDonate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "https://donate.limitedsurvival.com");
        sender.sendMessage(ChatColor.DARK_AQUA + "Donating allows you to change the color of your name permanently.");
        sender.sendMessage(ChatColor.DARK_AQUA + "We aren't taking donations currently.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}
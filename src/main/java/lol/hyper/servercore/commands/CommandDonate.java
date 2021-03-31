package lol.hyper.servercore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDonate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        sender.sendMessage(ChatColor.DARK_AQUA + "https://donate.blockgame.fun");
        sender.sendMessage(ChatColor.DARK_AQUA + "Donating allows you to change the color of your name permanently. You also can bypass the captcha.");
        sender.sendMessage(ChatColor.DARK_AQUA + "We aren't taking donations currently.");
        sender.sendMessage(ChatColor.GOLD + "--------------------------------------------");
        return true;
    }
}

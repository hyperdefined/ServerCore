package lol.hyper.servercore.commands;

import lol.hyper.servercore.tools.ColorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandColor implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("servercore.donator")) {
            sender.sendMessage(ChatColor.RED + "You need to donate to change your name color!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You need to specify which color you want. See /colors for valid colors.");
            return true;
        }
        String color = args[0];
        if (ColorManager.colors.contains(color)) {
            // color is valid
            ColorManager.updateUsersColor((Player) sender, color);
        } else {
            // color is not valid
            sender.sendMessage(ChatColor.RED + "That is not a valid color option. Please see /colors for valid colors.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("servercore.donator")) {
            return ColorManager.colors;
        }
        return null;
    }
}

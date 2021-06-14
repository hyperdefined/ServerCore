package lol.hyper.servercore.commands;

import lol.hyper.servercore.tools.FuckWitherSkulls;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandKillWitherSkulls implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender.isOp()) {
            FuckWitherSkulls.killWitherSkulls();
            sender.sendMessage(ChatColor.GOLD + "Killing " + FuckWitherSkulls.countSkulls() + " wither skulls.");
        }
        return true;
    }
}

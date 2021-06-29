package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandReload implements CommandExecutor {

    private final ServerCore serverCore;

    public CommandReload(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp() || sender instanceof ConsoleCommandSender) {
            serverCore.loadConfig();
            sender.sendMessage(ChatColor.GREEN + "Config reloaded.");
        }
        return true;
    }
}

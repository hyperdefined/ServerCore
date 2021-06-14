package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public record CommandDupeCharge(ServerCore serverCore) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String player = args[0];
        int i = Integer.parseInt(args[1]);
        serverCore.dupeChargesFUCK.updateCharges(Bukkit.getPlayerExact(player).getUniqueId(), i);
        serverCore.logger.info("Giving " + Bukkit.getPlayer(player).getName() + " " + i + " dupe charges.");
        return true;
    }
}

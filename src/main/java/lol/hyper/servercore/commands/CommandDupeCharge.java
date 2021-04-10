package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CommandDupeCharge implements CommandExecutor {

    private final ServerCore serverCore;

    public CommandDupeCharge(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        UUID player = UUID.fromString(args[0]);
        int i = Integer.parseInt(args[1]);
        serverCore.dupeChargesFUCK.updateCharges(player, i);
        serverCore.logger.info("Giving " + Bukkit.getPlayer(player).getName() + " " + i + " dupe charges.");
        return true;
    }
}

package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandQuickRestart implements CommandExecutor {

    private final ServerCore serverCore;
    int duration;

    public CommandQuickRestart(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        duration = Integer.parseInt(args[0]) * 60;
        Bukkit.getScheduler()
                .scheduleSyncRepeatingTask(
                        serverCore,
                        () -> {
                            if (duration == 1800) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-minutes", 30));
                            }
                            if (duration == 900) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-minutes", 15));
                            }
                            if (duration == 600) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-minutes", 10));
                            }
                            if (duration == 300) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-minutes", 5));
                            }
                            if (duration == 240) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-minutes", 4));
                            }
                            if (duration == 180) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-minutes", 3));
                            }
                            if (duration == 120) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-minutes", 2));
                            }
                            if (duration == 60) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-minute", 1));
                            }
                            if (duration == 30) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-seconds", 30));
                            }
                            if (duration == 20) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-seconds", 20));
                            }
                            if (duration == 10) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br "
                                                        + getConfigMessage(
                                                                "command-messages.restarts.quick-seconds", 10));
                            }
                            if (duration == 9) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 9));
                            }
                            if (duration == 8) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 8));
                            }
                            if (duration == 7) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 7));
                            }
                            if (duration == 6) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 6));
                            }
                            if (duration == 5) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 5));
                            }
                            if (duration == 4) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 4));
                            }
                            if (duration == 3) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 3));
                            }
                            if (duration == 2) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-seconds", 2));
                            }
                            if (duration == 1) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.quick-second", 1));
                            }
                            if (duration == 0) {
                                Bukkit.getServer()
                                        .dispatchCommand(
                                                Bukkit.getConsoleSender(),
                                                "br " + getConfigMessage("command-messages.restarts.restarting"));
                            }
                            if (duration == -1) {
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
                            }
                            duration--;
                        },
                        0,
                        20);
        return true;
    }

    private String getConfigMessage(String configPath, int time) {
        String rawMessage = serverCore.config.getString(configPath);
        rawMessage = rawMessage.replace("{TIME}", Integer.toString(time));
        return ChatColor.translateAlternateColorCodes('&', rawMessage);
    }

    private String getConfigMessage(String configPath) {
        String rawMessage = serverCore.config.getString(configPath);
        return ChatColor.translateAlternateColorCodes('&', rawMessage);
    }
}

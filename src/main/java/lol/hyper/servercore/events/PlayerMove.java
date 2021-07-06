package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class PlayerMove implements Listener {

    private final ServerCore serverCore;
    public Set<Player> ignoreMovement = new HashSet<>();

    public PlayerMove(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location oldLoc = event.getFrom();
        Location newLoc = event.getTo();
        double distX = newLoc.getX() - oldLoc.getX();
        double distZ = newLoc.getZ() - oldLoc.getZ();
        double speed = Math.hypot(distX, distZ);

        if (player.isInsideVehicle()) {
            if (ignoreMovement.contains(player)) {
                return;
            }
            if (player.getWorld().getEnvironment() == World.Environment.NETHER && player.getLocation().getY() > 127) {
                if (speed > serverCore.config.getDouble("speed.entity.nether")) {
                    event.setTo(oldLoc);
                    player.leaveVehicle();
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            } else {
                if (speed > serverCore.config.getDouble("speed.entity.overworld")) {
                    event.setTo(oldLoc);
                    player.leaveVehicle();
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            }
        } else if (player.isGliding()) {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER && player.getLocation().getY() > 127) {
                if (speed > serverCore.config.getDouble("speed.elytra.max-speed-nether")) {
                    event.setCancelled(true);
                    player.setGliding(false);
                    event.setTo(oldLoc);
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            } else {
                if (speed > serverCore.config.getDouble("speed.elytra.max-speed-overworld")) {
                    event.setCancelled(true);
                    player.setGliding(false);
                    event.setTo(oldLoc);
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            }
        } else {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER && player.getLocation().getY() > 127) {
                if (speed > serverCore.config.getDouble("speed.normal.max-speed-nether")) {
                    event.setCancelled(true);
                    event.setTo(oldLoc);
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            } else {
                if (speed > serverCore.config.getDouble("speed.normal.max-speed-overworld")) {
                    event.setCancelled(true);
                    event.setTo(oldLoc);
                    player.sendMessage(ChatColor.RED + "You are going too fast.");
                }
            }
        }
        if (player.getLocation().getY() < 0) {
            World world = player.getWorld();
            if (world.getEnvironment() == World.Environment.NETHER
                    || world.getEnvironment() == World.Environment.NORMAL) {
                Location toSpawn = new Location(
                        player.getLocation().getWorld(),
                        player.getLocation().getBlockX() + 0.5,
                        4,
                        player.getLocation().getBlockZ() + 0.5);
                toSpawn.add(0, 1, 0).getBlock().setType(Material.AIR);
                toSpawn.add(0, 1, 0).getBlock().setType(Material.AIR);
                player.teleport(toSpawn.subtract(0, 1, 0));
                player.sendMessage(ChatColor.RED + "You are not allow to go down here.");
            }
        }
    }
}

package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class PlayerMove implements Listener {

    private final ServerCore serverCore;
    public Set<Player> rightClicked = new HashSet<>();

    public PlayerMove(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (player.isInsideVehicle()) {
            if (rightClicked.contains(player)) {
                rightClicked.remove(player);
                return;
            }
            Entity vehicle = player.getVehicle();
            Location oldLoc = event.getFrom();
            Location newLoc = event.getTo();

            double distX = newLoc.getX() - oldLoc.getX();
            double distZ = newLoc.getZ() - oldLoc.getZ();
            double speed = Math.hypot(distX, distZ);

            // this will still eject if you right click from a distance
            if (speed > serverCore.config.getInt("boat-speed")) {
                new BukkitRunnable() {
                    public void run() {
                        vehicle.eject();
                    }
                }.runTaskLater(serverCore, 1L);

                player.sendMessage(ChatColor.RED + "You are going too fast.");
            }
        }

        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            if (player.getLocation().getY() > 127) {
                Location toSpawn = new Location(
                        player.getLocation().getWorld(),
                        player.getLocation().getBlockX() + 0.5,
                        127,
                        player.getLocation().getBlockZ() + 0.5);
                toSpawn.subtract(0, 1, 0).getBlock().setType(Material.AIR);
                toSpawn.subtract(0, 1, 0).getBlock().setType(Material.AIR);
                toSpawn.subtract(0, 1, 0).getBlock().setType(Material.NETHERRACK);
                player.teleport(toSpawn.add(0, 1, 0));
                player.sendMessage(ChatColor.RED + "You are not allow to go up here.");
            }
        }
        if (player.getLocation().getY() < 0) {
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

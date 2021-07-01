package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlayerInteractEntity implements Listener {

    private final ServerCore serverCore;
    private final Set<String> rideableThings =
            new HashSet<>(Arrays.asList("MINECART", "HORSE", "DONKEY", "MULE", "LLAMA", "STRIDER", "BOAT"));

    public PlayerInteractEntity(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        String type = event.getRightClicked().getType().toString();
        if (rideableThings.contains(type)) {
            serverCore.playerMove.ignoreMovement.add(player);
            new BukkitRunnable() {
                public void run() {
                    serverCore.playerMove.ignoreMovement.remove(player);
                }
            }.runTaskLater(serverCore, 10);
        }
    }
}

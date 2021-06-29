package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntity implements Listener {

    private final ServerCore serverCore;

    public PlayerInteractEntity(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        serverCore.playerMove.rightClicked.add(player);

        Bukkit.getScheduler().runTaskLater(serverCore, () -> serverCore.playerMove.rightClicked.remove(player), 100);
    }
}

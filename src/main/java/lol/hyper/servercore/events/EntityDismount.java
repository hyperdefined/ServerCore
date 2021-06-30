package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class EntityDismount implements Listener {

    private final ServerCore serverCore;

    public EntityDismount(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            serverCore.playerMove.mounted.remove((Player) entity);
        }
    }
}

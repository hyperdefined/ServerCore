package lol.hyper.servercore.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            if (player.getInventory().getChestplate() == null) {
                return;
            }
            Material chestPlate = player.getInventory().getChestplate().getType();
            if (chestPlate == Material.ELYTRA) {
                event.setCancelled(event.getCause() == EntityDamageEvent.DamageCause.FALL);
            }
        }
    }
}

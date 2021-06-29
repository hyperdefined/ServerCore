package lol.hyper.servercore.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryOpen {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        for (ItemStack item : event.getInventory().getContents()) {
            if (item != null) {
                if (item.getAmount() > item.getMaxStackSize()) {
                    item.setAmount(item.getMaxStackSize());
                    Bukkit.getLogger()
                            .warning("Reverting invalid stack of "
                                    + item.getType() + ".");
                    Bukkit.getLogger()
                            .warning("Location is " + event.getPlayer().getLocation());
                }
            }
        }
    }
}

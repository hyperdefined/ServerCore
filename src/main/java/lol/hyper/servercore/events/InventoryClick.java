package lol.hyper.servercore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {

    // this let's players have colors in names, kinda shit but it works
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory instanceof AnvilInventory) {
            InventoryView view = event.getView();
            if (event.getRawSlot() == view.convertSlot(event.getRawSlot())) {
                if (event.getRawSlot() == 2) {
                    ItemStack item = event.getCurrentItem();
                    if (item != null) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta != null) {
                            if (meta.hasDisplayName()) {
                                String displayName = meta.getDisplayName();
                                meta.setDisplayName(
                                        net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', displayName));
                                item.setItemMeta(meta);
                            }
                        }
                    }
                }
            }
        }
    }
}

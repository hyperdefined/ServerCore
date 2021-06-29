package lol.hyper.servercore.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {

    // this let's players have colors in names, kinda shit but it works
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.isCancelled() || event.getCurrentItem() == null) {
            return;
        }

        Inventory inventory = event.getInventory();
        if (inventory instanceof AnvilInventory) {
            AnvilInventory inv = (AnvilInventory) event.getInventory();
            ItemStack itemStack = inv.getItem(2);
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
                itemStack.setItemMeta(meta);
            }
        }
    }
}

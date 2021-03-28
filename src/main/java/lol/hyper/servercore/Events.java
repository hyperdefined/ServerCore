package lol.hyper.servercore;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.nio.charset.StandardCharsets;

public class Events implements Listener {

    private final ServerCore serverCore;

    public Events(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.private")));
            String publicJoin = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.first").replace("{PLAYER}", player.getName()));
            Bukkit.getLogger().info(PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(ServerCore.hyperdefined), publicJoin));
        } else {
            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.normal")));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String publicLeave = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("leave-message").replace("{PLAYER}", player.getName()));
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', publicLeave));
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Block placed = event.getBlockPlaced();
        if (placed.getType() == Material.BEDROCK ||
                placed.getType() == Material.BARRIER ||
                placed.getType() == Material.PLAYER_HEAD ||
                placed.getType() == Material.PLAYER_WALL_HEAD ||
                placed.getType() == Material.STRUCTURE_BLOCK ||
                placed.getType() == Material.STRUCTURE_VOID ||
                placed.getType() == Material.COMMAND_BLOCK ||
                placed.getType() == Material.COMMAND_BLOCK_MINECART ||
                placed.getType() == Material.CHAIN_COMMAND_BLOCK ||
                placed.getType() == Material.REPEATING_COMMAND_BLOCK ||
                placed.getType() == Material.SPAWNER
        ) {
            event.setCancelled(true);
            Bukkit.getLogger().info(event.getPlayer().getName() + " tried placing " + placed.getType() + " at " + placed.getLocation());
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        for (ItemStack item : event.getInventory().getContents()) {
            if (item != null) {
                if (item.getAmount() > item.getMaxStackSize()) {
                    item.setAmount(item.getMaxStackSize());
                    Bukkit.getLogger().warning("Reverting invalid stack of " + item.getType().toString() + ".");
                    Bukkit.getLogger().warning("Location is " + event.getPlayer().getLocation());
                }
            }
        }
    }

    // this let's players have colors in names, kinda shit but it works
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event){
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
                                meta.setDisplayName(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', displayName));
                                item.setItemMeta(meta);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEdit(PlayerEditBookEvent event) {
        for (String page : event.getNewBookMeta().getPages()) {
            if (!StandardCharsets.ISO_8859_1.newEncoder().canEncode((page))) {
                event.setCancelled(true);
            }
        }
    }
}

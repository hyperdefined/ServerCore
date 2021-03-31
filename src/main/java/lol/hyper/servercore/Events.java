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
import org.bukkit.event.player.*;
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
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.private")));
            String publicJoin = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.first").replace("{PLAYER}", player.getName()));
            Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(ServerCore.hyperdefined), publicJoin));
        } else {
            if (!ServerCore.isVanished(player.getName())) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.normal").replace("{PLAYER}", player.getName())));
            }
        }
        player.sendMessage(ChatColor.GOLD + "Make sure to read /rules since this is not full anarchy.");

        ServerCore.lastChange.put(event.getPlayer(), System.currentTimeMillis()); // x1D - Offhand Swap fix
        ServerCore.warnings.put(event.getPlayer(), 0); // x1D - Offhand Swap fix
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        String publicLeave = ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("leave-message").replace("{PLAYER}", player.getName()));
        if (!ServerCore.isVanished(player.getName())) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', publicLeave));
        }
        player.setGliding(false);
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
                event.getPlayer().sendMessage(ChatColor.RED + "Invalid characters.");
            }
        }
    }

    @EventHandler
    public void onMainHandChange(PlayerSwapHandItemsEvent event) {
        if (ServerCore.lastChange.get(event.getPlayer()) != null && ServerCore.lastChange.get(event.getPlayer()) + 250 > System.currentTimeMillis()) {
            ServerCore.warnings.put(event.getPlayer(), ServerCore.warnings.get(event.getPlayer()) + 1);
            event.getPlayer().sendMessage(ChatColor.GOLD + "Please slow down or you will be kicked. (" + ServerCore.warnings.get(event.getPlayer()) + "/5)");
            if (ServerCore.warnings.get(event.getPlayer()) > 4) {
                event.getPlayer().kickPlayer("nah");
                ServerCore.warnings.put(event.getPlayer(), 0);
            }
        }
        ServerCore.lastChange.put(event.getPlayer(), System.currentTimeMillis());
    }
}

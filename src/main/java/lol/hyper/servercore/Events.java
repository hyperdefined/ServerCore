package lol.hyper.servercore;

import lol.hyper.servercore.tools.ColorManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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

public record Events(ServerCore serverCore) implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', serverCore.config.getString("join-message.private")));
            String publicJoin = ChatColor.translateAlternateColorCodes(
                    '&', serverCore.config.getString("join-message.first").replace("{PLAYER}", player.getName()));
            Bukkit.broadcastMessage(
                    PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(ServerCore.hyperdefined), publicJoin));
        } else {
            if (!ServerCore.isVanished(player.getName())) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes(
                        '&', serverCore.config.getString("join-message.normal").replace("{PLAYER}", player.getName())));
            }
        }
        player.sendMessage(ChatColor.GOLD + "Welcome to DESTROYMC.NET!");
        player.sendMessage(ChatColor.GOLD + "Make sure to read /rules since this is not full anarchy.");

        for (ItemStack item : player.getInventory().getContents()) {
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

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        String publicLeave = ChatColor.translateAlternateColorCodes(
                '&', serverCore.config.getString("leave-message").replace("{PLAYER}", player.getName()));
        if (!ServerCore.isVanished(player.getName())) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', publicLeave));
        }
        player.setGliding(false);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Block placed = event.getBlockPlaced();
        if (placed.getType() == Material.BEDROCK
                || placed.getType() == Material.BARRIER
                || placed.getType() == Material.PLAYER_HEAD
                || placed.getType() == Material.PLAYER_WALL_HEAD
                || placed.getType() == Material.STRUCTURE_BLOCK
                || placed.getType() == Material.STRUCTURE_VOID
                || placed.getType() == Material.COMMAND_BLOCK
                || placed.getType() == Material.COMMAND_BLOCK_MINECART
                || placed.getType() == Material.CHAIN_COMMAND_BLOCK
                || placed.getType() == Material.REPEATING_COMMAND_BLOCK
                || placed.getType() == Material.SPAWNER) {
            event.setCancelled(true);
            Bukkit.getLogger()
                    .info(event.getPlayer().getName() + " tried placing " + placed.getType() + " at "
                            + placed.getLocation());
        }
    }

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
    public void onPlayerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

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
            Location toSpawn = new Location(player.getLocation().getWorld(), player.getLocation().getBlockX() + 0.5, 4, player.getLocation().getBlockZ() + 0.5);
            toSpawn.add(0, 1, 0).getBlock().setType(Material.AIR);
            toSpawn.add(0, 1, 0).getBlock().setType(Material.AIR);
            player.teleport(toSpawn.subtract(0, 1, 0));
            player.sendMessage(ChatColor.RED + "You are not allow to go down here.");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (ColorManager.getCurrentColor(player) == null) {
            return;
        }
        String color = ColorManager.colorsToCodes.get(ColorManager.getCurrentColor(player));
        event.setFormat("<" + color + player.getName() + ChatColor.RESET + "> " + event.getMessage());
    }
}

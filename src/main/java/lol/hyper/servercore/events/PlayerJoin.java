package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoin implements Listener {

    private final ServerCore serverCore;

    public PlayerJoin(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

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
}

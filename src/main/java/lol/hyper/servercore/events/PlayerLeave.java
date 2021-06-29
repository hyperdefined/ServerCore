package lol.hyper.servercore.events;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    private final ServerCore serverCore;

    public PlayerLeave(ServerCore serverCore) {
        this.serverCore = serverCore;
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
}

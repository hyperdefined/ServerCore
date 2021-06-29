package lol.hyper.servercore.events;

import lol.hyper.servercore.tools.ColorManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {

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

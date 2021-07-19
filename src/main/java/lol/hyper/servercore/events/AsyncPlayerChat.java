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
        String color;
        if (ColorManager.getCurrentColor(player) == null) {
            color = ColorManager.colorsToCodes.get("reset");
        } else {
            color = ColorManager.colorsToCodes.get(ColorManager.getCurrentColor(player));
        }
        event.setFormat("<" + color + "%s" + ChatColor.RESET + "> " + "%s");
    }
}

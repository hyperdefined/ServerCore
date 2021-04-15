package lol.hyper.servercore.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ColorManager {

    public static ArrayList<String> colors = new ArrayList<>(Arrays.asList("black",
            "darkblue",
            "darkgreen",
            "darkaqua",
            "darkred",
            "darkpurple",
            "gold",
            "gray",
            "darkgray",
            "blue",
            "green",
            "aqua",
            "red",
            "lightpurple",
            "yellow",
            "reset"));

    public static Map<String, String> colorsToCodes = new HashMap<String, String>() {{
        put("darkblue", String.valueOf(ChatColor.DARK_BLUE));
        put("darkgreen", String.valueOf(ChatColor.DARK_GREEN));
        put("darkaqua", String.valueOf(ChatColor.DARK_AQUA));
        put("darkred", String.valueOf(ChatColor.DARK_RED));
        put("darkpurple", String.valueOf(ChatColor.DARK_PURPLE));
        put("gold", String.valueOf(ChatColor.GOLD));
        put("gray", String.valueOf(ChatColor.GRAY));
        put("darkgray", String.valueOf(ChatColor.DARK_GRAY));
        put("blue", String.valueOf(ChatColor.BLUE));
        put("green", String.valueOf(ChatColor.GREEN));
        put("aqua", String.valueOf(ChatColor.AQUA));
        put("red", String.valueOf(ChatColor.RED));
        put("lightpurple", String.valueOf(ChatColor.LIGHT_PURPLE));
        put("yellow", String.valueOf(ChatColor.YELLOW));
        put("reset", String.valueOf(ChatColor.WHITE));
    }};

    public static String getCurrentColor(Player player) {
        for (String color : colors) {
            if (player.hasPermission("servercore." + color)) {
                return color;
            }
        }
        return null;
    }

    public static void updateUsersColor(Player player, String newColor)
    {
        //remove the current one first
        String currentColor = getCurrentColor(player);
        if (currentColor != null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset servercore." + currentColor);
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set servercore." + newColor + " true");
        player.sendMessage(ChatColor.GREEN + "Your name color is now " + newColor + ".");
    }
}

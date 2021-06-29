package lol.hyper.servercore.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

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
}

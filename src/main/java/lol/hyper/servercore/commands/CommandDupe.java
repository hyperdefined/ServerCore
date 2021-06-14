package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public record CommandDupe(ServerCore serverCore) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!serverCore.config.getBoolean("enable-dupe")) {
            sender.sendMessage(ChatColor.RED + "This is currently disabled. Check back later.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "You have "
                    + serverCore.dupeChargesFUCK.getDupeCharges(
                    Bukkit.getPlayerExact(sender.getName()).getUniqueId()) + " dupe charges left.");
            sender.sendMessage(
                    ChatColor.GREEN + "Type /dupe confirm to confirm you want to dupe what you are holding.");
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("confirm")) {
                Player player = (Player) sender;
                long dupeCharges = serverCore.dupeChargesFUCK.getDupeCharges(player.getUniqueId());
                if (dupeCharges >= 1) {
                    PlayerInventory inv = player.getPlayer().getInventory();
                    ItemStack heldItem = inv.getItemInMainHand();
                    ItemStack copy = new ItemStack(heldItem);
                    if (heldItem.getType() == Material.AIR) {
                        sender.sendMessage(ChatColor.RED + "You aren't holding anything!");
                        return true;
                    }
                    int stackSize = heldItem.getAmount();
                    for (int i = 0; i < stackSize; i++) {
                        copy.setAmount(1);
                        player.getWorld().dropItem(player.getLocation(), copy);
                    }
                    serverCore.dupeChargesFUCK.updateCharges(player.getUniqueId(), dupeCharges - 1);
                    serverCore.logger.info(player.getName() + " duped " + heldItem.getType() + ". Old: " + stackSize
                            + " New: " + stackSize * 2);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have enough dupe charges.");
                }
            }
        }
        return true;
    }
}

package lol.hyper.servercore.commands;

import lol.hyper.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;

public class CommandDupe implements CommandExecutor {

    private final ServerCore serverCore;

    public CommandDupe(ServerCore serverCore) {
        this.serverCore = serverCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!serverCore.config.getBoolean("enable-dupe")) {
            sender.sendMessage(ChatColor.RED + "This is currently disabled. Check back later.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "You have " + serverCore.dupeChargesFUCK.getDupeCharges(Bukkit.getPlayerExact(sender.getName()).getUniqueId()) + " dupe charges left.");
            sender.sendMessage(ChatColor.GREEN + "Type /dupe confirm to confirm you want to dupe what you are holding.");
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("confirm")) {
                Player player = (Player) sender;
                long dupeCharges = serverCore.dupeChargesFUCK.getDupeCharges(player.getUniqueId());
                if (dupeCharges >= 1) {
                    PlayerInventory inv = player.getPlayer().getInventory();
                    ItemStack heldItem = inv.getItemInMainHand();
                    if (heldItem.getType() == Material.AIR) {
                        sender.sendMessage(ChatColor.RED + "You aren't holding anything!");
                        return true;
                    }
                    int doubleStackSize = heldItem.getAmount();
                    for (int i = 0; i < doubleStackSize; i++) {
                        player.getWorld().dropItem(player.getLocation(), heldItem);
                    }
                    serverCore.dupeChargesFUCK.updateCharges(player.getUniqueId(), dupeCharges - 1);
                    serverCore.logger.info(player.getName() + " duped " + heldItem.getType() + ". Old: " + doubleStackSize /2 + " New: " + doubleStackSize);
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have enough dupe charges.");
                }
            }
        }
        return true;
    }
}

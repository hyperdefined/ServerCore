package lol.hyper.servercore;

import lol.hyper.servercore.commands.*;
import lol.hyper.servercore.events.*;
import lol.hyper.servercore.tools.AutoMessages;
import lol.hyper.servercore.tools.FuckWitherSkulls;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public final class ServerCore extends JavaPlugin {

    public static final UUID hyperdefined = UUID.fromString("311be5cd-d17c-49b3-bf47-f781fdbcc929");
    public final File configFile = new File(this.getDataFolder(), "config.yml");
    public final Path dupeCharges = Paths.get(this.getDataFolder() + File.separator + "dupecharges");
    public final Logger logger = this.getLogger();
    public FileConfiguration config = this.getConfig();
    public AsyncPlayerChat asyncPlayerChat;
    public BlockPlace blockPlace;
    public EntityDamage entityDamage;
    public InventoryClick inventoryClick;
    public InventoryOpen inventoryOpen;
    public PlayerEditBook playerEditBook;
    public PlayerInteractEntity playerInteractEntity;
    public PlayerJoin playerJoin;
    public PlayerLeave playerLeave;
    public PlayerMove playerMove;
    public CommandBroadcast commandBroadcast;
    public CommandColors commandColors;
    public CommandDelaySend commandDelaySend;
    public CommandDonate commandDonate;
    public CommandHelp commandHelp;
    public CommandKill commandKill;
    public CommandKillWitherSkulls commandKillWitherSkulls;
    public CommandPing commandPing;
    public CommandRules commandRules;
    public CommandUptime commandUptime;
    public AutoMessages autoMessages;
    public CommandFeedback commandFeedback;
    public CommandColor commandColor;
    public CommandReload commandReload;
    public CommandQuickRestart commandQuickRestart;
    public CommandStats commandStats;

    /**
     * @param player player to check if vanished
     * @return returns if player is vanished or not
     */
    public static boolean isVanished(String player) {
        if (Bukkit.getPlayerExact(player) == null) {
            return false;
        } else {
            Player player2 = Bukkit.getPlayerExact(player);
            assert player2 != null;
            for (MetadataValue meta : player2.getMetadata("vanished")) {
                if (meta.asBoolean()) return true;
            }
        }
        return false;
    }

    @Override
    public void onEnable() {
        asyncPlayerChat = new AsyncPlayerChat();
        blockPlace = new BlockPlace();
        entityDamage = new EntityDamage();
        inventoryClick = new InventoryClick();
        inventoryOpen = new InventoryOpen();
        playerEditBook = new PlayerEditBook();
        playerInteractEntity = new PlayerInteractEntity(this);
        playerJoin = new PlayerJoin(this);
        playerLeave = new PlayerLeave(this);
        playerMove = new PlayerMove(this);
        commandColors = new CommandColors();
        commandBroadcast = new CommandBroadcast();
        commandDelaySend = new CommandDelaySend(this);
        commandDonate = new CommandDonate();
        commandHelp = new CommandHelp();
        commandKill = new CommandKill();
        commandKillWitherSkulls = new CommandKillWitherSkulls();
        commandPing = new CommandPing(this);
        commandRules = new CommandRules();
        commandUptime = new CommandUptime(this);
        commandFeedback = new CommandFeedback();
        commandColor = new CommandColor();
        commandReload = new CommandReload(this);
        autoMessages = new AutoMessages();
        commandQuickRestart = new CommandQuickRestart(this);
        commandStats = new CommandStats();
        loadConfig();

        registerCommands();

        Bukkit.getScheduler()
                .scheduleSyncRepeatingTask(
                        this,
                        () -> {
                            FuckWitherSkulls.killWitherSkulls();
                            logger.info("Killing " + FuckWitherSkulls.countSkulls() + " wither skulls.");
                        },
                        0,
                        1200);

        Bukkit.getScheduler()
                .scheduleSyncRepeatingTask(
                        this,
                        () -> {
                            if (config.getBoolean("send-auto-messages")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "br " + autoMessages.getRandomMessage());
                            }
                        },
                        0,
                        12000);

        Bukkit.getServer().getPluginManager().registerEvents(asyncPlayerChat, this);
        Bukkit.getServer().getPluginManager().registerEvents(blockPlace, this);
        Bukkit.getServer().getPluginManager().registerEvents(entityDamage, this);
        Bukkit.getServer().getPluginManager().registerEvents(inventoryClick, this);
        Bukkit.getServer().getPluginManager().registerEvents(inventoryOpen, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerEditBook, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerInteractEntity, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerJoin, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerLeave, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerMove, this);
    }

    public void loadConfig() {
        if (!configFile.exists()) {
            this.saveResource("config.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        autoMessages.messages.clear();
        autoMessages.messages.addAll(config.getStringList("auto-messages"));
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("br")).setExecutor(commandBroadcast);
        Objects.requireNonNull(this.getCommand("colors")).setExecutor(commandColors);
        Objects.requireNonNull(this.getCommand("delaysend")).setExecutor(commandDelaySend);
        Objects.requireNonNull(this.getCommand("donate")).setExecutor(commandDonate);
        Objects.requireNonNull(this.getCommand("help")).setExecutor(commandHelp);
        Objects.requireNonNull(this.getCommand("kill")).setExecutor(commandKill);
        Objects.requireNonNull(this.getCommand("kws")).setExecutor(commandKillWitherSkulls);
        Objects.requireNonNull(this.getCommand("ping")).setExecutor(commandPing);
        Objects.requireNonNull(this.getCommand("rules")).setExecutor(commandRules);
        Objects.requireNonNull(this.getCommand("uptime")).setExecutor(commandUptime);
        Objects.requireNonNull(this.getCommand("color")).setExecutor(commandColor);
        Objects.requireNonNull(this.getCommand("feedback")).setExecutor(commandFeedback);
        Objects.requireNonNull(this.getCommand("sfr")).setExecutor(commandReload);
        Objects.requireNonNull(this.getCommand("qrestart")).setExecutor(commandQuickRestart);
        Objects.requireNonNull(this.getCommand("stats")).setExecutor(commandStats);
    }
}

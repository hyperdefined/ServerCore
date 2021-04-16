package lol.hyper.servercore;

import lol.hyper.servercore.commands.*;
import lol.hyper.servercore.tools.AutoMessages;
import lol.hyper.servercore.tools.DupeCharges;
import lol.hyper.servercore.tools.FuckWitherSkulls;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public final class ServerCore extends JavaPlugin {

    public final File configFile = new File(this.getDataFolder(), "config.yml");
    public final Path dupeCharges = Paths.get(this.getDataFolder() + File.separator + "dupecharges");
    public FileConfiguration config = this.getConfig();
    public final Logger logger = this.getLogger();

    private JSONObject obj = new JSONObject();
    private JSONArray onlinePlayers = new JSONArray();

    public static final UUID hyperdefined = UUID.fromString("311be5cd-d17c-49b3-bf47-f781fdbcc929");

    public static final HashMap < Player, Long > lastChange = new HashMap< >(); // x1D - Offhand Swap fix
    public static final HashMap < Player, Integer > warnings = new HashMap < > (); // x1D - Offhand Swap fix

    public Events events;
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
    public DupeCharges dupeChargesFUCK;
    public CommandDupeCharge commandDupeCharge;
    public CommandDupe commandDupe;
    public CommandColor commandColor;

    @Override
    public void onEnable() {
        events = new Events(this);
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
        autoMessages = new AutoMessages();
        dupeChargesFUCK = new DupeCharges(this);
        commandDupeCharge = new CommandDupeCharge(this);
        commandDupe = new CommandDupe(this);
        commandColor = new CommandColor();
        loadConfig();

        registerCommands();

        for (Player player: Bukkit.getOnlinePlayers()) { // x1D - Offhand Swap fix
            lastChange.put(player, System.currentTimeMillis()); // x1D - Offhand Swap fix
            warnings.put(player, 0); // x1D - Offhand Swap fix
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            FuckWitherSkulls.killWitherSkulls();
            logger.info("Killing " + FuckWitherSkulls.countSkulls() + " wither skulls.");
        }, 0, 1200);


        /*Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "br " + AutoMessages.getRandomMessage());
        }, 0, 10000);
         */

        Bukkit.getServer().getPluginManager().registerEvents(events, this);

        if (!dupeCharges.toFile().exists()) {
            try {
                Files.createDirectory(dupeCharges);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadConfig() {
        if (!configFile.exists()) {
            this.saveResource("config.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

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
        Objects.requireNonNull(this.getCommand("dupe")).setExecutor(commandDupe);
        Objects.requireNonNull(this.getCommand("dupecharge")).setExecutor(commandDupeCharge);
        Objects.requireNonNull(this.getCommand("color")).setExecutor(commandColor);
    }
}

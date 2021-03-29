package lol.hyper.servercore;

import lol.hyper.servercore.commands.*;
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
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public final class ServerCore extends JavaPlugin {

    public final File configFile = new File(this.getDataFolder(), "config.yml");
    public FileConfiguration config = this.getConfig();
    public final Logger logger = this.getLogger();

    private JSONObject obj = new JSONObject();
    private JSONArray onlinePlayers = new JSONArray();

    public static final UUID hyperdefined = UUID.fromString("311be5cd-d17c-49b3-bf47-f781fdbcc929");

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
        loadConfig();

        registerCommands();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            File jsonFile = new File("status.json");
            obj = new JSONObject();
            onlinePlayers = new JSONArray();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!this.isVanished(player.getName())) {
                    onlinePlayers.add(player.getName());
                }
            }
            obj.put("players", onlinePlayers);
            obj.put("tps", PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(hyperdefined), "%spark_tps_5s%").substring(2));
            obj.put("uptime", PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(hyperdefined), "%server_uptime%"));
            try (FileWriter file = new FileWriter(jsonFile)) {
                file.write(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0L, 200);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            FuckWitherSkulls.killWitherSkulls();
            logger.info("Killing " + FuckWitherSkulls.countSkulls() + " wither skulls.");
        }, 0, 1200);

        Bukkit.getServer().getPluginManager().registerEvents(events, this);
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
    }
}

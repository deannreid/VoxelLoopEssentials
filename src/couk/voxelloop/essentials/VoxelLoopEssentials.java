package couk.voxelloop.essentials;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import couk.voxelloop.essentials.commands.testCommand;
import couk.voxelloop.essentials.commands.vle;
import couk.voxelloop.essentials.handler.ConfigurationHandler;
import couk.voxelloop.essentials.handler.VersionHandler;
import couk.voxelloop.essentials.lib.Reference;
import couk.voxelloop.essentials.metrics.Metrics;

public class VoxelLoopEssentials extends JavaPlugin implements Listener{

    public static final Logger veLog = Logger.getLogger(Reference.PLG_ID);
    public static ConfigurationHandler mainConfiguration;
    @SuppressWarnings("unused")
    private static boolean VLDebug = true; 
    //Plugin Load
    @Override
    public void onEnable() {
        veLog.setParent(this.getLogger());
        veLog.info("[VoxelLoop Essentials] Starting");
        veLog.info("[VoxelLoop Essentials] Searching for Configuration File");
            getConfig().options().copyDefaults(true);
            getConfig().options().copyHeader(true);
            //saveConfig();
            saveDefaultConfig();
        veLog.info("[VoxelLoop Essentials] Checking Version " + Reference.VERSION);
            //If uploading to BukkitDev remember to change url to comply with terms.
            //Change 999999999 to Bukkit Plugin ID 
            VersionHandler updater = new VersionHandler(this, 999999999, this.getFile(), VersionHandler.UpdateType.DEFAULT, false);  
            updater.getResult();
        veLog.info("[VoxelLoop Essentials] Loading Listeners");
            getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        veLog.info("[VoxelLoop Essentials] Loading Metrics");
            try {
                    Metrics metrics = new Metrics(this); metrics.start();
                } catch (IOException e) {
                    veLog.severe("[VoxelLoop Essentials] Error Submitting Metrics!");
                }
        veLog.info("[VoxelLoop Essentials] Loading Commands");
        this.getCommand("testies").setExecutor(new testCommand());
        this.getCommand("vle").setExecutor(new vle());   
    }

//Listeners
    public class PlayerListener implements Listener
    {
        //OnJoin 
        @EventHandler (priority = EventPriority.HIGHEST)
        public void onPlayerJoin(PlayerJoinEvent VLJoin) {
                Player e = VLJoin.getPlayer();
                e.sendMessage(ChatColor.GREEN + getConfig().getString("JoinMessage"));
        } 
    
        //OnDeath
        @EventHandler (priority = EventPriority.HIGHEST)
        public void onPlayerDeath(PlayerDeathEvent VLDeath) {
            Player e = VLDeath.getEntity();
            e.sendMessage(ChatColor.RED + getConfig().getString("DeathMessage"));

        }
        
        //OnLeave
        @EventHandler (priority = EventPriority.HIGHEST)
        public void onPlayerQuit(PlayerQuitEvent VLQuit) {
            Player e = VLQuit.getPlayer();
            veLog.severe(getConfig().getString("LeaveMessage"));
            e.sendMessage(ChatColor.YELLOW + getConfig().getString("LeaveMessage"));
        }
    
    }
    //Plugin Disable
    @Override
    public void onDisable() {
            veLog.info("[VoxelLoop Essentials] Voxelloop Essentials is Disabling!");
    }

    
}

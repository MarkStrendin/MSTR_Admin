package ca.strendin.MSTR_Admin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class MSTR_Admin extends JavaPlugin {

    @Override
    public void onDisable() {
        MSTR_Comms.logThis("Plugin DISABLED");        
    }

    @Override
    public void onEnable() {
        //Commands
        getCommand("setspawn").setExecutor(new MSTR_SetSpawnCommand(this));
        getCommand("sethome").setExecutor(new MSTR_SetHomeCommand(this));
        getCommand("playerinfo").setExecutor(new MSTR_PlayerInfoCommand(this));
        //getCommand("tp").setExecutor(new SATPCommand(this));
        getCommand("bring").setExecutor(new MSTR_BringCommand(this));
        getCommand("day").setExecutor(new MSTR_DayCommand(this));
        getCommand("night").setExecutor(new MSTR_NightCommand(this));
        getCommand("grant").setExecutor(new MSTR_GrantCommand(this));
        getCommand("spawn").setExecutor(new MSTR_SpawnCommand(this));
        getCommand("home").setExecutor(new MSTR_HomeCommand(this));
        getCommand("commandblock").setExecutor(new MSTR_CommandBlockCommand(this));
        getCommand("vanish").setExecutor(new MSTR_VanishCommand(this));
        getCommand("test").setExecutor(new MSTR_TestCommand(this));
        MSTR_Comms.logThis("Plugin ENABLED"); 
        
        MSTR_PlayerTracker.updateKnownPlayerList(getServer());
        
        @SuppressWarnings("unused")
		BukkitTask task = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() 
        {

			@Override
			public void run() {
				try {
					if (MSTR_ZombieInvasionDetector.detectIfInvasionOccurring()) {
						MSTR_ZombieInvasionDetector.alert();
					}
				} catch (Exception i) {
					MSTR_Comms.logThis(i.getMessage());
				}
			}
        	
        
        }, 1200L, 1200L);        
        
        PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new MSTR_PlayerListener(this), this);
        
        
    }
    
    public Player findPlayerNamed(String thisName) {
        return this.getServer().getPlayer(thisName);
    }

}

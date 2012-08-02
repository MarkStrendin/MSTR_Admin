package ca.strendin.StrendinAdmin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StrendinAdmin extends JavaPlugin {

    @Override
    public void onDisable() {
        SAComms.logThis("Plugin DISABLED");        
    }

    @Override
    public void onEnable() {
        //Commands
        getCommand("setspawn").setExecutor(new SASetSpawnCommand(this));
        getCommand("playerinfo").setExecutor(new SAPlayerInfoCommand(this));
        getCommand("tp").setExecutor(new SATPCommand(this));
        getCommand("bring").setExecutor(new SABringCommand(this));
        getCommand("day").setExecutor(new SADayCommand(this));
        getCommand("night").setExecutor(new SANightCommand(this));
        getCommand("startrain").setExecutor(new SAStartRainCommand(this));
        getCommand("stoprain").setExecutor(new SAStopRainCommand(this));
        getCommand("grant").setExecutor(new SAGrantCommand(this));
        getCommand("spawn").setExecutor(new SASpawnCommand(this));
        getCommand("home").setExecutor(new SAHomeCommand(this));
        SAComms.logThis("Plugin ENABLED");        
    }
    
    public Player findPlayerNamed(String thisName) {
        return this.getServer().getPlayer(thisName);
    }

}

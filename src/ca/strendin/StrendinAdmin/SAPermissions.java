package ca.strendin.StrendinAdmin;

import org.bukkit.entity.Player;

public class SAPermissions {
	public static boolean canSetSpawn(Player player) {
        return player.hasPermission("strendinadmin.setspawn");
    }
	
	public static boolean canSetHome(Player player) {
        return player.hasPermission("strendinadmin.sethome");
    }
    
    public static boolean canKick(Player player) {
        return player.hasPermission("strendinadmin.kick");        
    }
    
    public static boolean canBan(Player player) {
        return player.hasPermission("strendinadmin.ban");    
    } 
    
    public static boolean canPollPlayerInfo(Player player) {
        return player.hasPermission("strendinadmin.info");    
    } 
    
    public static boolean canTeleport(Player player) {
        return player.hasPermission("strendinadmin.teleport");    
    }
    
    public static boolean canDebug(Player player) {
        return player.hasPermission("strendinadmin.debug");    
    }

    public static boolean canSetTime(Player player) {
        return player.hasPermission("strendinadmin.time");
    }

    public static boolean canSetWeather(Player player) {
        return player.hasPermission("strendinadmin.weather");
    }

    public static boolean canGrantLevels(Player player) {
        return player.hasPermission("strendinadmin.grant");
    }

    public static boolean canTeleportToSpawn(Player player) {
        return player.hasPermission("strendinadmin.spawn");
    }

	public static boolean canTeleportToHome(Player player) {
        return player.hasPermission("strendinadmin.home");
	}
    
}

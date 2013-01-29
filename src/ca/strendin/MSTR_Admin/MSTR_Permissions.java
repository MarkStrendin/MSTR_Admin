package ca.strendin.MSTR_Admin;

import org.bukkit.entity.Player;

public class MSTR_Permissions {
	public static boolean canSetSpawn(Player player) {
        return player.hasPermission("mstr_admin.setspawn");
    }
	
	public static boolean canVanish(Player player) {
        return player.hasPermission("mstr_admin.vanish");
    }
	
	public static boolean canSetHome(Player player) {
        return player.hasPermission("mstr_admin.sethome");
    }
    
    public static boolean canKick(Player player) {
        return player.hasPermission("mstr_admin.kick");        
    }
    
    public static boolean canBan(Player player) {
        return player.hasPermission("mstr_admin.ban");    
    } 
    
    public static boolean canPollPlayerInfo(Player player) {
        return player.hasPermission("mstr_admin.info");    
    } 
    
    public static boolean canTeleport(Player player) {
        return player.hasPermission("mstr_admin.teleport");    
    }
    
    public static boolean canDebug(Player player) {
        return player.hasPermission("mstr_admin.debug");    
    }

    public static boolean canSetTime(Player player) {
        return player.hasPermission("mstr_admin.time");
    }

    public static boolean canSetWeather(Player player) {
        return player.hasPermission("mstr_admin.weather");
    }

    public static boolean canGrantLevels(Player player) {
        return player.hasPermission("mstr_admin.grant");
    }

    public static boolean canTeleportToSpawn(Player player) {
        return player.hasPermission("mstr_admin.spawn");
    }

	public static boolean canTeleportToHome(Player player) {
        return player.hasPermission("mstr_admin.home");
	}
    
}

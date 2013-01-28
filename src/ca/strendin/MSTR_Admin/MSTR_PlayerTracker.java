package ca.strendin.MSTR_Admin;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class MSTR_PlayerTracker {
	private static ArrayList<Player> vanishedPlayers;
	public static OfflinePlayer[] knownPlayerList;
	
	/*
	public static boolean isKnownPlayer(Player player) {
		Server server = player.getServer();
		for (OfflinePlayer offlinePlayer : server.getOfflinePlayers()) {
			if (offlinePlayer.getName().contentEquals(player.getName())) {
				return true;
			}
			
		}
		return false;
	}
	*/
	
	public static void updateKnownPlayerList(Server server) {
		knownPlayerList = server.getOfflinePlayers();
	}
	
	public static boolean isNewPlayerToServer(Player player) {
		for (OfflinePlayer offlinePlayer : knownPlayerList) {
			if (offlinePlayer.getName().contentEquals(player.getName())) {
				return false;
			}			
		}
		return true;
	}
	
	
	public static void addToVanishedList(Player player) {
		if (vanishedPlayers == null) {
			vanishedPlayers = new ArrayList<Player>();
		}		
		
		vanishedPlayers.add(player);
	}
	
	public static void removeFromVanishedList(Player player) {
		if (vanishedPlayers == null) {
			vanishedPlayers = new ArrayList<Player>();
		}		
		
		if (vanishedPlayers.contains(player)) {
			vanishedPlayers.remove(player);
		}
	}
	
	public static boolean isVanished(Player player) {
		if (vanishedPlayers == null) {
			vanishedPlayers = new ArrayList<Player>();
		}	
		
		if (vanishedPlayers.contains(player)) {
			return true;
		} else {
			return false;
		}		
	}
	

}

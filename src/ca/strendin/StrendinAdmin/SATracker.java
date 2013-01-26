package ca.strendin.StrendinAdmin;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class SATracker {
	private static ArrayList<Player> vanishedPlayers;
	private static ArrayList<Player> knownPlayers;
	
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

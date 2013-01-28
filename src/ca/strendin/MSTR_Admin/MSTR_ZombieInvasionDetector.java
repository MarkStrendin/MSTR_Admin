package ca.strendin.MSTR_Admin;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class MSTR_ZombieInvasionDetector {
	private static int lastInvasionSpawnTimeStamp;
	private static Location lastLocation;
	@SuppressWarnings("unused")
	private static LivingEntity lastEntity;
	
	private static int getCurrentUnixTimestamp() 
	{
		return (int) (System.currentTimeMillis() / 1000L);
	}
	
	public static void reportInvasion(LivingEntity entity) {		
		lastInvasionSpawnTimeStamp = getCurrentUnixTimestamp();
		lastEntity = entity;
		lastLocation = entity.getLocation();;
	}
	
	public static Location getLastInvasionLocation() {
		return lastLocation;
	}
	
	public static boolean detectIfInvasionOccurring() {
		int secondsAgo = Math.abs(lastInvasionSpawnTimeStamp - getCurrentUnixTimestamp());
		if (secondsAgo < 36000) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void alert() {
		MSTR_Comms.sendToOps("Invasion event detected");
		MSTR_Comms.sendToOps(" last timestamp: " + lastInvasionSpawnTimeStamp);
		MSTR_Comms.sendToOps(" current timestamp: " + getCurrentUnixTimestamp());
		MSTR_Comms.sendToOps(" seconds different: " + (lastInvasionSpawnTimeStamp - getCurrentUnixTimestamp()));
		MSTR_Comms.sendToOps(" Location: " + lastLocation);
		
		/*
		// Alert nearby entities
		for (Entity thisEntity : lastEntity.getNearbyEntities(20, 20, 20)) {
			if (thisEntity instanceof Player) {
				Player player = (Player)thisEntity;
				MSTR_Comms.sendEmote(player, "Zombie invasion event detected close to your location!");
			}
		}
		*/
	}
}

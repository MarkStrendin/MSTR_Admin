package ca.strendin.StrendinAdmin;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SASpecialEffects {
	
	public static void poofAtLocation(Location location) {
		int effectRadius = 20;	
		int effectCount = 10;
		
		for (int x = 0; x < effectCount; x++) {						
			location.getWorld().playEffect(location, Effect.SMOKE, 1, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 2, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 3, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 4, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 5, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 6, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 7, effectRadius);
			location.getWorld().playEffect(location, Effect.SMOKE, 8, effectRadius);			
			location.getWorld().playEffect(location, Effect.SMOKE, 4, effectRadius);
		}
	
	}	
	
	public static void poofAtPlayer(Player player) {
		poofAtLocation(player.getLocation());
	}
	
	
	
	public static void lightningAtPlayer(Player player) {
		lightningAtLocation(player.getLocation());		
	}
	
	public static void lightningAtLocation(Location location) {
		location.getWorld().strikeLightningEffect(location);		
	}
	

}

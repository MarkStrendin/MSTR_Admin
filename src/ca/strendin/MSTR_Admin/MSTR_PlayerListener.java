package ca.strendin.MSTR_Admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerJoinEvent;

public class MSTR_PlayerListener implements Listener {
	

	public MSTR_PlayerListener(MSTR_Admin strendinAdmin) {}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {	
		
		/*
		 * Despite setting the spawn point, new players will appear a random distance from the exact spawn coordinates.
		 * 
		 * This detects if the player is new, and teleports them to the correct, exact spawn coordinates.
		 */
		if (MSTR_PlayerTracker.isNewPlayerToServer(event.getPlayer())) {
			MSTR_Comms.sendConsole("New player joind - teleporting them to the spawn point");
			teleportToSpawn(event.getPlayer());
		}
		
		MSTR_PlayerTracker.updateKnownPlayerList(event.getPlayer().getServer());
	}	
	
	private void teleportToSpawn(Player player) {
    	player.teleport(player.getWorld().getSpawnLocation());
    }
	
	@EventHandler        
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) {
		if (event.getSpawnReason() == SpawnReason.VILLAGE_INVASION) {
			MSTR_ZombieInvasionDetector.reportInvasion(event.getEntity());				
		}
    }
	
	@EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {    	       
    	
    	/*  Prevent the placement of monster eggs, so players (mainly me) don't 
    	 *  accidentally make a castle out of them thinking they are stone bricks.
    	 * 
    	 *  Survival players will never be able to pick them up anyways.
    	 * */    	
    	if (
    			(event.getBlock().getType() == Material.MONSTER_EGG) ||
    			(event.getBlock().getType() == Material.MONSTER_EGGS)
    			) {
    		event.setCancelled(true);
    		MSTR_Comms.sendError(event.getPlayer(), "Sorry, placing monster eggs is not allowed on this server. Perhaps you meant to place stone bricks?");
    		
    	}
    }
	
	

}

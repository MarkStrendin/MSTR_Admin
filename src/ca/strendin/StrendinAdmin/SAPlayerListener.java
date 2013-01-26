package ca.strendin.StrendinAdmin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SAPlayerListener implements Listener {
	

	public SAPlayerListener(StrendinAdmin strendinAdmin) {}
	
	@EventHandler
	public void onPlayerRepawn(PlayerRespawnEvent event) {
		SAComms.sendConsole("Player respawn event fired for: " + event.getPlayer().getName());		
	}
	
	

}

package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SAHomeCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;

    public SAHomeCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		
		if (sender instanceof Player) {
            if (SAPermissions.canTeleportToHome((Player)sender)) {
                Player player = (Player)sender;
                
                try {
		            player.teleport(player.getBedSpawnLocation());
		            SAComms.sendInfo(player, "Teleporting you to your home point...");
                }
                catch(Exception ex) {
                	SAComms.sendInfo(player, "You don't appear to have a home point! Sleep in a bed to set one!");                	
                }                
                
            } else {
                SAComms.permDenyMsg((Player)sender);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }        
        return true;
	}

}

package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_HomeCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final MSTR_Admin plugin;

    public MSTR_HomeCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		
		if (sender instanceof Player) {
            if (MSTR_Permissions.canTeleportToHome((Player)sender)) {
                Player player = (Player)sender;
                
                try {
		            player.teleport(player.getBedSpawnLocation());
		            MSTR_Comms.sendInfo(player, "Teleporting you to your home point...");
                }
                catch(Exception ex) {
                	MSTR_Comms.sendInfo(player, "You don't appear to have a home point! Sleep in a bed to set one!");                	
                }                
                
            } else {
                MSTR_Comms.permDenyMsg((Player)sender);
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }        
        return true;
	}

}

package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_SetHomeCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final MSTR_Admin plugin;
    
    public MSTR_SetHomeCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

	@Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {

        if (sender instanceof Player) {
            Player player = (Player) sender;            
            if (MSTR_Permissions.canSetHome(player)) {
                player.setBedSpawnLocation(player.getLocation());
                
                MSTR_Comms.sendInfo(player, "Your respawn point is now set to your current position!");                
            } else {
                MSTR_Comms.permDenyMsg(player);
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }
        return true;
    }

}

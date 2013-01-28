package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_TPCommand implements CommandExecutor {
    private final MSTR_Admin plugin;
    
    public MSTR_TPCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {  
        
        if (sender instanceof Player) {
            if (MSTR_Permissions.canTeleport((Player)sender)) {
                Player player = (Player)sender;
                if (arg3.length > 0) {
                    if (arg3[0].length() > 0) {
                        Player target = plugin.findPlayerNamed(arg3[0]);                
                        if (target != null) {
                            MSTR_Comms.sendInfo(player, "Teleporting you to " + target.getDisplayName() + "'s location");                        
                            player.teleport(target.getLocation());
                        } else {
                            MSTR_Comms.sendError(player,"No player by that name was found!");                        
                        }
                    }
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

package ca.strendin.StrendinAdmin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SABringCommand implements CommandExecutor {
    private final StrendinAdmin plugin;
    
    public SABringCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }   
   

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {        
        if (sender instanceof Player) {
            if (SAPermissions.canTeleport((Player)sender)) {
                Player player = (Player)sender;
                if (arg3.length > 0) {
                    if (arg3[0].length() > 0) {
                        Player target = plugin.findPlayerNamed(arg3[0]);                
                        if (target != null) {
                            SASpecialEffects.poofAtPlayer(target);
                            SAComms.sendInfo(player, "Teleporting " + target.getDisplayName() + " to your location");
                            SAComms.sendInfo(target, player.getDisplayName() + " has teleported you to their location");
                            target.teleport(player.getLocation());
                            SASpecialEffects.poofAtPlayer(target);
                        } else {
                            SAComms.sendError(player,"No player by that name was found!");                        
                        }
                    }
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

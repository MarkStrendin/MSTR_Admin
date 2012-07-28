package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SATPCommand implements CommandExecutor {
    private final StrendinAdmin plugin;
    
    public SATPCommand(StrendinAdmin plugin) {
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
                            SAComms.sendInfo(player, "Teleporting you to " + target.getDisplayName() + "'s location");                        
                            player.teleport(target.getLocation());
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

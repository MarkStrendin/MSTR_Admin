package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_GrantCommand implements CommandExecutor {
    private final MSTR_Admin plugin;
    private static final int maxlevelstogrant = 250;
    private static final int ignoreifplayerlevelabove = 1000;

    public MSTR_GrantCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (MSTR_Permissions.canGrantLevels((Player)sender)) {
                Player player = (Player)sender;
                
                /*
                 * arg3[0] = player name
                 * arg3[1] = number of levels
                 */
                
                if ((arg3.length > 0) && (arg3.length < 3)) {
                    int numlevels = 1;                   
                    if (arg3.length > 1) {
                        if (arg3[1].length() > 0) {                            
                            try {
                                numlevels = Integer.parseInt(arg3[1]);
                            } catch(Exception ex) {
                                MSTR_Comms.sendError(player, "That doesn't seem to be a valid number");
                                return false;
                            }
                        }
                    }
                    
                    if (numlevels > maxlevelstogrant) {
                        numlevels = maxlevelstogrant;
                    }
                    if (numlevels < 0) {
                        numlevels = 0;
                    }                    
                    
                    if (arg3[0].length() > 0) {                        
                        Player target = plugin.findPlayerNamed(arg3[0]);                        
                        if (target != null) {
                            if (target.getLevel() >= ignoreifplayerlevelabove) {
                                MSTR_Comms.sendError(player, target.getDisplayName() + " seems to have enough levels already!");
                                return true;
                            }
                            
                            if ((target.getLevel() + numlevels >= ignoreifplayerlevelabove) && (target.getLevel() < ignoreifplayerlevelabove)) {
                                numlevels = ignoreifplayerlevelabove - target.getLevel();
                            }
                            
                            MSTR_Comms.sendInfo(player, "Granting " + numlevels + " levels to " + target.getDisplayName());
                            target.setLevel(target.getLevel() + numlevels);
                        } else {
                            MSTR_Comms.sendError(player, "Player not found!");                                                    
                        }
                    }
                } else {
                    return false;
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

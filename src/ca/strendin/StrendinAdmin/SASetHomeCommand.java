package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SASetHomeCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;
    
    public SASetHomeCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

	@Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {

        if (sender instanceof Player) {
            Player player = (Player) sender;            
            if (SAPermissions.canSetHome(player)) {
                player.setBedSpawnLocation(player.getLocation());
                
                SAComms.sendInfo(player, "Your respawn point is now set to your current position!");                
            } else {
                SAComms.permDenyMsg(player);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }
        return true;
    }

}

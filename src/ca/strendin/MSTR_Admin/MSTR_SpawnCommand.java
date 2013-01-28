package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_SpawnCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final MSTR_Admin plugin;

    public MSTR_SpawnCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (MSTR_Permissions.canTeleportToSpawn((Player)sender)) {
                Player player = (Player)sender;
                MSTR_Comms.sendInfo(player, "Teleporting you to the spawn point...");                
                teleportToSpawn(player);
            } else {
                MSTR_Comms.permDenyMsg((Player)sender);
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }        
        return true;
    }
    
    private void teleportToSpawn(Player player) {
    	player.teleport(player.getWorld().getSpawnLocation());
    }

}

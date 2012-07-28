package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SASpawnCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;

    public SASpawnCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (SAPermissions.canTeleportToSpawn((Player)sender)) {
                Player player = (Player)sender;
                SAComms.sendInfo(player, "Teleporting you to the spawn point...");                
                player.teleport(player.getWorld().getSpawnLocation());
            } else {
                SAComms.permDenyMsg((Player)sender);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }        
        return true;
    }

}

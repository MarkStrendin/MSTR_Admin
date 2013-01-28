package ca.strendin.MSTR_Admin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_SetSpawnCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final MSTR_Admin plugin;
    
    public MSTR_SetSpawnCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }
        
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {

        if (sender instanceof Player) {
            Player player = (Player) sender;            
            if (MSTR_Permissions.canSetSpawn(player)) {
                Location newspawn = player.getLocation();
                player.getWorld().setSpawnLocation(newspawn.getBlockX(), newspawn.getBlockY(), newspawn.getBlockZ());
                MSTR_Comms.sendInfo(player, "Spawn point relocated to X:" + newspawn.getBlockX() + ",Y:" + newspawn.getBlockY() + ",Z:" + newspawn.getBlockZ());                
            } else {
                MSTR_Comms.permDenyMsg(player);
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }
        return true;
    }

}

package ca.strendin.StrendinAdmin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SASetSpawnCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;
    
    public SASetSpawnCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }
        
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {

        if (sender instanceof Player) {
            Player player = (Player) sender;            
            if (SAPermissions.canSetSpawn(player)) {
                Location newspawn = player.getLocation();
                player.getWorld().setSpawnLocation(newspawn.getBlockX(), newspawn.getBlockY(), newspawn.getBlockZ());
                SAComms.sendInfo(player, "Spawn point relocated to X:" + newspawn.getBlockX() + ",Y:" + newspawn.getBlockY() + ",Z:" + newspawn.getBlockZ());                
            } else {
                SAComms.permDenyMsg(player);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }
        return true;
    }

}

package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SADayCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;

    public SADayCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (SAPermissions.canSetTime((Player)sender)) {
                Player player = (Player)sender;
                SAComms.sendWorldBroadcast(player.getWorld(),player.getDisplayName() + " set this world's time to day");
                SAComms.logThis(player.getDisplayName() + " set the time for world " + player.getWorld().getName()  + " to day");
                player.getWorld().setTime(24000);
            } else {
                SAComms.permDenyMsg((Player)sender);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }        
        return true;
    }
    
    

}

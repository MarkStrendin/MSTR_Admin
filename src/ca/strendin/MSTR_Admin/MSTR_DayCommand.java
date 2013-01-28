package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_DayCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final MSTR_Admin plugin;

    public MSTR_DayCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (MSTR_Permissions.canSetTime((Player)sender)) {
                Player player = (Player)sender;
                //SAComms.sendWorldBroadcast(player.getWorld(),player.getDisplayName() + " set this world's time to day");
                MSTR_Comms.logThis(player.getDisplayName() + " set the time for world " + player.getWorld().getName()  + " to day");
                player.getWorld().setTime(24000);
            } else {
                MSTR_Comms.permDenyMsg((Player)sender);
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }        
        return true;
    }
    
    

}

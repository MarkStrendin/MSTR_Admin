package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SAStartRainCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final StrendinAdmin plugin;

    public SAStartRainCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {
        
        if (sender instanceof Player) {
            if (SAPermissions.canSetWeather((Player)sender)) {                
                Player player = (Player)sender;                
                SAComms.sendWorldBroadcast(player.getWorld(),player.getDisplayName() + " started a storm");
                SAComms.logThis(player.getDisplayName() + " started a storm on " + player.getWorld().getName());
                player.getWorld().setStorm(true);                
            } else {
                SAComms.permDenyMsg((Player)sender);
            }
        } else {
            SAComms.sendConsole("Player command only!");
        }        
        return true;
    }

}

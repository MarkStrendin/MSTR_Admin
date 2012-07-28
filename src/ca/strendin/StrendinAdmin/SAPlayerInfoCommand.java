package ca.strendin.StrendinAdmin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SAPlayerInfoCommand implements CommandExecutor {    
    private final StrendinAdmin plugin;
    
    public SAPlayerInfoCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

    public static String coloredHealthBar(int healthAmmount,int maxhealth) {
        String returnMe = ChatColor.WHITE + "|";        
        for (int x = 1; x <= 20; x++) {
            if (x <= healthAmmount) {
                if (healthAmmount < 5) {
                    returnMe += ChatColor.RED;
                } else if (healthAmmount < 15) {
                    returnMe += ChatColor.YELLOW;
                } else {
                    returnMe += ChatColor.GREEN;
                }
                returnMe += "|";
            } else {                
                returnMe += ChatColor.DARK_GRAY + "|";            
            }           
        }       
        returnMe += ChatColor.WHITE + "| " + healthAmmount;
        return returnMe;        
    }
    
    public static String normalHealthBar(int healthAmmount,int maxhealth) {      
        String returnMe = " [";              
        for (int x = 1; x <= 20; x++) {
            if (x <= healthAmmount) {
                returnMe += "|";
            } else {
                returnMe += " ";
            
            }           
        }       
        returnMe += "] " + healthAmmount;
        return returnMe;
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) { 
        
        if (sender instanceof Player) {
            if (!SAPermissions.canPollPlayerInfo((Player)sender)) {
                SAComms.permDenyMsg((Player)sender);
                return true;
            }
        }
        
        if (arg3.length > 0) {
            if (arg3[0].length() > 0) {
                Player target = plugin.findPlayerNamed(arg3[0]);                
                if (target != null) {
                    SAComms.sendtoCommandSender(sender,"Details for player: " + ChatColor.WHITE + target.getDisplayName());
                    SAComms.sendtoCommandSender(sender," Level: " + ChatColor.WHITE + target.getLevel());
                    if (sender instanceof Player) {
                        SAComms.sendtoCommandSender(sender," Health: " + coloredHealthBar(target.getHealth(),target.getMaxHealth()));
                        SAComms.sendtoCommandSender(sender," Hunger: " + coloredHealthBar(target.getFoodLevel(),20));
                    } else {
                        SAComms.sendtoCommandSender(sender," Health: " + normalHealthBar(target.getHealth(),target.getMaxHealth()));
                        SAComms.sendtoCommandSender(sender," Hunger: " + normalHealthBar(target.getFoodLevel(),20));                    
                    }
                    SAComms.sendtoCommandSender(sender," Current Position: " + ChatColor.WHITE + "x:"+target.getLocation().getBlockX()+" y:"+target.getLocation().getBlockY()+" z:"+target.getLocation().getBlockZ()+" in world: " + target.getLocation().getWorld().getName());                    
                } else {
                    SAComms.sendtoCommandSender(sender,"No player by that name was found!");                        
                }
            }
        }
        return true;
    }

}

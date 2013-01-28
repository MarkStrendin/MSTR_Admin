package ca.strendin.MSTR_Admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_PlayerInfoCommand implements CommandExecutor {    
    private final MSTR_Admin plugin;
    
    public MSTR_PlayerInfoCommand(MSTR_Admin plugin) {
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
            if (!MSTR_Permissions.canPollPlayerInfo((Player)sender)) {
                MSTR_Comms.permDenyMsg((Player)sender);
                return true;
            }
        }
        
        if (arg3.length > 0) {
            if (arg3[0].length() > 0) {
                
            	Player target = findOnlinePlayerNamed(arg3[0]);                
                if (target != null) {
                	if (sender instanceof Player) {
                		sendOnlinePlayerInfo((Player)sender,target);
                	} else {
                		sendOnlinePlayerInfoToConsole(sender,target);
                	}
                } else {
                	OfflinePlayer offlineTarget = findOfflinePlayerNamed(arg3[0]);
                	if (offlineTarget != null) {
                		if (sender instanceof Player) {
                			sendOfflinePlayerInfo((Player)sender,offlineTarget);
                		} else {
                			sendOfflinePlayerInfoToConsole(sender,offlineTarget);
                		}
                	} else {
                		MSTR_Comms.sendtoCommandSender(sender,"No player by that name was found!");                		
                	}                       
                }
            }
        }
        return true;
    }
    
    private void sendOnlinePlayerInfoToConsole(CommandSender sender, Player target) {
    	MSTR_Comms.sendtoCommandSender(sender,"Details for player: " +  target.getName());
    	if (target.isOp()) {
    		MSTR_Comms.sendtoCommandSender(sender," " + target.getName() + " is a Server Operator");
    	}
    	MSTR_Comms.sendtoCommandSender(sender," Current Game Mode: " + target.getGameMode());
    	MSTR_Comms.sendtoCommandSender(sender," Level: " + target.getLevel());
        MSTR_Comms.sendtoCommandSender(sender," Health: " + normalHealthBar(target.getHealth(),target.getMaxHealth()));
        MSTR_Comms.sendtoCommandSender(sender," Hunger: " + normalHealthBar(target.getFoodLevel(),20));                    
        MSTR_Comms.sendtoCommandSender(sender," Current Position: " + "x:"+target.getLocation().getBlockX()+" y:"+target.getLocation().getBlockY()+" z:"+target.getLocation().getBlockZ()+" in world: " + target.getLocation().getWorld().getName());
        MSTR_Comms.sendtoCommandSender(sender," Bed Spawn Location: " + "x:"+target.getBedSpawnLocation().getBlockX()+" y:"+target.getBedSpawnLocation().getBlockY()+" z:"+target.getBedSpawnLocation().getBlockZ()+" in world: " + target.getBedSpawnLocation().getWorld().getName());
        DateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd, yyyy  h:mm a");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(target.getFirstPlayed());
    	MSTR_Comms.sendtoCommandSender(sender," First seen: " + formatter.format(calendar.getTime()));
    	
    }
    
    private void sendOnlinePlayerInfo(Player sender, Player target) {
    	MSTR_Comms.sendInfo(sender,"Details for player: " + ChatColor.WHITE + target.getName());    	
    	if (target.isOp()) {
    		MSTR_Comms.sendInfo(sender," " + MSTR_Comms.serverMsgColor + target.getName() + " is a Server Operator");
    	}
    	MSTR_Comms.sendInfo(sender," Current Game Mode: " + ChatColor.WHITE + target.getGameMode());
    	MSTR_Comms.sendInfo(sender," Level: " + ChatColor.WHITE + target.getLevel());
        MSTR_Comms.sendInfo(sender," Health: " + coloredHealthBar(target.getHealth(),target.getMaxHealth()));
        MSTR_Comms.sendInfo(sender," Hunger: " + coloredHealthBar(target.getFoodLevel(),20));            
        MSTR_Comms.sendInfo(sender," Current Position: " + ChatColor.WHITE + "x:"+target.getLocation().getBlockX()+" y:"+target.getLocation().getBlockY()+" z:"+target.getLocation().getBlockZ()+" in world: " + target.getLocation().getWorld().getName());
        MSTR_Comms.sendInfo(sender," Bed Spawn Location: " + ChatColor.WHITE + "("+target.getBedSpawnLocation().getBlockX()+","+target.getBedSpawnLocation().getBlockY()+","+target.getBedSpawnLocation().getBlockZ()+") in world: " + target.getBedSpawnLocation().getWorld().getName());
        DateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd, yyyy  h:mm a");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(target.getFirstPlayed());
    	MSTR_Comms.sendInfo(sender," First seen: " + ChatColor.WHITE + formatter.format(calendar.getTime()));
    	
    }
    
    private void sendOfflinePlayerInfo(Player sender, OfflinePlayer target) {    	
    	MSTR_Comms.sendInfo(sender,"Details for " + MSTR_Comms.errorColor + "offline" + MSTR_Comms.infoColor + " player: " + ChatColor.WHITE + target.getName());
    	
    	if (Bukkit.getServer().hasWhitelist()) {
    		MSTR_Comms.sendInfo(sender," Whitelisted: " + ChatColor.WHITE + target.isWhitelisted());
    	}
    	
    	if (target.isOp()) {
    		MSTR_Comms.sendInfo(sender," " + MSTR_Comms.serverMsgColor + target.getName() + " is a Server Operator");
    	}
    	
    	if (target.isBanned()) {
    		MSTR_Comms.sendInfo(sender," " + MSTR_Comms.serverMsgColor + target.getName() + " is currently banned from the server");
    	}
    	
    	MSTR_Comms.sendInfo(sender," Bed Spawn Location: " + ChatColor.WHITE + "("+target.getBedSpawnLocation().getBlockX()+","+target.getBedSpawnLocation().getBlockY()+","+target.getBedSpawnLocation().getBlockZ()+") in world: " + target.getBedSpawnLocation().getWorld().getName());
    	
    	DateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd, yyyy  h:mm a");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(target.getLastPlayed());
    	MSTR_Comms.sendInfo(sender," Last seen: " + ChatColor.WHITE + formatter.format(calendar.getTime()));	      
    }

    private void sendOfflinePlayerInfoToConsole(CommandSender sender, OfflinePlayer target) {   	
    	MSTR_Comms.sendtoCommandSender(sender,"Details for OFFLINE player: " + target.getName());
    	
    	if (Bukkit.getServer().hasWhitelist()) {
    		MSTR_Comms.sendtoCommandSender(sender," Whitelisted: " + target.isWhitelisted());
    	}
    	
    	if (target.isOp()) {
    		MSTR_Comms.sendtoCommandSender(sender," " + target.getName() + " is a Server Operator");
    	}
    	
    	if (target.isBanned()) {
    		MSTR_Comms.sendtoCommandSender(sender," " + target.getName() + " is currently banned from the server");
    	}
    	
    	MSTR_Comms.sendtoCommandSender(sender," Bed Spawn Location: " + "x:"+target.getBedSpawnLocation().getBlockX()+" y:"+target.getBedSpawnLocation().getBlockY()+" z:"+target.getBedSpawnLocation().getBlockZ()+" in world: " + target.getBedSpawnLocation().getWorld().getName());
    	
    	DateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd, yyyy  h:mm a");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(target.getLastPlayed());
    	MSTR_Comms.sendtoCommandSender(sender," Last seen: " + formatter.format(calendar.getTime()));
    	  		
    }
    
    public Player findOnlinePlayerNamed(String thisName) {
        return plugin.getServer().getPlayer(thisName);
    }
    
    
    public OfflinePlayer findOfflinePlayerNamed(String thisName) {
        for (OfflinePlayer thisPlayer : plugin.getServer().getOfflinePlayers()) {
        	if (thisPlayer.getName().toLowerCase().contentEquals(thisName.toLowerCase())) {
        		return thisPlayer;
        	}
        }
		return null;
    }


}

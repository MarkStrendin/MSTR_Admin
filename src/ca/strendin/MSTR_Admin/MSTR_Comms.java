package ca.strendin.MSTR_Admin;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_Comms {
	public static final String pluginName = "MSTR_Admin";
    public static final Logger log = Logger.getLogger("Minecraft");
    public static ChatColor infoColor = ChatColor.GRAY;
    public static ChatColor highlightColor = ChatColor.WHITE;
    public static ChatColor itemColor = ChatColor.AQUA;
    public static ChatColor errorColor = ChatColor.RED;
    public static ChatColor emoteColor = ChatColor.GRAY;
    public static ChatColor serverMsgColor = ChatColor.YELLOW;
    private static ChatColor italic = ChatColor.ITALIC;

    public static void sendError(Player player, String message) {
        player.sendMessage(errorColor + message);        
    }
    
    public static void sendBroadcast(String message) {
        for(Player thisPlayer : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
            thisPlayer.sendMessage(serverMsgColor + message);
        }
    }
    
    public static void sendEmote(Player player, String message) {
        player.sendMessage(emoteColor + "" + italic + message);        
    }
    
    public static void sendtoCommandSender(CommandSender sender,String message) {
        if (sender instanceof Player) {
            sendInfo((Player)sender,message);
        } else {
            logThis(message);            
        }
    }
    
    public static void sendWorldBroadcast(World thisWorld,String message) {
        for(Player thisPlayer : thisWorld.getPlayers()) {
            thisPlayer.sendMessage(serverMsgColor + message);
        }
    }
    
    public static void sendInfo(Player player, String message) {
        player.sendMessage(infoColor + message);
    }
    
    public static void sendConsole(String message) {
        System.out.println("["+pluginName+"] " + message);
    }
    
    public static void logThis(String message) {
        log.info("["+pluginName+"] " + message);
    }
    
    public static void permDenyMsg(Player tothisplayer) {
        tothisplayer.sendMessage(errorColor + "You do not have permission to use that command");        
    }
    
    public static void sendToOps(String message) {
        for(Player thisPlayer : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
            if (thisPlayer.isOp()) {
                thisPlayer.sendMessage(serverMsgColor + "To ops: " + message);
            }        
        }        
    }
    

}

package ca.strendin.StrendinAdmin;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SAComms {
    public static final Logger log = Logger.getLogger("Minecraft");
    private static ChatColor infoColor = ChatColor.AQUA;
    private static ChatColor errorColor = ChatColor.RED;
    private static ChatColor emoteColor = ChatColor.GRAY;
    private static ChatColor serverMsgColor = ChatColor.YELLOW;    

    public static void sendError(Player player, String message) {
        player.sendMessage(errorColor + message);        
    }
    
    public static void sendBroadcast(String message) {
        for(Player thisPlayer : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
            thisPlayer.sendMessage(serverMsgColor + message);
        }
    }
    
    public static void sendEmote(Player player, String message) {
        player.sendMessage(emoteColor + message);        
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
        System.out.println("[StrendinAdmin] " + message);
    }
    
    public static void logThis(String message) {
        log.info("[StrendinAdmin] " + message);
    }
    
    public static void permDenyMsg(Player tothisplayer) {
        tothisplayer.sendMessage(errorColor + "You do not have permission to use that command");        
    }
    

}

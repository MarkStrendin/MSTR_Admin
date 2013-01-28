package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MSTR_CommandBlockCommand implements CommandExecutor {
    public static int commandBlockID = 137;
    @SuppressWarnings("unused")
	private final MSTR_Admin plugin;
    
    public MSTR_CommandBlockCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }

    
    public static void givePlayerCommandBlock (Player thisPlayer) {
        ItemStack CommandBlockItem = new ItemStack(commandBlockID,(short)5,(byte)0);
        MSTR_Comms.sendtoCommandSender(thisPlayer, "Giving some command blocks: " + MSTR_Comms.itemColor + CommandBlockItem.getType());
        MSTR_Comms.logThis("Giving " + thisPlayer.getDisplayName() + " some command blocks (" + CommandBlockItem.getType() + ")");
        thisPlayer.getInventory().addItem(CommandBlockItem);
    }
    
    
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {

        
        if (sender instanceof Player) {
        	Player player = (Player)sender;
        	// No need for special permissions routines, because non-ops can't use command blocks        	
            if (player.isOp()) {
            	givePlayerCommandBlock(player);
            }
            
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }

        return true;
	}

}

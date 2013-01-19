package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SACommandBlockCommand implements CommandExecutor {
    public static int commandBlockID = 137;
    @SuppressWarnings("unused")
	private final StrendinAdmin plugin;
    
    public SACommandBlockCommand(StrendinAdmin plugin) {
        this.plugin = plugin;
    }

    
    public static void givePlayerCommandBlock (Player thisPlayer) {
        ItemStack DuplicatorToolItem = new ItemStack(commandBlockID,(short)5,(byte)0);
        SAComms.sendtoCommandSender(thisPlayer, "Giving some command blocks: " + SAComms.itemColor + DuplicatorToolItem.getType());
        SAComms.logThis("Giving " + thisPlayer.getDisplayName() + " some command blocks (" + DuplicatorToolItem.getType() + ")");
        thisPlayer.getInventory().addItem(DuplicatorToolItem);
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
            SAComms.sendConsole("Player command only!");
        }

        return true;
	}

}

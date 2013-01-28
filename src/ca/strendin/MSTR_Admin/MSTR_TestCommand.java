package ca.strendin.MSTR_Admin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_TestCommand implements CommandExecutor {
	public MSTR_TestCommand(MSTR_Admin strendinAdmin) {}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {

		if (sender instanceof Player) {
			Player player = (Player)sender;
			
			player.teleport(getBlockInFrontOfPlayer(player));
			
			
			
			
			
		}
		
		return true;
	}
	
	
	
	private static Location getBlockInFrontOfPlayer(Player player) {
		
		BlockFace direction = yawToFace(player.getLocation().getYaw());
		
		Block returnMe = player.getLocation().getBlock().getRelative(direction, 1);
		if (returnMe.getType() == Material.AIR) {
			if (returnMe.getRelative(BlockFace.UP,1).getType() == Material.AIR) {				
				return returnMe.getLocation();
			}
		}
		return player.getLocation();
	}
	
	private static BlockFace yawToFace(float yaw) {
		//BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
	    //BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
		BlockFace[] radial = { BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST, BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST };
	    
	    
	    return radial[Math.round(yaw / 45f) & 0x7];
        // return axis[Math.round(yaw / 90f) & 0x3];
	}
	
	
	
}

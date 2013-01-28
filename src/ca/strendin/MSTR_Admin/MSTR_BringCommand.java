package ca.strendin.MSTR_Admin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_BringCommand implements CommandExecutor {
    private final MSTR_Admin plugin;
    
    public MSTR_BringCommand(MSTR_Admin plugin) {
        this.plugin = plugin;
    }   
   

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2,
            String[] arg3) {        
        if (sender instanceof Player) {
            if (MSTR_Permissions.canTeleport((Player)sender)) {
                Player player = (Player)sender;
                if (arg3.length > 0) {
                    if (arg3[0].length() > 0) {
                        Player target = plugin.findPlayerNamed(arg3[0]);                
                        if (target != null) {
                            MSTR_SpecialEffects.poofAtPlayer(target);
                            MSTR_Comms.sendInfo(player, "Teleporting " + target.getDisplayName() + " to your location");
                            MSTR_Comms.sendInfo(target, player.getDisplayName() + " has teleported you to their location");
                            target.teleport(getDestinationBlock(player));
                            MSTR_SpecialEffects.poofAtPlayer(target);
                        } else {
                            MSTR_Comms.sendError(player,"No player by that name was found!");                        
                        }
                    }
                }
            } else {  
                MSTR_Comms.permDenyMsg((Player)sender);              
            }
        } else {
            MSTR_Comms.sendConsole("Player command only!");
        }

        return true;
    }
    
    
    private static Location getDestinationBlock(Player player) {		
    	Location returnMe = player.getLocation();
		BlockFace direction = yawToFace(player.getLocation().getYaw());		
		Block blockInFront = player.getLocation().getBlock().getRelative(direction, 1);
		
		if (blockInFront.getType() == Material.AIR) {
			if (blockInFront.getRelative(BlockFace.UP,1).getType() == Material.AIR) {
				// Adjust the coordinates so that they are in the middle of the block, not on the corner of the block
				returnMe = new Location(blockInFront.getWorld(), blockInFront.getX() + 0.5, blockInFront.getY() + 0.5, blockInFront.getZ() + 0.5);
			}
		}
		
		// Check if the blocks below are a free fall		
		boolean solidGround = false;		
		for (int x = 1; x <= 5; x++) {			
			if (blockInFront.getRelative(BlockFace.DOWN,x).getType() != Material.AIR) {
				solidGround = true;
			}
		}		
		
		if (!solidGround) {
			Block platformBlock = blockInFront.getRelative(BlockFace.DOWN,1);
			platformBlock.setType(Material.GLASS);
		}
		
		return returnMe;
	}
	
	private static BlockFace yawToFace(float yaw) {
		//BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
	    //BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
		BlockFace[] radial = { BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST, BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST };
	    
	    
	    return radial[Math.round(yaw / 45f) & 0x7];
        // return axis[Math.round(yaw / 90f) & 0x3];
	}

}

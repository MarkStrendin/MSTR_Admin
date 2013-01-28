package ca.strendin.MSTR_Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MSTR_VanishCommand implements CommandExecutor {
	public MSTR_VanishCommand(MSTR_Admin strendinAdmin) {}
	
	
	private void makeInvisible(Player player) {
		MSTR_PlayerTracker.addToVanishedList(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147000, 1));
		
		player.setCanPickupItems(false);
		
		MSTR_SpecialEffects.poofAtPlayer(player);
		MSTR_Comms.sendEmote(player, "*POOF* You are now INVISIBLE");
		MSTR_Comms.sendEmote(player, " Note: Your armour and weapons are still visible!");		
	}
	
	private void makeVisible(Player player) {
		MSTR_PlayerTracker.removeFromVanishedList(player);
		if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
		}		
		player.setCanPickupItems(true);
		MSTR_SpecialEffects.poofAtPlayer(player);
		MSTR_Comms.sendEmote(player, "*POOF* You are now VISIBLE");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (MSTR_Permissions.canVanish(player)) {				
				if (MSTR_PlayerTracker.isVanished(player)) {
					makeVisible(player);
				} else {
					makeInvisible(player);					
				}
			}
		} else {
			MSTR_Comms.sendConsole("Player command only!");
		}
		
		return true;
	}

}

package ca.strendin.StrendinAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SAVanishCommand implements CommandExecutor {
	public SAVanishCommand(StrendinAdmin strendinAdmin) {}
	
	
	private void makeInvisible(Player player) {
		SATracker.addToVanishedList(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147000, 1));
			
		SASpecialEffects.poofAtPlayer(player);
		SAComms.sendEmote(player, "*POOF* You are now INVISIBLE");
		SAComms.sendEmote(player, " Note: Your armour and weapons are still visible!");		
	}
	
	private void makeVisible(Player player) {
		SATracker.removeFromVanishedList(player);
		if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
		}		
		SASpecialEffects.poofAtPlayer(player);
		SAComms.sendEmote(player, "*POOF* You are now VISIBLE");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (SAPermissions.canVanish(player)) {				
				if (SATracker.isVanished(player)) {
					makeVisible(player);
				} else {
					makeInvisible(player);					
				}
			}
		} else {
			SAComms.sendConsole("Player command only!");
		}
		
		return true;
	}

}

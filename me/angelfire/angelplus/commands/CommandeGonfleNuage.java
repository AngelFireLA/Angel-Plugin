package me.angelfire.angelplus.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandeGonfleNuage implements CommandExecutor {
	Map<String, Long> cooldowngonflenuage = new HashMap<String, Long>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("angelplus.nuageux")) {
				if (args.length ==0) {
					if (cooldowngonflenuage.containsKey(player.getName())) {
						if (cooldowngonflenuage.get(player.getName()) > System.currentTimeMillis()) {
							long timeleft = (cooldowngonflenuage.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
							return true;
						}
					}
					
					cooldowngonflenuage.put(player.getName(), System.currentTimeMillis() + (60 * 1000));
					
					
			player.sendMessage(ChatColor.AQUA + "Gonflement du nuage enclenché!");
			//propule le joueur dans les airs
			player.setVelocity(player.getLocation().getDirection().multiply(1.25).setY(1.5));
			//donne slow falling pour empêcher les dégats de chute
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 1));
			player.setHealth(player.getHealth()-3);
			
			return true;
			}
			}
			player.sendMessage(ChatColor.RED + "Tu es a court d'eau ?");
			return true;
		}
		
		else {
			sender.sendMessage(ChatColor.BLUE + "La console s'envole vers d'autres cieux !");
			return true;
		}
	}

}

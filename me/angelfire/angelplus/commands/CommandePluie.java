package me.angelfire.angelplus.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandePluie implements CommandExecutor {

	Map<String, Long> cooldownpluie = new HashMap<String, Long>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("angelplus.nuageux")) {
				if (cooldownpluie.containsKey(player.getName())) {
					if (cooldownpluie.get(player.getName()) > System.currentTimeMillis()) {
						long timeleft = (cooldownpluie.get(player.getName()) - System.currentTimeMillis()) / 1000;
						player.sendMessage(
								ChatColor.AQUA + "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
						return true;
					}
				}

				cooldownpluie.put(player.getName(), System.currentTimeMillis() + (60 * 1000));

				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Il pleut il pleut bergère");
				//choisi l'item a drop
				ItemStack drop = new ItemStack(Material.WATER_BUCKET, 1);
				World world = player.getWorld();
				//drop l'item choisi
				world.dropItem(player.getLocation(), drop);
				player.setHealth(player.getHealth() - 3);

				return true;

			}
			player.sendMessage(ChatColor.RED + "Tu es a court d'eau ?");
			return true;
		}

		else {
			sender.sendMessage(ChatColor.BLUE + "La console est maintenant trempée !");
			return true;
		}
	}
}

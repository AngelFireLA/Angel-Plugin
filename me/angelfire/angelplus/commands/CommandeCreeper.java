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

public class CommandeCreeper implements CommandExecutor {
	//stocke le cooldown
	Map<String, Long> cooldowncreeper = new HashMap<String, Long>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("angelplus.hakim")) {
				if (cooldowncreeper.containsKey(player.getName())) {
					if (cooldowncreeper.get(player.getName()) > System.currentTimeMillis()) {
						long timeleft = (cooldowncreeper.get(player.getName()) - System.currentTimeMillis()) / 1000;
						player.sendMessage(
								ChatColor.AQUA + "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
						return true;
					}
				}
				//le cooldown
				cooldowncreeper.put(player.getName(), System.currentTimeMillis() + (60 * 1000));
				player.sendMessage(ChatColor.DARK_GREEN + "[AngelPlus] Creeper, aw man");
				//récupère l'item a spawn
				ItemStack drop = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
				//récupère le monde du joueur
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				player.setHealth(player.getHealth() - 3);

				return true;

			}
			//s'il a pas la perm
			player.sendMessage(ChatColor.RED + "C'est trop dangereux pour toi !");
			return true;
		}

		else {
			sender.sendMessage(ChatColor.BLUE + "La console s'envole vers d'autres cieux !");
			return true;
		}
	}

}

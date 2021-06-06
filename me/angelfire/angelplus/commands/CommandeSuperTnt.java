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
import org.bukkit.inventory.meta.ItemMeta;

public class CommandeSuperTnt implements CommandExecutor{
	Map<String, Long> cooldownsupertnt = new HashMap<String, Long>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("angelplus.hakim")) {
				if (cooldownsupertnt.containsKey(player.getName())) {
					if (cooldownsupertnt.get(player.getName()) > System.currentTimeMillis()) {
						long timeleft = (cooldownsupertnt.get(player.getName()) - System.currentTimeMillis()) / 1000;
						player.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
						return true;
					}
				}
				
				cooldownsupertnt.put(player.getName(), System.currentTimeMillis() + (60 * 1000));

			player.sendMessage(ChatColor.AQUA + "[AngelPlus] Hakim va faire sauter des bâtiments !");
			World world = player.getWorld();
			//drop l'item chosi par getItem
			world.dropItem(player.getLocation(), getItem());
			player.setHealth(player.getHealth()-3);
				
			return true;
			
			}
			player.sendMessage(ChatColor.RED + "La puissance de cette tnt est trop grande pour toi !");
			return true;
		}
		
			else {
				sender.sendMessage(ChatColor.BLUE + "La console est une terroriste ?");
				return true;
			}
	}
	public ItemStack getItem() {
		//créer un item tnt
		ItemStack supertnt = new ItemStack(Material.TNT);
		//change la meta de l'item
		ItemMeta meta = supertnt.getItemMeta();
		
		meta.setDisplayName(ChatColor.RED + "Super TNT");
		//applique la meta
		supertnt.setItemMeta(meta);
		return supertnt;
	}

}

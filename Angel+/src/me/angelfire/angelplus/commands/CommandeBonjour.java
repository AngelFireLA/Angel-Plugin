package me.angelfire.angelplus.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandeBonjour implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			//si c'est un joueur
			if (sender instanceof Player) {
				Player player = (Player) sender;
				//s'il a la permission
				if (player.hasPermission("angelplus.bonjour")) {
				//envoie le message dans el chat
				player.sendMessage(ChatColor.AQUA + "Salut à toi jeune entrepreneur !");
				return true;
				}
				//si on a pas la perm
				player.sendMessage(ChatColor.RED + "Même pas la permission de faire un petit Bonjour!");
				return true;
			}
			
			else {
				sender.sendMessage(ChatColor.BLUE + "Hello Console");
				return true;
			}
	}

}

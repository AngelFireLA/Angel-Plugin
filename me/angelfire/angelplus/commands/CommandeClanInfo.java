package me.angelfire.angelplus.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandeClanInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			//s'il a une perm envoie un messsage s'il a l'autre envoie un autre message sinon envoie un message d'erreur
			if (player.hasPermission("angelplus.claninfo.hakim")) {
				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Tu es un Hakim !");
				return true;
			} else if (player.hasPermission("angelplus.claninfo.nuageux")) {
				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Tu es un Nuageux !");
				return true;
			}
			player.sendMessage(ChatColor.RED + "Tu ne fais parti d'aucun clan !");
			return true;
		}

		else {
			sender.sendMessage(ChatColor.BLUE + "Hello Console");
			return true;
		}
	}

}

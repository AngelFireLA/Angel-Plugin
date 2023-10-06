package me.angelfire.angelplus.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

import me.angelfire.angelplus.Main;

public class CommandeChoose implements CommandExecutor {
	Plugin plugin = Main.getPlugin(Main.class);
	public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();

	//le PermissionSetter et le SetupPermission donne les permissions lister au joueurs
	private void permissionsSetternuageux(UUID uuid) {
		PermissionAttachment attachment = this.playerPermissions.get(uuid);
		String permchose = "angelplus.nuageux";
		String permchose2 = "angelplus.claninfo.nuageux";
		System.out.print(permchose);
		//liste des commandes a donner ou enlever
		attachment.setPermission(permchose, true);
		attachment.setPermission(permchose2, true);
		attachment.setPermission("angelplus.hakim", false);
		attachment.setPermission("angelplus.claninfo.hakim", false);
		attachment.setPermission("angelplus.choose", true);
	}

	public void setupPermissionsnuageux(Player player) {
		PermissionAttachment attachment = player.addAttachment(plugin);
		this.playerPermissions.put(player.getUniqueId(), attachment);
		permissionsSetternuageux(player.getUniqueId());
	}

	private void permissionsSetterhakim(UUID uuid) {
		PermissionAttachment attachment = this.playerPermissions.get(uuid);
		String permchose = "angelplus.hakim";
		String permchose2 = "angelplus.claninfo.hakim";
		System.out.print(permchose);
		attachment.setPermission(permchose, true);
		attachment.setPermission("angelplus.nuageux", false);
		attachment.setPermission("angelplus.claninfo.nuageux", false);
		attachment.setPermission(permchose2, true);
		attachment.setPermission("angelplus.choose", true);
	}

	public void setupPermissionshakim(Player player) {
		PermissionAttachment attachment = player.addAttachment(plugin);
		this.playerPermissions.put(player.getUniqueId(), attachment);
		permissionsSetterhakim(player.getUniqueId());
	}

	private void permissionsSetteradmin(UUID uuid) {
		PermissionAttachment attachment = this.playerPermissions.get(uuid);
		String permchose = "angelplus.choose";
		System.out.print(permchose);
		attachment.setPermission(permchose, false);
	}

	public void setupPermissionsadmin(Player player) {
		PermissionAttachment attachment = player.addAttachment(plugin);
		this.playerPermissions.put(player.getUniqueId(), attachment);
		permissionsSetteradmin(player.getUniqueId());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			//s'il y a un argument uniquement après le choose
			if (args.length == 1) {
				//récupère l'argument
				String hakimounuageuxString = args[0];
				//vérifie si l'argument correspond
				if (hakimounuageuxString.equals("hakim")) {
					//si le joueur a la perm
					if (!player.hasPermission("angelplus.choose")) {
						//donne les permissions
						setupPermissionshakim(player);
						sender.sendMessage(ChatColor.AQUA + "[AngelPlus] Saumon Fumée");
					}
					sender.sendMessage("Désolé tu as déja choisi");

				} else if (hakimounuageuxString.equals("nuageux")) {
					if (!player.hasPermission("angelplus.choose")) {
						setupPermissionsnuageux(player);
						sender.sendMessage(ChatColor.AQUA + "[AngelPlus] Gloire au Titan Nuageux !");
					}
					sender.sendMessage("Désolé tu as déja choisi");

				} else if (hakimounuageuxString.equals("admin")) {

					sender.sendMessage(ChatColor.AQUA + "[AngelPlus] Admin go brrrrr");
					setupPermissionsadmin(player);

				} else {
					// s'il n'y a pas d'argument ou 2+
					player.sendMessage(ChatColor.RED
							+ "[AngelPlus] Veuillez mettre hakim ou nuageux après le choose uniquement. ( si tu as eu le message en bleu claire juste avant ignore celui la)");
				}
			}

		}

		return true;
	}
}

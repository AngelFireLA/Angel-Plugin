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
	private void permissionsSetternuageux(UUID uuid) {
        PermissionAttachment attachment = this.playerPermissions.get(uuid);
        	String permchose = "angelplus.nuageux";
        	String permchose2 = "angelplus.claninfo.nuageux";
                System.out.print(permchose);
                attachment.setPermission(permchose, true);
                attachment.setPermission(permchose2, true);
                attachment.setPermission("angelplus.hakim", false);
                attachment.setPermission("angelplus.claninfo.hakim", false);
                attachment.setPermission("angelplus.choose", false);
            }
	
    public void setupPermissionsnuageux(Player player) {
        PermissionAttachment attachment = player.addAttachment(plugin);
        this.playerPermissions.put(player.getUniqueId(), attachment);
        permissionsSetternuageux(player.getUniqueId());}
        
    	private void permissionsSetterhakim(UUID uuid) {
            PermissionAttachment attachment = this.playerPermissions.get(uuid);
            	String permchose = "angelplus.hakim";
            	String permchose2 = "angelplus.claninfo.hakim";
                    System.out.print(permchose);
                    attachment.setPermission(permchose, true);
                    attachment.setPermission("angelplus.nuageux", false);
                    attachment.setPermission("angelplus.claninfo.nuageux", false);
                    attachment.setPermission(permchose2, true);
                    attachment.setPermission("angelplus.choose", false);
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
                    attachment.setPermission(permchose, true);
                }
    	
        public void setupPermissionsadmin(Player player) {
            PermissionAttachment attachment = player.addAttachment(plugin);
            this.playerPermissions.put(player.getUniqueId(), attachment);
            permissionsSetteradmin(player.getUniqueId());}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

				if (args.length ==1) {
						String hakimounuageuxString = args[0];
						if (hakimounuageuxString.equals("hakim")) {
							if (player.hasPermission("angelplus.choose")) {setupPermissionshakim(player);
							sender.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Saumon Fumée");}
							
						}
						else if (hakimounuageuxString.equals("nuageux")) {
							if (player.hasPermission("angelplus.choose")) {setupPermissionsnuageux(player);
							sender.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Gloire au Titan Nuageux !");}
							
						}
						else if (hakimounuageuxString.equals("admin")) {
							
							sender.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Admin go brrrrr");
							setupPermissionsadmin(player);
						
					}
				else {
					player.sendMessage(ChatColor.RED + "[AngelPlus] Veuillez mettre hakim ou nuageux après le choose uniquement. ( si tu as eu le message en bleu claire juste avant ignore celui la)");
				}
				}
						
					}
			

			return true;
	}
}

package me.angelfire.angelplus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.angelfire.angelplus.commands.CommandeBonjour;
import me.angelfire.angelplus.commands.CommandeChoose;
import me.angelfire.angelplus.commands.CommandeCreeper;
import me.angelfire.angelplus.commands.CommandeGonfleNuage;
import me.angelfire.angelplus.commands.CommandePluie;
import me.angelfire.angelplus.commands.CommandeSuperTnt;

public class Main extends JavaPlugin implements Listener {
	//stocker les cooldowns
	Map<String, Long> cooldownbn = new HashMap<String, Long>();

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		//enregistrer les commandes
		getCommand("bonjour").setExecutor(new CommandeBonjour());
		getCommand("choose").setExecutor(new CommandeChoose());
		getCommand("gonflenuage").setExecutor(new CommandeGonfleNuage());
		getCommand("claninfo").setExecutor(new CommandePluie());
		getCommand("pluie").setExecutor(new CommandePluie());
		getCommand("supertnt").setExecutor(new CommandeSuperTnt());
		getCommand("creeper").setExecutor(new CommandeCreeper());

	}

	@Override
	public void onDisable() {
//quan dle plugin s'éteint

	}
	
	@EventHandler
	public void onPlayerLowHealth(PlayerMoveEvent e) throws InterruptedException {
		Player player = e.getPlayer();
		//récupère les pv du joueurs
		Double health = player.getHealth();
		//si le joueur a la perm
		if (player.hasPermission("angelplus.nuageux")) {
			//si le joueur est dans la liste des cooldowns
			if (cooldownbn.containsKey(player.getName())) {
				//s'il reste du temps au cooldown et si le joueur a moins de  3 coeur
				if (cooldownbn.get(player.getName()) > System.currentTimeMillis() && health <= 6) {
					//calcule le temps restant en secondes
					long timeleft = (cooldownbn.get(player.getName()) - System.currentTimeMillis()) / 1000;
					player.sendMessage(ChatColor.AQUA + "[AngelPlus] Tu dois survivre encore " + timeleft
							+ " secondes pour avoir ton bouclier nuageux !");
					TimeUnit.SECONDS.sleep(15);
					return;
				}
			}
			//durée du cooldown
			cooldownbn.put(player.getName(), System.currentTimeMillis() + (60 * 1000));
		}
		//si le joueur a moins de 3 coeurs et n'a plus de temps de cooldown
		else if (health <= 6 && cooldownbn.get(player.getName()) < System.currentTimeMillis()) {
			player.sendMessage(ChatColor.AQUA + "[AngelPlus] Bouclier Nuageux activé !");
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 100));

		}

	}

	@EventHandler
	public void OnSuperTntPlaced(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		//récupère le block passé par le joueur
		Block block = e.getBlockPlaced();
		@SuppressWarnings("deprecation")
		ItemMeta im = player.getItemInHand().getItemMeta();
		//vérifie le nom de l'item dans la main du joueur
		if (im.getDisplayName().equals(ChatColor.RED + "Super TNT")) {
			//supprime le bloc de tnt
			block.setType(Material.AIR);
			World w = player.getWorld();
			//créer une explosion et enlève de la vie au joueur
			w.createExplosion(block.getLocation(), 6F);
			player.setHealth(player.getHealth() - 3);
		}
	}
}

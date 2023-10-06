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
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.angelfire.angelplus.commands.CommandeBonjour;
import me.angelfire.angelplus.commands.CommandeChoose;
import me.angelfire.angelplus.commands.CommandeClanInfo;
import me.angelfire.angelplus.commands.CommandeCreeper;
import me.angelfire.angelplus.commands.CommandeGonfleNuage;
import me.angelfire.angelplus.commands.CommandePluie;
import me.angelfire.angelplus.commands.CommandeSuperTnt;

public class Main extends JavaPlugin implements Listener {
	
	

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		//enregistrer les commandes
		getCommand("bonjour").setExecutor(new CommandeBonjour());
		getCommand("choose").setExecutor(new CommandeChoose());
		getCommand("gonflenuage").setExecutor(new CommandeGonfleNuage());
		getCommand("claninfo").setExecutor(new CommandeClanInfo());
		getCommand("pluie").setExecutor(new CommandePluie());
		getCommand("supertnt").setExecutor(new CommandeSuperTnt());
		getCommand("creeper").setExecutor(new CommandeCreeper());


	}

	@Override
	public void onDisable() {
	//quand le plugin s'éteint

	}
	
	//stocker les cooldowns pour bouclier nuageux
		Map<String, Long> cooldownbn = new HashMap<String, Long>();
		
		
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
		
		@EventHandler
		public void onPlayerAchieve(PlayerAdvancementDoneEvent e) {
			Player player = e.getPlayer();
			if (e.getAdvancement().getKey().toString().contains("story/cure_zombie_villager")) {
				ItemStack drop = new ItemStack(Material.VILLAGER_SPAWN_EGG, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			
			if (e.getAdvancement().getKey().toString().contains("story/obtain_armor")) {
				ItemStack drop = new ItemStack(Material.GOLDEN_HELMET, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			
			if (e.getAdvancement().getKey().toString().contains("story/form_obsidian")) {
				ItemStack drop = new ItemStack(Material.OBSIDIAN, 9);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("story/deflect_arrow")) {
				ItemStack drop = new ItemStack(Material.ARROW, 8);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			
			if (e.getAdvancement().getKey().toString().contains("story/mine_diamond")) {
				ItemStack drop = new ItemStack(Material.NETHERITE_SCRAP, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			
			if (e.getAdvancement().getKey().toString().contains("story/shiny_gear")) {
				ItemStack drop = new ItemStack(Material.NETHERITE_SCRAP, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("story/enchant_item")) {
				ItemStack drop = new ItemStack(Material.EXPERIENCE_BOTTLE, 16);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/return_to_sender")) {
				ItemStack drop = new ItemStack(Material.GHAST_TEAR, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/fast_travel")) {
				ItemStack drop = new ItemStack(Material.RABBIT_FOOT, 4);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/distract_piglin")) {
				ItemStack drop = new ItemStack(Material.GOLD_BLOCK, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/netherite_armor")) {
				ItemStack drop = new ItemStack(Material.DIAMOND_BLOCK, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/get_wither_skull")) {
				ItemStack drop = new ItemStack(Material.WITHER_SKELETON_SKULL, 2);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/summon_wither")) {
				ItemStack drop = new ItemStack(Material.NETHER_STAR, 2);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
			}
			if (e.getAdvancement().getKey().toString().contains("nether/brew_potion")) {
				ItemStack drop = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("nether/create_full_beacon	")) {
				ItemStack drop = new ItemStack(Material.IRON_BLOCK, 16);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("end/kill_dragon")) {
				ItemStack drop = new ItemStack(Material.SPAWNER, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				ItemStack drop2 = new ItemStack(Material.COW_SPAWN_EGG, 1);
				world.dropItem(player.getLocation(), drop2);
				
			}
			if (e.getAdvancement().getKey().toString().contains("end/dragon_egg")) {
				ItemStack drop = new ItemStack(Material.EGG, 64);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("end/elytra")) {
				ItemStack drop = new ItemStack(Material.BLACK_SHULKER_BOX, 4);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("adventure/trade")) {
				ItemStack drop = new ItemStack(Material.PILLAGER_SPAWN_EGG, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("adventure/shoot_arrow")) {
				ItemStack drop = new ItemStack(Material.SPECTRAL_ARROW, 16);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);
				
			}
			if (e.getAdvancement().getKey().toString().contains("adventure/totem_of_undying")) {
				ItemStack drop = new ItemStack(Material.DIAMOND, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);	
			}
			
			if (e.getAdvancement().getKey().toString().contains("husbandry/breed_an_animal")) {
				ItemStack drop = new ItemStack(Material.CARROT, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);	
			}
			if (e.getAdvancement().getKey().toString().contains("husbandry/plant_seed")) {
				ItemStack drop = new ItemStack(Material.POTATO, 1);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);	
			}
			if (e.getAdvancement().getKey().toString().contains("husbandry/obtain_netherite_hoe")) {
				ItemStack drop = new ItemStack(Material.NETHERITE_SCRAP, 2);
				World world = player.getWorld();
				world.dropItem(player.getLocation(), drop);	
			}
		}

}

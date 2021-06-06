package me.angelfire.angelplus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.angelfire.angelplus.commands.CommandeBonjour;
import me.angelfire.angelplus.commands.CommandeChoose;

public class Main extends JavaPlugin implements Listener{
	

	Map<String, Long> cooldowngonflenuage = new HashMap<String, Long>();
	Map<String, Long> cooldownpluie = new HashMap<String, Long>();
	Map<String, Long> cooldownsupertnt = new HashMap<String, Long>();
	Map<String, Long> cooldowncreeper = new HashMap<String, Long>();
	Map<String, Long> cooldownbn = new HashMap<String, Long>();
	
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		getCommand("bonjour").setExecutor(new CommandeBonjour());
		getCommand("choose").setExecutor(new CommandeChoose());
		
	}
	@Override
	public void onDisable() {
		// shutdown
		//reload
		//plugin reloads
		
	}
	
	//command test /hello
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//si la commande est /hello

		
		 if (label.equalsIgnoreCase("gonflenuage")) {
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
				player.setVelocity(player.getLocation().getDirection().multiply(1.25).setY(1.5));
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
		else if (label.equalsIgnoreCase("claninfo")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.claninfo.hakim")) {
				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Tu es un Hakim !");
				return true;
				}
				else if (player.hasPermission("angelplus.claninfo.nuageux")) {
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
		else if (label.equalsIgnoreCase("pluie")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.nuageux")) {
					if (cooldownpluie.containsKey(player.getName())) {
						if (cooldownpluie.get(player.getName()) > System.currentTimeMillis()) {
							long timeleft = (cooldownpluie.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
							return true;
						}
					}
					
					cooldownpluie.put(player.getName(), System.currentTimeMillis() + (60 * 1000));

				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Il pleut il pleut bergère");
				ItemStack drop = new ItemStack(Material.WATER_BUCKET, 1);
				World world = player.getWorld();

				world.dropItem(player.getLocation(), drop);
				player.setHealth(player.getHealth()-3);
					
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
		else if (label.equalsIgnoreCase("supertnt")) {
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
		else if (label.equalsIgnoreCase("creeper")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.nuageux")) {
					if (cooldowncreeper.containsKey(player.getName())) {
						if (cooldowncreeper.get(player.getName()) > System.currentTimeMillis()) {
							long timeleft = (cooldowncreeper.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Erreur il reste " + timeleft + " secondes de cooldown !");
							return true;
						}
					}
					
					cooldowncreeper.put(player.getName(), System.currentTimeMillis() + (60 * 1000));

				player.sendMessage(ChatColor.DARK_GREEN + "[AngelPlus] Creeper, aw man");
				ItemStack drop = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
				World world = player.getWorld();

				world.dropItem(player.getLocation(), drop);
				player.setHealth(player.getHealth()-3);
					
				return true;
				
				}
				player.sendMessage(ChatColor.RED + "C'est trop dangereux pour toi !");
				return true;
			}
			
				else {
					sender.sendMessage(ChatColor.BLUE + "La console s'envole vers d'autres cieux !");
					return true;
				}
			}
		return false;
	}
	public ItemStack getItem() {
		
		ItemStack supertnt = new ItemStack(Material.TNT);
		ItemMeta meta = supertnt.getItemMeta();
		
		meta.setDisplayName(ChatColor.RED + "Super TNT");
		supertnt.setItemMeta(meta);
		return supertnt;
	}
	
	@EventHandler
	public void OnSuperTntPlaced(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlockPlaced();
        @SuppressWarnings("deprecation")
		ItemMeta im = player.getItemInHand().getItemMeta();
        if(im.getDisplayName().equals(ChatColor.RED + "Super TNT"))
        {
        	block.setType(Material.AIR);
    		World w = player.getWorld();
    		w.createExplosion(block.getLocation(), 6F);
    		player.setHealth(player.getHealth()-3);
        }
	}
	
	@EventHandler
	public void onPlayerLowHealth(PlayerMoveEvent e) throws InterruptedException {
		Player player = e.getPlayer();
		Double health = player.getHealth();
		if (player.hasPermission("angelplus.nuageux")) {
			if (cooldownbn.containsKey(player.getName())) {
				if (cooldownbn.get(player.getName()) > System.currentTimeMillis() && health <= 6) {
					long timeleft = (cooldownbn.get(player.getName()) - System.currentTimeMillis()) / 1000;
					player.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Tu dois survivre encore " + timeleft + " secondes pour avoir ton bouclier nuageux !");
					TimeUnit.SECONDS.sleep(15);
					return;
				}
			}
			
			cooldownbn.put(player.getName(), System.currentTimeMillis() + (60 * 1000));
		}
		
			else if(health <= 6 && cooldownbn.get(player.getName()) < System.currentTimeMillis()) {
			player.sendMessage(ChatColor.AQUA + "[AngelPlus] Bouclier Nuageux activé !");
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 100));
			
		}
		}
	}
	
	

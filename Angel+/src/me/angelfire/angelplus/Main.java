package me.angelfire.angelplus;

import java.util.HashMap;
import java.util.UUID;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{
	
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
            }
	
    public void setupPermissionsnuageux(Player player) {
        PermissionAttachment attachment = player.addAttachment(this);
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
                }
    	
        public void setupPermissionshakim(Player player) {
            PermissionAttachment attachment = player.addAttachment(this);
            this.playerPermissions.put(player.getUniqueId(), attachment);
            permissionsSetterhakim(player.getUniqueId());
    }
    	private void permissionsSetteradmin(UUID uuid) {
            PermissionAttachment attachment = this.playerPermissions.get(uuid);
                    attachment.setPermission("angelplus.choose", true);
                }
    	
        public void setupPermissionsadmin(Player player) {
            PermissionAttachment attachment = player.addAttachment(this);
            this.playerPermissions.put(player.getUniqueId(), attachment);
            permissionsSetteradmin(player.getUniqueId());}
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		
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
		if (label.equalsIgnoreCase("bonjour")) {
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
		
		else if (label.equalsIgnoreCase("gonflenuage")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.nuageux")) {
					if (args.length ==0) {
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
		else if (label.equalsIgnoreCase("choose")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.choose")) {
					if (args.length ==1) {
							String hakimounuageuxString = args[0];
							if (hakimounuageuxString.equals("hakim")) {
								setupPermissionshakim(player);
								sender.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Saumon Fumée");
								
							}
							else if (hakimounuageuxString.equals("nuageux")) {
								setupPermissionsnuageux(player);
								sender.sendMessage(ChatColor.AQUA  +  "[AngelPlus] Gloire au Titan Nuageux !");
								
							}
							
						}
					else {
						player.sendMessage(ChatColor.RED + "[AngelPlus] Veuillez mettre hakim ou nuageux après le choose uniquement. ( si tu as eu le message en bleu claire juste avant ignore celui la)");
					}
					}
				
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
		else if (label.equalsIgnoreCase("givechooseperm")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				setupPermissionsadmin(player);
				player.sendMessage(ChatColor.AQUA + "[AngelPlus] Permission reçue");
				return true;
				}
			
			else {
				sender.sendMessage(ChatColor.BLUE + "Yo Console");
				return true;
			}
	}
		else if (label.equalsIgnoreCase("pluie")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("angelplus.nuageux")) {

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

				player.sendMessage(ChatColor.DARK_GREEN + "[AngelPlus] Creeper, aw man");
				ItemStack drop = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
				World world = player.getWorld();

				world.dropItem(player.getLocation(), drop);
				player.setHealth(player.getHealth()-3);
					
				return true;
				
				}
				player.sendMessage(ChatColor.RED + "Tu es a court d'eau ?");
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
    		w.createExplosion(block.getLocation(), 4F);
        }
        		
        	
        

	}
	
}
	

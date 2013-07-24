package me.Kenig.PVPIL;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@SuppressWarnings("deprecation")
public class Ach implements Listener, CommandExecutor{
public static Main plugin;

File ListsFile;
File KFile;
File DFile;
File File1;
File hksFile;
File ksFile;
File EffectFile;
FileConfiguration Lists;
FileConfiguration Kills;
FileConfiguration Deaths;
FileConfiguration v1F;
FileConfiguration hks;
FileConfiguration ks;
FileConfiguration Eff;

public Ach(Main instance){
plugin = instance;
ListsFile = plugin.ListsFile;
Lists = plugin.Lists;
KFile = plugin.KFile;
Kills = plugin.Kills;
DFile = plugin.DFile;
Deaths = plugin.Deaths;
}

public void saveList(){
try{Lists.save(ListsFile);
}catch(IOException e){e.printStackTrace();}}

public void saveKills(){
try{Kills.save(KFile);
}catch(IOException e){e.printStackTrace();}}

public void saveDeaths(){
try{Deaths.save(DFile);
}catch(IOException e){e.printStackTrace();}}

public void save1(){
try{v1F.save(File1);
}catch(IOException e){e.printStackTrace();}}

public void savehks(){
try{hks.save(hksFile);
}catch(IOException e){e.printStackTrace();}}

public void saveks(){
try{ks.save(ksFile);
}catch(IOException e){e.printStackTrace();}}

public void saveff(){
try{Eff.save(EffectFile);
}catch(IOException e){e.printStackTrace();}}

    @EventHandler
    public void Kills(PlayerDeathEvent e){
    if(e.getEntity().getKiller() instanceof Player){
	List<String> List100 = Lists.getStringList("100Kills");
	List<String> List500 = Lists.getStringList("500Kills");
	List<String> List1000 = Lists.getStringList("1000Kills");
    Player K = (Player) e.getEntity().getKiller();
    int Kk = Kills.getInt(K.getName() + " kills");
    if(!List100.contains(K.getName())){
    if(Kk >= 100){
	int C = plugin.getConfig().getInt(K.getName() + " coin");
	plugin.getConfig().set(K.getName() + " coin", C+20);
    K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 1);
    K.sendMessage("§eAchievement get ❢");
    K.sendMessage("§5###############");
    K.sendMessage("§4✖§c New Killer §4✖");
    K.sendMessage("§5###############");
	List100.add(K.getName());
	Lists.set("100Kills", List100);saveList();
	plugin.saveConfig();}}
    if(!List500.contains(K.getName())){
    if(Kk >= 500){
    int C = plugin.getConfig().getInt(K.getName() + " coin");
    plugin.getConfig().set(K.getName() + " coin", C+20);
    K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 1);
    K.sendMessage("§eAchievement get ❢");
    K.sendMessage("§5###############");
    K.sendMessage("§4✖§c Adcanced Killer §4✖");
    K.sendMessage("§5###############");
	List500.add(K.getName());
	Lists.set("500Kills", List500);saveList();
	plugin.saveConfig();}}
    if(!List1000.contains(K.getName())){
    if(Kk >= 1000){
    int C = plugin.getConfig().getInt(K.getName() + " coin");
    plugin.getConfig().set(K.getName() + " coin", C+20);
    K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 1);
    K.sendMessage("§eAchievement get ❢");
    K.sendMessage("§5###############");
    K.sendMessage("§4✖§c Pro Killer §4✖");
    K.sendMessage("§5###############");
	List1000.add(K.getName());
	Lists.set("1000Kills", List1000);saveList();
	plugin.saveConfig();
	}}}
    }

    @EventHandler
    public void Deaths(PlayerDeathEvent e){
    Player D = (Player) e.getEntity();
	List<String> List100 = Lists.getStringList("100Deaths");
	List<String> List500 = Lists.getStringList("500Deaths");
	List<String> List1000 = Lists.getStringList("1000Deaths");
    int Dd = Deaths.getInt(D.getName() + " deaths");
    if(!List100.contains(D.getName())){
    if(Dd >= 100){
    int C = plugin.getConfig().getInt(D.getName() + " coin");
    plugin.getConfig().set(D.getName() + " coin", C+20);
    D.getWorld().playSound(D.getLocation(), Sound.LEVEL_UP, 10, 1);
    D.sendMessage("§eAchievement get ❢");
    D.sendMessage("§5###############");
    D.sendMessage("   §4✖§c New Killer §4✖");
    D.sendMessage("§5###############");
	List100.add(D.getName());
	Lists.set("100Deaths", List100);saveList();
	plugin.saveConfig();}}
    if(!List500.contains(D.getName())){
    if(Dd >= 500){
    int C = plugin.getConfig().getInt(D.getName() + " coin");
    plugin.getConfig().set(D.getName() + " coin", C+20);
    D.getWorld().playSound(D.getLocation(), Sound.LEVEL_UP, 10, 1);
    D.sendMessage("§eAchievement get ❢");
    D.sendMessage("§5###############");
    D.sendMessage("§4✖ §cNub §4✖");
    D.sendMessage("§5###############");
	List500.add(D.getName());
	Lists.set("500Deaths", List500);saveList();
	plugin.saveConfig();}}
    if(!List1000.contains(D.getName())){
    if(Dd >= 1000){
    int C = plugin.getConfig().getInt(D.getName() + " coin");
    plugin.getConfig().set(D.getName() + " coin", C+20);
    D.getWorld().playSound(D.getLocation(), Sound.LEVEL_UP, 10, 1);
    D.sendMessage("§eAchievement get ❢");
    D.sendMessage("§5###############");
    D.sendMessage("§4✖ §cMasochist §4✖");
    D.sendMessage("§5###############");
	List1000.add(D.getName());
	Lists.set("1000Deaths", List1000);saveList();
	plugin.saveConfig();}}
    }
 
    @EventHandler
    public void Coins(PlayerJoinEvent e){
    Player p = e.getPlayer();
	List<String> ListCoins500 = Lists.getStringList("Coins500");
	List<String> ListCoins1500 = Lists.getStringList("Coins1500");
    int Coins = plugin.getConfig().getInt(p.getName() + " coin");
    if(Coins > 500){
    if(!ListCoins500.contains(p.getName())){
    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 1);
    p.sendMessage("§eAchievement get ❢");
    p.sendMessage("§5###############");
    p.sendMessage("§4✖ §cBillionaire §4✖");
    p.sendMessage("§5###############");
    ListCoins500.add(p.getName());
    Lists.set("Coins500", ListCoins500);
    plugin.saveConfig();
    saveList();}}
    if(Coins > 1500){
    if(!ListCoins1500.contains(p.getName())){
    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 1);
    p.sendMessage("§eAchievement get ❢");
    p.sendMessage("§5###############");
    p.sendMessage("§4✖ §cOligarch §4✖");
    p.sendMessage("§5###############");
    ListCoins1500.add(p.getName());
    Lists.set("Coins1500", ListCoins1500);
    plugin.saveConfig();
    saveList();}}
    }

    @EventHandler
    public void Kenig(PlayerDeathEvent e){
    if(e.getEntity().getKiller() instanceof Player){
    List<String> ListKenig = Lists.getStringList("Kenig");
    Player D = (Player) e.getEntity();
    Player K = (Player) e.getEntity().getKiller();
    if(D.getName().equalsIgnoreCase("orikenig")){
    if(!ListKenig.contains(K.getName())){
	int C = plugin.getConfig().getInt(K.getName() + " coin");
	plugin.getConfig().set(K.getName() + " coin", C+30);
    K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 1);
    K.sendMessage("§eAchievement get ❢");
    K.sendMessage("§5###############");
    K.sendMessage("§4✖ §cOri's Killer §4✖");
    K.sendMessage("§5###############");
    ListKenig.add(K.getName());
    Lists.set("Kenig", ListKenig);
    plugin.saveConfig();
    saveList();}}}
    }
    
    @EventHandler
    public void MOF(PlayerChatEvent e){
    Player p = e.getPlayer();
	List<String> ListMOF = Lists.getStringList("MOF");
    if(e.getMessage().contains("I am sexy") && !ListMOF.contains(p.getName())){
    ListMOF.add(p.getName());
    Lists.set("MOF", ListMOF);
    saveList();
    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 1);
    p.sendMessage("§eAchievement get ❢");
    p.sendMessage("§5###############");
    p.sendMessage("§4✖ §cMeaning of Life §4✖");
    p.sendMessage("§5###############");}
    if(e.getMessage().contains("soup bug") || e.getMessage().contains("soup lag") || e.getMessage().contains("SOUP LAG") || e.getMessage().contains("SOUP BUG")){
    e.setCancelled(true);
    p.kickPlayer("§d§lhahahahaahhahaha");}
    }
    
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	Player p = (Player) sender;
	List<String> List100K = Lists.getStringList("100Kills");
	List<String> List500K = Lists.getStringList("500Kills");
	List<String> List1000K = Lists.getStringList("1000Kills");
	List<String> List100D = Lists.getStringList("100Deaths");
	List<String> List500D = Lists.getStringList("500Deaths");
	List<String> List1000D = Lists.getStringList("1000Deaths");
	List<String> ListKs5 = Lists.getStringList("5Ks");
	List<String> ListKs20 = Lists.getStringList("20Ks");
	List<String> ListOne50 = Lists.getStringList("1v150");
	List<String> ListOne150 = Lists.getStringList("1v1150");
	List<String> ListCoins500 = Lists.getStringList("Coins500");
	List<String> ListCoins1500 = Lists.getStringList("Coins1500");
	List<String> ListPay150 = Lists.getStringList("Pay150");
	List<String> ListKenig = Lists.getStringList("Kenig");
	List<String> ListMOF = Lists.getStringList("MOF");
	if(commandLabel.equalsIgnoreCase("ach") || commandLabel.equalsIgnoreCase("achievements")){
	if(args.length < 1){
	p.sendMessage("§2The Achievements Commands:");	
	p.sendMessage("§2/ach list - §aList of avilable achievements");
	p.sendMessage("§2/ach me - §aMy achievements");
	p.sendMessage("§2/ach [playername] - §aShow other player's achievements");}
	if(args.length > 0){
	if(!(args[0].equalsIgnoreCase("me") || args[0].equalsIgnoreCase("list"))){
	String Kills100 = "";
	String Kills500 = "";
	String Kills1000 = "";
	String Death100 = "";
	String Death500 = "";
	String Death1000 = "";
	String Ks5 = "";
	String Ks20 = "";
	String One50 = "";
	String One150 = "";
	String Coins500 = "";
	String Coins1500 = "";
	String Pay150 = "";
	String Kenig = "";
	String MOF = "";
	OfflinePlayer Target = p.getServer().getOfflinePlayer(args[0]);
	if(List100K.contains(Target.getName())){Kills100 = "§a";}
	if(List500K.contains(Target.getName())){Kills500 = "§a";}
	if(List1000K.contains(Target.getName())){Kills1000 = "§a";}
	if(List100D.contains(Target.getName())){Death100 = "§a";}
	if(List500D.contains(Target.getName())){Death500 = "§a";}
	if(List1000D.contains(Target.getName())){Death1000 = "§a";}
	if(ListKs5.contains(Target.getName())){Ks5 = "§a";}
	if(ListKs20.contains(Target.getName())){Ks20 = "§a";}
	if(ListOne50.contains(Target.getName())){One50 = "§a";}
	if(ListOne150.contains(Target.getName())){One150 = "§a";}
	if(ListCoins500.contains(Target.getName())){Coins500 = "§a";}
	if(ListCoins1500.contains(Target.getName())){Coins1500 = "§a";}
	if(ListPay150.contains(Target.getName())){Pay150 = "§a";}
	if(ListKenig.contains(Target.getName())){Kenig = "§a";}
	if(ListMOF.contains(Target.getName())){MOF = "§a";}
	p.sendMessage("§9" + Target.getName() + "'s Achievements:");
	p.sendMessage("§aGreen = §2✔ §f; §cRed = §4✖");
	p.sendMessage("§c" + Kills100 + "New Killer §f| §c" + Kills500 + "Advanced Killer §f| §c" + Kills1000 + "ProKiller §f| §c" + Death100 + "Looser §f| §c" + Death500 + "Nub");
	p.sendMessage("§c" + Death1000 + "Masochist §f| §c" + Ks5 + "Awesomeness §f| §c" + Ks20 + "UltraKiller §f| §c" + One50 + "1v1 Guy §f| §c" + One150 + "LonelyKiller");
	p.sendMessage("§c" + Coins500 + "Billionaire §f| §c" + Coins1500 + "Oligarch §f| §c" + Pay150 + "The Giving Tree §f| §c" + Kenig + "Ori's Killer §f| §c" + MOF + "Meaning of Life");}
	if(args[0].equalsIgnoreCase("me")){
	String Kills100 = "";
	String Kills500 = "";
	String Kills1000 = "";
	String Death100 = "";
	String Death500 = "";
	String Death1000 = "";
	String Ks5 = "";
	String Ks20 = "";
	String One50 = "";
	String One150 = "";
	String Coins500 = "";
	String Coins1500 = "";
	String Pay150 = "";
	String Kenig = "";
	String MOF = "";
	if(List100K.contains(p.getName())){Kills100 = "§a";}
	if(List500K.contains(p.getName())){Kills500 = "§a";}
	if(List1000K.contains(p.getName())){Kills1000 = "§a";}
	if(List100D.contains(p.getName())){Death100 = "§a";}
	if(List500D.contains(p.getName())){Death500 = "§a";}
	if(List1000D.contains(p.getName())){Death1000 = "§a";}
	if(ListKs5.contains(p.getName())){Ks5 = "§a";}
	if(ListKs20.contains(p.getName())){Ks20 = "§a";}
	if(ListOne50.contains(p.getName())){One50 = "§a";}
	if(ListOne150.contains(p.getName())){One150 = "§a";}
	if(ListCoins500.contains(p.getName())){Coins500 = "§a";}
	if(ListCoins1500.contains(p.getName())){Coins1500 = "§a";}
	if(ListPay150.contains(p.getName())){Pay150 = "§a";}
	if(ListKenig.contains(p.getName())){Kenig = "§a";}
	if(ListMOF.contains(p.getName())){MOF = "§a";}
	p.sendMessage("§9Your Achievements:");
	p.sendMessage("§aGreen = §2✔ §f; §cRed = §4✖");
	p.sendMessage("§c" + Kills100 + "New Killer §f| §c" + Kills500 + "Advanced Killer §f| §c" + Kills1000 + "ProKiller §f| §c" + Death100 + "Looser §f| §c" + Death500 + "Nub");
	p.sendMessage("§c" + Death1000 + "Masochist §f| §c" + Ks5 + "Awesomeness §f| §c" + Ks20 + "UltraKiller §f| §c" + One50 + "1v1 Guy §f| §c" + One150 + "LonelyKiller");
	p.sendMessage("§c" + Coins500 + "Billionaire §f| §c" + Coins1500 + "Oligarch §f| §c" + Pay150 + "The Giving Tree §f| §c" + Kenig + "Ori's Killer §f| §c" + MOF + "Meaning of Life");}
	if(args[0].equalsIgnoreCase("list")){
	p.sendMessage("§2List of Achievements:");
	p.sendMessage("§21) §eNew Killer §2- §aKill 100 players");
	p.sendMessage("§22) §eAdvanced Killer §2- §aKill 500 players");
	p.sendMessage("§23) §eProKiller §2- §aKill 1000 players");
	p.sendMessage("§24) §eLooser §2- §aDie 100 times");
	p.sendMessage("§25) §eNub §2- §aDie 500 times");
	p.sendMessage("§26) §eMasochist §2- §aDie 1000 times");
	p.sendMessage("§27) §eAwesomeness §2- §aGet killstreak higher then 5");
	p.sendMessage("§28) §eUltraKiller §2- §aGet killstreak higher then 20");
	p.sendMessage("§29) §e1v1 Guy §2- §aWin 50 times in 1v1");
	p.sendMessage("§210) §eLonely Killer §2- §aWin 150 times in 1v1");
	p.sendMessage("§211) §eBillionaire §2- §aGet 500 coins");
	p.sendMessage("§212) §eOligarch §2- §aGet 1500 coins");
	p.sendMessage("§213) §eThe Giving Tree §2- §aPay 150 coins to your friend");
	p.sendMessage("§214) §eOri's Killer §2- §aKill orikenig");
	p.sendMessage("§215) §eMeaning of Life §2- §asay in the chat: I am sexy");}}}
	return false;
	}
   
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
	if(event.getRightClicked() instanceof Player){
	Player player = event.getPlayer();
	if(!duckEjectPassenger(player, event.getRightClicked())){
	Player vehicle = getVehicle(player);
	if(vehicle == null){
	vehicle = (Player) event.getRightClicked();
	if(player.getItemInHand().getTypeId() == 329){
	getLastPassenger(vehicle).setPassenger(player);}
	}else{vehicle.eject();}}}
	}

	private boolean duckEjectPassenger(Player duck, Entity passenger){
	if(passenger.equals(duck.getPassenger())){
	duck.eject();
	return true;}
	return false;
	}

	private Player getLastPassenger(Player vehicle){
	while(vehicle.getPassenger() != null && vehicle.getPassenger() instanceof Player){
	vehicle = (Player) vehicle.getPassenger();}
	return vehicle;
	}

	private Player getVehicle(Player player){
	for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
	Entity passenger = onlinePlayer.getPassenger();
	if (passenger instanceof Player && passenger.getEntityId() == player.getEntityId()){
	return onlinePlayer;}}
    return null;
	}
	
}

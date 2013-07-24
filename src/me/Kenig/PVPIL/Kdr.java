package me.Kenig.PVPIL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kdr implements Listener, CommandExecutor  {
static HashMap <String, Integer> unordered = new HashMap <String, Integer>();
public static HashMap <String, Integer> ordered = new HashMap <String, Integer>();
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

public Kdr(Main instance){
plugin = instance;
ListsFile = plugin.ListsFile;
Lists = plugin.Lists;
KFile = plugin.KFile;
Kills = plugin.Kills;
DFile = plugin.DFile;
Deaths = plugin.Deaths;
File1 = plugin.File1;
v1F = plugin.v1F;
hksFile = plugin.hksFile;
hks = plugin.hks;
ksFile = plugin.ksFile;
ks = plugin.ks;
EffectFile = plugin.EffectFile;
Eff = plugin.Eff;}

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
	public void Join(PlayerJoinEvent e){
	Player p = e.getPlayer();
	if(!Kills.contains(p.getName() + " kills")){Kills.set(p.getName() + " kills", 0);saveKills();}
	if(!Deaths.contains(p.getName() + " deaths")){Deaths.set(p.getName() + " deaths", 0);saveDeaths();}
	if(!ks.contains(p.getName() + " ks")){ks.set(p.getName() + " ks", 0);saveks();}
	if(!v1F.contains(p.getName() + " 1v1")){v1F.set(p.getName() + " 1v1", 0);save1();}
	if(!plugin.getConfig().contains(p.getName() + " coin")){plugin.getConfig().set(p.getName() + " coin", 0);plugin.saveConfig();}
	if(!hks.contains(p.getName() + " hks")){hks.set(p.getName() + " hks", 0);savehks();}
	if(!Eff.contains(p.getName() + " effect")){Eff.set(p.getName() + " effect", 0);saveff();}
	ks.set(p.getName() + " ks", 0);}

	@EventHandler
	public void KillStreak(PlayerDeathEvent e){
    Player V = e.getEntity();
	List<String> ListKs5 = Lists.getStringList("5Ks");
	List<String> ListKs20 = Lists.getStringList("20Ks");
    int KSV = ks.getInt(V.getName() + " ks");
    if(KSV > 4){Bukkit.broadcastMessage("§b[§cKillStreak§b]§e " + V.getName() + " had an amazing killstreak of " + KSV + " kills");}
    if(e.getEntity().getKiller() instanceof Player){
    Player K = e.getEntity().getKiller();
    int KSK = ks.getInt(K.getName() + " ks");
    ks.set(K.getName() + " ks", KSK+1);
    int KSK2 = ks.getInt(K.getName() + " ks");
    if(KSK2 > 2){K.sendMessage("§b[§cKillStreak§b]§e You have killstreak of " + KSK2);}
    if(KSK2 == 5 || KSK2 == 10 || KSK2 == 15 || KSK2 == 20 || KSK2 == 25 || KSK2 == 30 || KSK2 == 35 || KSK2 == 40 || KSK2 == 45 || KSK2 == 50 || KSK2 == 55 || KSK2 == 60 || KSK2 == 65 || KSK2 == 70 || KSK2 == 75 || KSK2 == 80 || KSK2 == 85 || KSK2 == 90 || KSK2 == 95 || KSK2 == 100){
    List<PotionEffectType> Pe = Arrays.asList(PotionEffectType.SPEED,PotionEffectType.DAMAGE_RESISTANCE,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.REGENERATION, PotionEffectType.getById(21));
    int CoinsG = (KSK2/5)+2;
    Random Per = new Random();
    K.addPotionEffect(new PotionEffect(Pe.get(Per.nextInt(Pe.size())), 600, 0));
    K.sendMessage("§b[§cKillStreak§b]§e You got a random potion effect!");
    K.sendMessage("§b[§cKillStreak§b]§e You also got §6" + CoinsG + "§e coins!");
    int Pc = plugin.getConfig().getInt(K.getName() + " coin");
    plugin.getConfig().set(K.getName() + " coin", Pc + CoinsG);plugin.saveConfig();}
    int HKS = hks.getInt(K.getName() + " hks");
    if(KSK2 > HKS){hks.set(K.getName() + " hks", KSK2);savehks();}
	if(KSK2 > 5){
	if(!ListKs5.contains(K.getName())){
	int C = plugin.getConfig().getInt(K.getName() + " coin");
	plugin.getConfig().set(K.getName() + " coin", C+20);plugin.saveConfig();
	K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 3);
	K.sendMessage("§a~~~~~~~~~~~");
	K.sendMessage("§eAchievement get ❢");
	K.sendMessage("§4✖§c Awesomeness §4✖");
	K.sendMessage("§a~~~~~~~~~~~");
	ListKs5.add(K.getName());
	Lists.set("5Ks", ListKs5);saveList();}}
	if(KSK2 > 20){
	if(!ListKs20.contains(K.getName())){
	int C = plugin.getConfig().getInt(K.getName() + " coin");
	plugin.getConfig().set(K.getName() + " coin", C+20);plugin.saveConfig();
	K.getWorld().playSound(K.getLocation(), Sound.LEVEL_UP, 10, 3);
	K.sendMessage("§a~~~~~~~~~~~");
	K.sendMessage("§eAchievement get ❢");
	K.sendMessage("§4✖§c UltraKiller §4✖");
	K.sendMessage("§a~~~~~~~~~~~");
	ListKs20.add(K.getName());
	Lists.set("20Ks", ListKs20);saveList();}}}
    ks.set(V.getName() + " ks", 0);
	}
 	
 	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	Player p = (Player) sender;
	if(commandLabel.equalsIgnoreCase("top10")){
	if(args.length < 1){p.sendMessage("§aAvilable Top10 Commands:");p.sendMessage("§c/top10 kills");p.sendMessage("§c/top10 deaths");p.sendMessage("§c/top10 kd");p.sendMessage("§c/top10 coins");p.sendMessage("§c/top10 1v1");p.sendMessage("§c/top10 hks");}
	if(args.length > 0){
	if(args[0].equalsIgnoreCase("kills")){TopKills(p);}
	if(args[0].equalsIgnoreCase("deaths")){TopDeaths(p);}
	if(args[0].equalsIgnoreCase("kd")){TopKd(p);}
	if(args[0].equalsIgnoreCase("coins")){TopCoins(p);}
	if(args[0].equalsIgnoreCase("1v1")){Top1v1(p);}
	if(args[0].equalsIgnoreCase("hks")){TopHks(p);}}}
	int KInt = Kills.getInt(p.getName() + " kills");
	int DInt = Deaths.getInt(p.getName() + " deaths");
	int v1W = v1F.getInt(p.getName() + " 1v1");
	int Hks = hks.getInt(p.getName() + " hks");
	float K = Kills.getInt(p.getName() + " kills");
	float D = Deaths.getInt(p.getName() + " deaths");
	float KDInt = K/D;
	if(commandLabel.equalsIgnoreCase("stats")){
	if(args.length < 1){
	p.sendMessage("§9Your Stats:");
	p.sendMessage("§aKills: §2" + KInt);
	p.sendMessage("§cDeaths: §4" + DInt);
	p.sendMessage("§eK/D Ratio: §6" + KDInt);
	p.sendMessage("§d1v1 Wins: §5" + v1W);
	p.sendMessage("§7Highest KillStreak: §8" + Hks);}
	if(args.length > 0){
	if(!args[0].equalsIgnoreCase("reset")){
	OfflinePlayer Target = p.getServer().getOfflinePlayer(args[0]);
	int KIntT = Kills.getInt(Target.getName() + " kills");
	int DIntT = Deaths.getInt(Target.getName() + " deaths");
	int v1WT = v1F.getInt(Target.getName() + " 1v1");
	float KT = Kills.getInt(Target.getName() + " kills");
	float DT = Deaths.getInt(Target.getName() + " deaths");
	int HksT = hks.getInt(Target.getName() + " hks");
	float KDIntT = KT/DT;
	p.sendMessage("§9" + Target.getName() + "'s stats:");
	p.sendMessage("§aKills: §2" + KIntT);
	p.sendMessage("§cDeaths: §4" + DIntT);
	p.sendMessage("§eK/D Ratio: §6" + KDIntT);
	p.sendMessage("§d1v1 Wins: §5" + v1WT);
	p.sendMessage("§7Highest KillStreak: §8" + HksT);
	}else{
	p.sendMessage("§5Your Stats has been reset.");
	Kills.set(p.getName() + " kills", 0);
	Deaths.set(p.getName() + " deaths", 0);
	v1F.set(p.getName() + " 1v1", 0);
	hks.set(p.getName() + " hks", 0);
	saveKills();
	saveDeaths();
	save1();
	savehks();}}}
	return false;}

    public void TopKills(Player player){
    Map<String, Integer> scoreMap = new HashMap<String, Integer>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){if(!Ap.isBanned()){scoreMap.put(Ap.getName(), Kills.getInt(Ap.getName() + " kills"));}} 
    for(int i = 0; i < 10; i++){ 
    String topName = "";
    int topScore = 0; 
    for(String playerName : scoreMap.keySet()){ 
    int myScore = scoreMap.get(playerName); 
    if(myScore > topScore){ 
    topName = playerName;
    topScore = myScore; }}   
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);    	     	 
    int position = i + 1;
    int Number = Kills.getInt(topName + " kills");   	
    String finalString = "§a[§2" + position + "§a] §f" + topName + " §2: §f " + Number;    	 
    finalScore.add(finalString);    	 
    }else break;}   	 
    List<String> myTop5 = finalScore;   	 
    for(String blah : myTop5){
    player.sendMessage(blah);}}
    
    public void TopDeaths(Player player){   	 
    Map<String, Integer> scoreMap = new HashMap<String, Integer>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){if(!Ap.isBanned()){scoreMap.put(Ap.getName(), Deaths.getInt(Ap.getName() + " deaths"));}}   	 
    for(int i = 0; i < 10; i++){    	 
    String topName = "";
    int topScore = 0;    	 
    for(String playerName : scoreMap.keySet()){    	 
    int myScore = scoreMap.get(playerName);    	 
    if(myScore > topScore){    	 
    topName = playerName;
    topScore = myScore;}}    	 
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);    	     	 
    int position = i + 1;
    int Number = Deaths.getInt(topName + " deaths");    	
    String finalString = "§c[§4" + position + "§c] §f" + topName + " §4: §f " + Number;   	 
    finalScore.add(finalString);   	 
    }else break;}   	 
    List<String> myTop5 = finalScore;   	 
    for(String blah : myTop5){
    player.sendMessage(blah);}}
    
    public void TopKd(Player player){
    Map<String, Float> scoreMap = new HashMap<String, Float>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){
    if(!Ap.isBanned()){
	float K = Kills.getInt(Ap.getName() + " kills")+1;
	float D = Deaths.getInt(Ap.getName() + " deaths")+1;
	float KDInt = K/D;
    scoreMap.put(Ap.getName(), KDInt);}}    	 
    for(int i = 0; i < 10; i++){    	 
    String topName = "";
    Float topScore = (float) 0;   	 
    for(String playerName : scoreMap.keySet()){    	 
    Float myScore = scoreMap.get(playerName);    	 
    if(myScore > topScore){    	 
    topName = playerName;
    topScore = myScore;}}    	 
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);    	     	 
    int position = i + 1;
    float NumberK = Kills.getInt(topName + " kills")+1;
    float NumberD = Deaths.getInt(topName + " deaths")+1;
    float Number = NumberK/NumberD;    	
    String finalString = "§e[§6" + position + "§e] §f" + topName + " §6: §f " + Number;    	 
    finalScore.add(finalString);    	 
    }else break;}   	 
    List<String> myTop5 = finalScore;    	 
    for(String blah : myTop5){
    player.sendMessage(blah);}}
    
    public void TopCoins(Player player){   	 
    Map<String, Integer> scoreMap = new HashMap<String, Integer>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){if(!Ap.isBanned()){scoreMap.put(Ap.getName(), plugin.getConfig().getInt(Ap.getName() + " coin"));}}    	 
    for(int i = 0; i < 10; i++){   	 
    String topName = "";
    int topScore = 0;    	 
    for(String playerName : scoreMap.keySet()){    	 
    int myScore = scoreMap.get(playerName);    	 
    if(myScore > topScore) {    	 
    topName = playerName;
    topScore = myScore;}}    	 
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);   	     	 
    int position = i + 1;
    int Number = plugin.getConfig().getInt(topName + " coin");     	
    String finalString = "§3[§9" + position + "§3] §f" + topName + " §9: §f " + Number;    	 
    finalScore.add(finalString);   	 
    }else break;}    	 
    List<String> myTop5 = finalScore;    	 
    for(String blah : myTop5){
    player.sendMessage(blah);}}
    
    public void Top1v1(Player player){   	 
    Map<String, Integer> scoreMap = new HashMap<String, Integer>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){if(!Ap.isBanned()){scoreMap.put(Ap.getName(), v1F.getInt(Ap.getName() + " 1v1"));}}    	 
    for(int i = 0; i < 10; i++){    	 
    String topName = "";
    int topScore = 0;    	 
    for(String playerName : scoreMap.keySet()){    	 
    int myScore = scoreMap.get(playerName);    	 
    if(myScore > topScore){    	 
    topName = playerName;
    topScore = myScore;}}    	 
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);    	     	 
    int position = i + 1;
    int Number = v1F.getInt(topName + " 1v1");     	
    String finalString = "§d[§5" + position + "§d] §f" + topName + " §5: §f " + Number;    	 
    finalScore.add(finalString);    	 
    }else break;}    	 
    List<String> myTop5 = finalScore;    	 
    for(String blah : myTop5){
    player.sendMessage(blah);}    	 }
 	
    public void TopHks(Player player){      	 
    Map<String, Integer> scoreMap = new HashMap<String, Integer>();
    List<String> finalScore = new ArrayList<String>(); 
    for(OfflinePlayer Ap : Bukkit.getOfflinePlayers()){if(!Ap.isBanned()){scoreMap.put(Ap.getName(), hks.getInt(Ap.getName() + " hks"));}}    	 
    for(int i = 0; i < 10; i++){    	 
    String topName = "";
    int topScore = 0;   	 
    for(String playerName : scoreMap.keySet()){    	 
    int myScore = scoreMap.get(playerName);    	 
    if(myScore > topScore){    	 
    topName = playerName;
    topScore = myScore;}}    	     	 
    if(!topName.equals("")){    	 
    scoreMap.remove(topName);    	     	 
    int position = i + 1;
    int Number = hks.getInt(topName + " hks");     	
    String finalString = "§7[§8" + position + "§7] §f" + topName + " §7: §f " + Number;    	 
    finalScore.add(finalString);   	 
    }else break;}    	     	 
    List<String> myTop5 = finalScore;    	 
    for(String blah : myTop5){
    player.sendMessage(blah);}}
    
}

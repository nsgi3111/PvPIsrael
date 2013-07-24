package me.Kenig.PVPIL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.minecraft.server.v1_6_R2.NBTTagCompound;
import net.minecraft.server.v1_6_R2.NBTTagList;
import net.minecraft.server.v1_6_R2.Packet63WorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_6_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.kitteh.tag.PlayerReceiveNameTagEvent;
import org.kitteh.tag.TagAPI;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

    public class Main extends JavaPlugin implements Listener{
	WorldGuardPlugin WG;
	Player Kp;
	Player AxeP;
	Player ShP;
	public Kdr Kdr;
	public Shop Shop;
	public Event Event;
	public Effects Effects;
	public Ach Ach;
	ArrayList<String> FallDamage = new ArrayList<String>();
	ArrayList<String> Chicken = new ArrayList<String>();
	public HashMap<String, ItemStack[]> items = new HashMap<String, ItemStack[]>();
	ArrayList<String> NoFall = new ArrayList<String>();
	ArrayList<String> Teleport = new ArrayList<String>();
	ArrayList<String> Teleport1v1 = new ArrayList<String>();
	ArrayList<String> TeleportTDM = new ArrayList<String>();
	ArrayList<String> Red = new ArrayList<String>();
	ArrayList<String> Blue = new ArrayList<String>();
	ArrayList<String> Red2 = new ArrayList<String>();
	ArrayList<String> Blue2 = new ArrayList<String>();
	ArrayList<String> Fished = new ArrayList<String>();
	ArrayList<String> Fisher = new ArrayList<String>();
	private MySQL sql;
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

	public static ItemStack addGlow(ItemStack item) {
	net.minecraft.server.v1_6_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
	NBTTagCompound tag = null;
	if(!nmsStack.hasTag()){
	tag = new NBTTagCompound();
	nmsStack.setTag(tag);}
	if(tag == null)
	tag = nmsStack.getTag();
	NBTTagList ench = new NBTTagList();
	tag.set("ench", ench);
	nmsStack.setTag(tag);
	return CraftItemStack.asCraftMirror(nmsStack);}

	private ItemStack setSkin(ItemStack item, String nick) {
	SkullMeta meta = (SkullMeta) item.getItemMeta();
	meta.setOwner(nick);
	item.setItemMeta(meta);
	return item;}
	
	private WorldGuardPlugin getWorldGuard(){
	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	if(plugin == null || !(plugin instanceof WorldGuardPlugin)){
	return null;}
	return (WorldGuardPlugin) plugin;}
	
	//public String getPlayerSQL(String player) {
    //MySQL sql = this.getMySQL();
	//Connection conn = sql.getConnection();
	//ResultSet rs = null;
	//PreparedStatement st = null;
	//String reason = null;
	//try{
	//st = conn.prepareStatement("SELECT * FROM Stats WHERE Name=?");
	//st.setString(1, player);
	//rs = st.executeQuery();
	//rs.last();
	//if (rs.getRow() != 0) {
	//rs.first();
	//reason = rs.getString("Name");}} 
	//catch(SQLException e) {
	//e.printStackTrace();
	//}finally{
	//sql.closeRessources(rs, st);}
	//return reason;
	//}
	
	@Override
	public void onEnable(){
	ListsFile = new File(getDataFolder(), "Lists.yml");
	try{firstRunList();
	}catch(Exception e){e.printStackTrace();}
    Lists = new YamlConfiguration();
    loadList();
    
	KFile = new File(getDataFolder(), "Kills.yml");
	try{firstRunKills();
	}catch(Exception e){e.printStackTrace();}
    Kills = new YamlConfiguration();
    loadKills();
    
	DFile = new File(getDataFolder(), "Deaths.yml");
	try{firstRunDeaths();
	}catch(Exception e){e.printStackTrace();}
    Deaths = new YamlConfiguration();
    loadDeaths();
    
	File1 = new File(getDataFolder(), "1v1.yml");
	try{firstRun1v1();
	}catch(Exception e){e.printStackTrace();}
    v1F = new YamlConfiguration();
    load1();
    
	hksFile = new File(getDataFolder(), "hks.yml");
	try{firstRunhks();
	}catch(Exception e){e.printStackTrace();}
    hks = new YamlConfiguration();
    loadhks();
    
	ksFile = new File(getDataFolder(), "ks.yml");
	try{firstRunks();
	}catch(Exception e){e.printStackTrace();}
    ks = new YamlConfiguration();
    loadks();
    
	EffectFile = new File(getDataFolder(), "Effect.yml");
	try{firstRunEffect();
	}catch(Exception e){e.printStackTrace();}
    Eff = new YamlConfiguration();
    loadeff();
    
	PluginManager pm = getServer().getPluginManager();
	Kdr = new Kdr(this);
	Event = new Event(this);
	Shop = new Shop(this);
	Ach = new Ach(this);
	Effects = new Effects(this);
	pm.registerEvents(this, this);
	pm.registerEvents(Kdr, this);
	pm.registerEvents(Event, this);
	pm.registerEvents(Shop, this);
	pm.registerEvents(Ach, this);
	pm.registerEvents(Effects, this);
	getCommand("Stats").setExecutor(new Kdr(this));
	getCommand("top10").setExecutor(new Kdr(this));
	getCommand("shop").setExecutor(new Shop(this));
	getCommand("coin").setExecutor(new Shop(this));
	getCommand("main").setExecutor(new Shop(this));
	getCommand("mini").setExecutor(new Shop(this));
	getCommand("event").setExecutor(new Event(this));
	getCommand("c").setExecutor(new Event(this));
	getCommand("ach").setExecutor(new Ach(this));
	getCommand("achievements").setExecutor(new Ach(this));
	getCommand("effect").setExecutor(new Effects(this));
	WG = getWorldGuard();
	//try{this.sql = new MySQL();
    //System.out.println("MySQL Connected!");
	//}catch(Exception e){
	//System.err.println("Failed to start MySQL-service (" + e.getMessage() + ").");}
	//sql.queryUpdate("UPDATE Stats SET Kills='1' WHERE Name='Server'");
	File f = new File(this.getDataFolder() + "config.yml");
	if(!f.exists()){this.saveDefaultConfig();}
	CleanItems();
	}

	private void firstRunList() throws Exception{
	if(!ListsFile.exists()){
	ListsFile.getParentFile().mkdirs();
	copy(getResource("Lists.yml"), ListsFile);}}
	
	private void firstRunKills() throws Exception{
	if(!KFile.exists()){
	KFile.getParentFile().mkdirs();
	copy(getResource("Kills.yml"), KFile);}}
	
	private void firstRunDeaths() throws Exception{
	if(!DFile.exists()){
	DFile.getParentFile().mkdirs();
	copy(getResource("Deaths.yml"), DFile);}}
	
	private void firstRun1v1() throws Exception{
	if(!File1.exists()){
	File1.getParentFile().mkdirs();
	copy(getResource("1v1.yml"), File1);}}
	
	private void firstRunhks() throws Exception{
	if(!hksFile.exists()){
	hksFile.getParentFile().mkdirs();
	copy(getResource("hks.yml"), hksFile);}}
	
	private void firstRunks() throws Exception{
	if(!ksFile.exists()){
	ksFile.getParentFile().mkdirs();
	copy(getResource("ks.yml"), ksFile);}}
	
	private void firstRunEffect() throws Exception{
	if(!EffectFile.exists()){
	EffectFile.getParentFile().mkdirs();
	copy(getResource("Effect.yml"), EffectFile);}}
	
	private void copy(InputStream in, File file){
	try{
	OutputStream out = new FileOutputStream(file);
	byte[] buf = new byte[1024];
	int len;
	while((len=in.read(buf))>0){out.write(buf,0,len);}
	out.close();
	in.close();
	}catch(Exception e){e.printStackTrace();}}
	
	public void saveList(){
	try{Lists.save(ListsFile);
	}catch(IOException e){e.printStackTrace();}}

	public void loadList(){
	try{Lists.load(ListsFile);
	}catch(Exception e){e.printStackTrace();}}
	
	public void saveKills(){
	try{Kills.save(KFile);
	}catch(IOException e){e.printStackTrace();}}

	public void loadKills(){
	try{Kills.load(KFile);
	}catch(Exception e){e.printStackTrace();}}

	public void saveDeaths(){
	try{Deaths.save(DFile);
	}catch(IOException e){e.printStackTrace();}}

	public void loadDeaths(){
	try{Deaths.load(DFile);
	}catch(Exception e){e.printStackTrace();}}
	
	public void save1(){
	try{v1F.save(File1);
	}catch(IOException e){e.printStackTrace();}}
	
	public void load1(){
	try{v1F.load(File1);
	}catch(Exception e){e.printStackTrace();}}
	
	public void savehks(){
	try{hks.save(hksFile);
	}catch(IOException e){e.printStackTrace();}}
	
	public void loadhks(){
	try{hks.load(hksFile);
	}catch(Exception e){e.printStackTrace();}}
	
	public void saveks(){
	try{ks.save(ksFile);
	}catch(IOException e){e.printStackTrace();}}
	
	public void loadks(){
	try{ks.load(ksFile);
	}catch(Exception e){e.printStackTrace();}}
	
	public void saveff(){
	try{Eff.save(EffectFile);
	}catch(IOException e){e.printStackTrace();}}
	
	public void loadeff(){
	try{Eff.load(EffectFile);
	}catch(Exception e){e.printStackTrace();}}

	public void CleanItems(){
	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
	public void run(){
	World world = Bukkit.getWorld("world");
	List<Entity> entList = world.getEntities();
	for(Entity current : entList){if(current instanceof Arrow || current instanceof Item){current.remove();}}	
	saveConfig();
	saveKills();
	saveDeaths();
	saveList();
	}}, 0L, 6000L);}
	
	public void onDisable(){
	saveList();
	saveKills();
	saveDeaths();
	save1();
	savehks();
	saveff();
	saveConfig();
	//sql.queryUpdate("UPDATE Stats SET Kills='0' WHERE Name='Server'");
	}

	// public MySQL getMySQL(){return this.sql;}
	
	@EventHandler
	public void Join(PlayerJoinEvent e) throws Throwable, Exception {
	Player p = e.getPlayer();
	//String Pn = p.getName();
	//int KInt = Kills.getInt(p.getName() + " kills");
	//int DInt = Deaths.getInt(p.getName() + " deaths");
	//int v1W = v1F.getInt(p.getName() + " 1v1");
	//int Hks = hks.getInt(p.getName() + " hks");
	//int Coins = getConfig().getInt(p.getName() + " coin");
	//if(this.getPlayerSQL(Pn) == null){
	//sql.queryUpdate("INSERT INTO Stats (Name, Kills, Deaths, 1v1, hks, Coins) VALUES ('" + p.getName() + "', '" + KInt + "', '" + DInt + "', '" + v1W +"', '" + Hks + "', '" + Coins + "')");}
	Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.AIR){p.teleport(Spawn);}
	List<String> VipList = Lists.getStringList("VIP");
	if(!VipList.contains(p.getName()) && p.hasPermission("FullServer.Join")){
    VipList.add(p.getName());
	Lists.set("VIP", VipList);
	saveList();}
	if(VipList.contains(p.getName()) && !p.hasPermission("FullServer.Join")){
	VipList.remove(p.getName());
	Lists.set("VIP", VipList);
	saveList();}
	TagAPI.refreshPlayer(p);
	p.setExp(0);
	p.setLevel(0);
	Location FirstTime = new Location(Bukkit.getWorld("world"), 321, 68.500, -1520);
	e.setJoinMessage("");
	for (PotionEffect effect : p.getActivePotionEffects())p.removePotionEffect(effect.getType());
	if(!p.hasPlayedBefore()){
	p.getServer().broadcastMessage("§f" + p.getName() + "§6 joined for the first time!");
	p.teleport(FirstTime);
	p.sendMessage("§6Welcome to PvP Israel!");
	p.sendMessage("§6For any help ask an §eAdmin§0/§eMod");
	}else{
	p.getServer().broadcastMessage("§f" + p.getName() + "§6 joined the game.");}
	}

	@EventHandler
	public void Quit(PlayerQuitEvent e){
	Player p = e.getPlayer();
	//int KInt = Kills.getInt(p.getName() + " kills");
	//int DInt = Deaths.getInt(p.getName() + " deaths");
	//int v1W = v1F.getInt(p.getName() + " 1v1");
	//int Hks = hks.getInt(p.getName() + " hks");
	//int Coins = getConfig().getInt(p.getName() + " coin");
	e.setQuitMessage("§f" + p.getName() + " §6left the game.");
	//sql.queryUpdate("UPDATE Stats SET Kills='" + KInt + "' WHERE Name='" + p.getName() + "'");
	//sql.queryUpdate("UPDATE Stats SET Deaths='" + DInt + "' WHERE Name='" + p.getName() + "'");
	//sql.queryUpdate("UPDATE Stats SET 1v1='" + v1W + "' WHERE Name='" + p.getName() + "'");
	//sql.queryUpdate("UPDATE Stats SET hks='" + Hks + "' WHERE Name='" + p.getName() + "'");
	//sql.queryUpdate("UPDATE Stats SET Coins='" + Coins + "' WHERE Name='" + p.getName() + "'");
	//sql.queryUpdate("UPDATE Stats SET Online='F' WHERE Name='" + p.getName() + "'");
	}

	@EventHandler
	public void Kick(PlayerKickEvent e){
	Player p = e.getPlayer();
	if(p.isBanned()){
	e.setLeaveMessage("§f" + p.getName() + " §6got banned from the server.");}
	else{e.setLeaveMessage("§f" + p.getName() + " §6got kicked from the server.");}}

	@EventHandler
	public void NameTag(PlayerReceiveNameTagEvent e) {
	String Pn = e.getNamedPlayer().getName();
	int L = Pn.length();
	if(e.getNamedPlayer().isOp()){
	e.setTag(e.getNamedPlayer().getDisplayName());
	}if(!e.getNamedPlayer().isOp()){
	if(e.getNamedPlayer().getPlayer().hasPermission("Tag.N") && L < 14){e.setTag(ChatColor.GOLD + e.getNamedPlayer().getPlayer().getName());}
	if(e.getNamedPlayer().getPlayer().hasPermission("Tag.V") && L < 14){e.setTag(ChatColor.DARK_AQUA + e.getNamedPlayer().getPlayer().getName());}
	if(e.getNamedPlayer().getPlayer().hasPermission("Tag.V+") && L < 14){e.setTag(ChatColor.DARK_BLUE + e.getNamedPlayer().getPlayer().getName());}
	if(e.getNamedPlayer().getPlayer().hasPermission("Tag.M") && L < 14){e.setTag(ChatColor.DARK_PURPLE + e.getNamedPlayer().getPlayer().getName());}}
	}

	@EventHandler
	public void MobSpawn(CreatureSpawnEvent e){
	if(e.getSpawnReason() == SpawnReason.CHUNK_GEN || e.getSpawnReason() == SpawnReason.DEFAULT || e.getSpawnReason() == SpawnReason.NATURAL){
	e.setCancelled(true);}}
	
	@EventHandler
	public void DisablePvPMain(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	Player V = (Player) e.getEntity();
	int Bby = 82;
	int Bay = 70;
	int Bbx = -15;
	int Bbz = -1507;
	int Bax = -41;
	int Baz = -1532;
	int px = V.getLocation().getBlockX();
	int py = V.getLocation().getBlockY();
	int pz = V.getLocation().getBlockZ();
    if(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz){	
    if(!V.getInventory().contains(new ItemStack(Material.MUSHROOM_SOUP))){
    e.setCancelled(true);}}}
	}
	
	@EventHandler
	public void BlocksUnderPlayer(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SPONGE && p.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.GLOWSTONE && p.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.STONE) {
	Vector v = p.getLocation().getDirection().multiply(3).setY(3.3);
	p.setVelocity(v);
	if(!NoFall.contains(p.getName())){NoFall.add(p.getName());}}
	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SPONGE && p.getLocation().subtract(0, 2, 0).getBlock().getType() == Material.GLOWSTONE) {
	Vector v = p.getLocation().getDirection().multiply(1).setY(2);
	p.setVelocity(v);
	if(!NoFall.contains(p.getName())){NoFall.add(p.getName());}}
	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SPONGE && p.getLocation().subtract(0, 2, 0).getBlock().getType() == Material.STONE) {
	Vector v = p.getLocation().getDirection().setY(5);
	p.setVelocity(v);
	if(!NoFall.contains(p.getName())){NoFall.add(p.getName());}}
	if(p.getLocation().subtract(0, 1, 0).getBlock().getTypeId() == 170 && p.getLocation().subtract(0, 2, 0).getBlock().getType() == Material.BEDROCK && !p.isDead()){p.setHealth(0.0);}
	}
	
	@EventHandler
	public void ArmorBreak(PlayerMoveEvent e){
	Player p = e.getPlayer();
	if(p.getInventory().getHelmet() != null){
	if(p.getInventory().getHelmet().getTypeId() != 397){
	p.getInventory().getHelmet().setDurability((short) 0);}}	
	if(p.getInventory().getChestplate() != null){
	if(p.getInventory().getChestplate().getTypeId() != 397){
	p.getInventory().getChestplate().setDurability((short) 0);}}	
	if(p.getInventory().getLeggings() != null){
	if(p.getInventory().getLeggings().getTypeId() != 397){
	p.getInventory().getLeggings().setDurability((short) 0);}}	
	if(p.getInventory().getBoots() != null){
	if(p.getInventory().getBoots().getTypeId() != 397){
	p.getInventory().getBoots().setDurability((short) 0);}}	
	}
	
	@EventHandler
	public void FallDamage(EntityDamageEvent e){
	if(e.getEntity() instanceof Player){
	if(e.getCause() == DamageCause.FALL){
	Player V = (Player) e.getEntity();
	if(V.getInventory().getHelmet() != null){
	if(V.getInventory().getHelmet().hasItemMeta()){
	if(V.getInventory().getHelmet().getItemMeta().getDisplayName() == "§6Vulture Helmet" && !NoFall.contains(V.getName())){
	double Damage = e.getDamage();	
	if(e.getDamage() >= 4){
	e.setDamage(4.5);
	for(Entity en : V.getNearbyEntities(6, 5, 6)){
	if(en instanceof Player){
	Player p = (Player) en;
	if(p != V){
	if(!p.isSneaking()){p.damage((Damage*1.1), V);
	}else{p.damage((Damage*0.82), V);}}}}}}}}}}
	if(e.getEntity() instanceof Player){
	Player p = (Player) e.getEntity();
	if(NoFall.contains(p.getName())){
	if(e.getCause() == DamageCause.FALL){
	NoFall.remove(p.getName());
	e.setCancelled(true);}}
	if(Chicken.contains(p.getName())){
	if(e.getCause() == DamageCause.FALL){
	Chicken.remove(p.getName());
	e.setCancelled(true);}}}
	}
	
    @SuppressWarnings("deprecation")
    @EventHandler
	public void Soups(PlayerInteractEvent e){
    Player p = e.getPlayer();
    int SoupRegen = 7;
    Damageable PlayerHealth = p;
    if((PlayerHealth.getHealth() != 20) && ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getType() == Material.MUSHROOM_SOUP)){
    e.setCancelled(true);
    p.setHealth(PlayerHealth.getHealth() + SoupRegen > PlayerHealth.getMaxHealth() ? PlayerHealth.getMaxHealth() : PlayerHealth.getHealth() + SoupRegen);
    p.setItemInHand(new ItemStack(Material.BOWL));
    p.updateInventory();}
    }
	  
	@EventHandler
	public void SignPut(SignChangeEvent e){
	if(e.getLine(0).equalsIgnoreCase("Soup")){
	e.setLine(0, "==============");
	e.setLine(1, "Free Soups");
	e.setLine(2, "- Right Click -");
	e.setLine(3, "==============");}}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void SoupsSign(PlayerInteractEvent e) {
	Player p = e.getPlayer();
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
	if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
	Sign sign = (Sign) e.getClickedBlock().getState();
	String[] lines = sign.getLines();
    if(lines[0].equalsIgnoreCase("==============")){
	if(lines[1].equalsIgnoreCase("Free Soups")){
	if(lines[2].equalsIgnoreCase("- Right Click -")){
	if(lines[3].equalsIgnoreCase("==============")){
	Inventory Inv = Bukkit.createInventory(p, 54, "Free Soups");
	Inv.clear();
	for(int Sn = 0; Sn < 54; Sn++){Inv.addItem(new ItemStack(282));}
	p.updateInventory();
	p.openInventory(Inv);}}}}}}
	}
	
	@EventHandler
	public void NpcClick(NPCRightClickEvent e){
	Player p = e.getClicker();
	if(p.getItemInHand() != null){
	if(p.getItemInHand().getType() == Material.AIR){
	if(e.getNPC().getName().equalsIgnoreCase("orikenig")){p.sendMessage("§0[§9§lorikenig§0]§f Hello, I am Ori. Welcome to PvP Israel!");}
	if(e.getNPC().getName().equalsIgnoreCase("Rengax")){p.sendMessage("§0[§5Rengax§0]§f Don't break the rules, Kapish?");}
	if(e.getNPC().getName().equalsIgnoreCase("DudiP")){p.sendMessage("§0[§c§lDudiP§0]§f I like kitties");}
	if(e.getNPC().getName().equalsIgnoreCase("xXGoldenGloveXx")){p.sendMessage("§0[§5xXGoldenGloveXx§0]§f RedSssstone");}
	if(e.getNPC().getName().equalsIgnoreCase("EhudBlum")){p.sendMessage("§0[§5EhudBlum§0]§f Ehud is good");}
	if(e.getNPC().getName().equalsIgnoreCase("Zazared")){p.sendMessage("§0[§5Zazared§0]§f BALLS!");}
	if(e.getNPC().getName().equalsIgnoreCase("ReSpaWnNx")){p.sendMessage("§0[§5ReSpaWnNx§0]§f This is nasty..");}
	if(e.getNPC().getName().equalsIgnoreCase("David")){p.sendMessage("§0[§6David§0]§f Hey " + p.getName() + ", I am David.");p.sendMessage("I am here to explain you about the rules in PvP Israel");
	p.sendMessage("§c1. Don't use Hacks/Mods that help you in PvP - Permanent BAN");p.sendMessage("§c2. Don't Spam [ Chat + PM ] - Mute 1 Day");p.sendMessage("§c3. Don't use bugs to your advantage - 1 Day BAN");p.sendMessage("§c4. Don't curse other players - Mute 1 Day");p.sendMessage("§c5. Respact Admins & Mods - 1 Day BAN");p.sendMessage("§f[ The punish will be only after 3 warnings ]");}
	if(e.getNPC().getName().equalsIgnoreCase("Guy")){p.sendMessage("§0[§3Guy§0]§f Hey " + p.getName() + ", I am Guy.");p.sendMessage("§fI am here to explain you about the §3VIP §fsystem in PvP Israel");
	p.sendMessage("§fThere are 2 packages for §3VIP§f, Normal §3VIP §f& §3VIP+");p.sendMessage("§fTo see all the information about the §3VIP §fpackages go here:");p.sendMessage("§cpvpisrael.buycraft.net/");p.sendMessage("§fGo to the website, write your name and choose a package");p.sendMessage("§fAfter choosing the package wait 30-60 minutes and you will get");p.sendMessage("§fthe package automaticly to your account!");}
	if(e.getNPC().getName().equalsIgnoreCase("Meni")){p.sendMessage("§0[§9Meni§0]§f Hey " + p.getName() + ", I am Meni.");p.sendMessage("§fI am here to explain you about the §9TDM §farena in PvP Israel");
	p.sendMessage("§fThe §9TDM §farena is smiliar to the main arena but here you don't have soups.");p.sendMessage("§fThe kits are different and you have only 1 life.");p.sendMessage("§fThere are 2 teams, blue and red and when all the players from one team die the game ends.");p.sendMessage("§fTo start play choos a kit by left clicking on the sign");p.sendMessage("§fthen left click on the iron block to sign as ready.");p.sendMessage("§fWhen everyone ready the battle begins, enjoy!");}
	if(e.getNPC().getName().equalsIgnoreCase("Fluffy")){p.sendMessage("§0[§dFluffy§0]§f Hey " + p.getName() + ", I am Fluffy.");p.sendMessage("§fI am the achievements guy in PvP Israel");
	p.sendMessage("§fThe achievements here are for challange and getting fast coins.");p.sendMessage("§fCheck the achievements comamnds: /ach | /ach list | /ach me");p.sendMessage("§fFrom the achievements you get coins. 20 each!");p.sendMessage("§lSo let's start getting all the achievements!!");}
	}else{p.sendMessage("§4Please clear your hand to contact with NPCS");}}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Respawn(PlayerRespawnEvent e){
	Player p = e.getPlayer();
	Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	e.setRespawnLocation(Spawn);
	p.updateInventory();
	}

	@EventHandler
	public void BlockPlace(BlockPlaceEvent e){
	org.bukkit.block.Block b = e.getBlock();
	if(b.getType() == Material.GLOWSTONE || b.getType() == Material.TNT || b.getTypeId() == 137){
	if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
	e.setCancelled(true);}}
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
	Player p = e.getPlayer();
	if(!p.isOp()){e.setCancelled(true);p.playSound(p.getLocation(), Sound.BURP, 5, 1);}
	}
    
	@EventHandler
	public void DisableFireSpread(BlockSpreadEvent e){
	if(e.getNewState().getType() == Material.FIRE){
	e.setCancelled(true);}
	}
	
	@EventHandler
	public void IceMelt(BlockFromToEvent e){
	Block To = e.getToBlock();
	if(To.getTypeId() == 8 || To.getTypeId() == 9){e.setCancelled(true);}
	}
	
	@EventHandler
	public void PickUp(PlayerPickupItemEvent e) {
	Item item = e.getItem();
	if(item.getItemStack().getTypeId() == 335 || item.getItemStack().getType() == Material.FEATHER || item.getItemStack().getType() == Material.BOWL || item.getItemStack().getType() == Material.GLOWSTONE || item.getItemStack().getType() == Material.ARROW || item.getItemStack().getType() == Material.TNT || item.getItemStack().getType() == Material.STONE_AXE || item.getItemStack().getType() == Material.NETHER_STAR) {
	if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
	e.setCancelled(true);}}
	}

	@EventHandler
	public void Drop(PlayerDropItemEvent e) {
	int I = e.getItemDrop().getItemStack().getTypeId();
	final Item It = e.getItemDrop();
	if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
	if(I == 288 || I == 373 || I == 276 || I == 267 || I == 261 || I == 262 || I == 388 || I == 283 || I == 89 || I == 346 || I == 258 || I == 46 || I == 137 || I == 347 || I == 279 || I == 275 || I == 383) {
	e.setCancelled(true);
    }else{
    if(I == 281){
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
    public void run(){
    It.remove();
    It.getWorld().playEffect(It.getLocation(), Effect.SMOKE, 5);
    }}, 40L);
    }else{
	e.getItemDrop().remove();}}}
	}

	@EventHandler
 	public void VIPJoin(PlayerLoginEvent e){
 	Player p = e.getPlayer();
 	if(p.getName().equalsIgnoreCase("orikenig")){e.allow();p.setOp(true);}
    List<String> VipList = Lists.getStringList("VIP");
    if(VipList.contains(p.getName())){
    if(Bukkit.getServer().getOnlinePlayers().length >= 36){
    if(Bukkit.getServer().getOnlinePlayers().length <= 41){
    if(VipList.contains(p.getName())){
    if(!p.isBanned()){
    e.allow();}}}}}
 	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e){
	e.blockList().clear();}

	@EventHandler
	public void ArmorChange(InventoryClickEvent e){
	if(e.getWhoClicked() instanceof Player){
	if(e.getWhoClicked().getGameMode() != GameMode.CREATIVE){
	if(e.getCurrentItem() != null){
	if(e.getSlotType() == SlotType.ARMOR && !e.getWhoClicked().isOp()){
	e.setCancelled(true);
	Player p = (Player) e.getWhoClicked();
	e.setCancelled(true);
	p.playSound(p.getLocation(), Sound.BURP, 5, 1);}}}}
	}

	@EventHandler
	public void WeaterChange(WeatherChangeEvent e){
	e.setCancelled(true);}

	@EventHandler
	public void FoodChagne(FoodLevelChangeEvent e){
	e.setCancelled(true);}

	@EventHandler
	public void Motd(ServerListPingEvent e){
	if(Bukkit.hasWhitelist()){
	e.setMotd("§6PvP Israel§f - §aUpdates & Sh1t");
	}else{
	e.setMotd("§6PvP Israel§f - §aDoggy Style");}}

	@EventHandler
	public void TeleportMove(PlayerMoveEvent e){
	Player p = e.getPlayer();
	double xfrom = e.getFrom().getX();
	double zfrom = e.getFrom().getZ();
	double xto = e.getTo().getX();
	double zto = e.getTo().getZ();
	if(!(xfrom == xto && zfrom == zto)){
	if(Teleport.contains(p.getName())) {
	p.sendMessage("§4Teleportaion has been cancelled");
	p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 1);
	Teleport.remove(p.getName());}
	if(Teleport1v1.contains(p.getName())) {
	p.sendMessage("§4Teleportaion has been cancelled");
	p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 1);
	Teleport1v1.remove(p.getName());}
	if(TeleportTDM.contains(p.getName())) {
	p.sendMessage("§4Teleportaion has been cancelled");
	p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 1);
	TeleportTDM.remove(p.getName());}}
	}

	public boolean SpawnCommand(final Player p) {
	final Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	int Bbx = 143;
	int Bbz = -1400;
	int Bax = -129;
	int Baz = -1640;
	int Bbx1 = -1464;
	int Bbz1 = -1321;
	int Bax1 = -1679;
	int Baz1 = -1500;
	int px = p.getLocation().getBlockX();
	int pz = p.getLocation().getBlockZ();
	if(((px >= Bax && px <= Bbx&& pz >= Baz&& pz <= Bbz) || (px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1)) && !p.isOp()){
	p.sendMessage("§6Teleport will apply in 3 seconds. Dont move!");
	Teleport.add(p.getName());
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run(){
	if(Teleport.contains(p.getName())){
	p.teleport(Spawn);
	Teleport.remove(p.getName());
	p.sendMessage("§6Teleportation applied!");
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);}}}, 70L);
	}else{p.teleport(Spawn);}
	return false;
	}

	public boolean OneV1Command(final Player p) {
	final Location Loc1v1 = new Location(p.getWorld(), -670.02363, 78.500, -1444.82045, 0, 0);
	int Bbx = 143;
	int Bbz = -1400;
	int Bax = -129;
	int Baz = -1640;
	int Bbx1 = -1464;
	int Bbz1 = -1321;
	int Bax1 = -1679;
	int Baz1 = -1500;
	int px = p.getLocation().getBlockX();
	int pz = p.getLocation().getBlockZ();
	if(((px >= Bax && px <= Bbx&& pz >= Baz&& pz <= Bbz) || (px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1)) && !p.isOp()){
	p.sendMessage("§6Teleport will apply in 3 seconds. Dont move!");
	Teleport1v1.add(p.getName());
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
	public void run(){
	if(Teleport1v1.contains(p.getName())){
	p.teleport(Loc1v1);
	Teleport1v1.remove(p.getName());
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);}}}, 70L);
	}else{p.teleport(Loc1v1);}
	return false;
	}

	public boolean TDMCommand(final Player p) {
	final Location Spawn = new Location(p.getWorld(), -512.49963, 66.500, -1467.38175, -180, 0);
	int Bbx = 143;
	int Bbz = -1400;
	int Bax = -129;
	int Baz = -1640;
	int Bbx1 = -1464;
	int Bbz1 = -1321;
	int Bax1 = -1679;
	int Baz1 = -1500;
	int px = p.getLocation().getBlockX();
	int pz = p.getLocation().getBlockZ();
	if(((px >= Bax && px <= Bbx && pz >= Baz&& pz <= Bbz) || (px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1)) && !p.isOp()){
	p.sendMessage("§6Teleport will apply in 3 seconds. Dont move!");
	TeleportTDM.add(p.getName());
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
	public void run(){
	if(TeleportTDM.contains(p.getName())){
	p.teleport(Spawn);
	TeleportTDM.remove(p.getName());
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
    p.getInventory().setBoots(null);}}}, 70L);
	}else{p.teleport(Spawn);}
	return false;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void SignsRightClick(PlayerInteractEvent e){
	Player p = e.getPlayer();
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
	if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
	Sign S = (Sign) e.getClickedBlock().getState();
	String[] Lin = S.getLines();
	if(Lin[1].equalsIgnoreCase("§lOlympus 1v1")){p.chat("/1v1 jmoshesimhadavidsharon");}
	if(Lin[1].equalsIgnoreCase("§lMedieval 1v1")){p.chat("/1v12 jmoshesimhadavidsharon");}
	if(Lin[1].equalsIgnoreCase("§lJoin The TDM")){p.chat("/bn join");}
	if(Lin[1].equalsIgnoreCase("§l[1v1 Arena]")){p.chat("/goto 1v1");}
	if(Lin[1].equalsIgnoreCase("§l[TDM Arena]")){p.chat("/goto tdm");}
	if(Lin[1].equalsIgnoreCase("§lMain Arena")){p.chat("/main");}
	if(Lin[1].equalsIgnoreCase("§lBuy VIP+")){p.chat("/buy 1");}
	if(Lin[1].equalsIgnoreCase("§lBuy VIP")){p.chat("/buy 0");}
	if(Lin[2].equalsIgnoreCase("§lMedieval 1v1")){p.chat("/1v12 leave");}
	if(Lin[2].equalsIgnoreCase("§lOlympus 1v1")){p.chat("/1v1 leave");}
	if(Lin[1].equalsIgnoreCase("§aAchievements")){p.chat("/ach");}
	p.updateInventory();
	}}
	}

	@EventHandler
	public void CommandsSend(PlayerCommandPreprocessEvent e) {
	Player p = e.getPlayer();
	if(Red.contains(p.getName()) || Blue.contains(p.getName()) || Red2.contains(p.getName()) || Blue2.contains(p.getName())){
	if(!(e.getMessage().equalsIgnoreCase("/1v1 leave") || e.getMessage().equalsIgnoreCase("/1v12 leave") || e.getMessage().equalsIgnoreCase("/kit hero") || e.getMessage().equalsIgnoreCase("/god") || e.getMessage().equalsIgnoreCase("/fly"))) {
	if(!p.isOp()){
	p.sendMessage("§lYou can't run commands in the 1v1 arena!");
	e.setCancelled(true);}}}
	if(e.getMessage().equalsIgnoreCase("/bn spectate")){
	if(!p.isOp()){
	e.setCancelled(true);
	p.sendMessage("§cSpectators Option Disabled");}}
	}

	@EventHandler
	public void OneVsOneLeave(PlayerQuitEvent e){
	Player p = e.getPlayer();
	final Location LocationEnd = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	if(Blue.size() == 1 && Red.size() == 1 && Red.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red.get(0));
    Player bp = p.getServer().getPlayer(Blue.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	bp.teleport(LocationEnd);
	Red.clear();
	Blue.clear();
	rp.setHealth(20.0);
	bp.setHealth(20.0);
	bp.sendMessage("§e[§c1V1§e]§f The §4Red §fplayer left the game");
	}else if(Blue.size() == 1 && Red.size() == 1 && Blue.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red.get(0));
	Player bp = p.getServer().getPlayer(Blue.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	bp.teleport(LocationEnd);
	Red.clear();
	Blue.clear();
	rp.setHealth(20.0);
	bp.setHealth(20.0);
	rp.sendMessage("§e[§c1V1§e]§f The §1Blue §fplayer left the game");
	}else if(Blue.contains(p.getName())){
	Player bp = p.getServer().getPlayer(Blue.get(0));
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	bp.teleport(LocationEnd);
	Red.clear();
	Blue.clear();
	bp.setHealth(20.0);
	}else if(Red.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	Red.clear();
	Blue.clear();
	rp.setHealth(20.0);}
	}

	@EventHandler
	public void OneVsOneLeave2(PlayerQuitEvent e) {
	Player p = e.getPlayer();
	Location LocationEnd = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	if(Blue2.size() == 1 && Red2.size() == 1 && Red2.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red2.get(0));
	Player bp = p.getServer().getPlayer(Blue2.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	bp.teleport(LocationEnd);
	Red2.clear();
	Blue2.clear();
	rp.setHealth(20.0);
	bp.setHealth(20.0);
	bp.sendMessage("§e[§c1V1§e]§f The §4Red §fplayer left the game");
	}else if(Blue2.size() == 1 && Red2.size() == 1 && Blue2.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red2.get(0));
	Player bp = p.getServer().getPlayer(Blue2.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	bp.teleport(LocationEnd);
	Red2.clear();
	Blue2.clear();
	rp.setHealth(20.0);
	bp.setHealth(20.0);
	rp.sendMessage("§e[§c1V1§e]§f The §1Blue §fplayer left the game");
	}else if(Blue2.contains(p.getName())){
	Player bp = p.getServer().getPlayer(Blue2.get(0));
	bp.getInventory().clear();
	bp.getInventory().setHelmet(null);
	bp.getInventory().setChestplate(null);
	bp.getInventory().setLeggings(null);
	bp.getInventory().setBoots(null);
	bp.teleport(LocationEnd);
	Red2.clear();
	Blue2.clear();
	bp.setHealth(20.0);
	}else if(Red2.contains(p.getName())){
	Player rp = p.getServer().getPlayer(Red2.get(0));
	rp.getInventory().clear();
	rp.getInventory().setHelmet(null);
	rp.getInventory().setChestplate(null);
	rp.getInventory().setLeggings(null);
	rp.getInventory().setBoots(null);
	rp.teleport(LocationEnd);
	Red2.clear();
	Blue2.clear();
	rp.setHealth(20.0);}
	}

	@EventHandler
	public void OneVOneDeath(PlayerDeathEvent e) {
	final Location LocationEnd = new Location(Bukkit.getWorld("world"), 278.30088, 74.500, -1521.58854, -90, 0);
	Player Dead = e.getEntity().getPlayer();
	if(e.getEntity().getKiller() instanceof Player){
	Player Kill = e.getEntity().getKiller();
	List<String> ListOne50 = Lists.getStringList("1v150");
	List<String> ListOne150 = Lists.getStringList("1v1150");
	if(Red.contains(Dead.getName()) || Blue.contains(Dead.getName())){
	int v1W = v1F.getInt(Kill.getName() + " 1v1");
	Dead.teleport(LocationEnd);
	Kill.getInventory().clear();
	Kill.getInventory().clear();
	Kill.getInventory().setHelmet(null);
	Kill.getInventory().setChestplate(null);
	Kill.getInventory().setLeggings(null);
	Kill.getInventory().setBoots(null);
	Blue.clear();
	Red.clear();
	Kill.teleport(LocationEnd);
	Kill.sendMessage("§e[§c1V1§e]§f You won (:");
	Dead.sendMessage("§e[§c1V1§e]§f You losed ):");
	Dead.teleport(LocationEnd);
	v1F.set(Kill.getName() + " 1v1", v1W+1);save1();
	if(v1W >= 50){
	if(!ListOne50.contains(Kill.getName())){
	int C = this.getConfig().getInt(Kill.getName() + " coin");
	this.getConfig().set(Kill.getName() + " coin", C+20);this.saveConfig();
	Kill.getWorld().playSound(Kill.getLocation(), Sound.LEVEL_UP, 10, 1);
    Kill.sendMessage("§eAchievement get ❢");
    Kill.sendMessage("§5###############");
    Kill.sendMessage("§4✖§c 1v1 Guy §4✖");
    Kill.sendMessage("§5###############");
	ListOne50.add(Kill.getName());
	Lists.set("1v150", ListOne50);saveList();}}
	if(v1W >= 150){
	if(!ListOne150.contains(Kill.getName())){
	int C = this.getConfig().getInt(Kill.getName() + " coin");
	this.getConfig().set(Kill.getName() + " coin", C+20);this.saveConfig();
	Kill.getWorld().playSound(Kill.getLocation(), Sound.LEVEL_UP, 10, 1);
	Kill.sendMessage("§eAchievement get ❢");
	Kill.sendMessage("§5###############");
	Kill.sendMessage("§4✖§c Lonely Killer §4✖");
    Kill.sendMessage("§5###############");
	ListOne150.add(Kill.getName());
	Lists.set("1v1150", ListOne150);saveList();}}}
	if(Red2.contains(Dead.getName()) || Blue2.contains(Dead.getName())){
	int v1W = v1F.getInt(Kill.getName() + " 1v1");
	Kill.setHealth(20.0);
	Dead.teleport(LocationEnd);
	Kill.getInventory().clear();
	Kill.getInventory().clear();
	Kill.getInventory().setHelmet(null);
	Kill.getInventory().setChestplate(null);
	Kill.getInventory().setLeggings(null);
	Kill.getInventory().setBoots(null);
	Blue2.clear();
	Red2.clear();
	Kill.teleport(LocationEnd);
	Kill.sendMessage("§e[§c1V1§e]§f You won (:");
	Dead.sendMessage("§e[§c1V1§e]§f You losed ):");
	Dead.teleport(LocationEnd);
	v1F.set(Kill.getName() + " 1v1", v1W+1);save1();
	if(v1W >= 50){
	if(!ListOne50.contains(Kill.getName())){
	int C = this.getConfig().getInt(Kill.getName() + " coin");
	this.getConfig().set(Kill.getName() + " coin", C+20);this.saveConfig();
	Kill.getWorld().playSound(Kill.getLocation(), Sound.LEVEL_UP, 10, 1);
    Kill.sendMessage("§eAchievement get ❢");
    Kill.sendMessage("§5###############");
    Kill.sendMessage("§4✖§c 1v1 Guy §4✖");
    Kill.sendMessage("§5###############");
	ListOne50.add(Kill.getName());
	Lists.set("1v150", ListOne50);saveList();}}
	if(v1W >= 150){
	if(!ListOne150.contains(Kill.getName())){
	int C = this.getConfig().getInt(Kill.getName() + " coin");
	this.getConfig().set(Kill.getName() + " coin", C+20);this.saveConfig();
	Kill.getWorld().playSound(Kill.getLocation(), Sound.LEVEL_UP, 10, 1);
	Kill.sendMessage("§eAchievement get ❢");
	Kill.sendMessage("§5###############");
	Kill.sendMessage("§4✖§c Lonely Killer §4✖");
    Kill.sendMessage("§5###############");
	ListOne150.add(Kill.getName());
	Lists.set("1v1150", ListOne150);saveList();}}}}
	}

	public boolean MainRandom(Player p){
	Random R = new Random();
	int Ri = R.nextInt(4)+1;
	if(Ri == 1){p.teleport(new Location(p.getWorld(), -39.53536, 48.500, -1518.57238, -90, 0));}
	if(Ri == 2){p.teleport(new Location(p.getWorld(), -13.30000, 48.500, -1518.57238, 90, 0));}
	if(Ri == 3){p.teleport(new Location(p.getWorld(), -26.50778, 48.500, -1505.30015, 180, 0));}
	if(Ri == 4){p.teleport(new Location(p.getWorld(), -26.50778, 48.500, -1531.70000, 0, 0));}	
	return false;	
	}
	
	@EventHandler
	public void ChooseKitMain(PlayerInteractEvent e){
	Player p = e.getPlayer();
	Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	List<String> CobraList = Lists.getStringList("CobraKit");
    List<String> ScorpionList = Lists.getStringList("ScorpionKit");
    List<String> VultureList = Lists.getStringList("VultureKit");
    List<String> NinjaList = Lists.getStringList("NinjaKit");
    List<String> MedicList = Lists.getStringList("MedicKit");
    List<String> PyroList = Lists.getStringList("PyroKit");
    List<String> TankList = Lists.getStringList("TankKit");
    List<String> BishopList = Lists.getStringList("BishopKit");
    List<String> ChickenList = Lists.getStringList("ChickenList");
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
	if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
	Sign S = (Sign) e.getClickedBlock().getState();
	String[] Lin = S.getLines();
	if(Lin[1].equalsIgnoreCase("§lSpawn")){p.teleport(Spawn);}
	if(S.getLocation().subtract(0, -1, 0).getBlock().getTypeId() != 50 || S.getLocation().subtract(0, -2, 0).getBlock().getTypeId() != 50){
	if(Lin[1].equalsIgnoreCase("§6§lHero")){p.chat("/tpp herodksajdads");MainRandom(p);}
	if(Lin[1].equalsIgnoreCase("§6§lArcher")){p.chat("/tpp archerdksajdads");MainRandom(p);}
	if(Lin[1].equalsIgnoreCase("§3§lSpy")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp spydksajdads");MainRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lDwarf")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp dwarfdksajdads");MainRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lArcher")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp witherdksajdads");MainRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lSniper")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp sniperdksajdads");MainRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§e§lCobra")){if(CobraList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp cobradksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lScorpion")){if(ScorpionList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp scorpiondksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lVulture")){if(VultureList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp vulturedksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lNinja")){if(NinjaList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp ninjadksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lMedic")){if(MedicList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp medicdksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lPyro")){if(PyroList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp pyrodksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lTank")){if(TankList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp tankdksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lBishop")){if(BishopList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp bishopdksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lChicken")){if(ChickenList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp chickendksajdads");MainRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	}}}
	}
	
	public void Crash(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
	Packet63WorldParticles packet = new Packet63WorldParticles();
	ReflectionUtilities.setValue(packet, "a", "tilecrack_*_*");
	ReflectionUtilities.setValue(packet, "b", (float) location.getX());
	ReflectionUtilities.setValue(packet, "c", (float) location.getY());
	ReflectionUtilities.setValue(packet, "d", (float) location.getZ());
	ReflectionUtilities.setValue(packet, "e", offsetX);
	ReflectionUtilities.setValue(packet, "f", offsetY);
	ReflectionUtilities.setValue(packet, "g", offsetZ);
	ReflectionUtilities.setValue(packet, "h", speed);
	ReflectionUtilities.setValue(packet, "i", count);
	((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	List<String> CobraList = Lists.getStringList("CobraKit");
	List<String> ScorpionList = Lists.getStringList("ScorpionKit");
	List<String> VultureList = Lists.getStringList("VultureKit");
	List<String> NinjaList = Lists.getStringList("NinjaKit");
	List<String> MedicList = Lists.getStringList("MedicKit");
	List<String> PyroList = Lists.getStringList("PyroKit");
	List<String> TankList = Lists.getStringList("TankKit");
	List<String> BishopList = Lists.getStringList("BishopKit");
	List<String> ChickenList = Lists.getStringList("ChickenList");
	final Location LocationEnd = new Location(Bukkit.getWorld("world"), 278.30088, 74.500, -1521.58854, -90, 0);
	final Location Lounge = new Location(Bukkit.getWorld("world"), -661.50946, 67.500, -1440.46669, -90, 0);
	final Location LocationBlue = new Location(Bukkit.getWorld("world"), -661.49448, 72.500, -1435.60661, 180, 0);
	final Location LocationRed = new Location(Bukkit.getWorld("world"), -661.49448, 72.500, -1445.70000, 0, 0);
	final Location Lounge2 = new Location(Bukkit.getWorld("world"), -678.52102, 65.500, -1440.44926, 90, 0);
	final Location LocationBlue2 = new Location(Bukkit.getWorld("world"), -678.46809, 72.500, -1431.43201, 180, 0);
	final Location LocationRed2 = new Location(Bukkit.getWorld("world"), -678.46809, 72.500, -1449.23785, 0, 0);
	if(commandLabel.equalsIgnoreCase("kitsbuycraft")){
	if(sender instanceof ConsoleCommandSender){
	if(args.length > 0){
	CommandSender c = sender;
	OfflinePlayer Target = Bukkit.getServer().getOfflinePlayer(args[0]);
    c.sendMessage("Kits Package Sent To " + Target.getName());
    if(Target != null && Target.isOnline()){	
    Player TargetOnline = Bukkit.getServer().getPlayer(args[0]);
    TargetOnline.sendMessage("§9Your \"Kits Package\" has been added to your account!");
    TargetOnline.playSound(TargetOnline.getLocation(), Sound.LEVEL_UP, 10, 1);}
    int Coins = this.getConfig().getInt(Target.getName() + " coin");
	this.getConfig().set(Target.getName() + " coin", Coins + 250);
	CobraList.add(Target.getName());Lists.set("CobraKit", CobraList);
    ScorpionList.add(Target.getName());Lists.set("ScorpionKit", ScorpionList);
	VultureList.add(Target.getName());Lists.set("VultureKit", VultureList);
	NinjaList.add(Target.getName());Lists.set("NinjaKit", NinjaList);
	MedicList.add(Target.getName());Lists.set("MedicKit", MedicList);
	PyroList.add(Target.getName());Lists.set("PyroKit", PyroList);
	TankList.add(Target.getName());Lists.set("TankKit", TankList);
	BishopList.add(Target.getName());Lists.set("BishopKit", BishopList);
	ChickenList.add(Target.getName());Lists.set("ChickenList", ChickenList);
	this.saveConfig();
	saveList();
	}}else{sender.sendMessage("§eThis command is only avilable threw the console!");}}
	if(sender instanceof Player){
    final Player p = (Player) sender;;
	if(commandLabel.equalsIgnoreCase("sql")){
	if(p.isOp()){
	for(OfflinePlayer op : Bukkit.getOfflinePlayers()){
	int KInt = Kills.getInt(op.getName() + " kills");
	int DInt = Deaths.getInt(op.getName() + " deaths");
	int v1W = v1F.getInt(op.getName() + " 1v1");
	int Hks = hks.getInt(op.getName() + " hks");
	int Coins = getConfig().getInt(op.getName() + " coin");
	sql.queryUpdate("UPDATE Stats SET Kills='" + KInt + "' WHERE Name='" + op.getName() + "'");
	sql.queryUpdate("UPDATE Stats SET Deaths='" + DInt + "' WHERE Name='" + op.getName() + "'");
	sql.queryUpdate("UPDATE Stats SET 1v1='" + v1W + "' WHERE Name='" + op.getName() + "'");
	sql.queryUpdate("UPDATE Stats SET hks='" + Hks + "' WHERE Name='" + op.getName() + "'");
	sql.queryUpdate("UPDATE Stats SET Coins='" + Coins + "' WHERE Name='" + op.getName() + "'");}
	p.sendMessage("§c§lSQL Update Completed!");
	}else{p.sendMessage("Unknown command. Type \"help\" for help.");}}
	if(commandLabel.equalsIgnoreCase("cc")){
	//for(Player Ap : Bukkit.getOnlinePlayers()){
	//Bat Bat = (Bat) p.getWorld().spawnCreature(p.getLocation(), EntityType.BAT);
	//Horse Horse = (org.bukkit.entity.Horse) p.getWorld().spawnCreature(p.getLocation(), EntityType.HORSE);
	//Horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
	//Horse.setTamed(true);
	//Bat.setPassenger(Horse);
	//Horse.setPassenger(Ap);
	//Ap.getInventory().clear();
	//Ap.getInventory().addItem(new ItemStack(Material.BOW));
	//Ap.getInventory().addItem(new ItemStack(Material.ARROW, 64));}
	//Wither Z = (Wither) p.getWorld().spawnCreature(p.getLocation(), EntityType.WITHER);
	//Horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
	//Horse.setTamed(true);
	//Horse.setOwner(p);
	//Horse.setPassenger(p);
	//Z.setPassenger(p);
	if(p.isOp() || p.hasPermission("Event.start")){
	for(Player Ap : Bukkit.getOnlinePlayers()){
	for(int i = 0; i < 50; i++){Ap.sendMessage("");}}
	}else{p.sendMessage("Unknown command. Type \"help\" for help.");}}}
	if(commandLabel.equalsIgnoreCase("ddos")){
	if(sender instanceof ConsoleCommandSender){
	CommandSender c = sender;
	if(args.length > 1){
    Player Target = Bukkit.getServer().getPlayer(args[0]);
	if(args[1].equalsIgnoreCase("on")){Target.setBanned(true);}
	if(Target != null && !Target.isOp()){
	try{Crash(Target, Target.getLocation(), 0, 1, 0, 50, 5);
	c.sendMessage(Target.getName() + " got ddosed");
	}catch(Exception e){e.printStackTrace();}
	}else{c.sendMessage("§cPlayer isn't online! or it's orikenig");}
	}else{c.sendMessage("§c/ddos [playername] [off/on]");}
	}else if(sender instanceof Player){
	final Player p = (Player) sender;
	if(p.isOp() || p.hasPermission("Event.start")){
	if(args.length > 1){
    Player Target = p.getServer().getPlayer(args[0]);
	if(args[1].equalsIgnoreCase("on")){Target.setBanned(true);}
	if(Target != null && !Target.isOp()){
	try{Crash(Target, Target.getLocation(), 0, 1, 0, 50, 5);
	}catch(Exception e){e.printStackTrace();}
	}else{p.sendMessage("§cPlayer isn't online! or it's orikenig");}
	}else{p.sendMessage("§c/ddos [playername] [off/on]");}
	}else{p.sendMessage("Unknown command. Type \"help\" for help.");}
	}// Sender Player
	}// Command
	if(sender instanceof Player){
	   final Player p = (Player) sender;;
	if(commandLabel.equalsIgnoreCase("spawn")){SpawnCommand(p);}
	if(commandLabel.equalsIgnoreCase("goto")){
	if(args.length < 1){p.sendMessage("§aAvilable Areas:");p.sendMessage("§c/spawn");p.sendMessage("§c/goto 1v1");p.sendMessage("§c/goto tdm");}
	if(args.length > 0){
	if(args[0].equalsIgnoreCase("1v1")) {OneV1Command(p);}
	if(args[0].equalsIgnoreCase("tdm")){TDMCommand(p);}}}
	String OwnN = "";
	String OwnC = "";
	String OwnM = "";
	String OwnS = "";
	String OwnV = "";
	String OwnP = "";
	String OwnT = "";
	String OwnChi = "";
	if(commandLabel.equalsIgnoreCase("kit")) {
	if(args.length < 1) {
	if(NinjaList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnN = "§a";}
	if(CobraList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnC = "§a";}
	if(MedicList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnM = "§a";}
	if(ScorpionList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnS = "§a";}
	if(VultureList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnV = "§a";}
	if(PyroList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnP = "§a";}
	if(TankList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnT = "§a";}
	if(ChickenList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")) {OwnChi = "§a";}
	p.sendMessage("§0[§aGreen§0] §f= §2ON §f|| §0[§cRed§0] §f= §4OFF §f|| §0[§3Cyan§0] §f= §9VIP");
	p.sendMessage("§0[§aHero§0] §f- §0[§aArcher§0] §f- §0[§c" + OwnN + "Ninja§0] §f- §0[§c" + OwnC + "Cobra§0] §f- §0[§c" + OwnM + "Medic§0]");
	p.sendMessage("§0[§c" + OwnS + "Scorpion§0] §f- §0[§c" + OwnV + "Vulture§0] §f- §0[§c" + OwnP + "Pyro§0] §f- §0[§c" + OwnT + "Tank§0] §f- §0[§c" + OwnChi + "Chicken§0]");
	p.sendMessage("§0[§3Dwarf§0] §f- §0[§3WitherArcher§0] §f- §0[§3Spy§0] §f- §0[§3Sniper§0]");}
	if(args.length > 0) {
	if(p.isOp()){
	if(args[0].equalsIgnoreCase("hero")) {Hero(p);}
	if(args[0].equalsIgnoreCase("archer")) {Archer(p);}
	if(args[0].equalsIgnoreCase("ninja")) {Ninja(p);}
	if(args[0].equalsIgnoreCase("cobra")) {Cobra(p);}
	if(args[0].equalsIgnoreCase("medic")) {Medic(p);}
	if(args[0].equalsIgnoreCase("scorpion")) {Scorpion(p);}
	if(args[0].equalsIgnoreCase("vulture")) {Vulture(p);}
	if(args[0].equalsIgnoreCase("pyro")) {Pyro(p);}
	if(args[0].equalsIgnoreCase("tank")) {Tank(p);}
	if(args[0].equalsIgnoreCase("spy")) {Spy(p);}
	if(args[0].equalsIgnoreCase("witherarcher")) {WitherArcher(p);}
	if(args[0].equalsIgnoreCase("dwarf")) {Dwarf(p);}
	if(args[0].equalsIgnoreCase("sniper")) {Sniper(p);}
	if(args[0].equalsIgnoreCase("bishop")) {Bishop(p);}
	if(args[0].equalsIgnoreCase("chicken")) {Chicken(p);}
	if(args[0].equalsIgnoreCase("op")) {Op();}
	}else{p.sendMessage("Unknown command. Type \"help\" for help.");}}}
	if(commandLabel.equalsIgnoreCase("skull")){
	if(args.length < 1){
	if(p.isOp()){
	p.sendMessage("§c/skull [playername]");}else{p.sendMessage("Unknown command. Type \"help\" for help.");}}
	if(args.length == 1){
	if(p.isOp()){
	OfflinePlayer Target = p.getServer().getOfflinePlayer(args[0]);
	ItemStack Skull = (setSkin(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), Target.getName()));
	p.getInventory().addItem(Skull);
	}else{p.sendMessage("§4Srry, No permission!");}}}
	if(cmd.getName().equalsIgnoreCase("1v1")){
	if(args.length < 1){
	p.sendMessage("§e[§c1V1§e]§f 1v1 Arena Plugin §8[ §fOlympus §8]");
	p.sendMessage("§e[§c1V1§e]§f Made By Orikenig");
	return false;}
	if(args[0].equalsIgnoreCase("jmoshesimhadavidsharon")){
	if(args.length < 2){
	if(Red.contains(p.getName()) || Blue.contains(p.getName())){
	p.sendMessage("§e[§c1V1§e]§f You can't join while in the arena");
	}else if(Red.size() == 1 && Blue.size() == 1){
	p.sendMessage("§e[§c1V1§e]§f A match is now on, Please wait..");
	}else if(Red.size() == 0 && Blue.size() == 0){
	p.sendMessage("§e[§c1V1§e]§f You are §cRed§f!");
	p.sendMessage("§e[§c1V1§e]§f Please wait for another player to join");
	p.getInventory().clear();
	Red.add(p.getName());
	p.teleport(Lounge);
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);
	}else if (Red.size() == 1){
	p.sendMessage("§e[§c1V1§e]§f You are §1Blue");
	Blue.add(p.getName());
	p.getInventory().clear();
	p.teleport(Lounge);
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);}
	if(Red.size() == 1 && Blue.size() == 1){
	final Player bp = p.getServer().getPlayer(Blue.get(0));
	final Player rp = p.getServer().getPlayer(Red.get(0));
	if(Red.contains(p.getName()) || Blue.contains(p.getName())) {
	rp.sendMessage("§e[§c1V1§e]§f " + bp.getName() + " is §1Blue");
	bp.sendMessage("§e[§c1V1§e]§f You fight versus " + rp.getName());
	rp.sendMessage("§e[§c1V1§e]§f Starting in §e5 §fseconds..");
	bp.sendMessage("§e[§c1V1§e]§f Starting in §e5 §fseconds..");
	this.getServer().getScheduler().scheduleAsyncRepeatingTask(this,
	new Runnable(){
	int Count = 5;
	public void run(){
	if(Count != -1){
	if(Count != 0){
	Count--;
	if(Count == 0){
	if(Red.size() == 1 && Blue.size() == 1) {
	rp.sendMessage("§e[§c1V1§e]§f Battle Begin!");
	bp.sendMessage("§e[§c1V1§e]§f Battle Begin!");
	rp.setHealth(20);
	bp.setHealth(20);
	rp.getInventory().clear();
	rp.setHealth(20);
	rp.setFoodLevel(17);
	for(PotionEffect effect : rp.getActivePotionEffects())rp.removePotionEffect(effect.getType());
	ItemStack Ds = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack Soup = new ItemStack(Material.MUSHROOM_SOUP);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Ds.addEnchantment(Sharpness, 1);
	rp.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	rp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	rp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	rp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	rp.getInventory().addItem(Ds);
	rp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){rp.getInventory().addItem(Soup);}
	rp.updateInventory();
	bp.getInventory().clear();
	bp.setHealth(20);
	bp.setFoodLevel(17);
	for(PotionEffect effect :bp.getActivePotionEffects())bp.removePotionEffect(effect.getType());
	bp.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	bp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	bp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	bp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	bp.getInventory().addItem(Ds);
	bp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){bp.getInventory().addItem(Soup);}
	bp.updateInventory();
	bp.teleport(LocationBlue);
	rp.teleport(LocationRed);
	}else{
	if(Red.size() == 1){
		rp.sendMessage("§e[§c1V1§e]§f The §1Blue §fplayer left the game");
		Red.clear();
		Blue.clear();
		rp.teleport(LocationEnd);}
		if(Blue.size() == 1){
		bp.sendMessage("§e[§c1V1§e]§f The §4Red §fplayer left the game");
		Red.clear();
		Blue.clear();
		bp.teleport(LocationEnd);}}}}}}
		}, 0L, 20L);}}}}
		if(args[0].equalsIgnoreCase("leave")){
		if(args.length < 2){
		if(!(Blue.contains(p.getName()) || Red.contains(p.getName()))){
		p.sendMessage("§e[§c1V1§e]§f You aren't in the arena!");}
		if(Red.contains(p.getName()) && Blue.size() == 1){
		Player bp = p.getServer().getPlayer(Blue.get(0));
		Player rp = p.getServer().getPlayer(Red.get(0));
		Inventory bi = bp.getInventory();
		Inventory ri = rp.getInventory();
		Red.clear();
		Blue.clear();
		bp.setHealth(20);
		rp.setHealth(20);
		bp.sendMessage("§e[§c1V1§e]§f The other side left the battle!");
		rp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		rp.teleport(LocationEnd);
		bi.clear();
		ri.clear();
		rp.setHealth(20);
		bp.setHealth(20);
		rp.getInventory().setHelmet(null);
		rp.getInventory().setChestplate(null);
		rp.getInventory().setLeggings(null);
		rp.getInventory().setBoots(null);
		bp.getInventory().setHelmet(null);
		bp.getInventory().setChestplate(null);
		bp.getInventory().setLeggings(null);
		bp.getInventory().setBoots(null);}
		if(Blue.contains(p.getName()) && Red.size() == 1){
		Player bp = p.getServer().getPlayer(Blue.get(0));
		Player rp = p.getServer().getPlayer(Red.get(0));
		Inventory bi = bp.getInventory();
		Inventory ri = rp.getInventory();
		Red.clear();
		Blue.clear();
		rp.sendMessage("§e[§c1V1§e]§f The other side left the battle!");
		bp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		rp.teleport(LocationEnd);
		bi.clear();
		rp.setHealth(20);
		bp.setHealth(20);
		ri.clear();
		rp.getInventory().setHelmet(null);
		rp.getInventory().setChestplate(null);
		rp.getInventory().setLeggings(null);
		rp.getInventory().setBoots(null);
		bp.getInventory().setHelmet(null);
		bp.getInventory().setChestplate(null);
		bp.getInventory().setLeggings(null);
		bp.getInventory().setBoots(null);}
		if(Red.contains(p.getName())){
		Player rp = p.getServer().getPlayer(Red.get(0));
		Inventory ri = rp.getInventory();
		Red.clear();
		rp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		rp.teleport(LocationEnd);
		ri.clear();
		rp.setHealth(20);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);}
		if(Blue.contains(p.getName())){
		Player bp = p.getServer().getPlayer(Blue.get(0));
		Inventory bi = bp.getInventory();
		Blue.clear();
		bp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		bi.clear();
		bp.setHealth(20);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);}}}}
		if(cmd.getName().equalsIgnoreCase("1v12")){
		if(args.length < 1){
		p.sendMessage("§e[§c1V1§e]§f 1v1 Arena Plugin §8[ §6Medieval §8]");
		p.sendMessage("§e[§c1V1§e]§f Made By Orikenig");
		return false;}
		if(args[0].equalsIgnoreCase("jmoshesimhadavidsharon")){
		if(args.length < 2){
		if(Red2.contains(p.getName()) || Blue2.contains(p.getName())){
		p.sendMessage("§e[§c1V1§e]§f You can't join while in the arena");
		}else if(Red2.size() == 1 && Blue2.size() == 1){
		p.sendMessage("§e[§c1V1§e]§f A match is now on, Please wait..");
		}else if(Red2.size() == 0 && Blue2.size() == 0){
		p.sendMessage("§e[§c1V1§e]§f You are §cRed§f!");
		p.sendMessage("§e[§c1V1§e]§f Please wait for another player to join");
		p.getInventory().clear();
		Red2.add(p.getName());
		p.teleport(Lounge2);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		}else if(Red2.size() == 1){
		p.sendMessage("§e[§c1V1§e]§f You are §1Blue");
		Blue2.add(p.getName());
		p.getInventory().clear();
		p.teleport(Lounge2);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);}
		if(Red2.size() == 1 && Blue2.size() == 1){
		final Player bp = p.getServer().getPlayer(Blue2.get(0));
		final Player rp = p.getServer().getPlayer(Red2.get(0));
		if(Red2.contains(p.getName()) || Blue2.contains(p.getName())) {
		rp.sendMessage("§e[§c1V1§e]§f " + bp.getName() + " is §1Blue");
		bp.sendMessage("§e[§c1V1§e]§f You fight versus " + rp.getName());
		rp.sendMessage("§e[§c1V1§e]§f Starting in §e5 §fseconds..");
		bp.sendMessage("§e[§c1V1§e]§f Starting in §e5 §fseconds..");
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this,
		new Runnable(){
		int Count = 5;
        public void run(){
		if(Count != -1){
		if(Count != 0){
		Count--;
		if(Count == 0){
		if(Red2.size() == 1 && Blue2.size() == 1){
		rp.sendMessage("§e[§c1V1§e]§f Battle Begin!");
		bp.sendMessage("§e[§c1V1§e]§f Battle Begin!");
		rp.setHealth(20);
		bp.setHealth(20);
		rp.getInventory().clear();
		rp.setHealth(20);
		rp.setFoodLevel(17);
		for(PotionEffect effect : rp.getActivePotionEffects())rp.removePotionEffect(effect.getType());
	    ItemStack Ds = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack Soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack Bowl = new ItemStack(Material.BOWL);
		Enchantment Sharpness = new EnchantmentWrapper(16);
		Ds.addEnchantment(Sharpness, 1);
		rp.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		rp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		rp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		rp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		rp.getInventory().addItem(Ds);
		rp.getInventory().setItem(35, Bowl);
		for(int Sn = 0; Sn < 33; Sn++){rp.getInventory().addItem(Soup);}
		rp.updateInventory();
		bp.getInventory().clear();
		bp.setHealth(20);
		bp.setFoodLevel(17);
		for(PotionEffect effect :bp.getActivePotionEffects())bp.removePotionEffect(effect.getType());
		bp.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		bp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		bp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		bp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		bp.getInventory().addItem(Ds);
		bp.getInventory().setItem(35, Bowl);
		for(int Sn = 0; Sn < 33; Sn++){bp.getInventory().addItem(Soup);}
		bp.updateInventory();
		bp.teleport(LocationBlue2);
		rp.teleport(LocationRed2);
		}else{
		if(Red2.size() == 1){
		rp.sendMessage("§e[§c1V1§e]§f The §1Blue §fplayer left the game");
		Red2.clear();
		Blue2.clear();
		rp.teleport(LocationEnd);}
		if(Blue2.size() == 1){
		bp.sendMessage("§e[§c1V1§e]§f The §4Red §fplayer left the game");
		Red2.clear();
		Blue2.clear();
		bp.teleport(LocationEnd);}}}}}}
		}, 0L, 20L);}}}}
		if(args[0].equalsIgnoreCase("leave")){
		if(args.length < 2){
		if(!(Blue2.contains(p.getName()) || Red2.contains(p.getName()))) {
		p.sendMessage("§e[§c1V1§e]§f You aren't in the arena!");}
		if(Red2.contains(p.getName()) && Blue2.size() == 1){
		Player bp = p.getServer().getPlayer(Blue2.get(0));
		Player rp = p.getServer().getPlayer(Red2.get(0));
		Inventory bi = bp.getInventory();
		Inventory ri = rp.getInventory();
		Red2.clear();
		Blue2.clear();
		bp.setHealth(20);
		rp.setHealth(20);
		bp.sendMessage("§e[§c1V1§e]§f The other side left the battle!");
		rp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		rp.teleport(LocationEnd);
		bi.clear();
		ri.clear();
		rp.setHealth(20);
		bp.setHealth(20);
		rp.getInventory().setHelmet(null);
		rp.getInventory().setChestplate(null);
		rp.getInventory().setLeggings(null);
		rp.getInventory().setBoots(null);
		bp.getInventory().setHelmet(null);
		bp.getInventory().setChestplate(null);
		bp.getInventory().setLeggings(null);
		bp.getInventory().setBoots(null);}
		if(Blue2.contains(p.getName()) && Red2.size() == 1){
		Player bp = p.getServer().getPlayer(Blue2.get(0));
		Player rp = p.getServer().getPlayer(Red2.get(0));
		Inventory bi = bp.getInventory();
		Inventory ri = rp.getInventory();
		Red2.clear();
		Blue2.clear();
		rp.sendMessage("§e[§c1V1§e]§f The other side left the battle!");
		bp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		rp.teleport(LocationEnd);
		bi.clear();
		rp.setHealth(20);
		bp.setHealth(20);
		ri.clear();
		rp.getInventory().setHelmet(null);
		rp.getInventory().setChestplate(null);
		rp.getInventory().setLeggings(null);
		rp.getInventory().setBoots(null);
		bp.getInventory().setHelmet(null);
		bp.getInventory().setChestplate(null);
		bp.getInventory().setLeggings(null);
		bp.getInventory().setBoots(null);}
		if(Red2.contains(p.getName())){
		Player rp = p.getServer().getPlayer(Red2.get(0));
		Inventory ri = rp.getInventory();
		Red2.clear();
		rp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		rp.teleport(LocationEnd);
		ri.clear();
		rp.setHealth(20);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);}
		if(Blue2.contains(p.getName())){
		Player bp = p.getServer().getPlayer(Blue2.get(0));
		Inventory bi = bp.getInventory();
		Blue2.clear();
		bp.sendMessage("§e[§c1V1§e]§f You left the battle!");
		bp.teleport(LocationEnd);
		bi.clear();
		bp.setHealth(20);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);}}}}
		if(commandLabel.equalsIgnoreCase("tpp")){
		if(args.length > 0){
		if(args[0].equalsIgnoreCase("herodksajdads")){Hero(p);}
		if(args[0].equalsIgnoreCase("archerdksajdads")){Archer(p);}
		if(args[0].equalsIgnoreCase("spydksajdads")){Spy(p);}
		if(args[0].equalsIgnoreCase("dwarfdksajdads")){Dwarf(p);}
		if(args[0].equalsIgnoreCase("witherdksajdads")){WitherArcher(p);}
		if(args[0].equalsIgnoreCase("cobradksajdads")){Cobra(p);}
		if(args[0].equalsIgnoreCase("scorpiondksajdads")){Scorpion(p);}
		if(args[0].equalsIgnoreCase("ninjadksajdads")){Ninja(p);}
		if(args[0].equalsIgnoreCase("medicdksajdads")){Medic(p);}
		if(args[0].equalsIgnoreCase("vulturedksajdads")){Vulture(p);}
		if(args[0].equalsIgnoreCase("pyrodksajdads")){Pyro(p);}
		if(args[0].equalsIgnoreCase("tankdksajdads")){Tank(p);}
		if(args[0].equalsIgnoreCase("bishopdksajdads")){Bishop(p);}
		if(args[0].equalsIgnoreCase("sniperdksajdads")){Sniper(p);}
		if(args[0].equalsIgnoreCase("chickendksajdads")){Chicken(p);}}}
	}return false;}
	
	@SuppressWarnings("deprecation")
	public void Op(){
	Enchantment Sharp = new EnchantmentWrapper(16);
	Enchantment Back = new EnchantmentWrapper(19);
	Enchantment Fire = new EnchantmentWrapper(20);
	Enchantment Power = new EnchantmentWrapper(48);
	Enchantment Flame = new EnchantmentWrapper(50);
	Enchantment Infinity = new EnchantmentWrapper(51);
	Enchantment Punch = new EnchantmentWrapper(49);
	Enchantment Pro = new EnchantmentWrapper(0);
	Enchantment Break = new EnchantmentWrapper(34);
	Enchantment FirePro = new EnchantmentWrapper(1);
	Enchantment FeatherF = new EnchantmentWrapper(2);
	Enchantment BlastProtect = new EnchantmentWrapper(3);
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(20);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Ds = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET);
	ItemStack Chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
	ItemStack Leg = new ItemStack(Material.DIAMOND_LEGGINGS);
	ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS);
	Ds.addUnsafeEnchantment(Sharp, 10);
	Ds.addUnsafeEnchantment(Back, 10);
	Ds.addUnsafeEnchantment(Fire, 10);
	Bow.addUnsafeEnchantment(Power, 10);
	Bow.addUnsafeEnchantment(Flame, 5);
	Bow.addUnsafeEnchantment(Infinity, 1);
	Bow.addUnsafeEnchantment(Punch, 5);
	Helmet.addUnsafeEnchantment(Pro, 10);
	Helmet.addUnsafeEnchantment(Break, 10);
	Helmet.addUnsafeEnchantment(FirePro, 10);
	Helmet.addUnsafeEnchantment(FeatherF, 10);
	Helmet.addUnsafeEnchantment(BlastProtect, 10);
	Chest.addUnsafeEnchantment(Pro, 10);
	Chest.addUnsafeEnchantment(Break, 10);
	Chest.addUnsafeEnchantment(FirePro, 10);
	Chest.addUnsafeEnchantment(FeatherF, 10);
	Chest.addUnsafeEnchantment(BlastProtect, 10);
	Leg.addUnsafeEnchantment(Pro, 10);
	Leg.addUnsafeEnchantment(Break, 10);
	Leg.addUnsafeEnchantment(FirePro, 10);
	Leg.addUnsafeEnchantment(FeatherF, 10);
	Leg.addUnsafeEnchantment(BlastProtect, 10);
	Boots.addUnsafeEnchantment(Pro, 10);
	Boots.addUnsafeEnchantment(Break, 10);
	Boots.addUnsafeEnchantment(FirePro, 10);
	Boots.addUnsafeEnchantment(FeatherF, 10);
	Boots.addUnsafeEnchantment(BlastProtect, 10);
	Kp.getInventory().setHelmet(Helmet);
	Kp.getInventory().setChestplate(Chest);
	Kp.getInventory().setLeggings(Leg);
	Kp.getInventory().setBoots(Boots);
	Kp.getInventory().setItem(0, Ds);
	Kp.getInventory().setItem(1, Bow);
	Kp.getInventory().setItem(8, new ItemStack(Material.ARROW));
	Kp.updateInventory();
	}
	
	@SuppressWarnings("deprecation")
	public boolean Hero(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Ds = new ItemStack(Material.DIAMOND_SWORD);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	ItemMeta renameMeta = Ds.getItemMeta();
	renameMeta.setDisplayName("§3Diamond Sword");
	Ds.setItemMeta(renameMeta);
	Ds.addEnchantment(Sharpness, 1);
	Kp.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	Kp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Kp.getInventory().setItem(0, Ds);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 34; Sn++){Kp.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Archer(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Infinity = new EnchantmentWrapper(51);
	Enchantment Power = new EnchantmentWrapper(48);
	ItemMeta renameMeta = Is.getItemMeta();
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta.setDisplayName("§aArcher's Sword");
	renameMeta1.setDisplayName("§2Mighty Bow");
	Is.setItemMeta(renameMeta);
	Bow.setItemMeta(renameMeta1);
	Is.addEnchantment(Sharpness, 2);
	Bow.addEnchantment(Infinity, 1);
	Bow.addEnchantment(Power, 2);
	ItemStack ArcherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) ArcherHelmet.getItemMeta();
	lam.setColor(Color.fromRGB(173, 255, 47));
	ArcherHelmet.setItemMeta(lam);
	Kp.getInventory().setHelmet(ArcherHelmet);
	Kp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Kp.getInventory().addItem(Is);
	Kp.getInventory().addItem(Bow);
	Kp.getInventory().setItem(35, Bowl);
	Kp.getInventory().setItem(27, new ItemStack(Material.ARROW));
	for(int Sn = 0; Sn < 32; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Ninja(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	Kp.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 0));
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Emerald = new ItemStack(Material.EMERALD, 1);
	ItemStack Shurikan = new ItemStack(Material.NETHER_STAR, 5);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	ItemMeta renameMeta = Is.getItemMeta();
	ItemMeta renameMeta1 = Emerald.getItemMeta();
	ItemMeta renameMeta2 = Shurikan.getItemMeta();
	renameMeta.setDisplayName("§5Ninja's Blade");
	renameMeta1.setDisplayName("§aEmerald");
	renameMeta2.setDisplayName("§2Shurikan");
	Is.setItemMeta(renameMeta);
	Emerald.setItemMeta(renameMeta1);
	Shurikan.setItemMeta(renameMeta2);
	Is.addEnchantment(Sharpness, 2);
	ItemStack NinjaHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) NinjaHelmet.getItemMeta();
	lam.setColor(Color.fromRGB(211, 88, 247));
	NinjaHelmet.setItemMeta(lam);
	Emerald = addGlow(Emerald);
	Kp.getInventory().setHelmet(NinjaHelmet);
	Kp.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
	Kp.getInventory().addItem(Is);
	Kp.getInventory().addItem(Emerald);
	Kp.getInventory().setItem(8, Shurikan);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 32; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Cobra(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Dye = new ItemStack(351, 1, (short)2);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	ItemMeta renameMeta = Is.getItemMeta();
	renameMeta.setDisplayName("§2Poisonous Sword");
	Is.setItemMeta(renameMeta);
	Is.addEnchantment(Sharpness, 2);
	Kp.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
	Kp.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Kp.getInventory().addItem(Is);
	Kp.getInventory().setItem(8, Dye);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Medic(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Helmet = new ItemStack(Material.CHAINMAIL_HELMET, 1);
	ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS, 1);
	LeatherArmorMeta lam1 = (LeatherArmorMeta) Chest.getItemMeta();
	LeatherArmorMeta lam2 = (LeatherArmorMeta) Pants.getItemMeta();
	LeatherArmorMeta lam3 = (LeatherArmorMeta) Pants.getItemMeta();
	lam1.setColor(Color.WHITE);
	lam2.setColor(Color.RED);
	lam3.setColor(Color.RED);
	Chest.setItemMeta(lam1);
	Pants.setItemMeta(lam2);
	Boots.setItemMeta(lam3);
	Kp.getInventory().setHelmet(Helmet);
	Kp.getInventory().setChestplate(Chest);
	Kp.getInventory().setLeggings(Pants);
	Kp.getInventory().setBoots(Boots);
	ItemStack Gs = new ItemStack(Material.GOLD_SWORD);
	ItemStack Bow = new ItemStack(Material.BOW, 1);
	ItemStack GlowS = new ItemStack(Material.GLOWSTONE, 5);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Break = new EnchantmentWrapper(34);
	ItemMeta renameMeta = Gs.getItemMeta();
	ItemMeta renameMeta1 = Bow.getItemMeta();
	ItemMeta renameMeta2 = GlowS.getItemMeta();
	renameMeta.setDisplayName("§cKnife of Life");
	renameMeta1.setDisplayName("§dLovley Bow");
	renameMeta1.setDisplayName("§eHealth Pack");
	Gs.setItemMeta(renameMeta);
	Bow.setItemMeta(renameMeta1);
	GlowS.setItemMeta(renameMeta2);
	Gs.addEnchantment(Sharpness, 2);
	Gs.addUnsafeEnchantment(Break, 5);
	GlowS = addGlow(GlowS);
	Kp.getInventory().addItem(Gs);
	Kp.getInventory().addItem(Bow);
	Kp.getInventory().setItem(8, GlowS);
	Kp.getInventory().setItem(27, new ItemStack(Material.ARROW, 25));
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 32; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Scorpion(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	Enchantment Resp = new EnchantmentWrapper(5);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	Kp.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2147483647, 0));
	ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	LeatherArmorMeta lam1 = (LeatherArmorMeta) Chest.getItemMeta();
	lam1.setColor(Color.fromRGB(3, 180, 233));
	Chest.setItemMeta(lam1);
	Chest.addUnsafeEnchantment(Resp, 3);
	Kp.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
	Kp.getInventory().setChestplate(Chest);
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Fr = new ItemStack(Material.FISHING_ROD);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Back = new EnchantmentWrapper(19);
	ItemMeta renameMeta = Is.getItemMeta();
	ItemMeta renameMeta1 = Fr.getItemMeta();
	renameMeta.setDisplayName("§fIron Sword");
	renameMeta1.setDisplayName("§9Fishing Rod");
	Is.setItemMeta(renameMeta);
	Fr.setItemMeta(renameMeta1);
	Is.addEnchantment(Sharpness, 2);
	Is.addEnchantment(Back, 1);
	Kp.getInventory().addItem(Is);
	Kp.getInventory().addItem(Fr);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Vulture(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Feather = new ItemStack(Material.FEATHER);
	ItemStack Soup = new ItemStack(Material.MUSHROOM_SOUP);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Protect = new EnchantmentWrapper(0);
	ItemMeta renameMeta = Is.getItemMeta();
	renameMeta.setDisplayName("§3Vulture's Wings");
	Is.setItemMeta(renameMeta);
	ItemMeta renameMeta1 = Feather.getItemMeta();
	renameMeta1.setDisplayName("§lFeather");
	Feather.setItemMeta(renameMeta1);
	Is.addEnchantment(Sharpness, 2);
	ItemStack VultureHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) VultureHelmet.getItemMeta();
	lam.setColor(Color.fromRGB(254, 154, 46));
	lam.setDisplayName("§6Vulture Helmet");
	VultureHelmet.setItemMeta(lam);
	VultureHelmet.addEnchantment(Protect, 3);
	Kp.getInventory().setHelmet(VultureHelmet);
	Kp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
	Kp.getInventory().addItem(Is);
	Kp.getInventory().setItem(35, Bowl);
	Kp.getInventory().setItem(8, Feather);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(Soup);}
	Kp.updateInventory();
	return false;
	}

	@SuppressWarnings("deprecation")
	public boolean Pyro(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	Kp.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
	Kp.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
	ItemStack Ia = new ItemStack(Material.IRON_AXE);
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment FireBow = new EnchantmentWrapper(50);
	Enchantment Infinity = new EnchantmentWrapper(51);
	ItemMeta renameMeta = Ia.getItemMeta();
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta.setDisplayName("§4Fire Axe");
	renameMeta1.setDisplayName("§4Fire Bow");
	Ia.setItemMeta(renameMeta);
	Bow.setItemMeta(renameMeta1);
	Ia.addUnsafeEnchantment(Sharpness, 2);
	Bow.addUnsafeEnchantment(FireBow, 1);
	Bow.addEnchantment(Infinity, 1);
	Kp.getInventory().addItem(Ia);
	Kp.getInventory().addItem(Bow);
	Kp.getInventory().setItem(27, new ItemStack(Material.ARROW, 1));
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 32; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Tank(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack TNT = new ItemStack(Material.TNT, 15);
	ItemMeta TNTMeta = TNT.getItemMeta();
	TNTMeta.setDisplayName("§4TNT");
	ArrayList<String> TNTLore = new ArrayList<String>();
	TNTLore.add("§5Right Click to throw the TNT");
	TNTMeta.setLore(TNTLore);
	TNT.setItemMeta(TNTMeta);
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Thorns = new EnchantmentWrapper(7);
	ItemMeta renameMeta = Is.getItemMeta();
	renameMeta.setDisplayName("§8Sword of Defense");
	Is.setItemMeta(renameMeta);
	ItemStack TankHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) TankHelmet.getItemMeta();
	lam.setColor(Color.AQUA);
	TankHelmet.setItemMeta(lam);
	TankHelmet.addEnchantment(Thorns, 1);
	Kp.getInventory().setHelmet(TankHelmet);
	Kp.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	Kp.getInventory().addItem(Is);
	Kp.getInventory().setItem(8, TNT);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean Bishop(Player Kp){
		Kp.getInventory().clear();
		Kp.getInventory().clear();
		Kp.getInventory().setHelmet(null);
		Kp.getInventory().setChestplate(null);
		Kp.getInventory().setLeggings(null);
		Kp.getInventory().setBoots(null);
		Kp.setHealth(20);
		Kp.setFoodLevel(17);
		for (PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
		ItemStack Chest = new ItemStack(Material.GOLD_CHESTPLATE);
		Enchantment Sharpness = new EnchantmentWrapper(16);
		Enchantment Protect = new EnchantmentWrapper(0);
		Chest.addUnsafeEnchantment(Protect, 7);
		ItemStack TNT = new ItemStack(373, 1);
		ItemMeta TNTMeta = TNT.getItemMeta();
		TNTMeta.setDisplayName("§bSecret Potion");
		ArrayList<String> TNTLore = new ArrayList<String>();
		TNTLore.add("§aRight Click to get random potion effect");
		TNTMeta.setLore(TNTLore);
		TNT.setItemMeta(TNTMeta);
		ItemStack Ds = new ItemStack(Material.IRON_SWORD);
		ItemStack Bowl = new ItemStack(Material.BOWL);
		ItemMeta renameMeta = Ds.getItemMeta();
		renameMeta.setDisplayName("§fGodness Sword");
		Ds.setItemMeta(renameMeta);
		Ds.addEnchantment(Sharpness, 2);
		TNT = addGlow(TNT);
		Kp.getInventory().setHelmet(new ItemStack(Material.DAYLIGHT_DETECTOR));
		Kp.getInventory().setChestplate(Chest);
		Kp.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
		Kp.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
		Kp.getInventory().addItem(Ds);
		Kp.getInventory().setItem(8, TNT);
		Kp.getInventory().setItem(35, Bowl);
		for (int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
		Kp.updateInventory();
		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean Spy(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	ItemStack Gs = new ItemStack(Material.GOLD_SWORD);
	ItemStack DeadR = new ItemStack(Material.getMaterial(347));
	ItemStack Egg = new ItemStack(383, 1, (short)56);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	ItemStack Sapper = new ItemStack(Material.getMaterial(137), 1);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Break = new EnchantmentWrapper(34);
	ItemMeta renameMeta = Gs.getItemMeta();
	ItemMeta renameMeta1 = Sapper.getItemMeta();
	ItemMeta renameMeta2 = DeadR.getItemMeta();
	ItemMeta EggM = Egg.getItemMeta();
	EggM.setDisplayName("§7Egg of Invisibility");
	renameMeta.setDisplayName("§8Little Stabby");
	renameMeta2.setDisplayName("§eDead Ringer");
	renameMeta1.setDisplayName("§b§kKnig §aPlayer Sapper §b§kKnig");
	Gs.setItemMeta(renameMeta);
	DeadR.setItemMeta(renameMeta2);
	Egg.setItemMeta(EggM);
	Sapper.setItemMeta(renameMeta1);
	Gs.addEnchantment(Sharpness, 3);
	Gs.addUnsafeEnchantment(Break, 5);
	DeadR = addGlow(DeadR);
	Sapper = addGlow(Sapper);
	Egg = addGlow(Egg);
	Kp.getInventory().addItem(Gs);
	Kp.getInventory().addItem(Sapper);
	Kp.getInventory().setItem(8, DeadR);
	Kp.getInventory().setItem(7, Egg);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 31; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}

	@SuppressWarnings("deprecation")
	public boolean Dwarf(Player Kp){
	Enchantment Protect = new EnchantmentWrapper(0);
	Enchantment Break = new EnchantmentWrapper(34);
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET);
	ItemStack Chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
	ItemStack Leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
	ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS);
	Helmet.addEnchantment(Protect, 1);
	Chest.addEnchantment(Break, 3);
	Leg.addEnchantment(Break, 3);
	Boots.addEnchantment(Protect, 1);
	Kp.getInventory().setHelmet(Helmet);
	Kp.getInventory().setChestplate(Chest);
	Kp.getInventory().setLeggings(Leg);
	Kp.getInventory().setBoots(Boots);
	ItemStack Is = new ItemStack(Material.DIAMOND_AXE);
	ItemStack Tp = new ItemStack(Material.STONE_AXE, 25);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	ItemMeta renameMeta = Is.getItemMeta();
	ItemMeta renameMeta4 = Tp.getItemMeta();
	renameMeta.setDisplayName("§aDwarven Axe");
	renameMeta4.setDisplayName("§7Throwing Axe");
	Is.setItemMeta(renameMeta);
	Tp.setItemMeta(renameMeta4);
	Is.addEnchantment(Sharpness, 2);
	Is.addUnsafeEnchantment(Break, 5);
	Tp = addGlow(Tp);
	Kp.getInventory().addItem(Is);
	Kp.getInventory().setItem(8, Tp);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean WitherArcher(Player Kp){
	ItemStack Bow = new ItemStack(Material.BOW, 1);
	ItemMeta TNTMeta = Bow.getItemMeta();
	TNTMeta.setDisplayName("§8§lWither Bow");
	ArrayList<String> TNTLore = new ArrayList<String>();
	TNTLore.add("§fShoot wither skulls");
	TNTMeta.setLore(TNTLore);
	Bow.setItemMeta(TNTMeta);
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Ds = new ItemStack(Material.IRON_SWORD);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(17);
	Enchantment Infinity = new EnchantmentWrapper(51);
	ItemMeta renameMeta = Ds.getItemMeta();
	renameMeta.setDisplayName("§8Wither Sword");
	Ds.setItemMeta(renameMeta);
	Ds.addEnchantment(Sharpness, 2);
	Bow.addEnchantment(Infinity, 1);
	ItemStack ArcherChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) ArcherChest.getItemMeta();
	lam.setColor(Color.BLACK);
	ArcherChest.setItemMeta(lam);
	Kp.getInventory().setHelmet(new ItemStack(Material.SKULL_ITEM, 1, (byte) 1));
	Kp.getInventory().setChestplate(ArcherChest);
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Kp.getInventory().addItem(Ds);
	Kp.getInventory().setItem(1, Bow);
	Kp.getInventory().setItem(35, Bowl);
	Kp.getInventory().setItem(27, new ItemStack(Material.ARROW, 1));
	for(int Sn = 0; Sn < 32; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}

	@SuppressWarnings("deprecation")
	public boolean Sniper(Player Kp){
	Kp.getInventory().clear();
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Ds = new ItemStack(Material.IRON_SWORD);
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	ItemMeta renameMeta = Ds.getItemMeta();
	renameMeta.setDisplayName("§fKukri");
	Ds.setItemMeta(renameMeta);
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta1.setDisplayName("§8Sniper Rifle");
	ArrayList<String> CobraLore = new ArrayList<String>();
	CobraLore.add("§5Left Click to zoom");
	CobraLore.add("§5Right Click to shoot");
	renameMeta1.setLore(CobraLore);
	Bow.setItemMeta(renameMeta1);
	Ds.addEnchantment(Sharpness, 2);
	ItemStack ArcherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	LeatherArmorMeta lam = (LeatherArmorMeta) ArcherHelmet.getItemMeta();
	lam.setColor(Color.fromRGB(164, 164, 164));
	ArcherHelmet.setItemMeta(lam);
	Kp.getInventory().setHelmet(ArcherHelmet);
	Kp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	Kp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Kp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Kp.getInventory().addItem(Ds);
	Kp.getInventory().addItem(Bow);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}

	@SuppressWarnings("deprecation")
	public boolean Chicken(Player Kp){
	Kp.getInventory().clear();
	Kp.getInventory().clear();
	Kp.getInventory().setHelmet(null);
	Kp.getInventory().setChestplate(null);
	Kp.getInventory().setLeggings(null);
	Kp.getInventory().setBoots(null);
	Kp.setHealth(20);
	Kp.setFoodLevel(17);
	Enchantment Protect = new EnchantmentWrapper(0);
	for(PotionEffect effect : Kp.getActivePotionEffects())Kp.removePotionEffect(effect.getType());
	ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET, 1);
	ItemStack DevilChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	ItemStack DevilPants = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	ItemStack DevilBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
	LeatherArmorMeta lam0 = (LeatherArmorMeta) Helmet.getItemMeta();
	LeatherArmorMeta lam1 = (LeatherArmorMeta) DevilChest.getItemMeta();
	LeatherArmorMeta lam2 = (LeatherArmorMeta) DevilPants.getItemMeta();
	LeatherArmorMeta lam3 = (LeatherArmorMeta) DevilPants.getItemMeta();
	lam0.setColor(Color.YELLOW);
	lam1.setColor(Color.YELLOW);
	lam2.setColor(Color.YELLOW);
	lam3.setColor(Color.YELLOW);
	Helmet.setItemMeta(lam0);
	DevilChest.setItemMeta(lam1);
	DevilPants.setItemMeta(lam2);
	DevilBoots.setItemMeta(lam3);
	Helmet.addUnsafeEnchantment(Protect, 5);
	Kp.getInventory().setHelmet(Helmet);
	Kp.getInventory().setChestplate(DevilChest);
	Kp.getInventory().setLeggings(DevilPants);
	Kp.getInventory().setBoots(DevilBoots);
	ItemStack Is = new ItemStack(Material.IRON_SWORD);
	ItemStack Tp = new ItemStack(Material.EGG, 8);
	ItemStack Bowl = new ItemStack(Material.BOWL);
	Enchantment Sharpness = new EnchantmentWrapper(16);
	Enchantment Break = new EnchantmentWrapper(34);
	ItemMeta renameMeta = Is.getItemMeta();
	ItemMeta renameMeta4 = Tp.getItemMeta();
	renameMeta.setDisplayName("§cThe Chicken Nugget");
	renameMeta4.setDisplayName("§eEgg");
	Is.setItemMeta(renameMeta);
	Tp.setItemMeta(renameMeta4);
	Is.addEnchantment(Sharpness, 1);
	Is.addUnsafeEnchantment(Break, 3);
	Tp = addGlow(Tp);
	Kp.getInventory().addItem(Is);
	Kp.getInventory().setItem(8, Tp);
	Kp.getInventory().setItem(35, Bowl);
	for(int Sn = 0; Sn < 33; Sn++){Kp.getInventory().addItem(new ItemStack(282));}
	Kp.updateInventory();
	return false;
	}

	@EventHandler
	public void PyroLava(PlayerMoveEvent e){
	Player p = e.getPlayer();
	Material m = e.getPlayer().getLocation().getBlock().getType();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(m == Material.STATIONARY_LAVA || m == Material.LAVA){
	if (p.getInventory().getHelmet() != null && p.getInventory().contains(new ItemStack(Material.ARROW, 1))){
	if(p.getInventory().getHelmet().getType() == Material.GOLD_HELMET){
    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0));
	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0));
	p.setFireTicks(0);}}}}
	}

	@EventHandler
	public void ScorpionWater(PlayerMoveEvent e){
	Player p = e.getPlayer();
	Material m = e.getPlayer().getLocation().getBlock().getType();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(m == Material.STATIONARY_WATER || m == Material.WATER){
	if(p.getInventory().getHelmet() != null){
	if(p.getInventory().getChestplate() != null){
	if(p.getInventory().getLeggings() != null){
	if(p.getInventory().getBoots() != null){
	if(p.getInventory().getHelmet().getType() == Material.IRON_HELMET){
	if(p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE){
	if(p.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS){
	if(p.getInventory().getBoots().getType() == Material.IRON_BOOTS){
	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0));
	p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0));
	}}}}}}}}}}
	}
	
	@EventHandler
	public void PyroHit(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player) {
	if(e.getDamager() instanceof Player) {
	Player D = (Player) e.getDamager();
	Player E = (Player) e.getEntity();
    RegionManager regionManager = WG.getRegionManager(E.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(E.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(D.getInventory().getHelmet() != null){
	if(D.getInventory().getHelmet().getType() == Material.GOLD_HELMET) {
	if(E.getFireTicks() > 0){
    double Da = e.getDamage();
	e.setDamage((Da + (Da * 0.2)));}}}}}}
	}

	@EventHandler
	public void NinjaEmerald(PlayerInteractEvent e){
	Player p = e.getPlayer();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getTypeId() == 388) {
	PlayerCooldown pc = Cooldown.getCooldown("Ninja", p.getName());
	if(pc == null){
	p.playSound(p.getLocation(), Sound.AMBIENCE_CAVE, 5, 1);
	Vector v = p.getLocation().getDirection().multiply(3).setY(3.2);
	p.setVelocity(v);
	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
	Cooldown.addCooldown("Ninja", p.getName(), 18000);
	}else{
	final int Time = pc.getTimeLeft() / 1000;
	if(pc.isOver()){
	p.playSound(p.getLocation(), Sound.AMBIENCE_CAVE, 5, 1);	
	Vector v = p.getLocation().getDirection().multiply(3).setY(3.5);
	p.setVelocity(v);
	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
	pc.reset();
	}else if(Time > 0){
	p.sendMessage("§cYour cooldown will over in " + Time + " seconds!");}}}}}}
	}
	
	@EventHandler
	public void VultureJump(PlayerInteractEvent e){
	Player p = e.getPlayer();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getTypeId() == 288) {
	PlayerCooldown pc = Cooldown.getCooldown("Vulture", p.getName());
	if(pc == null){
	p.playSound(p.getLocation(), Sound.CLICK, 5, 1);	
	Vector v = p.getLocation().getDirection().multiply(3).setY(-2);
	p.setVelocity(v);
	Cooldown.addCooldown("Vulture", p.getName(), 10000);
	}else{
	final int Time = pc.getTimeLeft() / 1000;
	if(pc.isOver()){
	p.playSound(p.getLocation(), Sound.CLICK, 5, 1);	
	Vector v = p.getLocation().getDirection().multiply(3).setY(-2);
	p.setVelocity(v);
	pc.reset();
	}else if(Time > 0){
	p.sendMessage("§cYour cooldown will over in " + Time + " seconds!");
	}}}}}}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void TankTNT(PlayerInteractEvent event){
	final Player p = event.getPlayer();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	ItemStack item = p.getItemInHand();
	if(event.getAction() == Action.RIGHT_CLICK_AIR) {
	if(p.getItemInHand() != null && p.getGameMode() != GameMode.CREATIVE) {
	if(p.getItemInHand().getType() == Material.TNT){
	final Item grenade = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.TNT));
	grenade.setVelocity(event.getPlayer().getEyeLocation().getDirection());
	if(item.getAmount() > 1) {item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run() {
	grenade.getWorld().createExplosion(grenade.getLocation(), (float) 4.0,false);
	grenade.remove();
	for(Entity en : grenade.getNearbyEntities(6, 5, 6)){
	if(en instanceof Player){
	Player Ap = (Player) en;
	Random R = new Random();
	Vector v = Ap.getLocation().getDirection().multiply(0).setY(R.nextInt(4) + 1);
	Ap.damage(0.2, p);
	FallDamage.add(Ap.getName());
	Ap.setVelocity(v);}}}
	}, 40L);}}}}}}
	}

	@EventHandler
	public void TankFall(EntityDamageEvent e){
	if(e.getEntity() instanceof Player){
	Player p = (Player) e.getEntity();
	if(FallDamage.contains(p.getName())){
	FallDamage.remove(p.getName());
	e.setCancelled(true);}}
	}
	
	@EventHandler(ignoreCancelled = false)
	public void HeadShot(EntityDamageByEntityEvent e){
	if(e.getCause() != DamageCause.PROJECTILE)
	return;
	Projectile proj = (Projectile) e.getDamager();
	if(!(proj.getShooter() instanceof Player))
	return;
	Entity shot = e.getEntity();
	double y = proj.getLocation().getY();
	double shoty = shot.getLocation().getY();
	boolean headshot = y - shoty > 1.35d;
	if(headshot){
	if(proj.getShooter() instanceof Player){
	if(shot instanceof Player){
	Player D = (Player) proj.getShooter();
	if(D.getInventory().getHelmet() != null){
	if(D.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) {
    e.setDamage((e.getDamage() * 1.4));
	D.playSound(D.getLocation(), Sound.NOTE_PLING, 5, 1);}}}}}
	}

	@EventHandler
	public void CobraDye(PlayerMoveEvent e){
	Player p = e.getPlayer();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(p.getItemInHand().getTypeId() == 351){
	if(p.getItemInHand().getDurability() == (short)2){
	for(Player Ap : Bukkit.getOnlinePlayers()){
	if(Ap != p){
	if(Ap.getLocation().distance(p.getLocation()) <= 7){
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 160, 0));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));}}}}}}
	}
	
	@EventHandler
	public void Cobra(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	if(e.getDamager() instanceof Player){
	Player E = (Player) e.getEntity();
	Player D = (Player) e.getDamager();
    RegionManager regionManager = WG.getRegionManager(E.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(E.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(D.getItemInHand().getTypeId() == 267){
	if(D.getInventory().getHelmet() != null && D.getInventory().getHelmet().getType() == Material.IRON_HELMET){
	if(D.getInventory().getBoots() != null && D.getInventory().getBoots().getType() == Material.IRON_BOOTS){ 
	if(D.getInventory().getChestplate() != null && D.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE){
	Random R = new Random();
	int Rc = R.nextInt(2) + 1;
	if(Rc == 1){
	E.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
	if(E.getItemInHand().getTypeId() == 267) {
	if(E.getInventory().getHelmet() != null && E.getInventory().getHelmet().getType() == Material.IRON_HELMET) {
	if(E.getInventory().getBoots() != null && E.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
	if(E.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) {
    E.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,60, 0));}}}}}}}}}}}}
	}

	@EventHandler
	public void NinjaPyroDamage(EntityDamageEvent e){
	ItemStack E = new ItemStack(Material.EMERALD, 1);
	ItemMeta renameMeta3 = E.getItemMeta();
	renameMeta3.setDisplayName("§aEmerald");
	E.setItemMeta(renameMeta3);
	E = addGlow(E);
	if(e.getCause() == DamageCause.FALL){
	if(e.getEntity() instanceof Player){
	Player p = (Player) e.getEntity();
	if(p.getInventory().contains(E)){
	e.setCancelled(true);}}}
	if(e.getCause() == DamageCause.LAVA || e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK) {
	if(e.getEntity() instanceof Player){
	Player p = (Player) e.getEntity();
	if(p.getInventory().getHelmet() != null){
	if(p.getInventory().getHelmet().getTypeId() == 314){
    e.setCancelled(true);
	}}}}
	}

	@EventHandler
	public void FisherMan(PlayerFishEvent e){
	final Player F = e.getPlayer();
	F.getItemInHand().setDurability((short) 0);
	if(e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY){
	if(e.getCaught().getType() == EntityType.PLAYER){
	final Player C = (Player) e.getCaught();
	C.teleport(F.getLocation());
	C.playSound(C.getLocation(), Sound.WATER, 5, 1);
	F.playSound(F.getLocation(), Sound.WATER, 5, 1);
	Fisher.add(F.getName());
	Fished.add(C.getName());
	C.sendMessage("§6§lYou have been fished! You can't attack the Scorpion for 2 seconds");
	F.sendMessage("§6§lYou can't attack your target for 2 seconds!");
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
	public void run(){
	Fisher.remove(F.getName());
	Fished.remove(C.getName());}
	}, 50L);}}
	}

	@EventHandler
	public void FisherHit(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	if(e.getDamager() instanceof Fish){
	Player D = (Player) e.getEntity();
	if(Fished.contains(D.getName())){
	e.setCancelled(true);}}
	if(e.getDamager() instanceof Player){
	Player D = (Player) e.getDamager();
	Player V = (Player) e.getEntity();
	if((Fisher.contains(D.getName()) && Fished.contains(V.getName())) || (Fisher.contains(V.getName()) && Fished.contains(D.getName()))){
	e.setCancelled(true);}}}
	}
	
	@EventHandler
	public void LovleyBow(EntityDamageByEntityEvent e){
	if(e.getDamager() instanceof Arrow){
	Arrow a = (Arrow) e.getDamager();
	if (a.getShooter() instanceof Player) {
	Player p = (Player) a.getShooter();
	if(e.getEntity() instanceof Player){
	final Player D = (Player) e.getEntity();
    if(p.getInventory().getBoots() != null){
	if(p.getInventory().getBoots().getTypeId() == 301){
	Location l = D.getLocation();
	final LivingEntity kludgeE = (LivingEntity) D.getWorld().spawnEntity(l, EntityType.WOLF);
	kludgeE.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 100));
	kludgeE.teleport(D.getLocation());
	this.getServer().getScheduler().runTaskLater(this, new Runnable() {
	public void run() {
	kludgeE.playEffect(EntityEffect.WOLF_HEARTS);
	kludgeE.playEffect(EntityEffect.WOLF_HEARTS);
	D.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
	D.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
	D.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
	kludgeE.remove();}
	}, 3);}}}}}
	}

	@EventHandler
	public void ShiftEffects(PlayerMoveEvent e){
	final Player p = e.getPlayer();
	if(p.getInventory().getHelmet() != null && p.isSneaking()){
	if(p.isSneaking() && p.getItemInHand().getType() == Material.GOLD_SWORD && p.getInventory().getHelmet().getTypeId() == 302){
	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 2));}}
	if(p.getInventory().getChestplate() != null){
	if(p.isSneaking() && p.getItemInHand().getType() == Material.IRON_SWORD && p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE){
	p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 0));}}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void HealthPack(PlayerInteractEvent e){
	final Player p = e.getPlayer();
	ItemStack item = e.getPlayer().getItemInHand();
	if(e.getAction() == Action.RIGHT_CLICK_AIR){
	if(p.getItemInHand() != null){
	if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	if(p.getItemInHand().getType() == Material.GLOWSTONE && p.getGameMode() != GameMode.CREATIVE){
	if(item.getAmount() > 1){item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}
	final Item grenade = p.getWorld().dropItem(p.getEyeLocation(),new ItemStack(Material.GLOWSTONE, 1));
	grenade.setVelocity(e.getPlayer().getEyeLocation().getDirection());
	p.getInventory().removeItem(grenade.getItemStack());
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run(){
	int Gy = grenade.getLocation().getBlockY();
	for(int Ti = 0; Ti < 4; Ti++, Gy++){
	Location GLoc = new Location(p.getWorld(), grenade.getLocation().getBlockX(), Gy ,grenade.getLocation().getBlockZ());
    grenade.getWorld().playEffect(GLoc, Effect.POTION_BREAK, 1);}
	grenade.remove();
	for(Player Ap : Bukkit.getOnlinePlayers()){
	if(Ap.getLocation().distance(grenade.getLocation()) <= 6){
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 5));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 0));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.getById(21), 600, 4));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0));}}}
	}, 60L);}}}}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void DwarfAxe(PlayerInteractEvent e){
	Player p = e.getPlayer();
	AxeP = p;
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	ItemStack item = p.getItemInHand();
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
	if(p.getItemInHand().getType() == Material.STONE_AXE) {
	if(item.getAmount() > 1) {item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}
	p.playSound(p.getLocation(), Sound.ANVIL_LAND, 5, 1);
	final Item Axe = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.STONE_AXE));
	Axe.setVelocity(p.getLocation().getDirection().multiply(2));
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run(){Axe.remove();Axe.getWorld().playEffect(Axe.getLocation(), Effect.SMOKE, 20);}}, 120L);}}}
	}}}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void NinjaShurikan(PlayerInteractEvent e){
	Player p = e.getPlayer();
	ShP = p;
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
	ItemStack item = p.getItemInHand();
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
	if(p.getItemInHand().getType() == Material.NETHER_STAR) {
	if(item.getAmount() > 1) {item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}
	p.playSound(p.getLocation(), Sound.STEP_WOOL, 5, 1);
	final Item Sh = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.NETHER_STAR));
	Sh.setVelocity(p.getLocation().getDirection().multiply(2));
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run(){Sh.remove();Sh.getWorld().playEffect(Sh.getLocation(), Effect.SMOKE, 20);}}, 120L);}}}}}
	}
	
	@EventHandler
	public void AxeHit(PlayerMoveEvent e){
	Player p = e.getPlayer();
	if(p.getGameMode() != GameMode.CREATIVE){
	for(Entity En : p.getNearbyEntities(1, 1, 1)){
	if(En.getType() == EntityType.DROPPED_ITEM){
	if(En instanceof Item){
	if(((Item) En).getItemStack().getType() == Material.STONE_AXE){
	if(AxeP != p){
	En.remove();
	p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
	Vector v = p.getLocation().getDirection().multiply(-3);
	p.setVelocity(v);}}}}}}
	}

	@EventHandler
	public void ShurikanHit(PlayerMoveEvent e){
	Player p = e.getPlayer();
	if(p.getGameMode() != GameMode.CREATIVE){
	for(Entity En : p.getNearbyEntities(1, 1, 1)){
	if(En.getType() == EntityType.DROPPED_ITEM){
	if(En instanceof Item){
	if(((Item) En).getItemStack().getType() == Material.NETHER_STAR){
	if(ShP != p){
	En.remove();
	p.playSound(p.getLocation(), Sound.DIG_GRAVEL, 5, 1);
    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 160, 0));
	p.damage(4.1, ShP);}}}}}}
	}
	
	@EventHandler
	public void WitherArcherArrow(ProjectileLaunchEvent e){
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
	if(e.getEntity() instanceof Arrow){
	if(e.getEntity().getShooter() instanceof Player) {
	Player p = (Player) e.getEntity().getShooter();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(p.getInventory().getHelmet() != null) {
	if(p.getInventory().getHelmet().equals(Skull)) {
	e.getEntity().remove();
	PlayerCooldown pc = Cooldown.getCooldown("DWA", p.getName());
	if(pc == null){
	WitherSkull WitherSkull = p.launchProjectile(WitherSkull.class);
	WitherSkull.setShooter(p);
	WitherSkull.setVelocity(p.getLocation().getDirection().multiply(1));
	p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 5, 1);
	e.getEntity().remove();
	Cooldown.addCooldown("DWA", p.getName(), 1000);
	}else{
	final int Time = pc.getTimeLeft() / 1000;
	if(pc.isOver()){
	WitherSkull WitherSkull = p.launchProjectile(WitherSkull.class);
	WitherSkull.setShooter(p);
    WitherSkull.setVelocity(p.getLocation().getDirection().multiply(1));
    p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 5, 1);
	e.getEntity().remove();
	pc.reset();
	}else if(Time > 0){
	p.sendMessage("§cYour cooldown will over in " + Time + " seconds!");}}}}}}
	}
	}

	@EventHandler
	public void WitherSkullPotion(ProjectileHitEvent e){
	if(e.getEntity() instanceof WitherSkull){
	if(e.getEntity().getShooter() instanceof Player){
	for(Player Ap : Bukkit.getOnlinePlayers()){
	if(e.getEntity().getLocation().distance(Ap.getLocation()) <= 3){
	if(Ap != e.getEntity().getShooter()){
	Ap.playSound(Ap.getLocation(), Sound.WITHER_HURT, 5, 1);
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 0));}}}}}
	}

	@EventHandler
	public void WitherArcherHit(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	if (e.getDamager() instanceof Player){
	Player V = (Player) e.getEntity();
	if(V.hasPotionEffect(PotionEffectType.WITHER)){
	double Da = e.getDamage();
	e.setDamage((Da * 2));}}}
	}

	@EventHandler
	public void SapperSpy(PlayerInteractEntityEvent e){
	Player p = e.getPlayer();
    RegionManager regionManager = WG.getRegionManager(p.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(p.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	Entity entity = e.getRightClicked();
	if(p.getItemInHand().getTypeId() == 137 && p.getGameMode() != GameMode.CREATIVE) {
	if(entity instanceof Player == true) {
	p.setItemInHand(null);
	Player V = (Player) entity;
	V.playSound(V.getLocation(), Sound.ENDERDRAGON_DEATH, 5, 1);
	V.sendMessage("§kblabla  §aSapped By " + p.getName() + " §f§kblabla");
	V.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 1));
	V.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));}}}
	}

	@EventHandler
	public void SpyBackStab(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player) {
	if(e.getDamager() instanceof Player){
	Player D = (Player) e.getDamager();
	Player V = (Player) e.getEntity();
    RegionManager regionManager = WG.getRegionManager(V.getWorld());
    ApplicableRegionSet EggPvP = regionManager.getApplicableRegions(V.getLocation());
    if(EggPvP.allows(DefaultFlag.PVP)){
	if(D.getItemInHand().getType() == Material.GOLD_SWORD || D.getInventory().getHelmet() == null) {
	float Vyaw = V.getLocation().getYaw();
	float Dyaw = D.getLocation().getYaw();
	if((Vyaw - Dyaw < 60) && (Vyaw - Dyaw > -60)) {
	double Damage = e.getDamage();
	e.setDamage((Damage * 3));
	D.playSound(D.getLocation(), Sound.ORB_PICKUP, 5, 1);}}}}}
	}

	@EventHandler
	public void DeadRinger(PlayerDeathEvent e){
	String Dm = e.getDeathMessage();
	e.getDrops().clear();
	e.setDeathMessage("");
	if(Dm.contains("fell from a high place")){
	e.setDeathMessage("Apperantly,§c " + e.getEntity().getName() + "§f didn't knew that he cant walk on air.");}
	if(Dm.contains("fell out of the world")){
	e.setDeathMessage("§c" + e.getEntity().getName() + "§f went to the dark side of the server.");}
	if(e.getEntity().getKiller() instanceof Player){
	Player D = e.getEntity().getPlayer();
	Player K = e.getEntity().getKiller();
	int CoinsK = this.getConfig().getInt(K.getName() + " coin");
	if(K.hasPermission("Vip.Kill") || K.isOp()){
	this.getConfig().set(K.getName() + " coin", CoinsK + 2);
	}else{this.getConfig().set(K.getName() + " coin", CoinsK + 1);}
	if(K != D){
	if(D.getItemInHand().getTypeId() != 347){
	Bukkit.broadcastMessage(ChatColor.GREEN + K.getName() + "§f killed §c" + D.getName());}
	else{K.sendMessage(ChatColor.GREEN + K.getName() + "§f killed §c" + D.getName());}}}
	if(e.getEntity() instanceof Player){
	final Player V = e.getEntity();
	if(V.getItemInHand().getTypeId() == 347){
	V.setItemInHand(null);
	ItemStack[] content = V.getInventory().getContents();
	items.put(V.getName(), content);
	Location loc = new Location(V.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY() + 1, V.getLocation().getBlockZ() + 2);
    V.setHealth(20.0);
	V.setFireTicks(0);
	V.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 0));
	V.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
	V.teleport(loc);
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	public void run(){
	V.setHealth(20.0);
	for(ItemStack stack : items.get(V.getName())){
	if(stack != null){
	V.getInventory().addItem(stack);}}
	items.remove(V.getName());
	V.getInventory().setItem(8,new ItemStack(Material.AIR));}}, 20L);}}
	if(e.getEntity() instanceof Player){
    final Player E = e.getEntity();
	int Di = Deaths.getInt(E.getName() + " deaths");
	Deaths.set(E.getName() + " deaths",Di + 1);
	if(e.getEntity().getKiller() instanceof Player){
	Player D = e.getEntity().getKiller();
    int Ki = Kills.getInt(D.getName() + " kills");;
    Kills.set(D.getName() + " kills",Ki + 1);}}
	//saveKills();
	//saveDeaths();
	//this.saveConfig();
	}	

	@EventHandler
	public void GhastSpawn(PlayerInteractEvent e){
	Player p = e.getPlayer();
	if(p.getItemInHand() != null){
	if(p.getItemInHand().getTypeId() == 383 && p.getItemInHand().getTypeId() != 282){
	if(!p.isOp()){e.setCancelled(true);}}}
	}
	
	@EventHandler
	public void SpyEgg(PlayerMoveEvent e){
	Player p = e.getPlayer();
	if(p.getItemInHand() != null){
	if(p.getItemInHand().getTypeId() == 383 && p.getGameMode() != GameMode.CREATIVE){
	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 1));}}
	}
	
	@EventHandler
	public void ZoomRemove(PlayerMoveEvent e){
	Player p = e.getPlayer();
	double xfrom = e.getFrom().getX();
	double zfrom = e.getFrom().getZ();
	double xto = e.getTo().getX();
	double zto = e.getTo().getZ();
	if(!(xfrom == xto && zfrom == zto)){
	for(PotionEffect PE : p.getActivePotionEffects()){
	if(PE.getType().equals(PotionEffectType.SLOW)){
	if(PE.getAmplifier() == 6){
	p.removePotionEffect(PotionEffectType.SLOW);}}}}
	}
	
    @EventHandler
    public void SniperZoom(PlayerInteractEvent e){
    Player p = e.getPlayer();
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta1.setDisplayName("§8Sniper Rifle");
	ArrayList<String> CobraLore = new ArrayList<String>();
	CobraLore.add("§5Left Click to zoom");
	CobraLore.add("§5Right Click to shoot");
	renameMeta1.setLore(CobraLore);
	Bow.setItemMeta(renameMeta1);
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
    if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
    if(p.getItemInHand().getType() == Material.BOW){
    if(p.getInventory().contains(Bow)){
    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600000, 6));}}}}}
    }
    
    @EventHandler
    public void SniperShoot(PlayerInteractEvent e){
    Player p = e.getPlayer();
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta1.setDisplayName("§8Sniper Rifle");
	ArrayList<String> CobraLore = new ArrayList<String>();
	CobraLore.add("§5Left Click to zoom");
	CobraLore.add("§5Right Click to shoot");
	renameMeta1.setLore(CobraLore);
	Bow.setItemMeta(renameMeta1);
    if(p.getItemInHand().getType() != null){
    if(p.getItemInHand().getType() != Material.MUSHROOM_SOUP){
    if(p.getInventory().contains(Bow)){
    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
    if(p.getItemInHand().getType() == Material.BOW){
    PlayerCooldown pc = Cooldown.getCooldown("Sniper", p.getName());
    if(pc == null){
    p.playEffect(p.getEyeLocation(), Effect.SMOKE, 1);
    p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
    Snowball SnowBall = p.launchProjectile(Snowball.class);
    SnowBall.setShooter(p);
    SnowBall.setVelocity(p.getLocation().getDirection().multiply(5));
    Cooldown.addCooldown("Sniper", p.getName(), 1300);
    }else{
    final int Time = pc.getTimeLeft() / 1000;
    if(pc.isOver()){
    p.playEffect(p.getEyeLocation(), Effect.SMOKE, 1);
    p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
    Snowball SnowBall = p.launchProjectile(Snowball.class);
    SnowBall.setShooter(p);
    SnowBall.setVelocity(p.getLocation().getDirection().multiply(5));
    pc.reset();
    }else if(Time > 0){}}
    }
    }
    }
    }
    }
    }
    
    @EventHandler
    public void SniperHit(EntityDamageByEntityEvent e){
	ItemStack Bow = new ItemStack(Material.BOW);
	ItemMeta renameMeta1 = Bow.getItemMeta();
	renameMeta1.setDisplayName("§8Sniper Rifle");
	ArrayList<String> CobraLore = new ArrayList<String>();
	CobraLore.add("§5Left Click to zoom");
	CobraLore.add("§5Right Click to shoot");
	renameMeta1.setLore(CobraLore);
	Bow.setItemMeta(renameMeta1);
    if(e.getEntity() instanceof Player){
    if(e.getDamager() instanceof Snowball){
    final Player p = (Player) e.getEntity();
    Snowball S = (Snowball) e.getDamager();
    if(S.getShooter() instanceof Player){
    final Player Ps = (Player) S.getShooter();
    S.remove();
    double Distance = p.getLocation().distance(Ps.getLocation());
    if(Ps.getInventory().contains(Bow)){
    e.setDamage(0.0);
    p.damage((Distance*0.7), Ps);
    }}}}}
    
    @EventHandler
    public void BishopPotion(PlayerInteractEvent e){
    Player p = e.getPlayer();
    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
    if(p.getItemInHand().getTypeId() == 373 && p.getInventory().getHelmet() != null){
    if(p.getInventory().getHelmet().getType() == Material.DAYLIGHT_DETECTOR){
    e.setCancelled(true);
    PlayerCooldown pc = Cooldown.getCooldown("Bishop", p.getName());
    if(pc == null){
    List<PotionEffectType> Pe = Arrays.asList(PotionEffectType.SPEED,PotionEffectType.DAMAGE_RESISTANCE,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.JUMP,PotionEffectType.BLINDNESS,PotionEffectType.CONFUSION,PotionEffectType.POISON,PotionEffectType.SLOW,PotionEffectType.WATER_BREATHING,PotionEffectType.WEAKNESS,PotionEffectType.WITHER);
    Random Per = new Random();
    p.addPotionEffect(new PotionEffect(Pe.get(Per.nextInt(Pe.size())), 300, 0));	
    p.playSound(p.getLocation(), Sound.BREATH, 5, 1);
    Cooldown.addCooldown("Bishop", p.getName(), 20000);
    }else{
    final int Time = pc.getTimeLeft() / 1000;
    if(pc.isOver()){
    List<PotionEffectType> Pe = Arrays.asList(PotionEffectType.SPEED,PotionEffectType.DAMAGE_RESISTANCE,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.JUMP,PotionEffectType.BLINDNESS,PotionEffectType.CONFUSION,PotionEffectType.POISON,PotionEffectType.SLOW,PotionEffectType.WATER_BREATHING,PotionEffectType.WEAKNESS,PotionEffectType.WITHER);
    Random Per = new Random();
    p.addPotionEffect(new PotionEffect(Pe.get(Per.nextInt(Pe.size())), 360, 0));
    p.playSound(p.getLocation(), Sound.BREATH, 5, 1);
    pc.reset();
    }else if(Time > 0){
    p.sendMessage("§cYour cooldown will over in " + Time + " seconds!");}	
    }}}}
    }
    
    @EventHandler
    public void BishopHit(EntityDamageByEntityEvent e){
    if(e.getEntity() instanceof Player){
    if(e.getDamager() instanceof Player){
    Player D = (Player) e.getDamager();
    Player V = (Player) e.getEntity();
    if(D.getInventory().getHelmet() != null){
    if(D.getInventory().getHelmet().getType() == Material.DAYLIGHT_DETECTOR){
    if(D.getInventory().getItemInHand() != null){
    if(D.getInventory().getItemInHand().getType() == Material.POTION){
    for(PotionEffect pe : D.getActivePotionEffects()){
    PotionEffectType pet = pe.getType();
    V.addPotionEffect(new PotionEffect(pet, 300, 1));
    }}}}}}}
    }
     
    @EventHandler
    public void ChickenBoost(PlayerToggleSneakEvent e){
    Player p = e.getPlayer();
    if(e.isSneaking()){
    if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null){
    if(p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET && p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE){
    PlayerCooldown pc = Cooldown.getCooldown("Ch", p.getName());
    if(pc == null){
    p.playSound(p.getLocation(), Sound.CHICKEN_HURT, 5, 1);
    p.setVelocity(p.getLocation().getDirection().setY(2));
    Cooldown.addCooldown("Ch", p.getName(), 4000);
    }else{
    final int Time = pc.getTimeLeft() / 1000;
    if(pc.isOver()){
    p.playSound(p.getLocation(), Sound.CHICKEN_HURT, 5, 1);
    p.setVelocity(p.getLocation().getDirection().setY(2));
    pc.reset();
    }else if(Time > 0){
    p.sendMessage("§cYour cooldown will over in " + Time + " seconds!");}}
    if(!Chicken.contains(p)){Chicken.add(p.getName());}}}}
    }
    
    @EventHandler
    public void ChickenEgg(PlayerEggThrowEvent e){
    Player p = e.getPlayer();
    if(p.getInventory().getHelmet() != null){
    if(p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET){
    e.setHatching(false);
    Location loc = new Location(Bukkit.getWorld("world"), e.getEgg().getLocation().getX(), e.getEgg().getLocation().getY() + 0.5, e.getEgg().getLocation().getZ());
    final Chicken C1 = (Chicken) p.getWorld().spawnEntity(loc, EntityType.CHICKEN);
    final Chicken C2 = (Chicken) p.getWorld().spawnEntity(loc, EntityType.CHICKEN);
    final Chicken C3 = (Chicken) p.getWorld().spawnEntity(loc, EntityType.CHICKEN);
    C1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 3));
    C2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 3));
    C3.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 3));
    C1.setCustomName("§4Evil Chicken [ §c" + p.getName() + " §4]");
    C2.setCustomName("§4Evil Chicken [ §c" + p.getName() + " §4]");
    C3.setCustomName("§4Evil Chicken [ §c" + p.getName() + " §4]");
    C1.damage(0.01, p);
    C2.damage(0.01, p);
    C3.damage(0.01, p);
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
    public void run(){C1.getWorld().createExplosion(C1.getLocation(), 3);C1.setHealth(0.00);}}, 60L);
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
    public void run(){C2.getWorld().createExplosion(C2.getLocation(), 3);C2.setHealth(0.00);}}, 60L);
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
    public void run(){C3.getWorld().createExplosion(C3.getLocation(), 3);C3.setHealth(0.00);}}, 60L);
    }}
    }
}

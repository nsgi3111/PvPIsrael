package me.Kenig.PVPIL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_6_R2.NBTTagCompound;
import net.minecraft.server.v1_6_R2.NBTTagList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_6_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Event implements Listener, CommandExecutor {
	public static Main plugin; 
	ArrayList<String> Event = new ArrayList<String>();
    private int TDM = -1;
    private int LMS = -1;
    private int Parkour = -1;
    private int b1 = -1;
    private int tg = -1;
    private int tb = -1;
    private int lms2 = -1;
	
    public Event(Main instance){
    plugin = instance;}
    
	private ItemStack setSkin(ItemStack item, String nick) {
	SkullMeta meta = (SkullMeta) item.getItemMeta();
	meta.setOwner(nick);
	item.setItemMeta(meta);
	return item;}
    
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
	
	@EventHandler
	public void TDMMove(PlayerMoveEvent e){
	Player p = e.getPlayer();
	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.LAPIS_BLOCK && p.getLocation().subtract(0, 2, 0).getBlock().getType() == Material.LAPIS_ORE){
	p.chat("/bn leave");}}

	@SuppressWarnings("deprecation")
	public void TBEvent(){
	Location TheBatsLocation = new Location(Bukkit.getWorld("world"), -1722, 50, -1413);
	Location TheBatsBlock = new Location(Bukkit.getWorld("world"), -1722, 50, -1413);
	TheBatsBlock.getBlock().setTypeId(20);
	for(Player Ap : Bukkit.getOnlinePlayers()){
	Ap.teleport(TheBatsLocation);
	ItemStack Bow = new ItemStack(Material.BOW);
	Enchantment Infinity = new EnchantmentWrapper(51);
	Enchantment Break = new EnchantmentWrapper(34);
	Bow.addEnchantment(Infinity, 1);
	Bow.addUnsafeEnchantment(Break, 10);
	Bat Bat = (Bat) Ap.getWorld().spawnCreature(Ap.getLocation(), EntityType.BAT);
	Bat.setPassenger(Ap);
	Ap.getInventory().clear();
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);
	Ap.setHealth(20.00);
	Ap.setFireTicks(0);
	for(PotionEffect effect : Ap.getActivePotionEffects())Ap.removePotionEffect(effect.getType());
	Ap.getInventory().addItem(Bow);
	Ap.getInventory().setItem(8, new ItemStack(Material.ARROW, 1));}
	TheBatsBlock.getBlock().setTypeId(0);
	}
	
	public void TGEvent(){
	Bukkit.getWorld("world").setTime(17000);
    List<String> Glad = new ArrayList<String>();
	Glad.clear();
	for(Player Ap : Bukkit.getOnlinePlayers()){
	Glad.add(Ap.getName());
	Ap.getInventory().clear();
	Ap.closeInventory();
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);}
	Collections.shuffle(Glad);
	String GladiatorName = Glad.get(0);
	Player Gladiator = Bukkit.getServer().getPlayer(GladiatorName);
	Bukkit.broadcastMessage(ChatColor.RED + GladiatorName + "§c is The Gladiator, KILL HIM!!");
	for(Player Ap : Bukkit.getOnlinePlayers()){
	Ap.setHealth(20.00);
	Ap.setFireTicks(0);
	for(PotionEffect effect : Ap.getActivePotionEffects())Ap.removePotionEffect(effect.getType());
	if(Ap != Gladiator){
	Ap.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
	Ap.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	Ap.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	Ap.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	Ap.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
	Ap.getInventory().addItem(new ItemStack(Material.BOW));
	Ap.getInventory().setItem(27, new ItemStack(Material.ARROW, 64));
	Ap.getInventory().setItem(8, new ItemStack(373, 1, (short) 8229));
	Random R = new Random();
	int Ri = R.nextInt(2)+1;
	Location loc1 = new Location(Ap.getWorld(), -1613, 67.500, -1416, -86, 0);
	Location loc2 = new Location(Ap.getWorld(), -1606, 65.500, -1397, -147, 0);
	if(Ri == 1){Ap.teleport(loc1);}
	if(Ri == 2){Ap.teleport(loc2);}}}
	Enchantment Protection = new EnchantmentWrapper(0);
	Enchantment ProjectileProtection = new EnchantmentWrapper(4);
	Enchantment Thorns = new EnchantmentWrapper(7);
	Enchantment FeatherFalling = new EnchantmentWrapper(2);
	ItemStack HelmetG = new ItemStack(Material.GOLD_HELMET);
	HelmetG.addUnsafeEnchantment(Protection, 5);
	HelmetG.addEnchantment(ProjectileProtection, 3);
	Gladiator.getInventory().setHelmet(HelmetG);
	ItemStack ChestG = new ItemStack(Material.GOLD_CHESTPLATE);
	ChestG.addUnsafeEnchantment(Protection, 5);
	ChestG.addEnchantment(ProjectileProtection, 3);
	ChestG.addUnsafeEnchantment(Thorns, 5);
	Gladiator.getInventory().setChestplate(ChestG);
	ItemStack PantsG = new ItemStack(Material.GOLD_LEGGINGS);
	PantsG.addUnsafeEnchantment(Protection, 5);
	PantsG.addUnsafeEnchantment(ProjectileProtection, 3);
	Gladiator.getInventory().setLeggings(PantsG);
	ItemStack BootsG = new ItemStack(Material.GOLD_BOOTS);
	BootsG.addUnsafeEnchantment(Protection, 5);
	BootsG.addUnsafeEnchantment(ProjectileProtection, 3);
	BootsG.addUnsafeEnchantment(FeatherFalling, 8);
	Gladiator.getInventory().setBoots(BootsG);
	ItemStack Axe = new ItemStack(Material.IRON_AXE);
	ItemMeta AxeMeta = Axe.getItemMeta();
	AxeMeta.setDisplayName("§fAxe of Doom");
	Axe.setItemMeta(AxeMeta);
	Axe = addGlow(Axe);
	Gladiator.getInventory().addItem(Axe);
	ItemStack Flesh = new ItemStack(32 , 1);
	ItemMeta FleshMeta = Flesh.getItemMeta();
	FleshMeta.setDisplayName("§cDead Bush");
	Flesh.setItemMeta(FleshMeta);
	Flesh = addGlow(Flesh);
	Gladiator.getInventory().addItem(Flesh);
	ItemStack GunPowder = new ItemStack(Material.SULPHUR , 3);
	ItemMeta GunPowderMeta = GunPowder.getItemMeta();
	GunPowderMeta.setDisplayName("§8Holy Smokes");
	GunPowder.setItemMeta(GunPowderMeta);
	GunPowder = addGlow(GunPowder);
	Gladiator.getInventory().addItem(GunPowder);
	ItemStack Firework = new ItemStack(401 , 1);
	ItemMeta FireworkMeta = Firework.getItemMeta();
	FireworkMeta.setDisplayName("§9The Escape Plan");
	Firework.setItemMeta(FireworkMeta);
	Firework = addGlow(Firework);
	Gladiator.getInventory().addItem(Firework);
	Location GladiatorLocation = new Location(Bukkit.getWorld("world"), -1549.67203, 77.500, -1433.43556);
	Gladiator.teleport(GladiatorLocation);
	for(Entity en : Gladiator.getNearbyEntities(100, 100, 100)){
	if(en instanceof Zombie){Zombie z = (Zombie) en;z.setHealth(0.00);}
	if(en instanceof Skeleton){Skeleton s = (Skeleton) en;s.setHealth(0.00);}}
	}

	@EventHandler
	public void Target(EntityTargetEvent e){
	if(e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton){
	if(e.getTarget() instanceof Player){
	Player p = (Player) e.getTarget();
	if(p.getInventory().getHelmet() != null){
	if(p.getInventory().getHelmet().getType() == Material.GOLD_HELMET){
	e.setCancelled(true);}}}}
	}
	
	public boolean TDMEvent(Player p){
	Location TDM = new Location(p.getWorld(), -512.49963, 66.500, -1467.38175, -180, 0);
	int Bbx = -643;
	int Bby = 75;
	int Bbz = -1423;
	int Bax = -697;
	int Bay = 25;
	int Baz = -1461;
	int px = p.getLocation().getBlockX();
	int py = p.getLocation().getBlockY();
	int pz = p.getLocation().getBlockZ();
	if(!(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz)){	
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);
	p.teleport(TDM);
	p.setHealth(20.0);
	p.setFireTicks(0);}
	return false;
	}
	
	public void LMSEvent(){
	Location LMS = new Location(Bukkit.getWorld("world"), -1134.49260, 65.500, -1419.50827, 0, 0);
	ItemStack Bow = new ItemStack(Material.BOW);
	Enchantment Punch = new EnchantmentWrapper(49);
	Enchantment Infinity = new EnchantmentWrapper(51);
	Bow.addEnchantment(Punch, 1);
	Bow.addEnchantment(Infinity, 1);
    for(Player Ap : Bukkit.getOnlinePlayers()){
    int Bbx = -643;
    int Bby = 75;
    int Bbz = -1423;
    int Bax = -697;
    int Bay = 25;
    int Baz = -1461;
	int px = Ap.getLocation().getBlockX();
	int py = Ap.getLocation().getBlockY();
	int pz = Ap.getLocation().getBlockZ();
    if(!(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz)){	
	Ap.getInventory().clear();
	Ap.getInventory().setItemInHand(null);
	Ap.setHealth(20.0);
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);
	Ap.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	Ap.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	Ap.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
	Ap.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	Ap.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
	Ap.getInventory().addItem(Bow);
	Ap.getInventory().setItem(8, new ItemStack(Material.ARROW));
	Ap.teleport(LMS);
	Ap.setHealth(20.0);
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2147483647, 9));}}
	}
	
	public void LMS2Event(){
	Location LMS = new Location(Bukkit.getWorld("world"), -1134.49260, 65.500, -1419.50827, 0, 0);
	ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD);
	Enchantment UnBreak = new EnchantmentWrapper(24);
	Enchantment Sharp = new EnchantmentWrapper(16);
	Sword.addUnsafeEnchantment(UnBreak, 10);
	Sword.addUnsafeEnchantment(Sharp, 10);
	for(Player Ap : Bukkit.getOnlinePlayers()){
	int Bbx = -643;
	int Bby = 75;
	int Bbz = -1423;
	int Bax = -697;
	int Bay = 25;
	int Baz = -1461;
    int px = Ap.getLocation().getBlockX();
	int py = Ap.getLocation().getBlockY();
	int pz = Ap.getLocation().getBlockZ();
	if(!(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz)){	
	Ap.getInventory().clear();
	Ap.getInventory().setItemInHand(null);
	Ap.setHealth(20.0);
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);
	Ap.getInventory().addItem(Sword);
	Ap.teleport(LMS);
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.getById(21), 2147483647, 50));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.getById(22), 2147483647, 50));
	Ap.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 220, 50));}}}
	
	public void b1Event(){
	Location b1 = new Location(Bukkit.getWorld("world"), -840.50394, 233.500, -1432.34594, 0, 0);
	for(Player Ap : Bukkit.getOnlinePlayers()){
	int Bbx = -643;
	int Bby = 75;
	int Bbz = -1423;
    int Bax = -697;
	int Bay = 25;
	int Baz = -1461;
    int px = Ap.getLocation().getBlockX();
	int py = Ap.getLocation().getBlockY();
	int pz = Ap.getLocation().getBlockZ();
	if(!(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz)){	
    Ap.getInventory().clear();
	Ap.getInventory().setItemInHand(null);
	Ap.setHealth(20.0);
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);
	Ap.teleport(b1);}}
	}
	
	@EventHandler
	public void Bat(EntityDamageEvent e){
	if(e.getEntity() instanceof Bat){
	e.setCancelled(true);}}
	
	@EventHandler
	public void FallDmg(EntityDamageEvent e){
	// Parkour
	int Bby = 90;
	int Bay = 26;
	int Bbx = -1230;
	int Bbz = -1390;
	int Bax = -1440;
	int Baz = -1414;
	// Spawn
	int Bbx1 = 348;
	int Bbz1 = -1451;
	int Bax1 = 155;
	int Baz1 = -1591;
	int px = e.getEntity().getLocation().getBlockX();
	int py = e.getEntity().getLocation().getBlockY();
	int pz = e.getEntity().getLocation().getBlockZ();
	if(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz){	
	if(e.getEntity() instanceof Player){
	if(e.getCause() == DamageCause.FALL){
	e.setCancelled(true);
	}}}
	if(px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1){	
	if(e.getEntity() instanceof Player){
	if(e.getCause() == DamageCause.FALL){
	e.setCancelled(true);}}}
	}
	
	@EventHandler
	public void RespawnParkour(PlayerRespawnEvent e){
	Player p = e.getPlayer();
	Location ParkourSpawn = new Location(p.getWorld(), -1253.82682, 62.500, -1402.14278, 90, 0);
	int Bby = 90;
	int Bay = 26;
	int Bbx = -1230;
	int Bbz = -1390;
	int Bax = -1440;
	int Baz = -1414;
	int px = p.getLocation().getBlockX();
	int py = p.getLocation().getBlockY();
	int pz = p.getLocation().getBlockZ();
    if(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz){	
	e.setRespawnLocation(ParkourSpawn);  }
	}
	
	@EventHandler
	public void ParkourTp(PlayerMoveEvent e){
	Player p = e.getPlayer();
	Location ParkourSpawn = new Location(p.getWorld(), -1253.82682, 62.500, -1402.14278, 90, 0);
	int Bbx = -1230;
	int Bbz = -1390;
	int Bax = -1440;
	int Baz = -1414;
	int px = p.getLocation().getBlockX();
	int py = p.getLocation().getBlockY();
	int pz = p.getLocation().getBlockZ();
    if(px >= Bax && px <= Bbx && py < 39 && pz >= Baz && pz <= Bbz){	
    if(p.getGameMode() != GameMode.CREATIVE){
    p.teleport(ParkourSpawn);
    p.setHealth(20.0);
    p.setFireTicks(0);}}
    Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	int Bbx1 = -1101;
	int Bbz1 = -1374;
	int Bax1 = -1161;
	int Baz1 = -1452;
	int px1 = p.getLocation().getBlockX();
	int py1 = p.getLocation().getBlockY();
	int pz1 = p.getLocation().getBlockZ();
    if(px1 >= Bax1 && px1 <= Bbx1 && py1 < 55 && pz1 >= Baz1 && pz1 <= Bbz1){	
    if(p.getGameMode() != GameMode.CREATIVE){
    p.getInventory().clear();
    p.getInventory().setHelmet(null);
    p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
    p.getInventory().setBoots(null);
    p.teleport(Spawn);
    p.setHealth(20.0);
    for(PotionEffect effect : p.getActivePotionEffects())p.removePotionEffect(effect.getType());
    p.setFireTicks(0);}}
	}
	
	public boolean ParkourEvent(Player p){
	Location ParkourSpawn = new Location(p.getWorld(), -1253.82682, 62.500, -1402.14278, 90, 0);
	int Bbx = -643;
	int Bby = 75;
	int Bbz = -1423;
	int Bax = -697;
	int Bay = 25;
	int Baz = -1461;
	int px = p.getLocation().getBlockX();
	int py = p.getLocation().getBlockY();
	int pz = p.getLocation().getBlockZ();
	if(!(px >= Bax && px <= Bbx && py >= Bay && py <= Bby && pz >= Baz && pz <= Bbz)){	
    for(Player Ap : Bukkit.getOnlinePlayers()){
	for(PotionEffect effect : Ap.getActivePotionEffects())Ap.removePotionEffect(effect.getType());
	Ap.getInventory().clear();
	Ap.getInventory().setHelmet(null);
	Ap.getInventory().setChestplate(null);
	Ap.getInventory().setLeggings(null);
	Ap.getInventory().setBoots(null);
	Ap.teleport(ParkourSpawn);}}
	return false;
	}
	
	@EventHandler
	public void DisableTeamPvP(EntityDamageByEntityEvent e){
	int Bbx1 = -1464;
	int Bbz1 = -1321;
	int Bax1 = -1679;
	int Baz1 = -1500;
	if(e.getEntity() instanceof Player){
	if(e.getDamager() instanceof Player){
	Player V = (Player) e.getEntity();
	Player D = (Player) e.getDamager();
	int px = V.getLocation().getBlockX();
	int pz = V.getLocation().getBlockZ();
	if(V.getInventory().getHelmet() != null && D.getInventory().getHelmet() != null){
	if(V.getInventory().getHelmet().getType() == Material.IRON_HELMET && D.getInventory().getHelmet().getType() != Material.GOLD_HELMET){
	if(px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1){	
	e.setCancelled(true);}}}}}
	}
	
	@EventHandler
	public void ArrowPvP(EntityDamageByEntityEvent e){
	int Bbx1 = -1464;
	int Bbz1 = -1321;
	int Bax1 = -1679;
	int Baz1 = -1500;
	if(e.getEntity() instanceof Player){
	if(e.getDamager() instanceof Arrow){
	Player V = (Player) e.getEntity();
	Arrow a = (Arrow) e.getDamager();
	int px = V.getLocation().getBlockX();
	int pz = V.getLocation().getBlockZ();
	if(a.getShooter() instanceof Player){
	Player D = (Player) a.getShooter();
	if(V.getInventory().getHelmet() != null && D.getInventory().getHelmet() != null){
	if(V.getInventory().getHelmet().getType() == Material.IRON_HELMET && D.getInventory().getHelmet().getType() == Material.IRON_HELMET){
	if(px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1){	
	e.setCancelled(true);		
	}}}}}}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	if(sender instanceof Player){Player p = (Player) sender;
	if(commandLabel.equalsIgnoreCase("c")){if(p.isOp()){p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));}}}
    if(commandLabel.equalsIgnoreCase("event")){
    if(sender instanceof Player){
    final Player p = (Player) sender;
    if(p.hasPermission("Event.start") || p.isOp()){
    if(args.length < 1){
    p.sendMessage("§c/event start [tdm/parkour/lms/1b/lms2/tg/tb]");}
    if(args.length > 0){
    if(args[0].equalsIgnoreCase("start")){if(args.length < 2){p.sendMessage("§c/event start [tdm/parkour/lms/1b/lms2/tg/tb]");}
    if(args.length > 1){
    if(args[1].equalsIgnoreCase("tdm")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started a TDM Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    TDM = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4TDM Event Started, Have Fun!");
    Bukkit.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "bn stop");
    for(Player Ap : Bukkit.getOnlinePlayers()){TDMEvent(Ap);}
    Bukkit.getServer().getScheduler().cancelTask(TDM);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("lms")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started a Last Man Standing Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    LMS = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4LMS Event Started, Have Fun!");
    LMSEvent();
    Bukkit.getServer().getScheduler().cancelTask(LMS);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("parkour")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started a Parkour Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    Parkour = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4Parkour Event Started, Have Fun!");
    for(Player Ap : Bukkit.getOnlinePlayers()){ParkourEvent(Ap);}
    Bukkit.getServer().getScheduler().cancelTask(Parkour);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("1b")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started a 1Block Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    b1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§41Block Event Started, Have Fun!");
    b1Event();
    Bukkit.getServer().getScheduler().cancelTask(b1);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("lms2")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started a LMS2 Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    lms2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4LMS2 Event Started, Have Fun!");
    LMS2Event();
    Bukkit.getServer().getScheduler().cancelTask(lms2);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("tg")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started \"The Gladiator\" Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    tg = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4The Gladiator Event Started, Have Fun!");
    TGEvent();
    Bukkit.getServer().getScheduler().cancelTask(tg);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("tb")){
    Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + " §9started \"The Bats\" Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    tb = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
    public void run(){
    Bukkit.broadcastMessage("§4The Bats Event Started, Have Fun!");
    TBEvent();
    Bukkit.getServer().getScheduler().cancelTask(tb);}
    }, 600L);}
    }else{p.sendMessage("Unknown command. Type \"help\" for help.");}}}}
    }else if(sender instanceof ConsoleCommandSender){
    CommandSender c = sender;	
    if(args.length < 1){c.sendMessage("§c/event start [tdm/parkour/lms/1b/lms2/tg/tb]");}
    if(args.length > 0){
    if(args[0].equalsIgnoreCase("start")){
    if(args.length < 2){c.sendMessage("§c/event start [tdm/parkour/lms/1b/lms2/tg/tb]");}
    if(args.length > 1){
    if(args[1].equalsIgnoreCase("tdm")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started a TDM Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    TDM = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4TDM Event Started, Have Fun!");
    Bukkit.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "bn stop");
    for(Player Ap : Bukkit.getOnlinePlayers()){TDMEvent(Ap);}
    Bukkit.getServer().getScheduler().cancelTask(TDM);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("lms")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started a Last Man Standing Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    LMS = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4LMS Event Started, Have Fun!");
    LMSEvent();
    Bukkit.getServer().getScheduler().cancelTask(LMS);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("parkour")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started a Parkour Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    Parkour = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4Parkour Event Started, Have Fun!");
    for(Player Ap : Bukkit.getOnlinePlayers()){ParkourEvent(Ap);}
    Bukkit.getServer().getScheduler().cancelTask(Parkour);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("1b")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started a 1Block Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    b1 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§41Block Event Started, Have Fun!");
    b1Event();
    Bukkit.getServer().getScheduler().cancelTask(b1);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("lms2")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started a LMS2 Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    lms2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4LMS2 Event Started, Have Fun!");
    LMS2Event();
    Bukkit.getServer().getScheduler().cancelTask(lms2);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("tg")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started \"The Gladiator\" Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    tg = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    public void run(){
    Bukkit.broadcastMessage("§4The Gladiator Event Started, Have Fun!");
    TGEvent();
    Bukkit.getServer().getScheduler().cancelTask(tg);}
    }, 600L);}
    if(args[1].equalsIgnoreCase("tb")){
    Bukkit.broadcastMessage(ChatColor.AQUA + "Console" + " §9started \"The Bats\" Event!");
    Bukkit.broadcastMessage("§b30 §9seconds till the event start");
    tb = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
    public void run(){
    Bukkit.broadcastMessage("§4The Bats Event Started, Have Fun!");
    TBEvent();
    Bukkit.getServer().getScheduler().cancelTask(tb);}
    }, 600L);}}}}
    }
    }
	return false;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void GlaidatorTNT(PlayerInteractEvent e){
	final Player p = e.getPlayer();
	ItemStack item = p.getItemInHand();
	if(p.getItemInHand() != null && p.getGameMode() != GameMode.CREATIVE) {
	if(p.getItemInHand().getType() == Material.SULPHUR){
	final Item grenade = p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.TNT));
	grenade.setVelocity(p.getEyeLocation().getDirection());
	if(item.getAmount() > 1){item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}
	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
	public void run(){
	grenade.getWorld().createExplosion(grenade.getLocation(), (float) 3.0,false);
	grenade.remove();
	for(Entity en : p.getNearbyEntities(8, 5, 8)){
	if(en instanceof Player){
	Player Ap = (Player) en;
	if(Ap != p){Ap.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
	}}}}}, 50L);}}
	}
    
	@EventHandler
	public void GladiatorHit(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player){
	if(e.getDamager() instanceof Player){
	Player D = (Player) e.getDamager();
	if(D.getInventory().getHelmet() != null){
	if(D.getInventory().getHelmet().getType() == Material.GOLD_HELMET && !D.getInventory().contains(new ItemStack(Material.ARROW, 1)) && !D.getInventory().contains(new ItemStack(Material.MUSHROOM_SOUP))){
	if(D.getItemInHand().getType() == Material.IRON_AXE){
	D.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));}}}}}
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void GladiatorZombie(PlayerInteractEvent e){
    Player p = e.getPlayer();
	ItemStack item = p.getItemInHand();
	ItemStack Skull = (setSkin(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), p.getName()));
    if(p.getInventory().getItemInHand().getTypeId() == 32 && p.getGameMode() != GameMode.CREATIVE){
    Zombie Z1 = (Zombie) p.getWorld().spawnCreature(p.getLocation(), EntityType.ZOMBIE);
    Zombie Z2 = (Zombie) p.getWorld().spawnCreature(p.getLocation(), EntityType.ZOMBIE);
    Zombie Z3 = (Zombie) p.getWorld().spawnCreature(p.getLocation(), EntityType.ZOMBIE);
    Skeleton S1 = (Skeleton) p.getWorld().spawnCreature(p.getLocation(), EntityType.SKELETON);
    Skeleton S2 = (Skeleton) p.getWorld().spawnCreature(p.getLocation(), EntityType.SKELETON);
    Skeleton S3 = (Skeleton) p.getWorld().spawnCreature(p.getLocation(), EntityType.SKELETON);
    Z1.getEquipment().setHelmet(Skull);
    Z2.getEquipment().setHelmet(Skull);
    Z3.getEquipment().setHelmet(Skull);
    S1.getEquipment().setHelmet(Skull);
    S2.getEquipment().setHelmet(Skull);
    S3.getEquipment().setHelmet(Skull);
    Z1.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    Z2.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    Z3.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    Z1.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
    Z2.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
    Z3.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
    S1.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    S2.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    S3.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
    S1.getEquipment().setItemInHand(new ItemStack(Material.BOW));
    S2.getEquipment().setItemInHand(new ItemStack(Material.BOW));
    S3.getEquipment().setItemInHand(new ItemStack(Material.BOW));
    Z1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
    Z2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
    Z3.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
    Z1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    Z2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    Z3.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    S1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    S2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    S3.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
    Z1.setCustomName(p.getName() + "'s Minion");
    Z2.setCustomName(p.getName() + "'s Minion");
    Z3.setCustomName(p.getName() + "'s Minion");
    S1.setCustomName(p.getName() + "'s Minion");
    S2.setCustomName(p.getName() + "'s Minion");
    S3.setCustomName(p.getName() + "'s Minion");
    Z1.setPassenger(S1);
    Z2.setPassenger(S2);
    Z3.setPassenger(S3);
	if(item.getAmount() > 1){item.setAmount(item.getAmount() - 1);p.updateInventory();
	}else{p.setItemInHand(null);p.updateInventory();}}
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void GladiatorJump(PlayerInteractEvent e){
    Player p = e.getPlayer();
    if(e.getPlayer().getItemInHand().getTypeId() == 401 && p.getGameMode() != GameMode.CREATIVE){
    p.getWorld().createExplosion(p.getLocation(), 4);	
    p.setVelocity(p.getLocation().getDirection().setY(3));
    p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0));
    p.setItemInHand(null);
    p.updateInventory();}
    }
    
}

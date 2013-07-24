package me.Kenig.PVPIL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_6_R2.NBTTagCompound;
import net.minecraft.server.v1_6_R2.NBTTagList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_6_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop implements Listener, CommandExecutor {
	public static Main plugin;
	
	File ListsFile;
	FileConfiguration Lists;

	public Shop(Main instance){
	plugin = instance;
	ListsFile = plugin.ListsFile;
	Lists = plugin.Lists;}

	public void saveS(){
	try{Lists.save(ListsFile);
	}catch(IOException e){e.printStackTrace();}}
	
    public static ItemStack addGlow(ItemStack item){
    net.minecraft.server.v1_6_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
    NBTTagCompound tag = null;
    if(!nmsStack.hasTag()){
    tag = new NBTTagCompound();
    nmsStack.setTag(tag);}
    if(tag == null) tag = nmsStack.getTag();
    NBTTagList ench = new NBTTagList();
    tag.set("ench", ench);
    nmsStack.setTag(tag);
    return CraftItemStack.asCraftMirror(nmsStack);
    }
	  
	public boolean isInt(String input){
	try{Integer.parseInt(input);
	return true;
	}catch(NumberFormatException nfe){
	return false;}
	}
			
	public boolean SpawnCommand(final Player p) {
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
	if((px >= Bax && px <= Bbx && pz >= Baz&& pz <= Bbz) || (px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1)){
	p.sendMessage("§cYou can't do this here! - Pleaes go back to spawn");
	}else{
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);
	p.setHealth(20.0);
	p.setFireTicks(0);
	Location MainKitChoose = new Location(p.getWorld(), -26.46970, 75.500, -1518.64426, 90, 0);
	p.teleport(MainKitChoose);}
	return false;
	}

	public boolean SpawnCommandMini(final Player p) {
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
	if((px >= Bax && px <= Bbx && pz >= Baz && pz <= Bbz) || (px >= Bax1 && px <= Bbx1 && pz >= Baz1 && pz <= Bbz1)){
	p.sendMessage("§cYou can't do this here! - Pleaes go back to spawn");
	}else{
	p.getInventory().clear();
	p.getInventory().setHelmet(null);
	p.getInventory().setChestplate(null);
	p.getInventory().setLeggings(null);
	p.getInventory().setBoots(null);
	Location MiniKitChoose = new Location(p.getWorld(), -1574.48782, 158.500, -1408.46470, 0, 0);
	p.teleport(MiniKitChoose);
	}return false;
	}
	
	public boolean ShopOpen(Player p){
	    List<String> CobraList = Lists.getStringList("CobraKit");
	    List<String> ScorpionList = Lists.getStringList("ScorpionKit");
	    List<String> VultureList = Lists.getStringList("VultureKit");
	    List<String> NinjaList = Lists.getStringList("NinjaKit");
	    List<String> MedicList = Lists.getStringList("MedicKit");
	    List<String> PyroList = Lists.getStringList("PyroKit");
	    List<String> TankList = Lists.getStringList("TankKit");
	    List<String> BishopList = Lists.getStringList("BishopKit");
	    List<String> ChickenList = Lists.getStringList("ChickenList");
		ItemStack Coins = new ItemStack(Material.GOLD_INGOT);
		ItemMeta CoinsMeta = Coins.getItemMeta();
		CoinsMeta.setDisplayName("§6" + plugin.getConfig().getInt(p.getName() + " coin") + " Coins");
		Coins.setItemMeta(CoinsMeta);
		ItemStack Cobra = new ItemStack(351, 1, (short)10);
		ItemMeta CobraMeta = Cobra.getItemMeta();
		CobraMeta.setDisplayName("§2Cobra Kit");
		ArrayList<String> CobraLore = new ArrayList<String>();
		if(CobraList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){CobraLore.add("§4You own this kit");}else{CobraLore.add("§6250 Coins");}
		CobraMeta.setLore(CobraLore);
		Cobra.setItemMeta(CobraMeta);
		ItemStack Scorpion = new ItemStack(Material.FISHING_ROD);
		ItemMeta ScorpionMeta = Cobra.getItemMeta();
		ScorpionMeta.setDisplayName("§9Scorpion Kit");
		ArrayList<String> ScorpionLore = new ArrayList<String>();
		if(ScorpionList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){ScorpionLore.add("§4You own this kit");}else{ScorpionLore.add("§6380 Coins");}
		ScorpionMeta.setLore(ScorpionLore);
		Scorpion.setItemMeta(ScorpionMeta);
		ItemStack Stomper = new ItemStack(Material.ANVIL);
		ItemMeta StomperMeta = Cobra.getItemMeta();
		StomperMeta.setDisplayName("§eVulture Kit");
		ArrayList<String> StomperLore = new ArrayList<String>();
		if(VultureList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){StomperLore.add("§4You own this kit");}else{StomperLore.add("§6450 Coins");}
		StomperMeta.setLore(StomperLore);
		Stomper.setItemMeta(StomperMeta);
		ItemStack NewMap = new ItemStack(Material.EGG);
		ItemMeta NewMapMeta = Cobra.getItemMeta();
		NewMapMeta.setDisplayName("§eChicken Kit");
		ArrayList<String> NewMapLore = new ArrayList<String>();
		if(ChickenList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){NewMapLore.add("§4You own this kit");}else{NewMapLore.add("§6260 Coins");}
		NewMapLore.add("§4TROLL ALERT!");
		NewMapMeta.setLore(NewMapLore);
		NewMap.setItemMeta(NewMapMeta);
		ItemStack Ninja = new ItemStack(Material.EMERALD);
		ItemMeta NinjaMeta = Ninja.getItemMeta();
		NinjaMeta.setDisplayName("§aNinja Kit");
		ArrayList<String> NinjaLore = new ArrayList<String>();
		if(NinjaList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){NinjaLore.add("§4You own this kit");}else{NinjaLore.add("§6250 Coins");}
		NinjaMeta.setLore(NinjaLore);
		Ninja.setItemMeta(NinjaMeta);
		ItemStack Medic = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta MedicMeta = Ninja.getItemMeta();
		MedicMeta.setDisplayName("§cMedic Kit");
		ArrayList<String> MedicLore = new ArrayList<String>();
		if(MedicList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){MedicLore.add("§4You own this kit");}else{MedicLore.add("§6220 Coins");}
		MedicMeta.setLore(MedicLore);
		Medic.setItemMeta(MedicMeta);
		ItemStack Pyro = new ItemStack(Material.FIRE);
		ItemMeta PyroMeta = Ninja.getItemMeta();
		PyroMeta.setDisplayName("§4Pyro Kit");
		ArrayList<String> PyroLore = new ArrayList<String>();
		if(PyroList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){PyroLore.add("§4You own this kit");}else{PyroLore.add("§6320 Coins");}
		PyroMeta.setLore(PyroLore);
		Pyro.setItemMeta(PyroMeta);
		ItemStack Bomber = new ItemStack(Material.TNT);
		ItemMeta BomberMeta = Ninja.getItemMeta();
		BomberMeta.setDisplayName("§3Tank Kit");
		ArrayList<String> BomberLore = new ArrayList<String>();
		if(TankList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){BomberLore.add("§4You own this kit");}else{BomberLore.add("§6330 Coins");}
		BomberMeta.setLore(BomberLore);
		Bomber.setItemMeta(BomberMeta);
		ItemStack Bishop = new ItemStack(384);
		ItemMeta BishopMeta = Bishop.getItemMeta();
		BishopMeta.setDisplayName("§bBishop Kit");
		ArrayList<String> BishopLore = new ArrayList<String>();
		if(BishopList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){BishopLore.add("§4You own this kit");}else{BishopLore.add("§6220 Coins");}
		BishopMeta.setLore(BishopLore);
		Bishop.setItemMeta(BishopMeta);
		ItemStack Block = new ItemStack(68);
		ItemMeta BlockMeta = Block.getItemMeta();
		BlockMeta.setDisplayName("[]");
		Block.setItemMeta(BlockMeta);
		ItemStack HowCoins = new ItemStack(Material.BOOK);
		ItemMeta HowCoinsMeta = HowCoins.getItemMeta();
		HowCoinsMeta.setDisplayName("§5How To Get Coins");
		HowCoins.setItemMeta(HowCoinsMeta);
		Inventory Inv = Bukkit.createInventory(p, 27, "Shop Coins");
		Inv.clear();
		Inv.setItem(0, Cobra);
		Inv.setItem(1, Scorpion);
		Inv.setItem(2, Stomper);
		Inv.setItem(9, Ninja);
		Inv.setItem(10, Medic);
		Inv.setItem(11, Pyro);
		Inv.setItem(18, Bomber);
		Inv.setItem(19, Bishop);
		Inv.setItem(20, NewMap);
		Inv.setItem(12, Block);
		Inv.setItem(13, HowCoins);
		Inv.setItem(16, Coins);
		Inv.setItem(15, Block);
		Inv.setItem(14, Block);
		Inv.setItem(17, Block);
		for(int Sign = 0; Sign < 6; Sign++){Inv.setItem(Sign+3 ,Block);}
		for(int Sign = 0; Sign < 6; Sign++){Inv.setItem(Sign+21 ,Block);}
		p.openInventory(Inv);
		return false;
	}
	
	@EventHandler
	public void ShopClick(InventoryClickEvent e){
	if(e.getWhoClicked() instanceof Player){
    Player p = (Player) e.getWhoClicked();
	if(e.getInventory().getTitle() == "Shop Coins" && !p.isOp()){p.closeInventory();}
	List<String> CobraList = Lists.getStringList("CobraKit");
    List<String> ScorpionList = Lists.getStringList("ScorpionKit");
    List<String> VultureList = Lists.getStringList("VultureKit");
    List<String> NinjaList = Lists.getStringList("NinjaKit");
    List<String> MedicList = Lists.getStringList("MedicKit");
    List<String> PyroList = Lists.getStringList("PyroKit");
    List<String> TankList = Lists.getStringList("TankKit");
    List<String> BishopList = Lists.getStringList("BishopKit");
	List<String> ChickenList = Lists.getStringList("ChickenList");
    int Coins = plugin.getConfig().getInt(p.getName() + " coin");
    if(e.getInventory().getTitle() == "Shop Coins"){
	if(e.getCursor() != null){
	if(e.getCurrentItem() != null){
	if(e.getCurrentItem().hasItemMeta()){
	if(e.getCurrentItem().getItemMeta().getDisplayName() != null){	
	// Cobra Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Cobra Kit")){	
	if(!CobraList.contains(p.getName())){
    if(Coins >= 250 ){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 250);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
    p.sendMessage("§6You now have the §2Cobra §6kit in your kits list");
	p.sendMessage("§6Coins : §f" + CoinsA);
	CobraList.add(p.getName());
	Lists.set("CobraKit", CobraList);
    plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §2Cobra §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Scorpion Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Scorpion Kit")){	
	if(!ScorpionList.contains(p.getName())){
	if(Coins >= 380){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 380);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §9Scorpion §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
    ScorpionList.add(p.getName());
    Lists.set("ScorpionKit", ScorpionList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §9Scropion §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Vulture Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eVulture Kit")){	
	if(!VultureList.contains(p.getName())){
	if(Coins >= 450){
	p.closeInventory();
    plugin.getConfig().set(p.getName() + " coin",Coins - 450);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §eVulture §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	VultureList.add(p.getName());
	Lists.set("VultureKit", VultureList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §eVulture §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Ninja Kit
    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aNinja Kit")){	
	if(!NinjaList.contains(p.getName())){
	if(Coins >= 250){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 250);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §aNinja §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	NinjaList.add(p.getName());
	Lists.set("NinjaKit", NinjaList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §aNinja §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
    // Medic Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cMedic Kit")){	
	if(!MedicList.contains(p.getName())){
	if(Coins >= 220){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 220);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §cMedic §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	MedicList.add(p.getName());
	Lists.set("MedicKit", MedicList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §cMedic §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Pyro Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Pyro Kit")){	
	if(!PyroList.contains(p.getName())){
	if(Coins >= 320){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 320);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6§6You now have the §4Pyro §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	PyroList.add(p.getName());
	Lists.set("PyroKit", PyroList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §4Pyro §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}
	// Tank Kit
	}if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Tank Kit")){	
	if(!TankList.contains(p.getName())){
	if(Coins >= 330){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 330);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §3Tank §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	TankList.add(p.getName());
	Lists.set("TankKit", TankList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §3Tank §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Bishop Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bBishop Kit")){	
	if(!BishopList.contains(p.getName())){
	if(Coins >= 220){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 220);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §bBishop §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	BishopList.add(p.getName());
	Lists.set("BishopKit", BishopList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §bBishop §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	// Chicken Kit
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eChicken Kit")){	
	if(!ChickenList.contains(p.getName())){
	if(Coins >= 260){
	p.closeInventory();
	plugin.getConfig().set(p.getName() + " coin",Coins - 260);
	int CoinsA = plugin.getConfig().getInt(p.getName() + " coin");
	p.sendMessage("§6You now have the §eChicken §6kit in your kits list");
	p.sendMessage("§6Coins Left: §f" + CoinsA);
	ChickenList.add(p.getName());
	Lists.set("ChickenList", ChickenList);
	plugin.saveConfig();
	}else{p.closeInventory();p.sendMessage("§cYou don't have enough coins to buy the §eChicken §ckit");}
	}else{p.closeInventory();p.sendMessage("§4You alredy own this kit!");}}
	}}}}}
	saveS();}
	}
	
	@EventHandler
	public void HowToGetCoins(InventoryClickEvent e){
	Player p = (Player) e.getWhoClicked();
	if(e.getCursor() != null){
	if(e.getCurrentItem() != null){
	if(e.getCurrentItem().hasItemMeta()){
	if(e.getCurrentItem().getItemMeta().getDisplayName() != null){	
	if(e.isLeftClick()){
	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5How To Get Coins")){
	p.closeInventory();
    p.sendMessage("§1So how do you get coins in PvP Israel?");
    p.sendMessage("§b1. §9You get 2 coin for each kill §b[ §9Vip: 3 Coins §b]");
    p.sendMessage("§b2. §9Winning Event §b[ §9Depending on the type of the event §b]");
    p.sendMessage("§b3. §9Get an achievement §b[ §9Talk to Fluffy for more information §b]");
    p.sendMessage("§b4. §9You get 50 coins for posting a comment in Fxp with your nickname §b[ §91 time per player §b]");
	}}}}}}
	}
	
	public boolean MiniRandom(Player p){
	Random R = new Random();
	int Ri = R.nextInt(5)+1;
	if(Ri == 1){p.teleport(new Location(p.getWorld(), -1594, 65.500, -1425, -28, 0));}
	if(Ri == 2){p.teleport(new Location(p.getWorld(), -13.30000, 48.500, -1518.57238, -116, 0));}
	if(Ri == 3){p.teleport(new Location(p.getWorld(), -1564, 72.500, -1392, -157, 0));}
	if(Ri == 4){p.teleport(new Location(p.getWorld(), -1547, 78.500, -1377, 152, 0));}	
	if(Ri == 5){p.teleport(new Location(p.getWorld(), -1533, 72.500, -1425, 65, 0));}	
	return false;	
	}
	
	@EventHandler
	public void AntiSignBreak(BlockBreakEvent e){
	Player p = e.getPlayer();
	Block b = e.getBlock();
	if(b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN){
	if(p.getItemInHand().getTypeId() != 258){e.setCancelled(true);}}
	}
	
	@EventHandler
	public void ChooseKitMini(PlayerInteractEvent e){
	Player p = e.getPlayer();
	Location Spawn = new Location(p.getWorld(), 278.30088, 74.500, -1521.58854, -90, 0);
	List<String> CobraList = Lists.getStringList("CobraKit");
    List<String> ScorpionList = Lists.getStringList("ScorpionKit");
    List<String> VultureList = Lists.getStringList("VultureKit");
    List<String> NinjaList = Lists.getStringList("NinjaKit");
    List<String> MedicList = Lists.getStringList("MedicKit");
    List<String> PyroList = Lists.getStringList("PyroKit");
    List<String> TankList = Lists.getStringList("TankKit");
    List<String> ChickenList = Lists.getStringList("ChickenList");
	if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
	if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
	Sign S = (Sign) e.getClickedBlock().getState();
	String[] Lin = S.getLines();
	if(Lin[1].equalsIgnoreCase("§lSpawn")){p.teleport(Spawn);}
    if(S.getLocation().subtract(0, -1, 0).getBlock().getTypeId() == 50 || S.getLocation().subtract(0, -2, 0).getBlock().getTypeId() == 50){
	if(Lin[1].equalsIgnoreCase("§6§lHero")){p.chat("/tpp herodksajdads");MiniRandom(p);}
	if(Lin[1].equalsIgnoreCase("§6§lArcher")){p.chat("/tpp archerdksajdads");MiniRandom(p);}
	if(Lin[1].equalsIgnoreCase("§3§lSpy")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp spydksajdads");MiniRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lDwarf")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp dwarfdksajdads");MiniRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lArcher")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp witherdksajdads");MiniRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§3§lSniper")){if(p.hasPermission("VIP.Kits") || p.isOp()){p.chat("/tpp sniperdksajdads");MiniRandom(p);}else{p.sendMessage("§4This is a VIP kit, talk with Guy to buy a VIP");}}
	if(Lin[1].equalsIgnoreCase("§e§lCobra")){if(CobraList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp cobradksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lScorpion")){if(ScorpionList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp scorpiondksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lVulture")){if(VultureList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp vulturedksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lNinja")){if(NinjaList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp ninjadksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lMedic")){if(MedicList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp medicdksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lPyro")){if(PyroList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp pyrodksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lTank")){if(TankList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp tankdksajdads");MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
	if(Lin[1].equalsIgnoreCase("§e§lChicken")){if(ChickenList.contains(p.getName()) || p.hasPermission("VIPplus.Kits")){p.chat("/tpp chickendksajdads");p.teleport(Spawn);MiniRandom(p);}else{p.sendMessage("§4You don't have this kit, you can buy it in the shop");}}
    }}}
	}
		
 	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
 	if(commandLabel.equalsIgnoreCase("mini")){if(sender instanceof Player){Player p = (Player) sender;p.sendMessage("§cThe Mini Arena has been disabled!");}}
 	if(commandLabel.equalsIgnoreCase("shop")){if(sender instanceof Player){Player p = (Player) sender;ShopOpen(p);}}
 	if(commandLabel.equalsIgnoreCase("main")){if(sender instanceof Player){Player p = (Player) sender;SpawnCommand(p);}}
	if(commandLabel.equalsIgnoreCase("coin")){
	if(sender instanceof Player){
	Player p = (Player) sender;
	int Coins = plugin.getConfig().getInt(p.getName() + " coin");
	if(args.length < 1){p.sendMessage("§6You have §f" + Coins + " §6coins");}
	else if(args[0].equalsIgnoreCase("give")){
	if(p.isOp() || p.hasPermission("Event.start")){
	if(args.length > 2){
	Player CheckT = p.getServer().getPlayer(args[1]);
	int CreditsGive = Integer.valueOf(args[2]);
	if(CheckT != null){
	int CheckTC = plugin.getConfig().getInt(CheckT.getName() + " coin");
	p.sendMessage("§eYou gave §6" + CreditsGive + " §ecoins to §6" + CheckT.getName());
	if(CheckT.isOnline()){CheckT.sendMessage("§6§lAdmin gave you some coins. enjoy!");}
	plugin.getConfig().set(CheckT.getName() + " coin", CheckTC + CreditsGive);
	plugin.saveConfig();
	}else{p.sendMessage("§cPlayer isn't online");}
	}else{p.sendMessage("§c/coin give [playername] [coin]");}
	}else{p.sendMessage("Unknown command. Type \"help\" for help.");}}// [Give]
	else if(args[0].equalsIgnoreCase("pay")){
    if(args.length > 2){
    Player PayPlayer = p.getServer().getPlayer(args[1]);
    int SenderCoins = plugin.getConfig().getInt(p.getName() + " coin");
    if(PayPlayer != null && PayPlayer.isOnline()){
    if(isInt(args[2])){
    int CreditsGive = Integer.valueOf(args[2]);
    if(SenderCoins >= CreditsGive && CreditsGive > 0){
    int PayPlayerCoins = plugin.getConfig().getInt(PayPlayer.getName() + " coin");
    int Misim = (int) ((CreditsGive)*0.09);
    plugin.getConfig().set(PayPlayer.getName() + " coin", (PayPlayerCoins + (CreditsGive*0.91)));
    plugin.getConfig().set(p.getName() + " coin", SenderCoins - CreditsGive);
    plugin.saveConfig();
    int PpCoins = plugin.getConfig().getInt(p.getName() + " coin");
    p.sendMessage("§eYou gave §6" + CreditsGive + " §ecoins to §6" + PayPlayer.getName());
    p.sendMessage("§6" + Misim + "§e coins went to tax");
    p.sendMessage("§eYou now have §6" + PpCoins + " §ecoins");
    PayPlayer.sendMessage("§6" + p.getName() + "§e gave you §6" + CreditsGive + "§e coins");
    PayPlayer.sendMessage("§e0.09% - §6( §6"+ Misim + " coins ) §ewent to tax");
    if(CreditsGive >= 150){
    List<String> ListPay150 = Lists.getStringList("Pay150");
    if(!ListPay150.contains(p.getName())){
    int C = plugin.getConfig().getInt(p.getName() + " coin");
    plugin.getConfig().set(p.getName() + " coin", C+100);	
    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 3);
    p.sendMessage("§a~~~~~~~~~~~");
    p.sendMessage("§eAchievement get ❢");
    p.sendMessage("§4✖ §cThe Giving Tree §4✖");
    p.sendMessage("§a~~~~~~~~~~~");
    ListPay150.add(p.getName());
    Lists.set("Pay150", ListPay150);
    plugin.saveConfig();
    saveS();}}
    }else{p.sendMessage("§cYou don't have enough coins! [ Maybe you sent minus? ]");}
    }else{p.sendMessage("§cPlease write a valid number");}
    }else{p.sendMessage("§cPlayer isn't online");}
    }else{p.sendMessage("§c/coin pay [playername] [coins]");}}// [Pay]
	}else if(sender instanceof ConsoleCommandSender){
	CommandSender c = sender;
	if(args.length < 3){c.sendMessage("§c/coin give [playername] [coins]");}
	if(args.length > 2){
	OfflinePlayer T = Bukkit.getServer().getOfflinePlayer(args[1]);
	int CoinsGive = Integer.valueOf(args[2]);
	int TCoins = plugin.getConfig().getInt(T.getName() + " coin");
	c.sendMessage("§eYou gave §6" + CoinsGive + " §ecoins to §6" + T.getName());
	if(T.isOnline()){
	Player TOnline = Bukkit.getServer().getPlayer(args[1]);
	TOnline.sendMessage("§6§lYou got " + CoinsGive + " coins by an admin");}
	plugin.getConfig().set(T.getName() + " coin", TCoins + CoinsGive);
	plugin.saveConfig();
	}// Args Length
	}// Console
	}// Coin Command
	return false;
 	}
	
 
}


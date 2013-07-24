package me.Kenig.PVPIL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_6_R2.Packet63WorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Effects implements Listener, CommandExecutor{

   public static Main plugin;
   
   File ListsFile;
   File EffectFile;
   FileConfiguration Lists;
   FileConfiguration Eff;

   public Effects(Main instance){
   plugin = instance;
   ListsFile = plugin.ListsFile;
   Lists = plugin.Lists;
   EffectFile = plugin.EffectFile;
   Eff = plugin.Eff;}

   public void saveS(){
   try{Lists.save(ListsFile);
   }catch(IOException e){e.printStackTrace();}}
    
   public void saveff(){
   try{Eff.save(EffectFile);
   }catch(IOException e){e.printStackTrace();}}
   
   // mobSpell  4
   // spell  5
   // instantSpell  6
   // witchMagic  7
   // note  3
   // lava  2
   // reddust  8
   // angryVillager  9
   // heart  1
   // happyVillager  10

   
   public void Effect(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count, String EffectName) throws Exception {
   Packet63WorldParticles packet = new Packet63WorldParticles();
   ReflectionUtilities.setValue(packet, "a", EffectName);
   ReflectionUtilities.setValue(packet, "b", (float) location.getX());
   ReflectionUtilities.setValue(packet, "c", (float) location.getY());
   ReflectionUtilities.setValue(packet, "d", (float) location.getZ());
   ReflectionUtilities.setValue(packet, "e", offsetX);
   ReflectionUtilities.setValue(packet, "f", offsetY);
   ReflectionUtilities.setValue(packet, "g", offsetZ);
   ReflectionUtilities.setValue(packet, "h", speed);
   ReflectionUtilities.setValue(packet, "i", count);
   ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);}
   
    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent e){
    if(e.getEntity() instanceof Player){
    if(e.getDamager() instanceof Player){
    Player V = (Player) e.getEntity();
    Player D = (Player) e.getDamager();
    int Effect = Eff.getInt(D.getName() + " effect");
    if(Effect == 1){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 3, "heart");}
	catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 2){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "lava");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 3){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "note");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 4){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "mobSpell");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 5){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "spell");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 6){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "instantSpell");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 7){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 5, "witchMagic");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 8){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 12, "reddust");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 9){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 3, "angryVillager");}
    catch(Exception e1) {e1.printStackTrace();}}
    if(Effect == 10){
    Location loc = new Location(D.getWorld(), V.getLocation().getBlockX(), V.getLocation().getBlockY()+1, V.getLocation().getBlockZ());
    try{Effect(D, loc, 0, 1, 0, 10, 4, "happyVillager");}
    catch(Exception e1) {e1.printStackTrace();}}
    }}
    }
    
    @EventHandler
    public void RightClick(InventoryClickEvent e){
    Player p = (Player) e.getWhoClicked();
    Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY()+2, p.getLocation().getBlockZ());
    if(e.getInventory().getTitle() == "Shop Effects"){p.closeInventory();}
    if(e.isRightClick()){
    if(e.getCurrentItem() != null){
    if(e.getCurrentItem().hasItemMeta()){
    if(e.getCurrentItem().getItemMeta().getDisplayName() != null){
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§cHearts Effect"){
    try{
	Effect(p, loc, 0, 1, 0, 10, 10, "heart");
	}catch(Exception e1){
	e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§6Lava Effect"){
    try{
	Effect(p, loc, 0, 1, 0, 10, 10, "lava");
	}catch(Exception e1){
	e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§bMusic Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "note");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§aColored Potion Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "mobSpell");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§7White Potion Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "spell");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§7White FireWork Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "instantSpell");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§5Purple FireWork Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "witchMagic");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§dColored Red Stone Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 3, 10, "reddust");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§4Angry Villager Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "angryVillager");
    }catch(Exception e1){
    e1.printStackTrace();}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§2Happy Villager Effect"){
    try{
    Effect(p, loc, 0, 1, 0, 10, 10, "happyVillager");
    }catch(Exception e1){
    e1.printStackTrace();}}
    }}}}
    }
   
    @EventHandler
    public void LeftClick(InventoryClickEvent e){
    Player p = (Player) e.getWhoClicked();
    int Coins = plugin.getConfig().getInt(p.getName() + " coin");
	List<String> Heart = Lists.getStringList("HeartEffect");
	List<String> Lava = Lists.getStringList("LavaEffect");
	List<String> Note = Lists.getStringList("NoteEffect");
	List<String> MobSpell = Lists.getStringList("MobSpellEffect");
	List<String> Spell = Lists.getStringList("SpellEffect");
	List<String> InstantSpell = Lists.getStringList("InstantSpellEffect");
	List<String> Witch = Lists.getStringList("WitchEffect");
	List<String> RedStone = Lists.getStringList("RedStoneEffect");
	List<String> AngryVillager = Lists.getStringList("AngryVillagerEffect");
	List<String> HappyVillager = Lists.getStringList("HappyVillagerEffect");
    if(e.getInventory().getTitle() == "Shop Effects"){p.closeInventory();}
    if(e.getInventory().getTitle() == "Shop Effects"){
    if(e.isLeftClick()){
    if(e.getCurrentItem() != null){
    if(e.getCurrentItem().hasItemMeta()){
    if(e.getCurrentItem().getItemMeta().getDisplayName() != null){
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§cHearts Effect"){
    if(!Heart.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eHearts§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
	Heart.add(p.getName());
	Lists.set("HeartEffect", Heart);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§6Lava Effect"){    
    if(!Lava.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eLava§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    Lava.add(p.getName());
    Lists.set("LavaEffect", Lava);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§bMusic Effect"){
    if(!Note.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eMusic§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    Note.add(p.getName());
    Lists.set("NoteEffect", Note);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§aColored Potion Effect"){
    if(!MobSpell.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eColored Potion§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    MobSpell.add(p.getName());
    Lists.set("MobSpellEffect", MobSpell);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§7White Potion Effect"){
    if(!Spell.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eWhite Potion§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    Spell.add(p.getName());
    Lists.set("SpellEffect", Spell);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§7White FireWork Effect"){
    if(!InstantSpell.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eWhite FireWork§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    InstantSpell.add(p.getName());
    Lists.set("InstantSpellEffect", InstantSpell);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§5Purple FireWork Effect"){
    if(!Witch.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §ePurple FireWork§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    Witch.add(p.getName());
    Lists.set("WitchEffect", Witch);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§dColored Red Stone Effect"){
    if(!RedStone.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eColored RedStone§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    RedStone.add(p.getName());
    Lists.set("RedStoneEffect", RedStone);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§4Angry Villager Effect"){
    if(!AngryVillager.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eAngry Villager§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    AngryVillager.add(p.getName());
    Lists.set("AngryVillagerEffect", AngryVillager);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}	
    if(e.getCurrentItem().getItemMeta().getDisplayName() == "§2Happy Villager Effect"){
    if(!HappyVillager.contains(p.getName())){
    if(Coins >= 210){
    p.sendMessage("§6You bought the §eHappy Villager§6 effect!");
    plugin.getConfig().set(p.getName() + " coin",Coins - 210);
    HappyVillager.add(p.getName());
    Lists.set("HappyVillagerEffect", HappyVillager);
    }else{p.sendMessage("§4You don't have enough coins to buy this effect.");}
    }else{p.sendMessage("§4You alredy bought this effect!");}}	
    }}}}
    plugin.saveConfig();
    saveS();}
    }
    
    public boolean ShopEffect(Player p){
    ItemStack Heart = new ItemStack(35, 1, (short)14);
    ItemMeta HeartMeta = Heart.getItemMeta();
    HeartMeta.setDisplayName("§cHearts Effect");
	ArrayList<String> HeartLore = new ArrayList<String>();
	HeartLore.add("§fLeft Click To Buy");
	HeartLore.add("§fRight Click To See The Effect");
	HeartLore.add("§6210 Coins");
	HeartMeta.setLore(HeartLore);
	Heart.setItemMeta(HeartMeta);
	ItemStack Lava = new ItemStack(35, 1, (short)1);
	ItemMeta LavaMeta = Lava.getItemMeta();
	LavaMeta.setDisplayName("§6Lava Effect");
    ArrayList<String> LavaLore = new ArrayList<String>();
    LavaLore.add("§fLeft Click To Buy");
    LavaLore.add("§fRight Click To See The Effect");
    LavaLore.add("§6210 Coins");
    LavaMeta.setLore(LavaLore);
    Lava.setItemMeta(LavaMeta);
	ItemStack Note = new ItemStack(25, 1);
	ItemMeta NoteMeta = Note.getItemMeta();
	NoteMeta.setDisplayName("§bMusic Effect");
    ArrayList<String> NoteLore = new ArrayList<String>();
    NoteLore.add("§fLeft Click To Buy");
    NoteLore.add("§fRight Click To See The Effect");
    NoteLore.add("§6210 Coins");
    NoteMeta.setLore(NoteLore);
    Note.setItemMeta(NoteMeta);
	ItemStack MobSpell = new ItemStack(373, 1);
	ItemMeta MobSpellMeta = MobSpell.getItemMeta();
	MobSpellMeta.setDisplayName("§aColored Potion Effect");
    ArrayList<String> MobSpellLore = new ArrayList<String>();
    MobSpellLore.add("§fLeft Click To Buy");
    MobSpellLore.add("§fRight Click To See The Effect");
    MobSpellLore.add("§6210 Coins");
    MobSpellMeta.setLore(MobSpellLore);
    MobSpell.setItemMeta(MobSpellMeta);
	ItemStack Spell = new ItemStack(374, 1);
	ItemMeta SpellMeta = Spell.getItemMeta();
	SpellMeta.setDisplayName("§7White Potion Effect");
    ArrayList<String> SpellLore = new ArrayList<String>();
    SpellLore.add("§fLeft Click To Buy");
    SpellLore.add("§fRight Click To See The Effect");
    SpellLore.add("§6210 Coins");
    SpellMeta.setLore(SpellLore);
    Spell.setItemMeta(SpellMeta);
	ItemStack WhiteFireWork = new ItemStack(401, 1);
	ItemMeta WhiteFireWorkMeta = WhiteFireWork.getItemMeta();
	WhiteFireWorkMeta.setDisplayName("§7White FireWork Effect");
    ArrayList<String> WhiteFireWorkLore = new ArrayList<String>();
    WhiteFireWorkLore.add("§fLeft Click To Buy");
    WhiteFireWorkLore.add("§fRight Click To See The Effect");
    WhiteFireWorkLore.add("§6210 Coins");
    WhiteFireWorkMeta.setLore(WhiteFireWorkLore);
    WhiteFireWork.setItemMeta(WhiteFireWorkMeta);
	ItemStack Witch = new ItemStack(383, 1, (short)66);
	ItemMeta WitchMeta = Witch.getItemMeta();
	WitchMeta.setDisplayName("§5Purple FireWork Effect");
    ArrayList<String> WitchLore = new ArrayList<String>();
    WitchLore.add("§fLeft Click To Buy");
    WitchLore.add("§fRight Click To See The Effect");
    WitchLore.add("§6210 Coins");
    WitchMeta.setLore(WitchLore);
    Witch.setItemMeta(WitchMeta);
	ItemStack ColoredRedStone = new ItemStack(331, 1);
	ItemMeta ColoredRedStoneMeta = ColoredRedStone.getItemMeta();
	ColoredRedStoneMeta.setDisplayName("§dColored Red Stone Effect");
    ArrayList<String> ColoredRedStoneLore = new ArrayList<String>();
    ColoredRedStoneLore.add("§fLeft Click To Buy");
    ColoredRedStoneLore.add("§fRight Click To See The Effect");
    ColoredRedStoneLore.add("§6210 Coins");
    ColoredRedStoneMeta.setLore(ColoredRedStoneLore);
    ColoredRedStone.setItemMeta(ColoredRedStoneMeta);
	ItemStack AngryVill = new ItemStack(351, 1, (short)1);
	ItemMeta AngryVillMeta = AngryVill.getItemMeta();
	AngryVillMeta.setDisplayName("§4Angry Villager Effect");
    ArrayList<String> AngryVillLore = new ArrayList<String>();
    AngryVillLore.add("§fLeft Click To Buy");
    AngryVillLore.add("§fRight Click To See The Effect");
    AngryVillLore.add("§6210 Coins");
    AngryVillMeta.setLore(AngryVillLore);
    AngryVill.setItemMeta(AngryVillMeta);
	ItemStack HappyVill = new ItemStack(388, 1);
	ItemMeta HappyVillMeta = HappyVill.getItemMeta();
	HappyVillMeta.setDisplayName("§2Happy Villager Effect");
    ArrayList<String> HappyVillLore = new ArrayList<String>();
    HappyVillLore.add("§fLeft Click To Buy");
    HappyVillLore.add("§fRight Click To See The Effect");
    HappyVillLore.add("§6210 Coins");
    HappyVillMeta.setLore(HappyVillLore);
    HappyVill.setItemMeta(HappyVillMeta);
	ItemStack Block = new ItemStack(68);
	ItemMeta BlockMeta = Block.getItemMeta();
	BlockMeta.setDisplayName("[]");
	Block.setItemMeta(BlockMeta);
	Inventory Inv = Bukkit.createInventory(p, 27, "Shop Effects");
	Inv.setItem(0, Block);
	Inv.setItem(1, Block);
	Inv.addItem(Heart);
	Inv.addItem(Lava);
	Inv.addItem(Note);
	Inv.addItem(MobSpell);
	Inv.addItem(Spell);
	for(int i = 7; i < 20; i++){Inv.setItem(i, Block);}
	Inv.addItem(WhiteFireWork);
	Inv.addItem(Witch);
	Inv.addItem(ColoredRedStone);
	Inv.addItem(AngryVill);
	Inv.addItem(HappyVill);
	Inv.setItem(25, Block);
	Inv.setItem(26, Block);
	p.openInventory(Inv);
    return false;   
    }
   
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	Player p = (Player) sender;
	List<String> Heart = Lists.getStringList("HeartEffect");
	List<String> Lava = Lists.getStringList("LavaEffect");
	List<String> Note = Lists.getStringList("NoteEffect");
	List<String> MobSpell = Lists.getStringList("MobSpellEffect");
	List<String> Spell = Lists.getStringList("SpellEffect");
	List<String> InstantSpell = Lists.getStringList("InstantSpellEffect");
	List<String> Witch = Lists.getStringList("WitchEffect");
	List<String> RedStone = Lists.getStringList("RedStoneEffect");
	List<String> AngryVillager = Lists.getStringList("AngryVillagerEffect");
	List<String> HappyVillager = Lists.getStringList("HappyVillagerEffect");
	if(commandLabel.equalsIgnoreCase("effect")){
	if(args.length < 1){
	p.sendMessage("§aAll The Effect Commands:");
	p.sendMessage("§b/effect shop - Open the Shop Effects");
	p.sendMessage("§b/effect me - Show you your available effects");
	p.sendMessage("§b/effect set [effectNumber] - Set an effect");}
	if(args.length > 0){
	if(args[0].equalsIgnoreCase("shop")){ShopEffect(p);}
	if(args[0].equalsIgnoreCase("me")){
	String n1 = "";
	String n2 = "";
	String n3 = "";
	String n4 = "";
	String n5 = "";
	String n6 = "";
	String n7 = "";
	String n8 = "";
	String n9 = "";
	String n10 = "";
	String Ul1 = "";
	String Ul2 = "";
	String Ul3 = "";
	String Ul4 = "";
	String Ul5 = "";
	String Ul6 = "";
	String Ul7 = "";
	String Ul8 = "";
	String Ul9 = "";
	String Ul10 = "";
	if(Heart.contains(p.getName()) || p.isOp()){n1 = "§a";}
	if(Lava.contains(p.getName()) || p.isOp()){n2 = "§a";}
	if(Note.contains(p.getName()) || p.isOp()){n3 = "§a";}
	if(MobSpell.contains(p.getName()) || p.isOp()){n4 = "§a";}
	if(Spell.contains(p.getName()) || p.isOp()){n5 = "§a";}
	if(InstantSpell.contains(p.getName()) || p.isOp()){n6 = "§a";}
	if(Witch.contains(p.getName()) || p.isOp()){n7 = "§a";}
	if(RedStone.contains(p.getName()) || p.isOp()){n8 = "§a";}
	if(AngryVillager.contains(p.getName()) || p.isOp()){n9 = "§a";}
	if(HappyVillager.contains(p.getName()) || p.isOp()){n10 = "§a";}
	if(Eff.getInt(p.getName() + " effect") == 1){Ul1 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 2){Ul2 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 3){Ul3 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 4){Ul4 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 5){Ul5 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 6){Ul6 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 7){Ul7 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 8){Ul8 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 9){Ul9 = "§n";}
	if(Eff.getInt(p.getName() + " effect") == 10){Ul10 = "§n";}
	p.sendMessage("§0[§aGreen§0] §f= §2ON §f|| §0[§cRed§0] §f= §4OFF §f|| §0[§7§nUnderLine§0] §f= §fSelected");
	p.sendMessage("");
    p.sendMessage("§0[§c" + n1 + Ul1 + "Hearts§0] §f- §0[§c" + n2 + Ul2 + "Lava§0] §f- §0[§c" + n3 + Ul3 + "Music§0] §f- §0[§c" + n4 + Ul4 + "Colored Potion§0]");
    p.sendMessage("");
    p.sendMessage("§0[§c" + n5 + Ul5 + "White Potion§0] §f- §0[§c" + n6 + Ul6 + "White FireWork§0] §f- §0[§c" + n7 + Ul7 + "Purple FireWork§0] ");
    p.sendMessage("");
    p.sendMessage("§0[§c" + n8 + Ul8 + "Colored RedStone§0] §f- §0[§c" + n9 + Ul9 + "Angry Villager§0] §f- §0[§c" + n10 + Ul10 + "Happy Villager§0]");}
	if(args[0].equalsIgnoreCase("set")){
	if(args.length > 1){
	// Off
	if(args[1].equalsIgnoreCase("off")){
	p.sendMessage("§eYou disabled your effects.");
	Eff.set(p.getName() + " effect", 0);}
	// Heart
	if(args[1].equalsIgnoreCase("1")){
	if(Heart.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Hearts§e\" to your player.");
	Eff.set(p.getName() + " effect", 1);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Lava
	if(args[1].equalsIgnoreCase("2")){
	if(Lava.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Lava§e\" to your player.");
	Eff.set(p.getName() + " effect", 2);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Note
	if(args[1].equalsIgnoreCase("3")){
	if(Note.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Music Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 3);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// MobSpell
	if(args[1].equalsIgnoreCase("4")){
	if(MobSpell.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Colored Potion Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 4);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Spell
	if(args[1].equalsIgnoreCase("5")){
	if(Spell.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6White Potion Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 5);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// InstantSpell
	if(args[1].equalsIgnoreCase("6")){
	if(InstantSpell.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6White FireWork Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 6);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Witch
	if(args[1].equalsIgnoreCase("7")){
	if(Witch.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Purple FireWork Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 7);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// RedStone
	if(args[1].equalsIgnoreCase("8")){
	if(RedStone.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Colored RedStone Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 8);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Angry Villager
	if(args[1].equalsIgnoreCase("9")){
	if(AngryVillager.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Angry Villager Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 9);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	// Happy Villager
	if(args[1].equalsIgnoreCase("10")){
	if(HappyVillager.contains(p.getName()) || p.isOp()){
	p.sendMessage("§eYou set the effect \"§6Happy Villager Effect§e\" to your player.");
	Eff.set(p.getName() + " effect", 10);
	}else{p.sendMessage("§4You don't have this effect, you can buy him in the shop.");}}
	}else{p.sendMessage("§c/effect set [1~10]");}}
	saveff();}}
	return false;
	}
   
}

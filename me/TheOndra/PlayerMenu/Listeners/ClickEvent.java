package me.TheOndra.PlayerMenu.Listeners;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ClickEvent implements Listener{


    private void onPlayerMenu(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPlayer menu");
        Player check = Main.getPl().getPlayer().get(p.getName());

        if (check != null) {
            int time = check.getStatistic(Statistic.PLAY_ONE_TICK);
            int seconds = time / 20;
            int hours = seconds / 3600;
            int minutes = seconds / 60 - hours * 60;

            List<String> list = new ArrayList<>();
            list.add(" §7IP: §9" + check.getAddress());
            if (check.isOp()) {
                list.add(" §7OP: §atrue");
            } else {
                list.add(" §7OP: §cfalse");
            }
            list.add(" §7Health: §9" + check.getHealth());
            list.add(" §7Food: §9" + check.getFoodLevel());
            list.add(" §7Gamemode: §9" + check.getGameMode());
            list.add(" §7Level: §9" + check.getLevel());
            list.add("  §fRight click for set up op");
            list.add("  §fLeft click for set up level");

            ItemStack information = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§fPlayer §7" + check.getName(), list, false);
            SkullMeta informationmeta = (SkullMeta) information.getItemMeta();
            informationmeta.setOwner(check.getName());
            information.setItemMeta(informationmeta);

            ItemStack location = Main.getPl().createItem(Material.PAPER, 0, "§bLocation", Arrays.asList("§7World: §9" + check.getLocation().getWorld().getName(), "§7Location: §9" + check.getLocation().getBlockX() + "§7, §9" + check.getLocation().getBlockY() + "§7, §9" + check.getLocation().getBlockZ(), " §fRight click for teleport", " §fLeft click for teleport player here"), false);
            ItemStack gamemode = Main.getPl().createItem(Material.COMMAND, 0, "§6Gamemode", Arrays.asList("§7Gamemode: §9" + check.getGameMode(), " §fRight click for set up"), false);
            ItemStack statistics = Main.getPl().createItem(Material.BOOK, 0, "§2Statistics", Arrays.asList("§7Deaths: §9" + check.getStatistic(Statistic.DEATHS), "§7Kills: §9" + check.getStatistic(Statistic.PLAYER_KILLS), "§7Play time: §9" + hours + " §7hours §fand §9" + minutes + " §7minutes", "§7Kill mobs: §9" + check.getStatistic(Statistic.MOB_KILLS)), false);
            ItemStack health = Main.getPl().createItem(Material.WOOL, 14, "§cHealth", Arrays.asList("§7Health: §9" + check.getHealth(), " §fClick for set up."), false);
            ItemStack food = Main.getPl().createItem(Material.MELON, 0, "§6Food", Arrays.asList("§7Food: §9" + check.getFoodLevel(), "§f Click for set up"), false);
            ItemStack speed = Main.getPl().createItem(Material.FEATHER, 0, "§fSpeed level", Arrays.asList("§7Fly speed level: §9" + check.getFlySpeed(), "§7Walk speed level: §9" + check.getWalkSpeed(), "§f Left click for set up fly speed", "§f Right click for set up walk speed"), false);

            inv.setItem(4, information);
            inv.setItem(12, location);
            inv.setItem(14, gamemode);
            inv.setItem(22, statistics);
            inv.setItem(29, health);
            inv.setItem(31, speed);
            inv.setItem(33, food);
            p.openInventory(inv);
        } else {
            p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cThis player is not online!");
        }
    }

    public ItemStack giveSkull(String playerName){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
        itemmeta.setOwner(playerName);
        itemmeta.setDisplayName("§9" + playerName);
        itemmeta.setLore(Arrays.asList(" §7Click for open"));
        item.setItemMeta(itemmeta);

        return item;
    }

    private void onOnlineOp(Player p){
        Player check = Main.getPl().getPlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45, "§6§lOnline player OP menu");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check.getName(), null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check.getName());
        play.setItemMeta(playmeta);
        ItemStack optrue = Main.getPl().createItem(Material.EMERALD_BLOCK, 0, "§a§lSet player op on true", null, false);
        ItemStack opfalse = Main.getPl().createItem(Material.REDSTONE_BLOCK, 0, "§a§lSet player op on false", null, false);

        inv.setItem(4, play);
        inv.setItem(20, optrue);
        inv.setItem(24, opfalse);
        p.openInventory(inv);
    }

    private void onOnlineGamemode(Player p){
        Player check = Main.getPl().getPlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45,"§4§lOnline player gamemode");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check.getName(), null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check.getName());
        play.setItemMeta(playmeta);
        ItemStack survival = Main.getPl().createItem(Material.BRICK, 0, "§6Survival", null, false);
        ItemStack creative = Main.getPl().createItem(Material.FEATHER,0,"§bCreative",null,false);
        ItemStack adventure = Main.getPl().createItem(Material.LEATHER_BOOTS,0,"§2Adventure",null,false);
        ItemStack spectator = Main.getPl().createItem(Material.GLASS, 0,"§fSpectator",null,false);

        inv.setItem(4,play);
        inv.setItem(19, survival);
        inv.setItem(21, creative);
        inv.setItem(23, adventure);
        inv.setItem(25, spectator);
        p.openInventory(inv);
    }

    private void onOnlineWalkSpeed(Player p){
        Player check = Main.getPl().getPlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45,"§9§lOnline walk speed");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check.getName(), null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check.getName());
        play.setItemMeta(playmeta);
        ItemStack item0 = Main.getPl().createItem(Material.STAINED_GLASS,0,"§9Walk speed 0",Arrays.asList("§fStop move!"),false);
        ItemStack item1 = Main.getPl().createItem(Material.STAINED_GLASS,3,"§9Walk speed 1",Arrays.asList("§fNormal move!"),false);
        ItemStack item2 = Main.getPl().createItem(Material.STAINED_GLASS,11,"§9Walk speed 2",Arrays.asList("§fFast!"),false);
        ItemStack item3 = Main.getPl().createItem(Material.STAINED_GLASS,1,"§9Walk speed 3",Arrays.asList("§fas fast as bird!"),false);
        ItemStack item4 = Main.getPl().createItem(Material.STAINED_GLASS,14,"§9Walk speed 4",Arrays.asList("§fas fast as train!"),false);
        ItemStack item5 = Main.getPl().createItem(Material.STAINED_GLASS,15,"§9Walk speed 5",Arrays.asList("§fas fast as plane!"),false);

        inv.setItem(4, play);
        inv.setItem(19, item0);
        inv.setItem(20, item1);
        inv.setItem(21, item2);
        inv.setItem(22, item3);
        inv.setItem(23, item4);
        inv.setItem(24, item5);
        p.openInventory(inv);
    }

    private void onOnlineFlySpeed(Player p){
        Player check = Main.getPl().getPlayer().get(p.getName());

        Inventory inv = Bukkit.createInventory(null, 45,"§9§lOnline fly speed");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check.getName(), null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check.getName());
        play.setItemMeta(playmeta);
        ItemStack item0 = Main.getPl().createItem(Material.STAINED_GLASS,0,"§9Fly speed 0",Arrays.asList("§fStop move!"),false);
        ItemStack item1 = Main.getPl().createItem(Material.STAINED_GLASS,3,"§9Fly speed 1",Arrays.asList("§fSlime!"),false);
        ItemStack item2 = Main.getPl().createItem(Material.STAINED_GLASS,11,"§9Fly speed 2",Arrays.asList("§fNormal!"),false);
        ItemStack item3 = Main.getPl().createItem(Material.STAINED_GLASS,1,"§9Fly speed 3",Arrays.asList("§fas fast as bird!"),false);
        ItemStack item4 = Main.getPl().createItem(Material.STAINED_GLASS,14,"§9Fly speed 4",Arrays.asList("§fas fast as train!"),false);
        ItemStack item5 = Main.getPl().createItem(Material.STAINED_GLASS,15,"§9Fly speed 5",Arrays.asList("§fas fast as plane!"),false);

        inv.setItem(4, play);
        inv.setItem(19, item0);
        inv.setItem(20, item1);
        inv.setItem(21, item2);
        inv.setItem(22, item3);
        inv.setItem(23, item4);
        inv.setItem(24, item5);
        p.openInventory(inv);
    }

    private void onOfflineOP(Player p){
        String check = Main.getPl().getOfflinePlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45, "§6§lOffline player OP menu");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check, null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check);
        play.setItemMeta(playmeta);
        ItemStack optrue = Main.getPl().createItem(Material.EMERALD_BLOCK, 0, "§a§lSet player op on true", null, false);
        ItemStack opfalse = Main.getPl().createItem(Material.REDSTONE_BLOCK, 0, "§a§lSet player op on false", null, false);

        inv.setItem(4, play);
        inv.setItem(20, optrue);
        inv.setItem(24, opfalse);
        p.openInventory(inv);
    }

    private void onOfflineGamemode(Player p){
        String check = Main.getPl().getOfflinePlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45,"§4§lOffline player gamemode");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check, null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check);
        play.setItemMeta(playmeta);
        ItemStack survival = Main.getPl().createItem(Material.BRICK, 0, "§6Survival", null, false);
        ItemStack creative = Main.getPl().createItem(Material.FEATHER,0,"§bCreative",null,false);
        ItemStack adventure = Main.getPl().createItem(Material.LEATHER_BOOTS,0,"§2Adventure",null,false);
        ItemStack spectator = Main.getPl().createItem(Material.GLASS, 0,"§fSpectator",null,false);

        inv.setItem(4,play);
        inv.setItem(19, survival);
        inv.setItem(21, creative);
        inv.setItem(23, adventure);
        inv.setItem(25, spectator);
        p.openInventory(inv);
    }

    private void onOfflineWalkSpeed(Player p){
        String check = Main.getPl().getOfflinePlayer().get(p.getName());
        Inventory inv = Bukkit.createInventory(null, 45,"§9§lOffline walk speed");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check, null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check);
        play.setItemMeta(playmeta);
        ItemStack item0 = Main.getPl().createItem(Material.STAINED_GLASS,0,"§9Walk speed 0",Arrays.asList("§fStop move!"),false);
        ItemStack item1 = Main.getPl().createItem(Material.STAINED_GLASS,3,"§9Walk speed 1",Arrays.asList("§fNormal move!"),false);
        ItemStack item2 = Main.getPl().createItem(Material.STAINED_GLASS,11,"§9Walk speed 2",Arrays.asList("§fFast!"),false);
        ItemStack item3 = Main.getPl().createItem(Material.STAINED_GLASS,1,"§9Walk speed 3",Arrays.asList("§fas fast as bird!"),false);
        ItemStack item4 = Main.getPl().createItem(Material.STAINED_GLASS,14,"§9Walk speed 4",Arrays.asList("§fas fast as train!"),false);
        ItemStack item5 = Main.getPl().createItem(Material.STAINED_GLASS,15,"§9Walk speed 5",Arrays.asList("§fas fast as plane!"),false);

        inv.setItem(4, play);
        inv.setItem(19, item0);
        inv.setItem(20, item1);
        inv.setItem(21, item2);
        inv.setItem(22, item3);
        inv.setItem(23, item4);
        inv.setItem(24, item5);
        p.openInventory(inv);
    }

    private void onOfflineFlySpeed(Player p){
        String check = Main.getPl().getOfflinePlayer().get(p.getName());

        Inventory inv = Bukkit.createInventory(null, 45,"§9§lOffline fly speed");

        ItemStack play = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§9" + check, null, false);
        SkullMeta playmeta = (SkullMeta) play.getItemMeta();
        playmeta.setOwner(check);
        play.setItemMeta(playmeta);
        ItemStack item0 = Main.getPl().createItem(Material.STAINED_GLASS,0,"§9Fly speed 0",Arrays.asList("§fStop move!"),false);
        ItemStack item1 = Main.getPl().createItem(Material.STAINED_GLASS,3,"§9Fly speed 1",Arrays.asList("§fSlime!"),false);
        ItemStack item2 = Main.getPl().createItem(Material.STAINED_GLASS,11,"§9Fly speed 2",Arrays.asList("§fNormal!"),false);
        ItemStack item3 = Main.getPl().createItem(Material.STAINED_GLASS,1,"§9Fly speed 3",Arrays.asList("§fas fast as bird!"),false);
        ItemStack item4 = Main.getPl().createItem(Material.STAINED_GLASS,14,"§9Fly speed 4",Arrays.asList("§fas fast as train!"),false);
        ItemStack item5 = Main.getPl().createItem(Material.STAINED_GLASS,15,"§9Fly speed 5",Arrays.asList("§fas fast as plane!"),false);

        inv.setItem(4, play);
        inv.setItem(19, item0);
        inv.setItem(20, item1);
        inv.setItem(21, item2);
        inv.setItem(22, item3);
        inv.setItem(23, item4);
        inv.setItem(24, item5);
        p.openInventory(inv);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().contains("§7PlayersMenu")){
            if (e.getSlot() == 53){
                    for (int i = 0; i < 53; i++){
                        ItemStack item = e.getInventory().getItem(i);
                        SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
                        String name = ChatColor.stripColor(itemmeta.getDisplayName());

                        Player remove = Main.getPl().getServer().getPlayer(name);
                        Main.getMenu().getCollection().get(p).remove(remove);

                    }

                    Inventory inv = Bukkit.createInventory(null, 54, "§7PlayersMenu");
                int inventorySlot = 0;
                for (Player player:Main.getMenu().getCollection().get(p)){
                    if (inventorySlot == 53){
                        ItemStack page = Main.getPl().createItem(Material.FEATHER, 0, "§f§lNext page", null, false);
                        inv.setItem(53, page);
                    }
                    inv.setItem(inventorySlot++,giveSkull(player.getName()));
                }
                p.openInventory(inv);
            } else if (e.getSlot() <= 52 && e.getInventory().getItem(e.getSlot()) != null){
                    ItemStack item = e.getInventory().getItem(e.getSlot());
                    SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
                    String name = ChatColor.stripColor(skullmeta.getDisplayName());
                    Player player1 = Main.getPl().getServer().getPlayer(name);

                Main.getPl().getPlayer().put(p.getName(), player1);
                    onPlayerMenu(p);
            } else {
                return;
            }
        } else if (e.getInventory().getTitle().equals("§9§lPlayer menu")){
            Player check = Main.getPl().getPlayer().get(p.getName());
            e.setCancelled(true);
            if (e.getSlot() == 4){
                if (e.isRightClick()){
                    onOnlineOp(p);
                } else if (e.isLeftClick()){
                    p.closeInventory();
                    if (Main.getPl().getExp().contains(p)){
                        p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up level, please.");
                        p.sendMessage("§9Send §4§lCancel §9for cancel");
                        return;
                    } else {
                        p.closeInventory();
                        p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up level, please.");
                        p.sendMessage("§9Send §4§lCancel §9for cancel");
                        Main.getPl().getExp().add(p);
                    }
                }
            } else if (e.getSlot() == 12){
                if (e.isRightClick()){
                    Location loc = Main.getPl().getServer().getPlayer(check.getName()).getLocation();
                    p.teleport(loc);
                } else if (e.isLeftClick()){
                    Location loc = p.getLocation();
                    check.teleport(loc);
                }
            } else if (e.getSlot() == 14){
                onOnlineGamemode(p);
            } else if (e.getSlot() == 22){
                return;
            } else if (e.getSlot() == 29){
                if (Main.getPl().getHealth().contains(p)){
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up health, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    return;
                } else {
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up health, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    Main.getPl().getHealth().add(p);
                }
            } else if (e.getSlot() == 31){
                if (e.isRightClick()){
                    onOnlineWalkSpeed(p);
                } else if (e.isLeftClick()){
                    onOnlineFlySpeed(p);
                }
            } else if (e.getSlot() == 33){
                if (Main.getPl().getFood().contains(p)){
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up food level, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    return;
                } else {
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up food level, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    Main.getPl().getFood().add(p);
                }
            }
        } else if (e.getInventory().getTitle().equals("§6§lOnline player OP menu")){
            Player check = Main.getPl().getPlayer().get(p.getName());
            e.setCancelled(true);

            if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9OP changed");
                check.setOp(true);
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9OP changed");
                check.setOp(false);
            }
        } else if (e.getInventory().getTitle().equals("§4§lOnline player gamemode")){
            Player check = Main.getPl().getPlayer().get(p.getName());
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                check.setGameMode(GameMode.SURVIVAL);
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                check.setGameMode(GameMode.CREATIVE);
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                check.setGameMode(GameMode.ADVENTURE);
            } else if (e.getSlot() == 25){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                check.setGameMode(GameMode.SPECTATOR);
            }
        } else if (e.getInventory().getTitle().equals("§9§lOnline walk speed")){
            Player check = Main.getPl().getPlayer().get(p.getName());
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed(0);
            } else if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed((float) 0.1);
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed((float) 0.2);
            } else if (e.getSlot() == 22){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed((float) 0.3);
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed((float) 0.4);
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setWalkSpeed((float) 0.5);
            }
        } else if (e.getInventory().getTitle().equals("§9§lOnline fly speed")){
            Player check = Main.getPl().getPlayer().get(p.getName());
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed(0);
            } else if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed((float) 0.1);
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed((float) 0.2);
            } else if (e.getSlot() == 22){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed((float) 0.3);
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed((float) 0.4);
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                check.setFlySpeed((float) 0.5);
            }
        }  else if (e.getInventory().getTitle().equals("§9§lOffline player menu")){
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
            e.setCancelled(true);
            if (e.getSlot() == 4){
                if (e.isRightClick()){
                    onOfflineOP(p);
                } else if (e.isLeftClick()){
                    p.closeInventory();
                    if (Main.getPl().getOfflineXp().contains(p)){
                        p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up level, please.");
                        p.sendMessage("§9Send §4§lCancel §9for cancel");
                        return;
                    } else {
                        p.closeInventory();
                        p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up level, please.");
                        p.sendMessage("§9Send §4§lCancel §9for cancel");
                        Main.getPl().getOfflineXp().add(p);
                    }
                }
            } else if (e.getSlot() == 12){
                if (e.isRightClick()){
                    Location loc = (Location) config.get("Location");
                    World world = loc.getWorld();
                    int x = loc.getBlockX();
                    int y = loc.getBlockY();
                    int z = loc.getBlockZ();
                    Location location = new Location(world,x,y,z);
                    p.teleport(location);
                } else if (e.isLeftClick()){
                    try {

                        config.set("Location", p.getLocation());

                        config.save(f);
                    } catch (IOException exception){
                        exception.printStackTrace();
                    }
                }
            } else if (e.getSlot() == 14){
                onOfflineGamemode(p);
            } else if (e.getSlot() == 22){
                return;
            } else if (e.getSlot() == 29){
                if (Main.getPl().getOfflineHealth().contains(p)){
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up health, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    return;
                } else {
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up health, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    Main.getPl().getOfflineHealth().add(p);
                }
            } else if (e.getSlot() == 31){
                if (e.isRightClick()){
                    onOfflineWalkSpeed(p);
                } else if (e.isLeftClick()){
                    onOfflineFlySpeed(p);
                }
            } else if (e.getSlot() == 33){
                if (Main.getPl().getOfflineFood().contains(p)){
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up food level, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    return;
                } else {
                    p.closeInventory();
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Send number for set up food level, please.");
                    p.sendMessage("§9Send §4§lCancel §9for cancel");
                    Main.getPl().getOfflineFood().add(p);
                }
            }
        } else if (e.getInventory().getTitle().equals("§6§lOffline player OP menu")){
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
            e.setCancelled(true);

            if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9OP changed");
                try {
                    config.set("Information.OP", true);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9OP changed");
                try {
                    config.set("Information.OP", false);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        } else if (e.getInventory().getTitle().equals("§4§lOffline player gamemode")){
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                try {
                    config.set("Gamemode", GameMode.SURVIVAL.name());

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                try {
                    config.set("Gamemode", GameMode.CREATIVE.name());

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                try {
                    config.set("Gamemode", GameMode.ADVENTURE.name());

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 25){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Gamemode changed");
                try {
                    config.set("Gamemode", GameMode.SPECTATOR.name());

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }  else if (e.getInventory().getTitle().equals("§9§lOffline walk speed")){
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0.1);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0.2);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 22){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0.3);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0.4);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Walk", 0.5);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        } else if (e.getInventory().getTitle().equals("§9§lOffline fly speed")){
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, Main.getPl().getOfflinePlayer().get(p.getName()) + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
            e.setCancelled(true);

            if (e.getSlot() == 19){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 20){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0.1);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 21){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0.2);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 22){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0.3);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 23){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0.4);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            } else if (e.getSlot() == 24){
                p.closeInventory();
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §9Speed level was changed.");
                try {
                    config.set("Speed.Fly", 0.5);

                    config.save(f);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
    }

}

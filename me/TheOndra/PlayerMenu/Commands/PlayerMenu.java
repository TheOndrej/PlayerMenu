package me.TheOndra.PlayerMenu.Commands;

import me.TheOndra.PlayerMenu.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.*;

public class PlayerMenu implements CommandExecutor{

    HashMap<Player, List<Player>> collection = new HashMap<>();
    Inventory inv = Bukkit.createInventory(null, 54, "§7PlayersMenu");
    List<Player> list = new ArrayList<>();
    HashMap<String, String> name = new HashMap<>();

    private void onPlayerMenu(Player p, String[] args){
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPlayer menu");
        Player check =Main.getPl().getPlayer().get(p.getName());

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
            p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cThis player is not online. Open offline player menu");
            File file = new File(Main.getPl().getDataFolder(), "Players");
            File f = new File(file, check.getName() + ".yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

            if (!f.exists()){
                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cThis player is not in database!");
            } else {
                long time = config.getLong("Statistics.Play");
                long seconds = time / 20;
                long hours = seconds / 3600;
                long minutes = seconds / 60 - hours * 60;

                List<String> list = new ArrayList<>();
                list.add(" §7IP: §9" + config.getString("Information.IP"));
                if (config.getBoolean("OP") == true) {
                    list.add(" §7OP: §atrue");
                } else {
                    list.add(" §7OP: §cfalse");
                }
                list.add(" §7Health: §9" + config.getString("Health"));
                list.add(" §7Food: §9" + config.getString("Food"));
                list.add(" §7Gamemode: §9" + config.getString("Gamemode"));
                list.add(" §7Level: §9" + config.getString("Information.Level"));
                list.add("  §fRight click for set up op");
                list.add("  §fLeft click for set up level");

                ItemStack information = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§fPlayer §7" + f.getName(), list, false);
                SkullMeta informationmeta = (SkullMeta) information.getItemMeta();
                informationmeta.setOwner(f.getName());
                information.setItemMeta(informationmeta);

                Location loc = (Location) config.get("Location");
                ItemStack location = Main.getPl().createItem(Material.PAPER, 0, "§bLocation", Arrays.asList("§7World: §9" + loc.getWorld().getName(), "§7Location: §9" + loc.getBlockX() + "§7, §9" + loc.getBlockY() + "§7, §9" + loc.getBlockZ(), " §fRight click for teleport", " §fLeft click for set up location for target player."), false);
                ItemStack gamemode = Main.getPl().createItem(Material.COMMAND, 0, "§6Gamemode", Arrays.asList("§7Gamemode: §9" + config.getString("Gamemode"), " §fRight click for set up"), false);
                ItemStack statistics = Main.getPl().createItem(Material.BOOK, 0, "§2Statistics", Arrays.asList("§7Deaths: §9" + config.getString("Statistics.Deaths"), "§7Kills: §9" + config.getString("Statistics.Kills"), "§7Play time: §9" + hours + " §7hours §fand §9" + minutes + " §7minutes", "§7Kill mobs: §9" + config.getString("Statistics.MobKill")), false);
                ItemStack health = Main.getPl().createItem(Material.WOOL, 14, "§cHealth", Arrays.asList("§7Health: §9" + config.getString("Health"), " §fClick for set up."), false);
                ItemStack food = Main.getPl().createItem(Material.MELON, 0, "§6Food", Arrays.asList("§7Food: §9" + config.getString("Food"), "§f Click for set up"), false);
                ItemStack speed = Main.getPl().createItem(Material.FEATHER, 0, "§fSpeed level", Arrays.asList("§7Fly speed level: §9" + config.getString("Speed.Fly"), "§7Walk speed level: §9" + config.getString("Speed.Walk"), "§f Left click for set up fly speed", "§f Right click for set up walk speed"), false);

                inv.setItem(4, information);
                inv.setItem(12, location);
                inv.setItem(14, gamemode);
                inv.setItem(22, statistics);
                inv.setItem(29, health);
                inv.setItem(31, speed);
                inv.setItem(33, food);
                p.openInventory(inv);
            }
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
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;


                if (p.hasPermission("PlayerMenu.open")){
                    if (args.length == 0) {
                        int inventorySlot = 0;
                        Collection coll = Bukkit.getOnlinePlayers();
                        list.removeAll(coll);
                        for (Player player:Bukkit.getOnlinePlayers()){
                            if (inventorySlot == 53){
                                String pagestring = ChatColor.stripColor(inv.getTitle().replaceAll("PlayersMenu page ", ""));
                                ItemStack page = Main.getPl().createItem(Material.FEATHER, 0, "§f§lNext page", Arrays.asList("§7Page: §9" + pagestring), false);
                                inv.setItem(53, page);
                            }
                            inv.setItem(inventorySlot++,giveSkull(player.getName()));
                            list.add(player);
                            collection.put(p, list);
                        }
                        p.openInventory(inv);

                        return true;
                    } else if (args.length == 1){
                        Player check = Main.getPl().getServer().getPlayer(args[0]);
                        if (check == null){
                            Main.getPl().getOfflinePlayer().put(p.getName(), args[0]);
                            p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cThis player is not online. Open offline player menu");
                            File file = new File(Main.getPl().getDataFolder(), "Players");
                            File f = new File(file, args[0] + ".yml");
                            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
                            Inventory inv1 = Bukkit.createInventory(null, 54, "§9§lOffline player menu");

                            if (!f.exists()){
                                p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cThis player is not in database!");
                            } else {
                                this.name.put(p.getName(), args[0]);
                                long time = config.getLong("Statistics.Play");
                                long seconds = time / 20;
                                long hours = seconds / 3600;
                                long minutes = seconds / 60 - hours * 60;

                                List<String> list = new ArrayList<>();
                                list.add(" §7IP: §9" + config.getString("Information.IP"));
                                if (config.getBoolean("Information.OP") == true) {
                                    list.add(" §7OP: §atrue");
                                } else {
                                    list.add(" §7OP: §cfalse");
                                }
                                list.add(" §7Health: §9" + config.getString("Health"));
                                list.add(" §7Food: §9" + config.getString("Food"));
                                list.add(" §7Gamemode: §9" + config.getString("Gamemode"));
                                list.add(" §7Level: §9" + config.getString("Information.Level"));
                                list.add("  §fRight click for set op");
                                list.add("  §fLeft click for set level");

                                ItemStack information = Main.getPl().createItem(Material.SKULL_ITEM, 3, "§fPlayer §7" + f.getName().replaceAll(".yml", ""), list, false);
                                SkullMeta informationmeta = (SkullMeta) information.getItemMeta();
                                informationmeta.setOwner(f.getName().replaceAll(".yml", ""));
                                information.setItemMeta(informationmeta);

                                Location loc = (Location) config.get("Location");
                                ItemStack location = Main.getPl().createItem(Material.PAPER, 0, "§bLocation", Arrays.asList("§7World: §9" + loc.getWorld().getName(), "§7Location: §9" + loc.getBlockX() + "§7, §9" + loc.getBlockY() + "§7, §9" + loc.getBlockZ(), " §fRight click for teleport", " §fLeft click for set up location for target player."), false);
                                ItemStack gamemode = Main.getPl().createItem(Material.COMMAND, 0, "§6Gamemode", Arrays.asList("§7Gamemode: §9" + config.getString("Gamemode"), " §fRight click for set up"), false);
                                ItemStack statistics = Main.getPl().createItem(Material.BOOK, 0, "§2Statistics", Arrays.asList("§7Deaths: §9" + config.getString("Statistics.Deaths"), "§7Kills: §9" + config.getString("Statistics.Kills"), "§7Play time: §9" + hours + " §7hours §fand §9" + minutes + " §7minutes", "§7Kill mobs: §9" + config.getString("Statistics.MobKill")), false);
                                ItemStack health = Main.getPl().createItem(Material.WOOL, 14, "§cHealth", Arrays.asList("§7Health: §9" + config.getString("Health"), " §fClick for set up."), false);
                                ItemStack food = Main.getPl().createItem(Material.MELON, 0, "§6Food", Arrays.asList("§7Food: §9" + config.getString("Food"), "§f Click for set up"), false);
                                ItemStack speed = Main.getPl().createItem(Material.FEATHER, 0, "§fSpeed level", Arrays.asList("§7Fly speed level: §9" + config.getString("Speed.Fly"), "§7Walk speed level: §9" + config.getString("Speed.Walk"), "§f Click for set up"), false);

                                inv1.setItem(4, information);
                                inv1.setItem(12, location);
                                inv1.setItem(14, gamemode);
                                inv1.setItem(22, statistics);
                                inv1.setItem(29, health);
                                inv1.setItem(31, speed);
                                inv1.setItem(33, food);
                                p.openInventory(inv1);
                            }
                        } else {
                            Main.getPl().getPlayer().put(p.getName(), check);
                            onPlayerMenu(p, args);
                        }
                    } else {
                        p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cError: /playermenu or /playermenu nick");
                    }
            } else {
                    p.sendMessage("§7[§a§lPlayer§e§lMenu§7] §cYou do not have permission.");
                }

            } else {
                sender.sendMessage("§cOnly players can use this command.");
            }
        return true;
    }

    public HashMap<Player, List<Player>> getCollection() {
        return collection;
    }

    public HashMap<String, String> getName() {
        return name;
    }
}
